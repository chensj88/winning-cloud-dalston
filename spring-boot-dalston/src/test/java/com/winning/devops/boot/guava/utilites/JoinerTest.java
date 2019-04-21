package com.winning.devops.boot.guava.utilites;

import com.google.common.base.Joiner;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * @author chensj
 * @title Guava Joiner Test
 * @project winning-cloud-dalston
 * @package com.winning.devops.boot.guava.utilites
 * @date: 2019-04-21 12:59
 */
public class JoinerTest {

    private final List<String> stringList = Arrays.asList(
            "Google", "Guava", "Java", "Scala", "Kafka"
    );

    private final List<String> stringListWithNullValue = Arrays.asList(
            "Google", "Guava", "Java", "Scala", null
    );

    /**
     * 使用Join的join方法生成字符串
     * {@see Joiner#join}
     */
    @Test
    public void testJoinOnJoin() {
        // 使用# 作为连接符生成字符串
        String result = Joiner.on("#").join(stringList);

        assertThat(result, equalTo("Google#Guava#Java#Scala#Kafka"));
    }

    /**
     * 使用Join的join方法生成字符串
     * 包含null的list
     * {@see Joiner#join}
     */
    @Test(expected = NullPointerException.class)
    public void testJoinOnJoinWithNullValue() {

        String result = Joiner.on("#").join(stringListWithNullValue);

        assertThat(result, equalTo("Google#Guava#Java#Scala#null"));

    }

    /**
     * 使用Join的join方法生成字符串,跳过null
     * 包含null的list
     * {@see Joiner#join}
     */
    @Test()
    public void testJoinOnJoinWithNullValueButSkip() {

        String result =
                Joiner
                        .on("#") // 连接符
                        .skipNulls() // 跳过null值
                        .join(stringListWithNullValue);  // 字符串拼接

        assertThat(result, equalTo("Google#Guava#Java#Scala"));

    }

    /**
     * 使用Join的join方法生成字符串,null则是使用默认值
     * 包含null的list
     * {@see Joiner#join}
     */
    @Test()
    public void testJoin_On_Join_WithNullValue_UseDefaultValue() {

        String result =
                Joiner
                        .on("#") // 连接符
                        .useForNull("default") // null 给默认值
                        .join(stringListWithNullValue); // 字符串拼接

        assertThat(result, equalTo("Google#Guava#Java#Scala#default"));

    }

    /**
     * 使用appendTo，添加到StringBuilder
     */
    @Test()
    public void testJoin_On_Append_To_StringBuilder() {
        StringBuilder builder = new StringBuilder();
        StringBuilder result = Joiner
                .on("#") // 连接符
                .useForNull("default") // null 给默认值
                .appendTo(builder, stringListWithNullValue);// 添加到AppendTo

        // 判断当前返回值与传入的Appendable的实现类是否一样
        assertThat(result, sameInstance(builder));

        assertThat(builder.toString(), equalTo("Google#Guava#Java#Scala#default"));

        assertThat(result.toString(), equalTo("Google#Guava#Java#Scala#default"));

    }


    /**
     * 默认文件路径
     */
    public static final String TARGET_FILE_NAME = "g:\\joiner-test.txt";

    /**
     * 测试输出到文件
     */
    @Test()
    public void testJoin_On_Append_To_WriteFile() {


        try (FileWriter writer = new FileWriter(new File(TARGET_FILE_NAME))) {
            Joiner
                    .on("#") // 连接符
                    .useForNull("default") // null 给默认值
                    .appendTo(writer, stringListWithNullValue);// 添加到AppendTo
            // 判断是否是文件，文件是否存在
            boolean test = Files.isFile().test(new File(TARGET_FILE_NAME));

            assertThat(test, equalTo(true));

        } catch (IOException e) {
            fail("Append to file by use FileWriter is error, message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 使用jdk8提供的streams实现
     */
    @Test()
    public void testJoiningByStream() {

        // filter 方法
        String collect =
                stringListWithNullValue
                        .stream()
                        .filter(
                                item -> item != null && !item.isEmpty()
                        )
                        .collect(Collectors.joining("#"));
        assertThat(collect.toString(), equalTo("Google#Guava#Java#Scala"));


        // 转换默认值
        String s1 = stringListWithNullValue
                .stream().map(this::defaultValue)
                .collect(Collectors.joining("#"));

        System.out.println(s1);

        assertThat(s1,equalTo("Google#Guava#Java#Scala#default"));
        // map 方法
        List<String> list =
                stringList
                        .stream()
                        .map(item -> "Item:" + item)
                        .collect(Collectors.toList());
        for (String s : list) {
            System.out.println(s);
        }

    }

    private String defaultValue(String item){
        return item == null || item.isEmpty() ? "default" : item;
    }
}
