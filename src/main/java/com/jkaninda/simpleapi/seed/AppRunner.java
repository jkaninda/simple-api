package com.jkaninda.simpleapi.seed;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class AppRunner implements ApplicationRunner {

    @Value("${run.data.seed}")
    private boolean runDataSeedOnBoot;
    private final DataBaseSeed dataBaseSeed;
    @Override
    public void run(ApplicationArguments args) throws Exception {

        //Run Data base seed
        if (runDataSeedOnBoot){
            log.info("Seed:: Start Database seed ");
            dataBaseSeed.run();
        }else {
            log.info("Seed::  Database seed on boot is disabled");

        }

    }
}
