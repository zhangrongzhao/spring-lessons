package org.geekbang.thinking.in.spring.conversion;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Properties;

/**
 *  String -> Properties {@link PropertyEditor}
 * */
public class StringToPropertiesPropertyEditor extends PropertyEditorSupport implements PropertyEditor {

    //1.实现setAsText方法
    @Override
    public void setAsText(String text) {
        //super.setAsText(text);
        //2.将String 转换成 Properties 类型
        Properties properties = new Properties();

        try {
            properties.load(new StringReader(text));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //3.临时存储Properties对象
        setValue(properties);

        //4.获取Properties临时对象
    }



    @Override
    public String getAsText(){
        Properties properties = (Properties)getValue();
        StringBuilder textBuilder = new StringBuilder();

        for (Map.Entry<Object,Object> entry:properties.entrySet()){
               textBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append(System.getProperty("line.separator"));
        }
        return textBuilder.toString();
    }

}
