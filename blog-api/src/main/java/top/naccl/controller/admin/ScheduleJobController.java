package top.naccl.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.naccl.annotation.OperationLogger;
import top.naccl.entity.ScheduleJob;
import top.naccl.entity.ScheduleJobLog;
import top.naccl.model.vo.Result;
import top.naccl.service.ScheduleJobService;
import top.naccl.util.common.ValidatorUtils;

import java.util.Date;

/**
 * @Description: 定时任务动态管理
 * @Author: Naccl
 * @Date: 2020-11-01
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "back-系统管理-定时任务")
public class ScheduleJobController {
	@Autowired
	private ScheduleJobService scheduleJobService;

	/**
	 * 分页查询定时任务列表
	 *
	 * @param pageNum  页码
	 * @param pageSize 每页条数
	 * @return
	 */
	@GetMapping("/jobs")
	@ApiOperation("查询定时任务的分页列表")
	public Result jobs(@RequestParam(defaultValue = "1") Integer pageNum,
	                   @RequestParam(defaultValue = "10") Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		PageInfo<ScheduleJob> pageInfo = new PageInfo<>(scheduleJobService.getJobList());
		return Result.ok("请求成功", pageInfo);
	}

	/**
	 * 新建定时任务
	 *
	 * @param scheduleJob
	 * @return
	 */
	@OperationLogger("新建定时任务")
	@PostMapping("/job")
	@ApiOperation("新增定时任务")
	public Result saveJob(@RequestBody ScheduleJob scheduleJob) {
		scheduleJob.setStatus(false);
		scheduleJob.setCreateTime(new Date());
		ValidatorUtils.validateEntity(scheduleJob);
		scheduleJobService.saveJob(scheduleJob);
		return Result.ok("添加成功");
	}

	/**
	 * 修改定时任务
	 *
	 * @param scheduleJob
	 * @return
	 */
	@OperationLogger("修改定时任务")
	@PutMapping("/job")
	@ApiOperation("修改定时任务")
	public Result updateJob(@RequestBody ScheduleJob scheduleJob) {
		scheduleJob.setStatus(false);
		ValidatorUtils.validateEntity(scheduleJob);
		scheduleJobService.updateJob(scheduleJob);
		return Result.ok("修改成功");
	}

	/**
	 * 删除定时任务
	 *
	 * @param jobId 任务id
	 * @return
	 */
	@OperationLogger("删除定时任务")
	@DeleteMapping("/job")
	@ApiOperation("删除定时任务")
	public Result deleteJob(@RequestParam Long jobId) {
		scheduleJobService.deleteJobById(jobId);
		return Result.ok("删除成功");
	}

	/**
	 * 立即执行任务
	 *
	 * @param jobId 任务id
	 * @return
	 */
	@OperationLogger("立即执行定时任务")
	@PostMapping("/job/run")
	@ApiOperation("立即执行定时任务")
	public Result runJob(@RequestParam Long jobId) {
		scheduleJobService.runJobById(jobId);
		return Result.ok("提交执行");
	}

	/**
	 * 更新任务状态：暂停或恢复
	 *
	 * @param jobId  任务id
	 * @param status 状态
	 * @return
	 */
	@OperationLogger("更新任务状态")
	@PutMapping("/job/status")
	@ApiOperation("更新定时任务的状态")
	public Result updateJobStatus(@RequestParam Long jobId, @RequestParam Boolean status) {
		scheduleJobService.updateJobStatusById(jobId, status);
		return Result.ok("更新成功");
	}

	/**
	 * 分页查询定时任务日志列表
	 *
	 * @param date     按执行时间查询
	 * @param pageNum  页码
	 * @param pageSize 每页条数
	 * @return
	 */
	@GetMapping("/job/logs")
	@ApiOperation("按照执行时间查询定时任务列表")
	public Result logs(@RequestParam(defaultValue = "") String[] date,
	                   @RequestParam(defaultValue = "1") Integer pageNum,
	                   @RequestParam(defaultValue = "10") Integer pageSize) {
		String startDate = null;
		String endDate = null;
		if (date.length == 2) {
			startDate = date[0];
			endDate = date[1];
		}
		String orderBy = "create_time desc";
		PageHelper.startPage(pageNum, pageSize, orderBy);
		PageInfo<ScheduleJobLog> pageInfo = new PageInfo<>(scheduleJobService.getJobLogListByDate(startDate, endDate));
		return Result.ok("请求成功", pageInfo);
	}

	/**
	 * 按id删除任务日志
	 *
	 * @param logId 日志id
	 * @return
	 */
	@DeleteMapping("/job/log")
	@ApiOperation("按照id删除定时任务日志")
	public Result delete(@RequestParam Long logId) {
		scheduleJobService.deleteJobLogByLogId(logId);
		return Result.ok("删除成功");
	}
}
