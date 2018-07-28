package org.jfteam.service;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: fengwenping
 * Date: 2018-07-28
 * Time: 下午10:20
 */
public interface SecurityService {
    /**
     * 根据appId获取对应的公钥
     *
     * @param appId
     * @return
     */
    String getPublicKey(String appId);
}
