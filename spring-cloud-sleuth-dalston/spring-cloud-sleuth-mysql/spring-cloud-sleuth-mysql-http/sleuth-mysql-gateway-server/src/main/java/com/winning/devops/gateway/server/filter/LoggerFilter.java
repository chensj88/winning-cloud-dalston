package com.winning.devops.gateway.server.filter;

import com.netflix.zuul.ZuulFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;

/**
 * @author chensj
 * @title 向链路数据中添加自定义数据
 * @project winning-cloud-dalston
 * @package com.winning.devops.gateway.server
 * @date: 2019-06-06 11:14
 */
@Component
public class LoggerFilter extends ZuulFilter {
    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(LoggerFilter.class);

    @Autowired
    private Tracer tracer;
    /**
     * 拦截类型
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    /**
     * 拦截器顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 900;
    }

    /**
     * 是否拦截
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 拦截逻辑
     * @return
     */
    @Override
    public Object run() {
        tracer.addTag("operate","chensj");
        tracer.addTag("timestamp",System.currentTimeMillis()+"");
        logger.info(tracer.getCurrentSpan().traceIdString());
        return null;
    }
}
