package com.winning.devops.boot.base.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chensj
 * @title 前台String转java.util.Date
 * @email chensj@winning.com.cn
 * @package com.winning.mbk.base.convert
 * @date: 2018-11-12 13:16
 */
public class StringToDateConverter implements Converter<String, Date> {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String SHORT_DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATE_FORMAT_2 = "yyyy/MM/dd HH:mm:ss";
    private static final String SHORT_DATE_FORMAT_2 = "yyyy/MM/dd";

    private static final String SPLIT_FLAG_1 = "-";
    private static final String SPLIT_FLAG_2 = ":";
    private static final String SPLIT_FLAG_3 = "/";
    private static final String SPLIT_FLAG_4 = ":";
    @Override
    public Date convert(String source) {
        if(StringUtils.isEmpty(source)){
            return  null;
        }
        source = source.trim();
        try {
            SimpleDateFormat formatter;
            if (source.contains(SPLIT_FLAG_1)) {
                if (source.contains(SPLIT_FLAG_2)) {
                    formatter = new SimpleDateFormat(DATE_FORMAT);
                } else {
                    formatter = new SimpleDateFormat(SHORT_DATE_FORMAT);
                }
                Date dtDate = formatter.parse(source);
                return dtDate;
            } else if (source.contains(SPLIT_FLAG_3)) {
                if (source.contains(SPLIT_FLAG_4)) {
                    formatter = new SimpleDateFormat(DATE_FORMAT_2);
                } else {
                    formatter = new SimpleDateFormat(SHORT_DATE_FORMAT_2);
                }
                Date dtDate = formatter.parse(source);
                return dtDate;
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("parser %s to Date fail", source));
        }

        throw new RuntimeException(String.format("parser %s to Date fail", source));

    }
}
