package org.jfteam.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Created with IntelliJ IDEA.
 * Description: RSA非对称加密算法
 * User: fengwenping
 * Date: 2018-07-28
 * Time: 下午10:13
 */
public class RSAUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(RSAUtils.class);

    private static final String ENCRYPT_NAME = "RSA";

    public static final int ENCRYPT_KEY_SIZE = 512;

    private static KeyPairGenerator keyPairGenerator;

    static {
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(ENCRYPT_NAME);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("init KeyPairGenerator failure.", e);
        }
        keyPairGenerator.initialize(ENCRYPT_KEY_SIZE);
    }

    public static KeyPairVO getKeyPair() {
        final KeyPair keyPair = keyPairGenerator.generateKeyPair();
        final PrivateKey privateKey = keyPair.getPrivate();
        final PublicKey publicKey = keyPair.getPublic();
        KeyPairVO keyPairVO = new KeyPairVO();
        keyPairVO.setPrivateKey(Base64.getEncoder().encodeToString(privateKey.getEncoded()));
        keyPairVO.setPublicKey(Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        return keyPairVO;
    }

    /**
     * 生成私钥
     *
     * @param key
     * @return
     */
    private static PrivateKey generatePrivateKey(String key) {
        PrivateKey rsaPrivateKey = null;
        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key));
            KeyFactory keyFactory = KeyFactory.getInstance(ENCRYPT_NAME);
            rsaPrivateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return rsaPrivateKey;
    }

    /**
     * 生成公钥
     *
     * @param key
     * @return
     */
    private static PublicKey generatePublicKey(String key) {
        PublicKey rsaPublicKey = null;
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(key));
            KeyFactory keyFactory = KeyFactory.getInstance(ENCRYPT_NAME);
            rsaPublicKey = keyFactory.generatePublic(keySpec);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return rsaPublicKey;
    }

    /**
     * 私钥加密,公钥解密
     *
     * @param str
     * @param privateKey
     * @return
     */
    private static String encrypt(String str, PrivateKey privateKey) {
        String result = null;
        try {
            final Cipher cipher = Cipher.getInstance(ENCRYPT_NAME);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            result = Base64.getEncoder().encodeToString(cipher.doFinal(str.getBytes()));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            LOGGER.error("encrypt with private key failure.", e);
        }
        return result;
    }

    /**
     * 公钥加密,私钥解密
     *
     * @param str
     * @param publicKey
     * @return
     */
    private static String encrypt(String str, PublicKey publicKey) {
        String result = null;
        try {
            final Cipher cipher = Cipher.getInstance(ENCRYPT_NAME);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            result = Base64.getEncoder().encodeToString(cipher.doFinal(str.getBytes()));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            LOGGER.error("decrypt with public key failure.", e);
        }
        return result;
    }

    /**
     * 私钥加密,公钥解密
     *
     * @param str
     * @param publicKey
     * @return
     */
    private static String decrypt(String str, PublicKey publicKey) {
        String result = null;
        try {
            final Cipher cipher = Cipher.getInstance(ENCRYPT_NAME);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            result = new String(cipher.doFinal(Base64.getDecoder().decode(str)));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            LOGGER.error("decrypt with public key failure.", e);
        }
        return result;
    }

    /**
     * 公钥加密,私钥解密
     *
     * @param str
     * @param privateKey
     * @return
     */
    private static String decrypt(String str, PrivateKey privateKey) {
        String result = null;
        try {
            final Cipher cipher = Cipher.getInstance(ENCRYPT_NAME);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            result = new String(cipher.doFinal(Base64.getDecoder().decode(str)));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            LOGGER.error("encrypt with private key failure.", e);
        }
        return result;
    }

    /**
     * 私钥加密
     *
     * @param str
     * @param privateKey
     * @return
     */
    public static String encryptWithPrivateKey(String str, String privateKey) {
        return RSAUtils.encrypt(str, RSAUtils.generatePrivateKey(privateKey));
    }

    /**
     * 公钥加密
     *
     * @param str
     * @param publicKey
     * @return
     */
    public static String encryptWithPublicKey(String str, String publicKey) {
        return RSAUtils.encrypt(str, RSAUtils.generatePublicKey(publicKey));
    }

    /**
     * 私钥解密
     *
     * @param str
     * @param privateKey
     * @return
     */
    public static String decryptWithPrivateKey(String str, String privateKey) {
        return RSAUtils.decrypt(str, RSAUtils.generatePrivateKey(privateKey));
    }

    /**
     * 公钥解密
     *
     * @param str
     * @param publicKey
     * @return
     */
    public static String decryptWithPublicKey(String str, String publicKey) {
        return RSAUtils.decrypt(str, RSAUtils.generatePublicKey(publicKey));
    }
}

