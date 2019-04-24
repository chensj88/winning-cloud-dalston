package com.winning.devops.ribbon.client.base.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.feign.client.base.listener
 * @date: 2019-04-21 5:09
 */
@Component
public class ApplicationEventListener implements org.springframework.context.ApplicationListener<ApplicationEvent> {
    /**logger*/
    private static final Logger logger = LoggerFactory.getLogger(ApplicationEventListener.class);
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        logger.info(event.toString());
    }
}
