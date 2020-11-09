package com.github.cdtft.euripides.utils;

/**
 * @author : wangcheng
 * @date : 2020年11月06日 11:05
 */
public class HttpProtocolUtils {

    public static String getHttpHeader200(long contextLength) {
        return "HTTP/1.1 200 OK\n" +
                //带上长度浏览器才能将解析
                "Content-Length: " + contextLength + "\n" +
                "Content-Type: application/json ;charset=UTF-8\n" +
                "\r\n";
    }
}
