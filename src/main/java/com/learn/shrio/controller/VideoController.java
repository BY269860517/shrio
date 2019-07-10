package com.learn.shrio.controller;


import com.learn.shrio.domain.JsonData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("video")
public class VideoController {


    @RequestMapping("/update")
    public JsonData updateVideo(){

        return JsonData.buildSuccess("video更新成功");

    }

}
