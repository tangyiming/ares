package com.tangym.exception;

/**
 * @author backtym@live.cn
 */
public interface ErrorCode {
    /**
     * 获取错误码
     *
     * @return String
     */
    String getCode();

    /**
     * 获取错误信息
     *
     * @return String
     */
    String getDescription();
}
