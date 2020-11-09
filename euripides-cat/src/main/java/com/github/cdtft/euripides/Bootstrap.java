package com.github.cdtft.euripides;

import com.github.cdtft.euripides.utils.HttpProtocolUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : wangcheng
 * @date : 2020年11月05日 15:32
 */
public class Bootstrap {

    /**
     * 响应请求返回json数据
     * @throws Exception
     */
    public void start0() throws Exception {
        ServerSocket socket = new ServerSocket(8080);
        System.out.println("euripides cat start<<");
        while (true) {
            Socket accept = socket.accept();
            OutputStream outputStream = accept.getOutputStream();
            String data = "{\"data\":\"hello\"}";
            String responseText = HttpProtocolUtils.getHttpHeader200(data.getBytes().length) + data;
            outputStream.write(responseText.getBytes());
            accept.close();
        }
    }

    public void start1() throws Exception {
        ServerSocket socket = new ServerSocket(8080);
        System.out.println("euripides cat start<<");
        while (true) {
            Socket accept = socket.accept();
            InputStream inputStream = accept.getInputStream();
            int count = 0;
            while (count == 0) {
                count = inputStream.available();
            }
            byte[] bytes = new byte[count];
            inputStream.read(bytes);
            System.out.println(new String(bytes));
            accept.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.start1();
    }
}
