package org.geekbang.thinking.in.spring.conversion;

import java.beans.PropertyEditor;

/**
 * {@link PropertyEditor} 示例
 * */
public class PropertyEditorDemo {
    public static void main(String[] args){
        //模拟 Spring framework 操作
        //有一段文本：name=小马哥

        String text = "name = 小马哥";

        PropertyEditor propertyEditor = new StringToPropertiesPropertyEditor();
        //传递String类型的内容
        propertyEditor.setAsText(text);

        System.out.println(propertyEditor.getValue());

        System.out.println(propertyEditor.getAsText());

    }
}
