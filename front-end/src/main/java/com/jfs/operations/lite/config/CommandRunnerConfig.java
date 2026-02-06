package com.jfs.operations.lite.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommandRunnerConfig implements CommandLineRunner {

    @Value("${server.port}")
    private String frontEndPort;
    @Value("${server.url}")
    private String backEndUrl;
    @Value("${spring.profiles.active}")
    private String activeProfile;


    @Override
    public void run(String... args) throws Exception {
        logStartupInfo();
    }

    private void logStartupInfo() {
        log.info("jfs-operations-web startup "
                + " front end port on: " + frontEndPort
                + ", profile: " + activeProfile
                + ", back end running under: " + backEndUrl);
    }
}
