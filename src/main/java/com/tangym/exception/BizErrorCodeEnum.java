package com.tangym.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * @author backtym@live.cn
 */

public enum BizErrorCodeEnum implements ErrorCode {
    //未指明的异常
    UNSPECIFIED("500", "网络异常，请稍后再试"),
    NO_SERVICE("404", "网络异常, 服务器熔断"),

    // 通用异常
    REQUEST_ERROR("400", "入参异常,请检查入参后再次调用"),
    PAGE_NUM_IS_NULL("4001", "页码不能为空"),
    PAGE_SIZE_IS_NULL("4002", "页数不能为空"),
    ID_IS_NULL("4003", "ID不能为空"),
    SEARCH_IS_NULL("4004", "搜索条件不能为空"),

    // 读文件相关
    FILE_IS_NULL("4005", "文件不能为空"),
    ENV_PROPERTIES_NOT_EXIST("4006", "env.properties配置文件不存在"),
    ENV_YAML_NOT_EXIST("4007", "env.yml配置文件不存在");

    /**
     * 错误码
     */
    private final String code;

    /**
     * 描述
     */
    private final String description;

    /**
     * @param code        错误码
     * @param description 描述
     */
    BizErrorCodeEnum(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据编码查询枚举。
     *
     * @param code 编码。
     * @return 枚举。
     */
    public static BizErrorCodeEnum getByCode(String code) {
        for (BizErrorCodeEnum value : BizErrorCodeEnum.values()) {
            if (StringUtils.equals(code, value.getCode())) {
                return value;
            }
        }
        return UNSPECIFIED;
    }

    /**
     * 枚举是否包含此code
     * @param code 枚举code
     * @return 结果
     */
    public static Boolean contains(String code) {
        for (BizErrorCodeEnum value : BizErrorCodeEnum.values()) {
            if (StringUtils.equals(code, value.getCode())) {
                return true;
            }
        }
        return  false;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }

}
