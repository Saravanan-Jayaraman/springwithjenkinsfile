package com.springwithjenkinsfile.springwithjenkinsfile;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	 @GetMapping("/jenkinfile")
	    public String hello() {
	        return "Hello Jenkins  with Docker by Pipline and JenkinsFile !";
	    }
}
