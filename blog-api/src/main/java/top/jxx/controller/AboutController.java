package top.jxx.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.jxx.annotation.VisitLogger;
import top.jxx.enums.VisitBehavior;
import top.jxx.model.vo.Result;
import top.jxx.service.AboutService;

/**
 * @Description: 关于我页面
 * @Author: Naccl
 * @Date: 2020-08-31
 */
@RestController
@Api(tags = "front-关于我页面")
public class AboutController {
	@Autowired
	AboutService aboutService;

	/**
	 * 获取关于我页面信息
	 *
	 * @return
	 */
	@VisitLogger(VisitBehavior.ABOUT)
	@GetMapping("/about")
	@ApiOperation("获取关于我页面信息")
	public Result about() {
		return Result.ok("获取成功", aboutService.getAboutInfo());
	}
}
