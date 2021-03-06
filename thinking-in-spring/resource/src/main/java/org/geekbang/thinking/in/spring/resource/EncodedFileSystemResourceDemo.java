package org.geekbang.thinking.in.spring.resource;

import org.apache.commons.io.IOUtils;
import org.geekbang.thinking.in.spring.resource.util.ResourceUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 * 带有字符编码的 {@link FileSystemResource }  示例
 * @author
 * @since
 * @see FileSystemResource
 * @see EncodedResource
 * */
public class EncodedFileSystemResourceDemo {
    public static void main(String[] args) throws IOException {
        String currentJavaFilePath = System.getProperty("user.dir") + "/thinking-in-spring/resource/src/main/java/org/geekbang/thinking/in/spring/resource/EncodedFileSystemResourceDemo.java";
        File currentJavaFile = new File(currentJavaFilePath);
        //FileSystemResource=>WritableResource=>Resource
        FileSystemResource fileSystemResource = new FileSystemResource(currentJavaFile);
        System.out.println(ResourceUtils.getContent(fileSystemResource));
    }
}
