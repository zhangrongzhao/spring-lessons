package sun.net.www.protocol.x;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * x handler 测试示例
 *
 * **/
public class HandlerTest {
    public static void main(String[] args) throws IOException {
//        URL url = new URL("x:///META-INF/default.properties");//类似于classpath://META-INF/default.properties
//        InputStream inputStream = url.openStream();
//        System.out.println(StreamUtils.copyToString(inputStream, Charset.forName("utf-8")));


        URL url = new URL("springx:///META-INF/production.properties");//类似于classpath://META-INF/default.properties
        InputStream inputStream = url.openStream();
        System.out.println(StreamUtils.copyToString(inputStream, Charset.forName("utf-8")));
    }
}
