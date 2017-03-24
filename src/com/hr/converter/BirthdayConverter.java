package com.hr.converter;


import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用户封装DATE类型的转换器
 */
public class BirthdayConverter implements Converter<String[], Date> {

    @Override
    public Date convert(String[] strings) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strings.length; i++) {
            if (i == strings.length - 1) {
                sb.append(strings[i]);
            } else {
                sb.append(strings[i]);
                sb.append("/");
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            return simpleDateFormat.parse(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
