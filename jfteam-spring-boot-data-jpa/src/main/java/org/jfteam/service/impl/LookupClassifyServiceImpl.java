package org.jfteam.service.impl;

import org.jfteam.core.ioc.SelfInjectService;
import org.jfteam.service.LookupClassifyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: fengwenping
 * Date: 2018-07-17
 * Time: 下午11:45
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LookupClassifyServiceImpl implements LookupClassifyService, SelfInjectService {

    private LookupClassifyService lookupClassifyService;

    @Override
    public void setSelf(SelfInjectService self) {
        this.lookupClassifyService = (LookupClassifyService) self;
    }
}
