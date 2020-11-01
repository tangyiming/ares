package com.tangym.dataparser;

import com.alibaba.fastjson.JSON;
import com.tangym.constant.Const;
import com.tangym.utils.JsonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 解析excel内容
 *
 * @author backtym@live.cn
 */
public class ExcelDataParser {
    public final Map<String, Object> requestParams;
    public final Map<String, Object> commonParams;

    public ExcelDataParser() {
        requestParams = new HashMap<>();
        commonParams = new HashMap<>();
    }

    /**
     * 解析出接口请求的参数和其他普通常规参数
     *
     * @param providerParams 读取excel表中的测试数据
     */
    public void parseProviderParams(Map<String, String> providerParams) {
        for (Map.Entry<String, String> entry : providerParams.entrySet()) {
            switch (entry.getKey()) {
                case Const.TEST_NAME:
                case Const.URL:
                case Const.EXCEPT_RESULT:
                    if (entry.getValue().isEmpty()) {
                        commonParams.put(entry.getKey(), "");
                    } else if (JsonUtil.isJsonFormat(entry.getValue())) {
                        commonParams.put(entry.getKey(), JSON.parseObject(entry.getValue()));
                    } else {
                        commonParams.put(entry.getKey(), entry.getValue());
                    }
                    break;
                // 以上符合case值的测试数据存放到commonParams中，剩余存入requestParams中
                default:
                    requestParams.put(entry.getKey(), entry.getValue());
                    break;
            }
        }

    }

}
