package com.carometro.wrapper;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

public class MultiReadHttpServletRequest extends HttpServletRequestWrapper {

  private String requestBody;

  public MultiReadHttpServletRequest(HttpServletRequest request) throws IOException {
    super(request);
    this.requestBody = getBody(request);
  }

  public String getBody(HttpServletRequest request) throws IOException {
    StringWriter writer = new StringWriter();
    InputStream inputStream = request.getInputStream();
    InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
    char[] buffer = new char[1024];
    int bytesRead;
    while ((bytesRead = reader.read(buffer)) != -1) {
      writer.write(buffer, 0, bytesRead);
    }
    return writer.toString();
  }

  @Override
  public ServletInputStream getInputStream() {
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(requestBody.getBytes(StandardCharsets.UTF_8));
    return new ServletInputStream() {
      @Override
      public int read() {
        return byteArrayInputStream.read();
      }

      @Override
      public boolean isFinished() {
        return byteArrayInputStream.available() == 0;
      }

      @Override
      public boolean isReady() {
        return true;
      }

      @Override
      public void setReadListener(ReadListener readListener) {
        // Not implemented
      }
    };
  }

  public String getRequestBody() {
    return this.requestBody;
  }
}
