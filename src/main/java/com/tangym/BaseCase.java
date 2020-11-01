package com.tangym;

import com.tangym.annotations.Api;
import com.tangym.constant.Const;
import com.tangym.dataparser.ExcelDataProcessor;
import com.tangym.exception.BizErrorCodeEnum;
import com.tangym.exception.BizException;
import com.tangym.utils.RequestUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * 测试脚本基类，包含通用方法与实例初始化
 *
 * @author backtym@live.cn
 */
public class BaseCase {
    protected RequestUtil ru = new RequestUtil();
//    String project;
//    String env;

    /**
     * 在测试类执行前执行，进行初始化设置工作
     */
    @BeforeClass
    public void beforeClass() {
        // 多项目多环境时的方式
//        String className = this.getClass().getName();
//        project = className.split("\\.")[0];
//        env = EnvUtil.getEnv();
//        ru.setBaseURI("","","baseURI");
        ru.setBaseURI("baseURI");
        ru.setEncode("UTF-8");

    }

    @DataProvider(name = Const.PROVIDER_DATA, parallel = true)
    public Object[][] providerTestData(Method method) {
        // excel文件名 == 注解 @Api 中name的值
        String excelFileName = null;
        // sheet表名 == 测试方法名
        String sheetName = method.getName();

        Object[][] params;
        Class<?> cls = method.getDeclaringClass();
        if (cls.isAnnotationPresent(Api.class)) {
            excelFileName = cls.getAnnotation(Api.class).name();
        }
        params = getParamsFromExcel(excelFileName, sheetName);
        return params;
    }

    private Object[][] getParamsFromExcel(String excelFileName, String sheetName){
        Object[][] result;
        ExcelDataProcessor excelDataProcessor = new ExcelDataProcessor(excelFileName, sheetName);
        List<Map<String, String>> list;
        try {
//            list = excelDataProcessor.readExcelWithTitle(project, env);
            list = excelDataProcessor.readExcelWithTitle("", "");
        }catch (Exception e){
            e.printStackTrace();
            throw new BizException(BizErrorCodeEnum.FILE_IS_NULL);
        }
        Assert.assertNotNull(list, excelFileName + "中的" + sheetName + "未发现可以使用的数据\n");

        int length = list.size();
        result = new Object[length][1];
        for (int i = 0; i < list.size(); i++) {
            result[i][0] = list.get(i);
        }
        return result;
    }
}
