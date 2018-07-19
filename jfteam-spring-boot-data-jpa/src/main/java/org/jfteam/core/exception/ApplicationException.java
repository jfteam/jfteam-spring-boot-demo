package org.jfteam.core.exception;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: fengwenping
 * Date: 2018-07-19
 * Time: 下午10:54
 */
public abstract class ApplicationException extends Exception {

    private String errorCode;
    private Object[] params;

    public ApplicationException(String errorCode){
        this.errorCode= errorCode;
    }

    public ApplicationException(String errorCode, Throwable throwable){
        this.errorCode=errorCode;
        this.addSuppressed(throwable);
    }
}
