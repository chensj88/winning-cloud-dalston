package com.winning.devops.boot.demo.beans;

import com.winning.devops.boot.demo.beans.model.Foo;
import com.winning.devops.boot.demo.beans.services.FooImportBeanDefinitionRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author chensj
 * @desc
 * @package com.winning.devops.boot.demo.beans
 * @date: 2019-04-22 14:55
 */
@SpringBootApplication
@Import(FooImportBeanDefinitionRegistrar.class)
public class DemoImport  implements CommandLineRunner {

    @Autowired
    private Foo foo;

    public static void main(String[] args){
        SpringApplication.run(DemoImport.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        foo.foo();
    }
}