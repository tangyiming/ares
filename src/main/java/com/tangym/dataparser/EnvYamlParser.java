package com.tangym.dataparser;

import com.tangym.exception.BizErrorCodeEnum;
import com.tangym.exception.BizException;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * resources 下 yaml文件的解析类
 *
 * @author backtym@live.cn
 */
public class EnvYamlParser {
    private static final String YAML = "env.yml";
    private static Map<String, Object> properties;
    Yaml yml = new Yaml();

    EnvYamlParser() {
        try {
            Path resourceDirectory = Paths.get("src", "test", "resources", YAML);
            String absolutePath = resourceDirectory.toFile().getAbsolutePath();
            InputStream input = new FileInputStream(absolutePath);
            properties = yml.load(input);
        } catch (Exception e) {
            throw new BizException(BizErrorCodeEnum.ENV_YAML_NOT_EXIST);
        }
    }

    public static String getProperty(String project, String env, String propName) {
        // 取 Project Common Config
        if ("".equals(project) && "".equals(env) && !"".equals(propName)) {
            return (String) new EnvYamlParser().getProps().get(propName);
        }
        // 取 Env Common Config
        if (!"".equals(project) && "".equals(env) && !"".equals(propName)) {
            Map<String, Object> map = (Map<String, Object>) new EnvYamlParser().getProps().get(project);
            return (String) map.get(propName);
        }

        // 取Env Config
        if (!"".equals(project) && !"".equals(env) && !"".equals(propName)) {
            Map<String, Object> map = (Map<String, Object>) new EnvYamlParser().getProps().get(project);
            Map<String, Object> map2 = (Map<String, Object>) map.get(env);
            return (String) map2.get(propName);
        }
        return "";
    }

    public static String getProperty(String propName) {
        // 取 Project Common Config
        if (!"".equals(propName)) {
            return (String) new EnvYamlParser().getProps().get(propName);
        }
        return "";
    }

    public Map<String, Object> getProps() {
        return properties;
    }

}
