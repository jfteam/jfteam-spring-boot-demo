package org.jfteam.util;

/**
 * Created with IntelliJ IDEA.
 * Description: 非对称公私钥对
 * User: fengwenping
 * Date: 2018-07-28
 * Time: 下午10:16
 */
public final class KeyPairVO {

    private String privateKey;
    private String publicKey;

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
