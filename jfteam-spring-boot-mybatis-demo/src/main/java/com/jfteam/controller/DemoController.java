package com.jfteam.controller;

import com.jfteam.framework.page.PageBean;
import com.jfteam.framework.page.PageParam;
import com.jfteam.service.DemoService;
import com.jfteam.vo.DemoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: fengwenping
 * Date: 2017-10-29
 * Time: 下午7:05
 */
@RestController
@RequestMapping("demos")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping
    public PageBean<DemoVO> queryPageDemo(DemoVO demoVO, PageParam pageParam){
        return demoService.queryPageDemo(demoVO, pageParam);
    }
}
