package com.tangym.utils;

import com.alibaba.fastjson.JSON;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

/**
 * @author backtym@live.cn
 */
public class JsonUtil {
    private JsonUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 判断字符串是否为 json 格式
     *
     * @param jsonStr json字符串
     * @return 布尔值
     */
    public static boolean isJsonFormat(String jsonStr) {
        try {
            JSON.parseObject(jsonStr);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 验证jsonSchema是否正确
     *
     * @param response
     * @param jsonPath
     * @return
     */
    public static ValidatableResponse matchesJsonSchema(Response response, String jsonPath) {
        return response.then().body(matchesJsonSchemaInClasspath(jsonPath));
    }

}
