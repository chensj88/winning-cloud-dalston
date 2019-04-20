package com.winning.devops.boot.guava;

import com.google.common.base.Optional;

import java.util.Set;

/**
 * @author chensj
 * @title Guava Optional使用
 * @project winning-cloud-dalston
 * @package com.winning.devops.boot.guava
 * @date: 2019-04-20 17:53
 */
public class OptionalTest {

    /**
     * Guava用Optional表示可能为null的T类型引用。一个Optional实例可能包含非null的引用
     * （我们称之为引用存在），也可能什么也不包括（称之为引用缺失）。它从不说包含的是null值，
     * 而是用存在或缺失来表示。但Optional从不会包含null值引用。
     * 常用静态方法：
     * Optional.of(v2)          创建指定引用的Optional实例，其内部包含了一个非null的T数据类型实例，若T=null，则立刻报错。
     * Optional.absent()        获得一个Optional对象，其内部包含了空值
     * Optional.ofNullable(v1)  创建指定引用的Optional实例，若引用为null则表示缺失
     * Optional.fromNullable(T) 将一个T的实例转换为Optional对象，T的实例可以不为空，也可以为空[Optional.fromNullable(null)，和Optional.absent()等价。
     * Optional.isPresent()     Optional包含非null的引用（引用存在），返回true
     * Optional.get()           返回Optional所包含的引用，若引用缺失，则抛出java.lang.IllegalStateException
     */
    public static void main(String[] args) {
        testOptional();
        testMethodReturn();

    }

    public static Integer add(Optional<Integer> a, Optional<Integer> b) {
        System.out.println("a is present: " + a.isPresent());
        System.out.println("b is present: " + b.isPresent());
        // isPresent():如果Optional包含非null的引用（引用存在），返回true
        if (!a.isPresent()) {
            a = Optional.of(0);
        }
        // 返回Optional所包含的引用,若引用缺失,返回指定的值
        if (!b.isPresent()) {
            b = Optional.of(0);
        }

        return a.get() + b.get();
    }

    /**
     * 常用静态方法：
     *    Optional.of(v2)          创建指定引用的Optional实例，其内部包含了一个非null的T数据类型实例，若T=null，则立刻报错。
     *    Optional.absent()        获得一个Optional对象，其内部包含了空值
     *    Optional.ofNullable(v1)  创建指定引用的Optional实例，若引用为null则表示缺失
     *    Optional.fromNullable(T) 将一个T的实例转换为Optional对象，T的实例可以不为空，也可以为空[Optional.fromNullable(null)，和Optional.absent()等价。
     *    Optional.isPresent()     Optional包含非null的引用（引用存在），返回true
     *    Optional.get()           返回Optional所包含的引用，若引用缺失，
     */
    public static void testOptional() {

        Optional<Integer> possible = Optional.of(6);
        Optional<Integer> absentOpt = Optional.absent();
        Optional<Integer> NullableOpt = Optional.fromNullable(null);
        Optional<Integer> NoNullableOpt = Optional.fromNullable(10);
        if (possible.isPresent()) {
            System.out.println("possible isPresent:" + possible.isPresent());
            System.out.println("possible value:" + possible.get());
        }
        if (absentOpt.isPresent()) {
            System.out.println("absentOpt isPresent:" + absentOpt.isPresent());
        }
        if (NullableOpt.isPresent()) {
            System.out.println("fromNullableOpt isPresent:" + NullableOpt.isPresent());
        }
        if (NoNullableOpt.isPresent()) {
            System.out.println("NoNullableOpt isPresent:" + NoNullableOpt.isPresent());
        }
    }
    /**
     * 实例方法
     *  boolean isPresent()     如果Optional包含的T实例不为null，则返回true；
     *                          若T实例为null，返回false
     *  T get()                 返回Optional包含的T实例，该T实例必须不为空；
     *                          否则，对包含null的Optional实例调用get()会抛出一个IllegalStateException异常
     *  T or(T)                 若Optional实例中包含了传入的T的相同实例，返回Optional包含的该T实例，
     *                          否则返回输入的T实例作为默认值
     *  T orNull()              返回Optional实例中包含的非空T实例，如果Optional中包含的是空值，返回null，
     *                          逆操作是fromNullable()
     *  Set asSet()             返回一个不可修改的Set，该Set中包含Optional实例中包含的所有非空存在的T实例，且在该Set中，每个T实例都是单态，
     *                          如果Optional中没有非空存在的T实例，返回的将是一个空的不可修改的Set。
     */
    public static void testMethodReturn() {
        Optional<Long> value = method();
        if(value.isPresent()==true){
            System.out.println("获得返回值: " + value.get());
        }else{

            System.out.println("获得返回值: " + value.or(-12L));
        }

        System.out.println("获得返回值 orNull: " + value.orNull());

        Optional<Long> valueNoNull = methodNoNull();
        if(valueNoNull.isPresent()==true){
            Set<Long> set=valueNoNull.asSet();
            System.out.println("获得返回值 set 的 size : " + set.size());
            System.out.println("获得返回值: " + valueNoNull.get());
        }else{
            System.out.println("获得返回值: " + valueNoNull.or(-12L));
        }

        System.out.println("获得返回值 orNull: " + valueNoNull.orNull());
    }

    private static Optional<Long> method() {
        return Optional.fromNullable(null);
    }
    private static Optional<Long> methodNoNull() {
        return Optional.fromNullable(15L);
    }
}
