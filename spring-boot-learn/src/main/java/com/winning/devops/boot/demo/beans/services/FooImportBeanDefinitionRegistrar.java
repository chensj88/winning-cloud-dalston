package com.winning.devops.boot.demo.beans.services;

import com.winning.devops.boot.demo.beans.model.Foo;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AssignableTypeFilter;

/**
 * @author chensj
 * @desc 注册Foo
 * @package com.winning.devops.boot.demo.imports.services
 * @date: 2019-04-22 14:44
 */
public class FooImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // 直接指定Foo.class
        BeanDefinitionBuilder builder =
                BeanDefinitionBuilder.genericBeanDefinition(Foo.class);
        BeanDefinition beanDefinition =
                builder.getBeanDefinition();
        // 借助spring类ClassPathBeanDefinitionScanner来扫描Bean并注册
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
        // 增加过滤器  可以用特定的注解来进行过滤
        scanner.addIncludeFilter(new AssignableTypeFilter(Foo.class));
        scanner.scan("com.study.demo.domain");
        //这里也可以扫描自定义注解并生成BeanDefinition并注册到Spring上下文中

        registry.registerBeanDefinition("foo",beanDefinition);
    }
}
