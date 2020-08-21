package org.geekbang.thinking.in.spring.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.*;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

/**
 * 动态资源的MessageSource示例{@link MessageSource}
 * <p>
 *  自动刷新MessageSource的实现步骤
 * </p>
 * 1.定位资源位置(Properties 文件)
 * 2.初始化Properties对象
 * 3.实现AbstractMessageSource.resolveCode方法
 * 4.监听资源文件（Properties 文件），使用技术（java NIO 2 WatchService）
 * 5.使用线程池处理文件变化
 * 6.重新装载 Properties 对象
 * @see WatchService
 * @see MessageSource
 * @see AbstractMessageSource
 * @see ResourceBundle
 * */
public class DynamicResourceMessageSource extends AbstractMessageSource implements ResourceLoaderAware {

    private static final String RESOURCE_FILE_NAME = "msg.properties";
    private static final String RESOURCE_PATH = "classpath:/META-INF/" + RESOURCE_FILE_NAME;
    private static final String ENCODING = "utf-8";
    private final Resource messagePropertiesResource;
    private Properties messageProperties;
    private ResourceLoader resourceLoader;
    private ExecutorService executorService;

    public DynamicResourceMessageSource(){
        this.executorService = Executors.newSingleThreadExecutor();
        this.messagePropertiesResource = this.getMessagePropertiesResource();
        this.messageProperties = loadMessageProperties();
        //监听资源文件（Properties 文件），使用技术（java NIO 2 WatchService）
        onMessagePropertiesChanged();
    }

    private void onMessagePropertiesChanged() {
        if(this.messagePropertiesResource.isFile()){//判断是否为文件
            //获取文件系统中的文件
            try {
                File messagePropertiesFile = this.messagePropertiesResource.getFile();
                //文件监听
                Path messagePropertiesFilePath = messagePropertiesFile.toPath();
                //获取文件系统
                FileSystem fileSystem = FileSystems.getDefault();
                //新建WatchService
                WatchService watchService = fileSystem.newWatchService();
                //获取资源文件的目录
                Path dirPath = messagePropertiesFilePath.getParent();
                //注册WatchService 到 dirPath,并且关心修改时间
                dirPath.register(watchService,ENTRY_MODIFY);
                //异步处理资源文件变化（异步）
                processMessagePropertiesChanged(watchService);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 处理资源文件变化（异步）
     * **/
    public void processMessagePropertiesChanged(WatchService watchService) {
        this.executorService.submit(() -> {
            while (true) {
                WatchKey watchKey = watchService.take();
                try {
                    //watchKey是否有效
                    if (watchKey.isValid()) {
                        for (WatchEvent event : watchKey.pollEvents()) {
                            Watchable watchable = watchKey.watchable();
                            //目录路径
                            Path dirPath = (Path) watchable;
                            //时间所关联的对象即注册目录的子文件（或者子文件夹）
                            //事件发生源是相对路径
                            Path fileRelativePath = (Path) event.context();
                            if(RESOURCE_FILE_NAME.equals(fileRelativePath.getFileName().toString())){
                                //处理为绝对路径
                                Path filePath = dirPath.resolve(fileRelativePath);
                                File file = filePath.toFile();
                                Properties properties = loadMessageProperties(new FileReader(file));
                                synchronized(messageProperties){
                                    this.messageProperties.clear();
                                    this.messageProperties.putAll(properties);
                                }
                            }
                        }
                    }
                } finally {
                    if(watchKey!=null){
                        watchKey.reset();//重置watchKey
                    }
                }
            }
        });
    }

    private Properties loadMessageProperties() {
        EncodedResource encodedResource = new EncodedResource(this.messagePropertiesResource,ENCODING);
        try {
            return loadMessageProperties(encodedResource.getReader());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Properties loadMessageProperties(Reader reader){
        Properties properties = new Properties();
        try{
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return properties;
    }

    private Resource getMessagePropertiesResource(){
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(RESOURCE_PATH);
        return resource;
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String messageFormatPattern = messageProperties.getProperty(code);
        if(StringUtils.hasText(messageFormatPattern)){
            MessageFormat messageFormat = new MessageFormat(messageFormatPattern,locale);
            return messageFormat;
        }
        return null;
    }

    public ResourceLoader getResourceLoader(){
        return this.resourceLoader==null?new DefaultResourceLoader():this.resourceLoader;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public static void main(String[] args) throws InterruptedException {
        DynamicResourceMessageSource messageSource = new DynamicResourceMessageSource();
        for(int i=0;i<1000;i++){
            String message = messageSource.getMessage("name",new Object[]{}, Locale.getDefault());
            System.out.println(message);
            Thread.sleep(1000);
        }
        //Thread.sleep(1000*100);
    }
}
