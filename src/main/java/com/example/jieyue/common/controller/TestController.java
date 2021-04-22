package com.example.jieyue.common.controller;

import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
public class TestController {
    @RequestMapping("test")
    public String test() throws FileNotFoundException {

        return ResourceUtils.getFile("classpath:/static").getPath()+"\\";
    }

    @RequestMapping("/test/check")
    public String check(int id){
        System.out.println("id="+id);
        return "This is test/check";
    }
}
