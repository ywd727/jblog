package top.jxx.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.jxx.annotation.OperationLogger;
import top.jxx.constant.BlogConstants;
import top.jxx.entity.Blog;
import top.jxx.entity.Category;
import top.jxx.entity.Tag;
import top.jxx.entity.User;
import top.jxx.model.dto.BlogVisibility;
import top.jxx.model.vo.Result;
import top.jxx.service.BlogService;
import top.jxx.service.CategoryService;
import top.jxx.service.CommentService;
import top.jxx.service.TagService;
import top.jxx.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

/**
 * @Description: 博客文章后台管理
 * @Author: Naccl
 * @Date: 2020-07-29
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "back-博客管理-文章接口")
public class BlogAdminController {
	@Autowired
	BlogService blogService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	TagService tagService;
	@Autowired
	CommentService commentService;

	/**
	 * 获取博客文章列表
	 *
	 * @param title      按标题模糊查询
	 * @param categoryId 按分类id查询
	 * @param pageNum    页码
	 * @param pageSize   每页个数
	 * @return
	 */
	@GetMapping("/blogs")
	@ApiOperation("博客文章-文章列表的获取")
	public Result blogs(@RequestParam(defaultValue = "") String title,
	                    @RequestParam(defaultValue = "") Integer categoryId,
	                    @RequestParam(defaultValue = "1") Integer pageNum,
	                    @RequestParam(defaultValue = "10") Integer pageSize) {
		String orderBy = "create_time desc";
		PageHelper.startPage(pageNum, pageSize, orderBy);
		PageInfo<Blog> pageInfo = new PageInfo<>(blogService.getListByTitleAndCategoryId(title, categoryId));
		List<Category> categories = categoryService.getCategoryList();
		Map<String, Object> map = new HashMap<>(4);
		map.put("blogs", pageInfo);
		map.put("categories", categories);
		return Result.ok("请求成功", map);
	}

	/**
	 * 删除博客文章、删除博客文章下的所有评论、同时维护 blog_tag 表
	 *
	 * @param id 文章id
	 * @return
	 */
	@OperationLogger("删除博客")
	@DeleteMapping("/blog")
	@ApiOperation("博客文章-根据id删除文章")
	public Result delete(@RequestParam Long id) {
		blogService.deleteBlogTagByBlogId(id);
		blogService.deleteBlogById(id);
		commentService.deleteCommentsByBlogId(id);
		return Result.ok("删除成功");
	}

	/**
	 * 获取分类列表和标签列表
	 *
	 * @return
	 */
	@GetMapping("/categoryAndTag")
	@ApiOperation("博客文章-根据id删除文章")
	public Result categoryAndTag() {
		List<Category> categories = categoryService.getCategoryList();
		List<Tag> tags = tagService.getTagList();
		Map<String, Object> map = new HashMap<>(4);
		map.put("categories", categories);
		map.put("tags", tags);
		return Result.ok("请求成功", map);
	}

	/**
	 * 更新博客置顶状态
	 *
	 * @param id  博客id
	 * @param top 是否置顶
	 * @return
	 */
	@OperationLogger("更新博客置顶状态")
	@PutMapping("/blog/top")
	@ApiOperation("博客文章-更新博客的置顶状态")
	public Result updateTop(@RequestParam Long id, @RequestParam Boolean top) {
		blogService.updateBlogTopById(id, top);
		return Result.ok("操作成功");
	}

	/**
	 * 更新博客推荐状态
	 *
	 * @param id        博客id
	 * @param recommend 是否推荐
	 * @return
	 */
	@OperationLogger("更新博客推荐状态")
	@PutMapping("/blog/recommend")
	@ApiOperation("博客文章-更新博客的推荐状态")
	public Result updateRecommend(@RequestParam Long id, @RequestParam Boolean recommend) {
		blogService.updateBlogRecommendById(id, recommend);
		return Result.ok("操作成功");
	}

	/**
	 * 更新博客可见性状态
	 *
	 * @param id             博客id
	 * @param blogVisibility 博客可见性DTO
	 * @return
	 */
	@OperationLogger("更新博客可见性状态")
	@PutMapping("blog/{id}/visibility")
	@ApiOperation("博客文章-更新博客的可见性状态")
	public Result updateVisibility(@PathVariable Long id, @RequestBody BlogVisibility blogVisibility) {
		blogService.updateBlogVisibilityById(id, blogVisibility);
		return Result.ok("操作成功");
	}

	/**
	 * 按id获取博客详情
	 *
	 * @param id 博客id
	 * @return
	 */
	@GetMapping("/blog")
	@ApiOperation("博客文章-根据id获取博客的内容")
	public Result getBlog(@RequestParam Long id) {
		Blog blog = blogService.getBlogById(id);
		return Result.ok("获取成功", blog);
	}

	/**
	 * 上传图片并回显
	 * @param request
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/uploadImg")
	@ApiOperation("博客文章-上传图片并进行回显")
	public Result uploadImg(HttpServletRequest request,
							@RequestParam(value = "editormd-image-file", required = false) MultipartFile file){
		Map<String,Object> map = new HashMap<>();
		if (file != null){
			//获取此项目的tomcat路径
			String webapp = request.getServletContext().getRealPath("/");
			try{
				//获取文件名
				String filename = file.getOriginalFilename();
				UUID uuid = UUID.randomUUID();
				String name = "";
				if (filename != null){
					name = filename.substring(filename.lastIndexOf(".")); //获取文件后缀名
				}
				// 图片的路径+文件名称
				String fileName = "/upload/" + uuid + name;
				// 图片的在服务器上面的物理路径
				File destFile = new File(webapp, fileName);
				// 生成upload目录
				File parentFile = destFile.getParentFile();
				if (!parentFile.exists()) {
					parentFile.mkdirs();// 自动生成upload目录
				}
				// 把上传的临时图片，复制到当前项目的webapp路径
				FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(destFile));
				map.put("url",fileName); //图片回显的url 上传成功时才返回
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return Result.ok("上传成功", map);
	}

	/**
	 * 保存草稿或发布新文章
	 *
	 * @param blog 博客文章DTO
	 * @return
	 */
	@OperationLogger("发布博客")
	@PostMapping("/blog")
	@ApiOperation("博客文章-发布新的文章或则保存博客的草稿")
	public Result saveBlog(@RequestBody top.jxx.model.dto.Blog blog) {
		return getResult(blog, "save");
	}

	/**
	 * 更新博客
	 *
	 * @param blog 博客文章DTO
	 * @return
	 */
	@OperationLogger("更新博客")
	@PutMapping("/blog")
	@ApiOperation("博客文章-更新博客")
	public Result updateBlog(@RequestBody top.jxx.model.dto.Blog blog) {
		return getResult(blog, "update");
	}

	/**
	 * 执行博客添加或更新操作：校验参数是否合法，添加分类、标签，维护博客标签关联表
	 *
	 * @param blog 博客文章DTO
	 * @param type 添加或更新
	 * @return
	 */
	private Result getResult(top.jxx.model.dto.Blog blog, String type) {
		//计算文章的字数
		int article = blog.getContent().length() + blog.getDescription().length();
		//计算博客内容的长度，进行存储
		blog.setWords(article);
		//计算博客的阅读时长进行存储，最低的阅读时长设置为1分钟
		blog.setReadTime(article / BlogConstants.READ_TIME_MARK);

		//验证普通字段
		if (StringUtils.isEmpty(blog.getTitle(), blog.getFirstPicture(), blog.getContent(), blog.getDescription())
				|| blog.getWords() == null || blog.getWords() < 0) {
			return Result.error("参数有误");
		}

		//处理分类
		Object cate = blog.getCate();
		if (cate == null) {
			return Result.error("分类不能为空");
		}
		if (cate instanceof Integer) {//选择了已存在的分类
			Category c = categoryService.getCategoryById(((Integer) cate).longValue());
			blog.setCategory(c);
		} else if (cate instanceof String) {//添加新分类
			//查询分类是否已存在
			Category category = categoryService.getCategoryByName((String) cate);
			if (category != null) {
				return Result.error("不可添加已存在的分类");
			}
			Category c = new Category();
			c.setName((String) cate);
			categoryService.saveCategory(c);
			blog.setCategory(c);
		} else {
			return Result.error("分类不正确");
		}

		//处理标签
		List<Object> tagList = blog.getTagList();
		List<Tag> tags = new ArrayList<>();
		for (Object t : tagList) {
			if (t instanceof Integer) {//选择了已存在的标签
				Tag tag = tagService.getTagById(((Integer) t).longValue());
				tags.add(tag);
			} else if (t instanceof String) {//添加新标签
				//查询标签是否已存在
				Tag tag1 = tagService.getTagByName((String) t);
				if (tag1 != null) {
					return Result.error("不可添加已存在的标签");
				}
				Tag tag = new Tag();
				tag.setName((String) t);
				tagService.saveTag(tag);
				tags.add(tag);
			} else {
				return Result.error("标签不正确");
			}
		}

		//将文章中的图片进行保存

		Date date = new Date();
		if (blog.getReadTime() == null || blog.getReadTime() < 0) {
			blog.setReadTime((int) Math.round(blog.getWords() / 200.0));//粗略计算阅读时长
		}
		if (blog.getViews() == null || blog.getViews() < 0) {
			blog.setViews(0);
		}
		if ("save".equals(type)) {
			blog.setCreateTime(date);
			blog.setUpdateTime(date);
			User user = new User();
			user.setId(1L);//个人博客默认只有一个作者
			blog.setUser(user);

			blogService.saveBlog(blog);
			//关联博客和标签(维护 blog_tag 表)
			for (Tag t : tags) {
				blogService.saveBlogTag(blog.getId(), t.getId());
			}
			return Result.ok("添加成功");
		} else {
			blog.setUpdateTime(date);


			blogService.updateBlog(blog);
			//关联博客和标签(维护 blog_tag 表)
			blogService.deleteBlogTagByBlogId(blog.getId());
			for (Tag t : tags) {
				blogService.saveBlogTag(blog.getId(), t.getId());
			}
			return Result.ok("更新成功");
		}
	}
}
