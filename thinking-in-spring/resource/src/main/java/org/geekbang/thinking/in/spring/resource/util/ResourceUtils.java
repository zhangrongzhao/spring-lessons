package org.geekbang.thinking.in.spring.resource.util;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

/**
 * Resource 工具类
 * {@link Resource}
 * **/
public interface ResourceUtils {
     static String getContent(Resource resource){
         try {
             return ResourceUtils.getContent(resource,"utf-8");
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
     }
     static String getContent(Resource resource,String encoding) throws IOException {
         EncodedResource encodedResource = new EncodedResource(resource,encoding);
         //字符流读取inputStream
         try(Reader reader = encodedResource.getReader()){
             return IOUtils.toString(reader);
         }
     }
}
