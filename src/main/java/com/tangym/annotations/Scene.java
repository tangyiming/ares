package com.tangym.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于场景测试用例，获取测试脚本对应的测试数据文件的文件名
 *
 * @author backtym@live.cn
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Scene {
    String name() default "";
}
