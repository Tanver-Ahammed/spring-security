package com.mss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.InetAddress;

@RestController
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @GetMapping
    public String home() {
        log.info("Home page started Info");
        log.debug("Home page started Debug");
        return "Hello World";
    }

    @GetMapping("/host")
    public String getInfo() {
        String hostname = getHostName();
        String commitHash = getCommitHash();
        return "<h1>Hostname: " + hostname + "</h1><h2>Commit Hash: " + commitHash + "</h2>";
    }

    private String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (IOException e) {
            return "unknown";
        }
    }

    private String getCommitHash() {
        String commitHash = System.getenv("COMMIT_HASH");
        return commitHash != null ? commitHash : "unknown";
    }

}
