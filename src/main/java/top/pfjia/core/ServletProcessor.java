package top.pfjia.core;


import top.pfjia.connector.http.HttpRequest;
import top.pfjia.connector.http.HttpRequestFacade;
import top.pfjia.connector.http.HttpResponse;
import top.pfjia.connector.http.HttpResponseFacade;

import javax.servlet.Servlet;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

public class ServletProcessor {

    public void process(HttpRequest request, HttpResponse response) {

        String uri = request.getRequestURI();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);
        servletName = "top.pfjia.servlet." + servletName;
        URLClassLoader loader = null;
        try {
            // create a URLClassLoader
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classPath = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath());
            String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
            urls[0] = new URL(null, repository, streamHandler);
            loader = new URLClassLoader(urls);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        Class myClass = null;
        try {
            myClass = loader.loadClass(servletName);
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }

        Servlet servlet = null;

        try {
            servlet = (Servlet) myClass.newInstance();
            HttpRequestFacade requestFacade = new HttpRequestFacade(request);
            HttpResponseFacade responseFacade = new HttpResponseFacade(response);
            servlet.service(requestFacade, responseFacade);
            response.finishResponse();
        } catch (Throwable e) {
            System.out.println(e.toString());
        }
    }
}