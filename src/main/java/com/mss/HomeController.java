package com.mss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;

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

    @GetMapping("/index")
    public String home() throws IOException {
        String gitRepo = "https://github.com/Tanver-Ahammed/spring-security.git";
        ProcessBuilder builder = new ProcessBuilder("git", "ls-remote", gitRepo, "refs/heads/play");
        // Process process = builder.start();
        // BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;
        // line = reader.readLine();
        String hash;
        if (line != null && !line.isEmpty()) {
            String[] parts = line.split("\\s+");
            hash = parts[0];
        } else {
            hash = "No output from git ls-remote.";
        }
        return "<b>Host Name: </b>" + InetAddress.getLocalHost().getHostName() + "<br>" + "<b>Last commit Hash: </b>" + hash;
    }

}
