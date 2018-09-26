package top.pfjia.connector.http;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author pfjia
 * @since 2018/9/26 19:38
 */
@Slf4j
public class HttpConnector implements Runnable {
    private boolean stopped;
    @Getter
    private String scheme = "http";

    /**
     * 1.等待Http请求
     * 2.为每个请求创建一个HttpProcessor实例
     * 3.调用HttpProcessor.process()
     */
    @Override
    public void run() {
        ServerSocket serverSocket = null;
        int port = 80;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            System.exit(1);
        }

        // Loop waiting for a request
        while (!stopped) {
            Socket socket = null;

            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                continue;
            }

            HttpProcessor httpProcessor = new HttpProcessor(this);
            httpProcessor.process(socket);
        }
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();

    }
}
