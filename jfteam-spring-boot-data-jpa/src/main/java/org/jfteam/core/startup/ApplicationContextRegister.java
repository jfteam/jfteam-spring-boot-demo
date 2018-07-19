package org.jfteam.core.startup;

import org.jfteam.core.holder.AppContextHolder;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: fengwenping
 * Date: 2018-07-19
 * Time: 下午11:00
 */
@Component
public class ApplicationContextRegister implements ApplicationContextAware {

    public void setAppName(String appName) {
        AppContextHolder.setAppName(appName);
    }

    public void setAppId(String appId) {
        AppContextHolder.setAppId(appId);
    }

    public void setSubAppId(String subAppId) {
        AppContextHolder.setSubAppId(subAppId);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        synchronized (ApplicationContextRegister.class) {
            AppContextHolder.setContext(applicationContext);
        }
    }
}
