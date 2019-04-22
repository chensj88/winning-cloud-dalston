/*********************************************************************************************
 * @author chensj
 * @desc  ImportBeanDefinitionRegistrar使用
 * @package com.winning.devops.boot.demo.imports
 * @date: 2019-04-22 14:41
 *
 *  手动注册bean的两种方式：
 *    实现ImportBeanDefinitionRegistrar
 *    实现BeanDefinitionRegistryPostProcessor
 *
 *      ImportBeanDefinitionRegistrar其本质也是通过BeanDefinitionRegistryPostProcessor来实现的。
 *  实现ImportBeanDefinitionRegistrar比较简单，也有多种方式，本包将使用最简单的方式来实现
 *
 ********************************************************************************************/
package com.winning.devops.boot.demo.imports;