package com.jfteam.service;

import com.jfteam.framework.page.PageBean;
import com.jfteam.framework.page.PageParam;
import com.jfteam.vo.DemoVO;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: fengwenping
 * Date: 2017-10-29
 * Time: 下午7:49
 */
public interface DemoService {
    PageBean<DemoVO> queryPageDemo(DemoVO demoVO, PageParam pageParam);
}
