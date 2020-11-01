package com.tangym.dataparser;

import com.tangym.exception.BizErrorCodeEnum;
import com.tangym.exception.BizException;

import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Properties;

/**
 * resources下 env.properties的解析工具
 *
 * @author backtym@live.cn
 */
public class EnvPropertiesParser {
    private static final String FILE = "env.properties";
    private final Properties props = new Properties();

    public Properties getProps() {
        return props;
    }

    EnvPropertiesParser() {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(FILE)));
            props.load(inputStreamReader);
        } catch (Exception e) {
            throw new BizException(BizErrorCodeEnum.ENV_PROPERTIES_NOT_EXIST);
        }
    }

    public static String getProperty(String propName) {
        return new EnvPropertiesParser().getProps().getProperty(propName);
    }

}
