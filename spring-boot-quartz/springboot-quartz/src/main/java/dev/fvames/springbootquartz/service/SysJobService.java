package dev.fvames.springbootquartz.service;

import dev.fvames.springbootquartz.entity.SysJob;
import dev.fvames.springbootquartz.exception.BizException;
import dev.fvames.springbootquartz.mapper.SysJobMapper;
import dev.fvames.springbootquartz.utils.SchedulerUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * TODO 类描述
 *
 * @author
 * @version 2019/12/31 17:55
 */
@Service
public class SysJobService {
	@Autowired
	private SysJobMapper sysJobMapper;

	public int getJobCount() {
		return sysJobMapper.getJobCount();
	}

	public List<SysJob> querySysJobList(HashMap<String, String> map) {
		return sysJobMapper.querySysJobList(map);
	}

	public int insertSelective(SysJob sysJob) throws Exception {
		if (null == sysJob) {
			throw new BizException("Job 任务参数不能为空");
		}
		String jobName = sysJob.getJobName();
		if (StringUtils.isBlank(jobName)) {
			throw new BizException("Job 名称不能为空");
		}
		String jobGroup = sysJob.getJobGroup();
		if (StringUtils.isBlank(jobGroup)) {
			throw new BizException("Job 组别名称不能为空");
		}
		if (StringUtils.isBlank(sysJob.getJobCron())) {
			throw new BizException("Job Cron 不能为空");
		}
		if (StringUtils.isBlank(sysJob.getJobClassPath())) {
			throw new BizException("Job 任务处理类路径不能为空");
		}

		// 校验是否已存在 job
		SysJob queryBean = new SysJob();
		queryBean.setJobName(jobName);
		queryBean.setJobGroup(jobGroup);
		SysJob existJob = sysJobMapper.selectByBean(queryBean);
		if (null != existJob) {
			throw new BizException(String.format("JobName: %s, JobGroup: %s， 已存在。", jobName, jobGroup));
		}

		sysJob.setJobStatus(SysJob.JOB_STATE_YES);
		int i = sysJobMapper.insertSelective(sysJob);

		SchedulerUtil.addJob(sysJob.getJobClassPath(), jobName, jobGroup, sysJob.getJobCron());

		return i;
	}

	public int delete(SysJob sysJob) throws Exception {
		if (null == sysJob) {
			throw new BizException("Job 任务参数不能为空");
		}
		if (null == sysJob.getId()) {
			throw new BizException("任务 id 不能为空");
		}

		int i = sysJobMapper.deleteByPrimaryKey(sysJob.getId());

		SchedulerUtil.jobDelete(sysJob.getJobName(), sysJob.getJobGroup());

		return i;
	}

	public SysJob selectByPrimaryKey(Integer id) {
		return sysJobMapper.selectByPrimaryKey(id);
	}

	public SysJob selectByBean(SysJob bean) {
		return sysJobMapper.selectByBean(bean);
	}

	public int updateByPrimaryKeySelective(SysJob bean) {
		return sysJobMapper.updateByPrimaryKeySelective(bean);
	}

	public void stopJob(SysJob sysJob) throws Exception {
		if (null == sysJob) {
			throw new BizException("Job 任务参数不能为空");
		}
		if (null == sysJob.getId()) {
			throw new BizException("任务 id 不能为空");
		}
		String jobName = sysJob.getJobName();
		if (StringUtils.isBlank(jobName)) {
			throw new BizException("Job 名称不能为空");
		}
		String jobGroup = sysJob.getJobGroup();
		if (StringUtils.isBlank(jobGroup)) {
			throw new BizException("Job 组别名称不能为空");
		}

		SysJob updateBean = new SysJob();
		updateBean.setId(sysJob.getId());
		updateBean.setJobStatus(SysJob.JOB_STATE_NO);
		sysJobMapper.updateByPrimaryKeySelective(updateBean);

		//SchedulerUtil.jobPause(sysJob.getJobName(), sysJob.getJobGroup());
		SchedulerUtil.jobDelete(jobName, jobGroup);
	}

	public void startJob(SysJob sysJob) throws Exception {
		if (null == sysJob) {
			throw new BizException("Job 任务参数不能为空");
		}
		if (null == sysJob.getId()) {
			throw new BizException("任务 id 不能为空");
		}
		String jobName = sysJob.getJobName();
		if (StringUtils.isBlank(jobName)) {
			throw new BizException("Job 名称不能为空");
		}
		String jobGroup = sysJob.getJobGroup();
		if (StringUtils.isBlank(jobGroup)) {
			throw new BizException("Job 组别名称不能为空");
		}
		if (StringUtils.isBlank(sysJob.getJobCron())) {
			throw new BizException("Job Cron 不能为空");
		}
		if (StringUtils.isBlank(sysJob.getJobClassPath())) {
			throw new BizException("Job 任务处理类路径不能为空");
		}

		SysJob updateBean = new SysJob();
		updateBean.setId(sysJob.getId());
		updateBean.setJobStatus(SysJob.JOB_STATE_YES);
		sysJobMapper.updateByPrimaryKeySelective(updateBean);

		//SchedulerUtil.jobResume(sysJob.getJobName(), sysJob.getJobGroup());
		SchedulerUtil.addJob(sysJob.getJobClassPath(), sysJob.getJobName(), sysJob.getJobGroup(), sysJob.getJobCron());
	}

	public int reSchedulejob(SysJob sysJob) throws Exception {
		if (null == sysJob) {
			throw new BizException("Job 任务参数不能为空");
		}
		String jobName = sysJob.getJobName();
		if (StringUtils.isBlank(jobName)) {
			throw new BizException("Job 名称不能为空");
		}
		String jobGroup = sysJob.getJobGroup();
		if (StringUtils.isBlank(jobGroup)) {
			throw new BizException("Job 组别名称不能为空");
		}
		if (StringUtils.isBlank(sysJob.getJobCron())) {
			throw new BizException("Job Cron 不能为空");
		}
		if (null == sysJob.getJobStatus()) {
			throw new BizException("Job 状态 不能为空");
		}

		SysJob updateBean = new SysJob();
		updateBean.setId(sysJob.getId());
		updateBean.setJobCron(sysJob.getJobCron());
		int i = sysJobMapper.updateByPrimaryKeySelective(updateBean);
		// 如果是启用状态的则更新，更新后直接启动，停止状态时点击启动，从数据库加载配置重新创建任务
		if (SysJob.JOB_STATE_YES.equals(sysJob.getJobStatus())) {

			SchedulerUtil.jobReschedule(sysJob.getJobName(), sysJob.getJobGroup(), sysJob.getJobCron());
		}
		return i;
	}
}
