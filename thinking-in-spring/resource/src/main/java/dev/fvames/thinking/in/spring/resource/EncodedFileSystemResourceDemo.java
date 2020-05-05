package dev.fvames.thinking.in.spring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

/**
 * 带字符编码的 {@link FileSystemResource} 示例
 */
public class EncodedFileSystemResourceDemo {

    public static void main(String[] args) throws IOException {
        String currentJavaFilePath = System.getProperty("user.dir")
                + "/resource/src/main/java/dev/fvames/thinking/in/spring/resource/EncodedFileSystemResourceDemo.java";
        FileSystemResource fileSystemResource = new FileSystemResource(currentJavaFilePath);
        EncodedResource encodedResource = new EncodedResource(fileSystemResource, "UTF-8");
        try(Reader reader = encodedResource.getReader()) {
            System.out.println(IOUtils.toString(reader));
        }
    }
}
