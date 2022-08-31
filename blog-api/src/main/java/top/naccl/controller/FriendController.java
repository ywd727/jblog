package top.naccl.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.naccl.annotation.VisitLogger;
import top.naccl.enums.VisitBehavior;
import top.naccl.model.vo.Friend;
import top.naccl.model.vo.FriendInfo;
import top.naccl.model.vo.Result;
import top.naccl.service.FriendService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 友链
 * @Author: Naccl
 * @Date: 2020-09-08
 */
@RestController
@Api(tags = "front-友人账（对应后端的友链）")
public class FriendController {
	@Autowired
	FriendService friendService;

	/**
	 * 获取友链页面
	 *
	 * @return
	 */
	@VisitLogger(VisitBehavior.FRIEND)
	@GetMapping("/friends")
	@ApiOperation("获取友链页面")
	public Result friends() {
		List<Friend> friendList = friendService.getFriendVOList();
		FriendInfo friendInfo = friendService.getFriendInfo(true, true);
		Map<String, Object> map = new HashMap<>(4);
		map.put("friendList", friendList);
		map.put("friendInfo", friendInfo);
		return Result.ok("获取成功", map);
	}

	/**
	 * 按昵称增加友链浏览次数
	 *
	 * @param nickname 友链昵称
	 * @return
	 */
	@VisitLogger(VisitBehavior.CLICK_FRIEND)
	@PostMapping("/friend")
	@ApiOperation("按昵称增加友链浏览次数")
	public Result addViews(@RequestParam String nickname) {
		friendService.updateViewsByNickname(nickname);
		return Result.ok("请求成功");
	}
}
