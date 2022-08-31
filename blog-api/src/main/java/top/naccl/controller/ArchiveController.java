package top.naccl.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.naccl.annotation.VisitLogger;
import top.naccl.enums.VisitBehavior;
import top.naccl.model.vo.Result;
import top.naccl.service.BlogService;

import java.util.Map;

/**
 * @Description: 归档页面
 * @Author: Naccl
 * @Date: 2020-08-12
 */
@RestController
@Api(tags = "front-归档")
public class ArchiveController {
	@Autowired
	BlogService blogService;

	/**
	 * 按年月分组归档公开博客 统计公开博客总数
	 *
	 * @return
	 */
	@VisitLogger(VisitBehavior.ARCHIVE)
	@GetMapping("/archives")
	@ApiOperation("获取归档页面中的信息")
	public Result archives() {
		Map<String, Object> archiveBlogMap = blogService.getArchiveBlogAndCountByIsPublished();
		return Result.ok("请求成功", archiveBlogMap);
	}
}
