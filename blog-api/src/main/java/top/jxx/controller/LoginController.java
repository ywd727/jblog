package top.jxx.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.jxx.constant.JwtConstants;
import top.jxx.constant.ProjectConstant;
import top.jxx.entity.User;
import top.jxx.model.dto.LoginInfo;
import top.jxx.model.vo.Result;
import top.jxx.service.RedisService;
import top.jxx.service.UserService;
import top.jxx.util.JwtUtils;
import top.jxx.util.RedisSupport;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 前台登录
 * @Author: Naccl
 * @Date: 2020-09-02
 */
@RestController
@Api(tags = "front-登录")
public class LoginController {
	@Autowired
	UserService userService;

	@Resource
	private RedisService redisService;

	@Resource
	private DefaultKaptcha defaultKaptcha;

	@Resource
	private RedisSupport redisSupport;

	/**
	 * 登录成功后，签发博主身份Token
	 *
	 * @param loginInfo
	 * @return
	 */
	@PostMapping("/login/login")
	@ApiOperation("登录接口")
	public Result login(@RequestBody LoginInfo loginInfo) {
		User user = userService.findUserByUsernameAndPassword(loginInfo.getUsername(), loginInfo.getPassword());
		if (!"ROLE_admin".equals(user.getRole())) {
			return Result.create(403, "无权限");
		}
		user.setPassword(null);
		String jwt = JwtUtils.generateToken(JwtConstants.ADMIN_PREFIX + user.getUsername());
		Map<String, Object> map = new HashMap<>(4);

		redisService.saveObjectToValue("demo", "我是进行测试的");

		map.put("user", user);
		map.put("token", jwt);
		return Result.ok("登录成功", map);
	}

	@GetMapping(value = "/getCode", produces = "image/jpeg")
	@ApiOperation("获取验证码的接口")
	public void getCode(HttpServletRequest request, HttpServletResponse response) {

		// 定义response输出类型为image/jpeg类型
		response.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		// return a jpeg
		response.setContentType("image/jpeg");

		//-------------------生成验证码 begin --------------------------
		String text = defaultKaptcha.createText();
		System.out.println("验证码为:" + text);
		//将验证码放入session中
        /*tring mark = UUID.randomUUID().toString().replace("-", "");
        request.getSession().setAttribute(mark, text);*/
		//将验证码存放到redis中（过期时间是半小时）
		redisSupport.set(ProjectConstant.VerifyCode.VERIFY_CODE, text, 60 * 30);

		//根据验证码创建图片
		BufferedImage image = defaultKaptcha.createImage(text);

		ServletOutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			//根据image生成jpg的图像 由outputStream输出
			ImageIO.write(image, "jpg", outputStream);
			outputStream.flush();
		} catch (IOException e) {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
			}
		}

	}
}
