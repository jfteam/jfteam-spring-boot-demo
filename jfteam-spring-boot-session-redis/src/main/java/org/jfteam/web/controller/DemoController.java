package org.jfteam.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: fengwenping
 * Date: 2017-11-03
 * Time: 上午12:11
 */
@RestController
@RequestMapping("demos")
public class DemoController {

    @GetMapping
    public Map<String, String> querySessionInfo() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("sessionId", request.getSession().getId());
        return resultMap;
    }
}
