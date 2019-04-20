package com.winning.devops.boot.base.utils;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chensj
 * @title P6Spy日志
 * @project mbk
 * @package com.winning.mbk.base.utils
 * @date: 2019-03-29 16:21
 */
public class P6SpyLogger  implements MessageFormattingStrategy {
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    public P6SpyLogger() {
    }

    /**
     *
     * @param connectionId 数据库连接ID
     * @param now 当前时间戳
     * @param elapsed 消耗时间
     * @param category 类型  statement PrepareStatement
     * @param prepared 预加载SQL
     * @param sql 执行SQL
     * @param url  数据库连接URL
     * @return
     */
    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql,String url) {
        return !"".equals(sql.trim())?this.format.format(new Date()) + " | took " + elapsed + "ms | " + category + " | connection " + connectionId + "\n " + sql + ";":"";
    }

}
