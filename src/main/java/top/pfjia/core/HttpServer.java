package top.pfjia.core;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class HttpServer {

    // shutdown command
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    // the shutdown command received
    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        log.info("before await ");
        server.await();
    }

    public void await() {
        ServerSocket serverSocket = null;
        int port = 80;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            System.exit(1);
        }

        // Loop waiting for a request
        while (!shutdown) {
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;
            try {
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                // create Request object and parse
                HttpRequest httpRequest = new HttpRequest(input);
                httpRequest.parse();

                // create Response object
                HttpResponse httpResponse = new HttpResponse(output);
                httpResponse.setHttpRequest(httpRequest);

                // check if this is a request for a servlet or a static resource
                // a request for a servlet begins with "/servlet/"
                if (httpRequest.getUri().startsWith(Const.SERVLET)) {
                    ServletProcessor processor = new ServletProcessor();
                    processor.process(httpRequest, httpResponse);
                } else {
                    StaticResourceProcessor processor = new StaticResourceProcessor();
                    processor.process(httpRequest, httpResponse);
                }

                // Close the socket
                socket.close();

                //check if the previous URI is a shutdown command
                shutdown = httpRequest.getUri().equals(SHUTDOWN_COMMAND);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
