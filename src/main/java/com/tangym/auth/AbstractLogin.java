package com.tangym.auth;

import io.restassured.http.Cookies;
import io.restassured.http.Headers;

/**
 * 登录认证抽象类，获取登录返回的headers或cookies或token
 *
 * @author backtym@live.cn
 */
public abstract class AbstractLogin {
    /**
     * 返回登录请求headers
     * response.getHeaders()
     *
     * @param url
     * @param account
     * @param password
     * @return
     */
    public abstract Headers getLoginHeaders(String url, String account, String password);

    /**
     * 返回登录请求cookies
     * response.getDetailedCookies();
     *
     * @param url
     * @param account
     * @param password
     * @return
     */
    public abstract Cookies getLoginCookies(String url, String account, String password);

    /**
     * 返回登录token
     * 例如 response.getHeaders().getValue("x-auth-token");
     *
     * @param url
     * @param account
     * @param password
     * @return
     */
    public abstract String getLoginToken(String url, String account, String password);
}
