package com.winning.devops.boot.demo.imports.main;

import com.winning.devops.boot.demo.imports.model.Foo;
import com.winning.devops.boot.demo.imports.services.FooImportBeanDefinitionRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author chensj
 * @desc
 * @package com.winning.devops.boot.demo.imports.main
 * @date: 2019-04-22 14:46
 */
@SpringBootApplication
@Import({FooImportBeanDefinitionRegistrar.class})
public class ImportDemo implements CommandLineRunner {

    @Autowired
    private Foo foo;

    public static void main(String[] args){
        SpringApplication.run(ImportDemo.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        foo.foo();
    }
}
