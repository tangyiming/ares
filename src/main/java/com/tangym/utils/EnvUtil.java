package com.tangym.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取执行mvn指令中 -D 指定的环境变量 默认测试环境 beta
 *
 * @author backtym@live.cn
 */
public class EnvUtil {
    private static final Logger logger = LoggerFactory.getLogger(EnvUtil.class);

    private EnvUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String getEnv() {
        String env = System.getProperty("environment");
        logger.info("env:{}", env);
        if (env == null) {
            return "beta";
        }
        return env;
    }

}
