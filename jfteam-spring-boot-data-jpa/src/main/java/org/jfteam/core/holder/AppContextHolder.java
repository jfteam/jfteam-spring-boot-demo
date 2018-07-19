package org.jfteam.core.holder;

import org.springframework.context.ApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: fengwenping
 * Date: 2018-07-19
 * Time: 下午11:05
 */
public final class AppContextHolder {

    private static String appName;
    private static String appId;
    private static String subAppId;
    private static ApplicationContext context;

    public static String getAppName() {
        return appName;
    }

    public static void setAppName(String appName) {
        AppContextHolder.appName = appName;
    }

    public static String getAppId() {
        return appId;
    }

    public static void setAppId(String appId) {
        AppContextHolder.appId = appId;
    }

    public static String getSubAppId() {
        return subAppId;
    }

    public static void setSubAppId(String subAppId) {
        AppContextHolder.subAppId = subAppId;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static void setContext(ApplicationContext context) {
        AppContextHolder.context = context;
    }
}
