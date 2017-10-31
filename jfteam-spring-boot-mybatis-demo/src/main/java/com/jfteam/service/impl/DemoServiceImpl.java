package com.jfteam.service.impl;

import com.jfteam.dao.DemoDao;
import com.jfteam.framework.page.PageBean;
import com.jfteam.framework.page.PageParam;
import com.jfteam.service.DemoService;
import com.jfteam.vo.DemoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: fengwenping
 * Date: 2017-10-29
 * Time: 下午7:49
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoDao demoDao;

    @Override
    public PageBean<DemoVO> queryPageDemo(DemoVO demoVO, PageParam pageParam) {
        return demoDao.findPageByEntity(demoVO, pageParam);
    }
}
