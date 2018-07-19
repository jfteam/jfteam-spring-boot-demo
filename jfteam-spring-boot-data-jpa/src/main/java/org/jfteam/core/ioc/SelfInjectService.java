package org.jfteam.core.ioc;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: fengwenping
 * Date: 2018-07-19
 * Time: 下午11:15
 */
public interface SelfInjectService {

    void setSelf(SelfInjectService self);
}
