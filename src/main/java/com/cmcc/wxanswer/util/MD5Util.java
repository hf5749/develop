package com.cmcc.wxanswer.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**
 * Created by LY on 2014/11/27.
 */
public class MD5Util {

    private static final String salt="njxtqgjyptfromlianchuang";

    private static String salt(String password) {
        return password + salt;
    }

    /**
     * md5+Base64组合算法
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    private static  String md5Base64(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        String result = base64en.encode(md5.digest(str.getBytes("utf-8")));
        return result;
    }

    /**
     * md5+Base64+Salt 算法
     * @param password
     * @return
     */
    public static String md5Base64Salt(String password) {
        String result = null;
        try {
            result = md5Base64(salt(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * md5加密，
     * 并把密文转换成十六进制的字符串形式
     * @param val
     * @return
     */
    public static String md5Hexadecimal(String val) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        String res="";
        try{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] md = md5.digest(val.getBytes("utf-8"));
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            res=new String(str);
        }catch (Exception e){}
        return res;
    }

    public static void main(String args[]) throws  Exception{
//        flag=f6d322b019824d3d92476b11f3694f7e&token=72b4d11f9092d8304fbf0a20f0e93997
       System.out.println("需要加密的明文"+md5Hexadecimal("f6d322b019824d3d92476b11f3694f7eabcd"));
       System.out.println("72b4d11f9092d8304fbf0a20f0e93997 与"+md5Hexadecimal("f6d322b019824d3d92476b11f3694f7eabcd")+"对比"+"72b4d11f9092d8304fbf0a20f0e93997".equals(md5Hexadecimal("f6d322b019824d3d92476b11f3694f7eabcd")));
//
//        String jj="111222qqq";
////        String kk=Md5Crypt.md5Crypt(salt(jj).getBytes("utf-8"));
////        System.out.println( kk);
//        BASE64Encoder base64en = new BASE64Encoder();
////        System.out.println(base64en.encode(kk.getBytes()));
//
//        String aaa=DigestUtils.md5(salt(jj).getBytes("utf-8")).toString();
//        System.out.println(aaa);
//        String bbb=base64en.encode(aaa.getBytes("utf-8"));
//        System.out.println(bbb);
//        System.out.println(base64en.encode(DigestUtils.md5(salt(jj).getBytes("utf-8"))));
//
//        String aaaa=DigestUtils.md5Hex(salt(jj).getBytes("utf-8"));
//        System.out.println(aaaa);
//        System.out.println(base64en.encode(aaaa.getBytes()));
//        BASE64Decoder base64Decoder=new BASE64Decoder();
//        System.out.println(base64Decoder.decodeBuffer("GZ/JbTSt87atIcpUiKbqQg=="));
    }
}
