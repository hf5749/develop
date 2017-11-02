package com.cmcc.wxanswer.util;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * <b>类名称：</b>Des3
 * <b>类描述：</b>3DES加密工具类
 * <b>创建人：</b>ChenSong
 * <b>修改人：</b>ChenSong
 * <b>修改时间：</b>2016-8-2 上午11:07:42
 * <b>修改备注：</b>
 * @version v1.0<br/>
 */
public class Des3 {
    // 密钥
    private final static String secretKey = "liuyunqiang@lx100$#365#$";
    // 向量
    private final static String iv = "01234567";
    // 加解密统一使用的编码方式
    private final static String encoding = "utf-8";
    /**
     * 3DES加密
     *
     * @param plainText
     *            普通文本
     * @return
     * @throws Exception
     */
    public static String encode(String plainText) throws Exception {
        return encode(plainText, secretKey);
    }

    public static String encode(String plainText, String key) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
        return Base64.encode(encryptData);
    }

    /**
     * 3DES解密
     *
     * @param encryptText
     *            加密文本
     * @return
     * @throws Exception
     */
    public static String decode(String encryptText) throws Exception {
        return decode(encryptText, secretKey);
    }

    /**
     * 通过key进行解密
     * @param encryptText
     * @param key
     * @return
     * @throws Exception
     */
    public static String decode(String encryptText, String key) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

        byte[] decryptData = cipher.doFinal(Base64.decode(encryptText));

        return new String(decryptData, encoding);
    }

    public static void main(String[] args) throws Exception {
        String org = "abcd-efgh-ijkl-mnop,201407281043,189347234";
        org = "afjoefjo949*;\"*&&^%$!\\[]!_=923+";
        String jiamihou = encode(org);

        try {
            decode(jiamihou + "a");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        };
        String jiemihou = null;
        try {
            jiemihou = decode(jiamihou);
        } catch (Exception ex) {

        };
        System.out.println("原始值："+org);
        System.out.println("加密："+jiamihou);
        System.out.println("解密："+jiemihou);
    }
}
