package org.jfteam.web.controller;

import org.jfteam.service.LookupItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: fengwenping
 * Date: 2018-07-17
 * Time: 下午11:48
 */
@RestController
public class LookupItemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LookupItemController.class);

    @Autowired
    private LookupItemService lookupItemService;
}
