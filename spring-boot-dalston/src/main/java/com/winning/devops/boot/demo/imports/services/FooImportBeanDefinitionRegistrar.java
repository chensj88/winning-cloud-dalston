package com.winning.devops.boot.demo.imports.services;

import com.winning.devops.boot.demo.imports.model.Foo;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author chensj
 * @desc
 * @package com.winning.devops.boot.demo.imports.services
 * @date: 2019-04-22 14:44
 */
public class FooImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder builder =
                BeanDefinitionBuilder.genericBeanDefinition(Foo.class);
        BeanDefinition beanDefinition =
                builder.getBeanDefinition();

        registry.registerBeanDefinition("foo",beanDefinition);
    }
}
