package com.mss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @GetMapping
    public String home() {
        log.info("Home page started Info");
        log.debug("Home page started Debug");
        return "Hello World";
    }

    public String getInfo() {
        String vmHostname = getVmHostName();  // Fetch VM Hostname from env
        String commitHash = getCommitHash();
        return "<h1>VM Hostname: " + vmHostname + "</h1><h2>Commit Hash: " + commitHash + "</h2>";
    }

    // Fetch the VM Hostname from the environment variable
    private String getVmHostName() {
        String vmHostName = System.getenv("VM_HOSTNAME");  // Accessing the VM hostname passed to the container
        return (vmHostName != null) ? vmHostName : "unknown";
    }

    private String getCommitHash() {
        String commitHash = System.getenv("COMMIT_HASH");
        return (commitHash != null) ? commitHash : "unknown";
    }

}
