package com.jkaninda.simpleapi.cron;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
@RequiredArgsConstructor
@Slf4j
public class SimpleCronJob {
    //An example of Springboot Scheduler
    @Scheduled(cron = "0 */5 * ? * *")
    public void runEvey5Minutes() {
        log.info("CronJob:: I'm a Springboot cronJob, and I'm running at every 5 minutes");
    }


}
