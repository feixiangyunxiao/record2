package com.game.util;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.security.Key;


public class ShiroCheckUtil {
    public static final String slat = "linxingjie";

    // 配置md5的摘要，主要是将密码进行加密处理
    public static String md5(String passwors) {
        SimpleHash simpleHash = new SimpleHash("MD5",passwors,slat,64);
        return simpleHash.toString();
    }

    // base64进行编码操作，主要是将byte数组转化为base64编码的字符串
    public static String toBase64(byte[] data) {
        return Base64.encodeToString(data);
    }

    // 使用base64进行解码操作，将字符串进行解码
    public static String base64To(String data) {
        return Base64.decodeToString(data.getBytes());
    }

    // aes加密模式,该函数主要是为了获取到一个密钥
    public static Key creatKey() {
        AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setKeySize(256);
        return aesCipherService.generateNewKey();
    }

    // aes解密模式
    public static String aesEncode(Key key, String data) {
        AesCipherService aesCipherService = new AesCipherService();
        return aesCipherService.encrypt(data.getBytes(),key.getEncoded()).toBase64();
    }

    // aes解密模式
    public static String aesDecode(Key key,String data) {
        AesCipherService aesCipherService = new AesCipherService();
        return new String(aesCipherService.decrypt(Base64.decode(data),key.getEncoded()).getBytes());
    }
}
