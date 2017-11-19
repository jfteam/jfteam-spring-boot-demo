package org.jfteam.web;

import org.jfteam.config.SessionConfiguration;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: fengwenping
 * Date: 2017-11-03
 * Time: 上午12:00
 */
//初始化Session配置
public class SessionInitializer extends AbstractHttpSessionApplicationInitializer {
    public SessionInitializer() {
        super(SessionConfiguration.class);
    }
}
