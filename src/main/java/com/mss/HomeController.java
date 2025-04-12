package com.mss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @GetMapping
    public String getInfo() {
        log.info("Hello World");
        String vmHostname = getVmHostName();  // Fetch VM Hostname from env
        String commitHash = getCommitHash();
        return "<h1>VM Hostname is: <span style='color:red;'>" + vmHostname + "</span></h1>"
         + "<h2>Commit Hash is: " + commitHash + "</h2>";
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
