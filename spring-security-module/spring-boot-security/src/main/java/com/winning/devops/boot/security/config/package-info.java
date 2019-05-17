/**
 * @author chensj
 * @title 配置包，主要配置一些Spring security的配置信息
 * @project winning-cloud-dalston
 * @package com.winning.devops.boot.security.config
 * @date: 2019-05-16 13:35
 *
 * {@link com.winning.devops.boot.security.config.BasicSecurityConfig} 基础配置，只包含用户、密码和角色配置，其他均使用Spring Security提供的
 * {@link com.winning.devops.boot.security.config.HttpSecurityConfig} http配置，可以自定义登录、登出、资源权限
 * {@link com.winning.devops.boot.security.config.DatabaseSecurityConfig} 基于数据库的http配置，可以自定义登录、登出、资源权限
 * {@link com.winning.devops.boot.security.config.CommonSercurityConfig} 网上提供的参考文件
 */
package com.winning.devops.boot.security.config;
