package dev.fvames.springbootquartz.job;

import com.alibaba.fastjson.JSONObject;
import dev.fvames.springbootquartz.entity.SysJob;
import dev.fvames.springbootquartz.service.SysJobService;
import dev.fvames.springbootquartz.utils.MailUtil;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: qingshan
 * @Date: 2018/9/11 16:31
 * @Description: 咕泡学院，只为更好的你
 */
public class TestTask3 implements Job {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysJobService sysJobService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("开始执行任务3... ...");
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("jobGroup", "mail");
        map.put("jobStatus", "1");
        List<SysJob> jobList= sysJobService.querySysJobList(map);

        if( null == jobList || jobList.size() ==0){
            logger.info("没有状态为可用的发送邮件任务... ...");
        }

        for (SysJob sysJob:jobList) {
            String jobClassName=sysJob.getJobName();
            String jobGroupName=sysJob.getJobGroup();

            if (StringUtils.isNotEmpty(sysJob.getJobDataMap())) {
                JSONObject jd = JSONObject.parseObject(sysJob.getJobDataMap());
                JSONObject data = jd.getJSONObject("data");
                String loginAccount = data.getString("loginAccount");
                String loginAuthCode = data.getString("loginAuthCode");
                String sender = data.getString("sender");
                String recipientsStr = data.getString("recipients");
                String[] recipients = recipientsStr.split(",");
                String emailSubject = data.getString("emailSubject");
                String emailContent = data.getString("emailContent");
                String emailContentType = data.getString("emailContentType");

                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                emailSubject = emailSubject + sdf.format(date) ;
                logger.info("开始发送邮件... ...");
                MailUtil.sendEmail(loginAccount,loginAuthCode,sender,recipients,emailSubject,emailContent,emailContentType);
            }else {
                logger.info("JobDataMap为空，没有发送邮件的相关信息... ...");
            }

        }
    }
}
