package org.geekbang.thinking.in.spring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

/**
 * 带有字符编码的 {@link org.springframework.core.io.FileSystemResourceLoader }  示例
 * @author
 * @since
 * @see FileSystemResource
 * @see FileSystemResourceLoader
 * @see EncodedResource
 * */
public class EncodedFileSystemResourceLoaderDemo {
    public static void main(String[] args) throws IOException {
        String currentJavaFilePath = System.getProperty("user.dir") + "/thinking-in-spring/resource/src/main/java/org/geekbang/thinking/in/spring/resource/EncodedFileSystemResourceLoaderDemo.java";
        //新建一个FileSystemResourceLoader 对象
        FileSystemResourceLoader resourceLoader = new FileSystemResourceLoader();
        Resource resource = resourceLoader.getResource(currentJavaFilePath);
        EncodedResource encodedResource = new EncodedResource(resource,"utf-8");
        //字符流读取inputStream
        try(Reader reader = encodedResource.getReader()){
            System.out.println(IOUtils.toString(reader));
        }
    }
}
