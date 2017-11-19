package org.jfteam.util;

import org.springframework.web.client.RestTemplate;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: fengwenping
 * Date: 2017-11-09
 * Time: 下午11:47
 */
public class HttpRequestUtils {

    private static RestTemplate restTemplate;

    /**
     * 单例获取RestTemplate
     *
     * @return
     */
    public static RestTemplate getRestTemplate() {
        if (restTemplate == null) {
            synchronized (HttpRequestUtils.class) {
                if (restTemplate == null) {
                    restTemplate = SpringContextUtils.getBean(RestTemplate.class);
                }
            }
        }
        return restTemplate;
    }

}
