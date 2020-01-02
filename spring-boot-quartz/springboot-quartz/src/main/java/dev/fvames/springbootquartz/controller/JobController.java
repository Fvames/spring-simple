package dev.fvames.springbootquartz.controller;

import dev.fvames.springbootquartz.entity.SysJob;
import dev.fvames.springbootquartz.exception.BizException;
import dev.fvames.springbootquartz.service.SysJobService;
import dev.fvames.springbootquartz.utils.LayuiData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * TODO 类描述
 *
 * @author
 * @version 2019/12/31 17:59
 */
@Controller
@RequestMapping("/job")
public class JobController {

	@Autowired
	private SysJobService sysJobService;

	@RequestMapping("/job-list")
	public String jobList() {
		return "jobListPage";
	}

	/**
	 * 打开详情界面
	 *
	 * @param id
	 * @param model
	 */
	@RequestMapping("/toDetail")
	public String toDetail(Integer id, Model model) {
		SysJob job = sysJobService.selectByPrimaryKey(id);
		model.addAttribute("job", job);
		return "jobDetail";
	}

	/**
	 * 打开修改界面
	 *
	 * @param id
	 * @param model
	 */
	@RequestMapping("/toUpdate")
	public String toUpdate(Integer id, Model model) {
		SysJob job = sysJobService.selectByPrimaryKey(id);
		model.addAttribute("job", job);
		return "jobUpdate";
	}

	/**
	 * 打开新增界面
	 */
	@RequestMapping("/toJob")
	public String toJob() {
		return "jobAdd";
	}

	/**
	 * 展示任务调度管理页
	 *
	 * @param request
	 * @param rep
	 */
	@RequestMapping(value = "/jobPage", method = RequestMethod.GET)
	public String getJobPage(HttpServletRequest request, HttpServletResponse rep) {
		return "job/job_info";
	}

	@ResponseBody
	@GetMapping(value = "/queryList")
	public LayuiData queryJobList(HttpServletRequest request, HttpServletResponse response) {
		String idStr = request.getParameter("id");
		String jobName = request.getParameter("jobName");
		String jobGroup = request.getParameter("jobGroup");
		String jobCron = request.getParameter("jobCron");
		String jobClassPath = request.getParameter("jobClassPath");
		String jobDescribe = request.getParameter("jobDescribe");

		HashMap<String, String> map = new HashMap<String, String>();
		if (StringUtils.isNotBlank(idStr)) {
			map.put("id", idStr);
		}
		if (StringUtils.isNotBlank(jobName)) {
			map.put("jobName", jobName);
		}
		if (StringUtils.isNotBlank(jobGroup)) {
			map.put("jobGroup", jobGroup);
		}
		if (StringUtils.isNotBlank(jobCron)) {
			map.put("jobCron", jobCron);
		}
		if (StringUtils.isNotBlank(jobClassPath)) {
			map.put("jobClassPath", jobClassPath);
		}
		if (StringUtils.isNotBlank(jobDescribe)) {
			map.put("jobDescribe", jobDescribe);
		}

		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		if (page >= 1) {
			page = (page - 1) * limit;
		}
		LayuiData layuiData = new LayuiData();

		try {
			List<SysJob> jobList = sysJobService.querySysJobList(map);
			int count = sysJobService.getJobCount();
			layuiData.setCode(0);
			layuiData.setCount(count);
			layuiData.setMsg("数据请求成功");
			layuiData.setData(jobList);
			return layuiData;
		} catch (Exception e) {
			throw new BizException("查询任务列表异常：" + e.getMessage());
		}
	}

	@ResponseBody
	@PostMapping("/addJob")
	public int addJob(SysJob sysJob) throws Exception {

		return sysJobService.insertSelective(sysJob);
	}

	@ResponseBody
	@PostMapping("/deleteJob")
	public int deleteJob(@RequestParam(value = "id") Integer id) throws Exception {
		// 校验 id
		if (null == id) {
			throw new BizException("Job 任务 id 不能为空");
		}
		// 校验是否存在任务
		SysJob sysJob = new SysJob();
		sysJob.setId(id);
		SysJob existBean = sysJobService.selectByBean(sysJob);
		if (null == existBean) {
			throw new BizException("任务id 为 " + id + " 的 Job 不存在");
		}
		// 删除 job 及数据库数据
		return sysJobService.delete(existBean);
	}

	@ResponseBody
	@GetMapping("/changeState")
	public int changeState(@RequestParam(value = "id") Integer id) throws Exception {
		// 校验 id
		if (null == id) {
			throw new BizException("Job 任务 id 不能为空");
		}
		SysJob sysJob = new SysJob();
		sysJob.setId(id);
		SysJob existBean = sysJobService.selectByBean(sysJob);
		if (null == existBean) {
			throw new BizException("任务 id 为 "+id+" 的 Job 不存在");
		}

		if (SysJob.JOB_STATE_YES.equals(existBean.getJobStatus())) {
			// 停用
			sysJobService.stopJob(existBean);
		}

		if (SysJob.JOB_STATE_NO.equals(existBean.getJobStatus())) {
			// 启用
			sysJobService.startJob(existBean);
		}
		return 1;
	}

	@PostMapping("/reSchedulejob")
	@ResponseBody
	public int reSchedulejob(SysJob sysJob) throws Exception {
		if (null == sysJob.getId()) {
			throw new BizException("Job 任务 id 不能为空");
		}

		Integer id = sysJob.getId();
		SysJob queryBean = new SysJob();
		queryBean.setId(id);
		SysJob existBean = sysJobService.selectByBean(queryBean);
		if (null == existBean) {
			throw new BizException("任务 id 为 " + id + " 的 Job 不存在");
		}

		return sysJobService.reSchedulejob(existBean);
	}
}
