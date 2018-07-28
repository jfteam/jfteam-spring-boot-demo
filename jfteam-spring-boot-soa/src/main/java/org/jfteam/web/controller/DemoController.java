package org.jfteam.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: fengwenping
 * Date: 2018-07-28
 * Time: 下午10:30
 */
@RestController
@RequestMapping("/demos")
public class DemoController {

    @GetMapping(value = "all")
    public String findAll() {
        return "{\"name\":\"demo\"}";
    }

    @GetMapping(value = "find")
    public Map<String, String> find() {
        Map<String, String> result = new HashMap<>();
        result.put("name", "demo");
        return result;
    }
}
