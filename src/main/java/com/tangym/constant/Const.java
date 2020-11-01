package com.tangym.constant;

/**
 * 常量定义
 *
 * @author backtym@live.cn
 */
public class Const {
    /**
     * 公共的工具类不应该由构造函数，公用的工具类是静态成员的集合，并不意味着要实例化
     * 所以需要声明一个非公共的构造函数
     */
    private Const() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * providerData名
     */
    public static final String PROVIDER_DATA = "providerData";

    /**
     * excel参数名常量，规范部分参数在excel中的命名
     */
    public static final String TEST_NAME = "testName";
    public static final String URL = "url";
    public static final String EXCEPT_RESULT = "expectResult";

    /**
     * content type
     */
    public static final String APPLICATION_JSON= "\"application/json\"";
}
