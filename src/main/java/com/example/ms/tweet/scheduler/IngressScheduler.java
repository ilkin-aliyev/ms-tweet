package com.example.ms.tweet.scheduler;

import net.javacrumbs.shedlock.core.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class IngressScheduler {
    //k8s create job
    // add cron to job
    // call api which you create: v1/scheduler/birthday

    @Scheduled(fixedDelayString = "PT1H")
    @SchedulerLock(name = "print-hello-world",
            lockAtLeastForString = "PT5M",
            lockAtMostForString = "PT30M")
    public void printHelloWorld() {
        System.out.println("Hello world");
    }
}
