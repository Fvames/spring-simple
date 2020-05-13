package dev.fvames.thinking.in.spring.resource.springx;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

public class Handler extends sun.net.www.protocol.x.Handler{
    // -Djava.protocol.handler.pkgs=dev.fvames.thinking.in.spring.resource
    public static void main(String[] args) throws IOException {
        URL url = new URL("springx:///META-INF/default.properties");
        InputStream inputStream = url.openStream();
        System.out.println(StreamUtils.copyToString(inputStream, Charset.forName("UTF-8")));
    }
}
