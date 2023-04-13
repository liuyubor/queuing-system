package com.liuyubo.qs.schedule;

import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    // 每天8点执行一次，检测人流量
//    @Scheduled(cron = "0 0 8 ?")
//    public void scheduledTask() {
//        System.out.println("定时检测人流量任务执行时间：" + now());
//    }
}
