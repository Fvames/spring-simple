package dev.fvames.springbootquartz.controller;

import dev.fvames.springbootquartz.service.SysJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO 类描述
 *
 * @author
 * @version 2019/12/31 17:59
 */
@RestController
@RequestMapping("/job")
public class JobController {

	@Autowired
	private SysJobService sysJobService;

	@RequestMapping("/job-list")
	public String jobList() {
		return "jobListPage";
	}
}
