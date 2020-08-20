package org.geekbang.thinking.in.spring.i18n;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * {@link MessageFormat} 示例
 * @see MessageFormat
 * */
public class MessageFormatDemo {
    public static void main(String[] args) {
        int planet = 7;
        String event = "a disturbance in the force";
        String messagePattern = "At {1,time,long} on {1,date,full},there was {2} on planet {0,number,integer}.";
        MessageFormat messageFormat = new MessageFormat(messagePattern);
        String result = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result);

        //重置MessageFormatPattern
        //applyPattern
        messagePattern="this is  a  text:{0},{1},{2}";
        messageFormat.applyPattern(messagePattern);
        result = messageFormat.format(new Object[]{"hello world","6666"});
        System.out.println(result);

        //重置locale
        messagePattern = "At {1,time,long} on {1,date,full},there was {2} on planet {0,number,integer}.";
        messageFormat.setLocale(Locale.ENGLISH);
        messageFormat.applyPattern(messagePattern);
        result = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result);

        //重置format
        //根据参数索引来设置 Pattern
        messagePattern = "At {1,time,long} on {1,date,full},there was {2} on planet {0,number,integer}.";
        messageFormat.setLocale(Locale.ENGLISH);
        messageFormat.applyPattern(messagePattern);
        messageFormat.setFormat(1,new SimpleDateFormat("YYYY-MM-dd HH:mm:ss"));
        result = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result);
    }
}
