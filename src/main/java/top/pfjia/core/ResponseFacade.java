package top.pfjia.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import javax.servlet.ServletResponse;
import javax.servlet.ServletOutputStream;

public class ResponseFacade implements ServletResponse {

  private ServletResponse response;
  public ResponseFacade(HttpResponse httpResponse) {
    this.response = httpResponse;
  }

  public void flushBuffer() throws IOException {
    response.flushBuffer();
  }

  public int getBufferSize() {
    return response.getBufferSize();
  }

  public String getCharacterEncoding() {
    return response.getCharacterEncoding();
  }

  public Locale getLocale() {
    return response.getLocale();
  }

  public ServletOutputStream getOutputStream() throws IOException {
    return response.getOutputStream();
  }

  public PrintWriter getWriter() throws IOException {
    return response.getWriter();
  }

  public boolean isCommitted() {
    return response.isCommitted();
  }

  public void reset() {
    response.reset();
  }

  public void resetBuffer() {
    response.resetBuffer();
  }

  public void setBufferSize(int size) {
    response.setBufferSize(size);
  }

  public void setContentLength(int length) {
    response.setContentLength(length);
  }

  public void setContentType(String type) {
    response.setContentType(type);
  }

  public void setLocale(Locale locale) {
    response.setLocale(locale);
  }


    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public void setCharacterEncoding(String charset) {

    }

    @Override
    public void setContentLengthLong(long len) {

    }
}