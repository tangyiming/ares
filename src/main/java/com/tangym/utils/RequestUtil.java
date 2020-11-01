package com.tangym.utils;

import com.tangym.constant.Const;
import com.tangym.dataparser.EnvYamlParser;
import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.config.EncoderConfig.encoderConfig;

/**
 * @author backtym@live.cn
 */
public class RequestUtil {
    /**
     * 多项目、多环境
     * 设置 RestAssured 默认请求站点域名或IP
     * 在env.properties中配置
     */
    public void setBaseURI(String project, String env, String propName) {
        RestAssured.baseURI = EnvYamlParser.getProperty(project, env, propName);
    }

    /**
     * 设置 RestAssured 默认请求站点域名或IP
     * 在env.properties中配置
     */
    public void setBaseURI(String propName) {
        RestAssured.baseURI = EnvYamlParser.getProperty(propName);
    }

    /**
     * 设置 RestAssured 默认请求编码
     */
    public void setEncode(String charset) {
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().defaultContentCharset(charset));
    }

    /**
     * GET 请求
     */
    public Response httpGet(String url) {
        return get(url);
    }

    public Response httpGet(String url, Headers headers) {
        return given().headers(headers).get(url);
    }

    public Response httpGet(String url, Map<String, ?> params) {
        return given().params(params).get(url);
    }

    public Response httpGet(String url, Cookies cookies) {
        return given().cookies(cookies).get(url);
    }

    public Response httpGet(String url, Cookies cookies, Map<String, ?> params) {
        return given().cookies(cookies).params(params).get(url);
    }

    public Response httpGet(String url, Headers headers, Cookies cookies, Map<String, ?> params) {
        return given().headers(headers).cookies(cookies).params(params).get(url);
    }

    /**
     * POST 请求
     * Body -  form-data
     */
    public Response httpPost(String url) {
        return post(url);
    }

    public Response httpPost(String url, Map<String, ?> params) {
        return given().params(params).post(url);
    }

    public Response httpPost(String url, Headers headers) {
        return given().headers(headers).post(url);
    }

    public Response httpPost(String url, Headers headers, Cookies cookies) {
        return given().headers(headers).cookies(cookies).post(url);
    }

    public Response httpPost(String url, Headers headers, Map<String, ?> params) {
        return given().headers(headers).params(params).post(url);
    }

    public Response httpPost(String url, Cookies cookies) {
        return given().cookies(cookies).post(url);
    }

    public Response httpPost(String url, Cookies cookies, Map<String, ?> params) {
        return given().cookies(cookies).params(params).post(url);
    }

    public Response httpPost(String url, Headers headers, Cookies cookies, Map<String, ?> params) {
        return given().headers(headers).cookies(cookies).params(params).post(url);
    }

    /**
     * POST 请求 & query params
     */
    public Response httpPostQuery(String url, Headers headers, Cookies cookies, Map<String, ?> queryParams) {
        return given().headers(headers).cookies(cookies).queryParams(queryParams).post(url);
    }

    public Response httpPostQuery(String url, Headers headers, Map<String, ?> queryParams) {
        return given().headers(headers).queryParams(queryParams).post(url);
    }

    public Response httpPostQuery(String url, Cookies cookies, Map<String, ?> queryParams) {
        return given().cookies(cookies).queryParams(queryParams).post(url);
    }

    public Response httpPostQuery(String url, Map<String, ?> queryParams) {
        return given().queryParams(queryParams).post(url);
    }

    /**
     * POST 请求 & json
     */
    public Response httpPostBody(String url, Cookies cookies, Object body) {
        return given().contentType(Const.APPLICATION_JSON).cookies(cookies).body(body).post(url);
    }

    public Response httpPostBody(String url, Headers headers, Object body) {
        return given().contentType(Const.APPLICATION_JSON).headers(headers).body(body).post(url);
    }

    public Response httpPostBody(String url, Headers headers, Cookies cookies, Object body) {
        return given().contentType(Const.APPLICATION_JSON).headers(headers).cookies(cookies).body(body)
                .post(url);
    }

    public Response httpPostBody(String url, Object body) {
        return given().contentType(Const.APPLICATION_JSON).body(body).post(url);
    }

    /**
     * POST 请求 & file
     */
    public Response httpPostFile(String url, String controlName, File file) {
        return given().multiPart(controlName, file).post(url);
    }

    public Response httpPostFile(String url, Headers headers, String controlName, File file) {
        return given().headers(headers).multiPart(controlName, file).post(url);
    }

    public Response httpPostFile(String url, Cookies cookies, String controlName, File file) {
        return given().cookies(cookies).multiPart(controlName, file).post(url);
    }

    public Response httpPostFile(String url, Headers headers, Cookies cookies, String controlName, File file) {
        return given().headers(headers).cookies(cookies).multiPart(controlName, file).post(url);
    }

    public Response httpPostHCQueryFormFile(String url, Headers headers, Cookies cookies, Map<String, ?> queryParams,
                                            Map<String, ?> formParams, String controlName, File file) {
        return given().headers(headers).cookies(cookies).queryParams(queryParams).formParams(formParams)
                .multiPart(controlName, file).post(url);
    }

    public Response httpPostHCQueryFile(String url, Headers headers, Cookies cookies, Map<String, ?> queryParams,
                                        String controlName, File file) {
        return given().headers(headers).cookies(cookies).queryParams(queryParams)
                .multiPart(controlName, file).post(url);
    }

    public Response httpPostHCFormFile(String url, Headers headers, Cookies cookies, Map<String, ?> formParams,
                                       String controlName, File file) {
        return given().headers(headers).cookies(cookies).formParams(formParams)
                .multiPart(controlName, file).post(url);
    }

    public Response httpPostHQueryFormFile(String url, Headers headers, Map<String, ?> queryParams,
                                           Map<String, ?> formParams, String controlName, File file) {
        return given().headers(headers).queryParams(queryParams).formParams(formParams)
                .multiPart(controlName, file).post(url);
    }

    public Response httpPostHQueryFile(String url, Headers headers, Map<String, ?> queryParams, String controlName,
                                       File file) {
        return given().headers(headers).queryParams(queryParams).multiPart(controlName, file).post(url);
    }

    public Response httpPostHFormFile(String url, Headers headers, Map<String, ?> formParams, String controlName,
                                      File file) {
        return given().headers(headers).formParams(formParams).multiPart(controlName, file).post(url);
    }

    public Response httpPostCQueryFormFile(String url, Cookies cookies, Map<String, ?> queryParams,
                                           Map<String, ?> formParams, String controlName, File file) {
        return given().cookies(cookies).queryParams(queryParams).formParams(formParams)
                .multiPart(controlName, file).post(url);
    }

    public Response httpPostCQueryFile(String url, Cookies cookies, Map<String, ?> queryParams, String controlName,
                                       File file) {
        return given().cookies(cookies).queryParams(queryParams).multiPart(controlName, file).post(url);
    }

    public Response httpPostCFormFile(String url, Cookies cookies, Map<String, ?> formParams, String controlName,
                                      File file) {
        return given().cookies(cookies).formParams(formParams).multiPart(controlName, file).post(url);
    }

    public Response httpPostQueryFormFile(String url, Map<String, ?> queryParams, Map<String, ?> formParams,
                                          String controlName, File file) {
        return given().queryParams(queryParams).formParams(formParams).multiPart(controlName, file)
                .post(url);
    }

    public Response httpPostQueryFile(String url, Map<String, ?> queryParams, String controlName, File file) {
        return given().queryParams(queryParams).multiPart(controlName, file).post(url);
    }

    public Response httpPostFormFile(String url, Map<String, ?> formParams, String controlName, File file) {
        return given().formParams(formParams).multiPart(controlName, file).post(url);
    }

    public Response httpPostBodyFile(String url, Headers headers, Object body, String controlName, File file) {
        return given().headers(headers).body(body).multiPart(controlName, file).post(url);
    }

    /**
     * POST 请求 & form
     */
    public Response httpPostHCQueryForm(String url, Headers headers, Cookies cookies, Map<String, ?> queryParams,
                                        Map<String, ?> formParams) {
        return given().headers(headers).cookies(cookies).queryParams(queryParams).formParams(formParams)
                .post(url);
    }

    public Response httpPostHQueryForm(String url, Headers headers, Map<String, ?> queryParams,
                                       Map<String, ?> formParams) {
        return given().headers(headers).queryParams(queryParams).formParams(formParams).post(url);
    }

    public Response httpPostCQueryForm(String url, Cookies cookies, Map<String, ?> queryParams,
                                       Map<String, ?> formParams) {
        return given().cookies(cookies).queryParams(queryParams).formParams(formParams).post(url);
    }

    public Response httpPostQueryForm(String url, Map<String, ?> queryParams, Map<String, ?> formParams) {
        return given().queryParams(queryParams).formParams(formParams).post(url);
    }

    /**
     * PUT 请求
     */

    public Response httpPutBody(String url, Cookies cookies, Object body) {
        return given().contentType(Const.APPLICATION_JSON).cookies(cookies).body(body).put(url);
    }

    public Response httpPutBody(String url, Headers headers, Object body) {
        return given().contentType(Const.APPLICATION_JSON).headers(headers).body(body).put(url);
    }

    public Response httpPutBody(String url, Object body) {
        return given().contentType(Const.APPLICATION_JSON).body(body).put(url);
    }

    /**
     * DEL 请求
     */
    public Response httpDel(String url) {
        return given().delete(url);
    }

    public Response httpDel(String url, Cookies cookies) {
        return given().cookies(cookies).delete(url);
    }

    public Response httpDel(String url, Headers headers) {
        return given().headers(headers).delete(url);
    }

}
