package com.jack.authorizationserver;

import com.gargoylesoftware.htmlunit.HttpMethod;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.util.Map;

@SpringBootTest
public class TT {

    public static String authorization_server = "http://127.0.0.1:53020/";
    public static String resource_server = "http://127.0.0.1:53021/";
    //授权码模式
    @Test
    public void getTokenByCode() throws IOException {
        //标准模式
        //浏览器访问
        //http://127.0.0.1:53020/oauth/authorize?client_id=sq_bi_client&response_type=code&scope=sq_scope&redirect_uri=http://www.baidu.com

        //重定向结果
        //https://www.baidu.com/?code=S4UMvQ

        String[] params = new String[]{
                "client_id", "sq_bi_client",
                "client_secret", "Deservice#*2021",
                "grant_type", "authorization_code",
                "code", "S4UMvQ",
                "redirect_uri", "http://www.baidu.com"
        };
        HttpUtil.send(HttpMethod.POST, authorization_server + "oauth/token", null, null, params);
        /*{
            "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiYmlfd2ViIl0sInVzZXJfbmFtZSI6ImJpIiwic2NvcGUiOlsic3Ffc2NvcGUiXSwiZXhwIjoxNjIyMTc2ODk4LCJhdXRob3JpdGllcyI6WyJwMSIsInAzIl0sImp0aSI6Ijg3NTY2NWFjLTgxZjUtNDlmMy1hMjc2LTQxYjE0OGI4MDBiYSIsImNsaWVudF9pZCI6InNxX2JpX2NsaWVudCJ9.3Xvub8R-ZrmULZjDCexjD7k6hD2QSzt-iYV4w8c1mVU",
            "token_type": "bearer",
            "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiYmlfd2ViIl0sInVzZXJfbmFtZSI6ImJpIiwic2NvcGUiOlsic3Ffc2NvcGUiXSwiYXRpIjoiODc1NjY1YWMtODFmNS00OWYzLWEyNzYtNDFiMTQ4YjgwMGJhIiwiZXhwIjoxNjIyNDI4ODk4LCJhdXRob3JpdGllcyI6WyJwMSIsInAzIl0sImp0aSI6IjU0ZDhmM2FmLTk1OGMtNDQ2NS1iYzVmLWNiZWRiNjgxYzY5YyIsImNsaWVudF9pZCI6InNxX2JpX2NsaWVudCJ9.mnFT5sr7X6Zk9sK63ECR5FlOaDx3HhJ_J9j3f1-yIC0",
            "expires_in": 7199,
            "scope": "sq_scope",
            "jti": "875665ac-81f5-49f3-a276-41b148b800ba"
        }*/
    }

    //简单模式
    @Test
    public void simpleMode() {
        //这个一般给纯前端的项目用,没有后台的
        //浏览器访问
        //http://127.0.0.1:53020/oauth/authorize?client_id=sq_bi_client&response_type=token&scope=sq_scope&redirect_uri=http://www.baidu.com

        //重定向结果
        //https://www.baidu.com/#access_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiYmlfd2ViIl0sInVzZXJfbmFtZSI6ImJpIiwic2NvcGUiOlsic3Ffc2NvcGUiXSwiZXhwIjoxNjIyMTc2ODA1LCJhdXRob3JpdGllcyI6WyJwMSIsInAzIl0sImp0aSI6ImViZDg0MjY0LWJjZjEtNDcwNi1hNjE3LWI4ZTI1M2UzYjkyZSIsImNsaWVudF9pZCI6InNxX2JpX2NsaWVudCJ9.3TkmUsXrCAI85R9_AZNiNVWNSjRPTYylCihrPR6qqjY&token_type=bearer&expires_in=7199&jti=ebd84264-bcf1-4706-a617-b8e253e3b92e
    }

    //获取jwt token,把用户信息加密成token,服务端不存储token
    //密码模式
    @Test
    public void getTokenByPassword() throws IOException {
        String[] params = new String[]{
                "client_id", "sq_bi_client",
                "client_secret", "Deservice#*2021",
                "grant_type", "password",
                "username", "bi",
                "password", "Deservice#*2021"
        };
        HttpUtil.send(HttpMethod.POST, authorization_server + "oauth/token", null, null, params);
        /*{
            "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiYmlfd2ViIl0sInVzZXJfbmFtZSI6ImJpIiwic2NvcGUiOlsic3Ffc2NvcGUiXSwiZXhwIjoxNjIyMTc1OTcwLCJhdXRob3JpdGllcyI6WyJwMSIsInAzIl0sImp0aSI6IjUzMzhlNmQ1LThjMzYtNDJiYS05ZGY5LTI0NzNhNTI1MWZlNyIsImNsaWVudF9pZCI6InNxX2JpX2NsaWVudCJ9.PgU7szNGv7nIfC9FnyDoK6oh4Y7IGIzc-QeCJdKgM3k",
            "token_type": "bearer",
            "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiYmlfd2ViIl0sInVzZXJfbmFtZSI6ImJpIiwic2NvcGUiOlsic3Ffc2NvcGUiXSwiYXRpIjoiNTMzOGU2ZDUtOGMzNi00MmJhLTlkZjktMjQ3M2E1MjUxZmU3IiwiZXhwIjoxNjIyNDI3OTcwLCJhdXRob3JpdGllcyI6WyJwMSIsInAzIl0sImp0aSI6IjU5NGI5MzFjLTAyYTQtNGM2Zi04NzhlLTQ3NDM1NjAxYjljZCIsImNsaWVudF9pZCI6InNxX2JpX2NsaWVudCJ9.pFWWL6nphUfli_yUYsOl5fDYwJIZuqF3zLqcjfw2FGk",
            "expires_in": 7199,
            "scope": "sq_scope",
            "jti": "5338e6d5-8c36-42ba-9df9-2473a5251fe7"
        }*/
    }

    //客户端模式
    @Test
    public void getTokenByClient() throws IOException {
        //直接用客户端id去申请权限
        String[] params = new String[]{
                "client_id", "sq_bi_client",
                "client_secret", "Deservice#*2021",
                "grant_type", "client_credentials"
        };
        HttpUtil.send(HttpMethod.POST, authorization_server + "oauth/token", null, null, params);
        /*{
            "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiYmlfd2ViIl0sInNjb3BlIjpbInNxX3Njb3BlIl0sImV4cCI6MTYyMjE3NjcyMCwianRpIjoiZDM4MTg4MjQtNWE4MC00NDkzLWIyMzktOTkyYmRkMjY2ZmQxIiwiY2xpZW50X2lkIjoic3FfYmlfY2xpZW50In0.LhwAb0dlk9bGQ9EFgc0XazBZ1ySvr_0yXOdEkFEAujg",
            "token_type": "bearer",
            "expires_in": 7199,
            "scope": "sq_scope",
            "jti": "d3818824-5a80-4493-b239-992bdd266fd1"
        }*/
    }

    //验证jwt token
    @Test
    public void checkToken() throws IOException {
        String[] params = new String[]{
                "token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiYmlfd2ViIl0sInVzZXJfbmFtZSI6ImJpIiwic2NvcGUiOlsic3Ffc2NvcGUiXSwiZXhwIjoxNjIyMTc2NTE3LCJhdXRob3JpdGllcyI6WyJwMSIsInAzIl0sImp0aSI6IjM3Yjg1NTU4LWFkMmYtNGU2MS1iYjg0LTBiYmY5NWUwYmFjZCIsImNsaWVudF9pZCI6InNxX2JpX2NsaWVudCJ9.J0TkFiXIkK3Ah4SMzQSQ78YCHla2i1CvwqKoljpCB2Q",
        };
        HttpUtil.send(HttpMethod.POST, authorization_server + "oauth/check_token", null, null, params);
        /*{
            "aud": [
                "bi_web"
            ],
            "user_name": "bi",
            "scope": [
                "sq_scope"
            ],
            "active": true,
            "exp": 1622176517,
            "authorities": [
                "p1",
                "p3"
            ],
            "jti": "37b85558-ad2f-4e61-bb84-0bbf95e0bacd",
            "client_id": "sq_bi_client"
        }*/
    }

    //刷新token
    @Test
    public void refreshToken() throws IOException {
        //直接用客户端id去申请权限
        String[] params = new String[]{
                "client_id", "sq_bi_client",
                "client_secret", "Deservice#*2021",
                "refresh_token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiYmlfd2ViIl0sInVzZXJfbmFtZSI6ImJpIiwic2NvcGUiOlsic3Ffc2NvcGUiXSwiYXRpIjoiNTMzOGU2ZDUtOGMzNi00MmJhLTlkZjktMjQ3M2E1MjUxZmU3IiwiZXhwIjoxNjIyNDI3OTcwLCJhdXRob3JpdGllcyI6WyJwMSIsInAzIl0sImp0aSI6IjU5NGI5MzFjLTAyYTQtNGM2Zi04NzhlLTQ3NDM1NjAxYjljZCIsImNsaWVudF9pZCI6InNxX2JpX2NsaWVudCJ9.pFWWL6nphUfli_yUYsOl5fDYwJIZuqF3zLqcjfw2FGk",
                "grant_type", "refresh_token"
        };
        HttpUtil.send(HttpMethod.POST, authorization_server + "oauth/token", null, null, params);
        /*{
            "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiYmlfd2ViIl0sInVzZXJfbmFtZSI6ImJpIiwic2NvcGUiOlsic3Ffc2NvcGUiXSwiZXhwIjoxNjIyMTc2NTE3LCJhdXRob3JpdGllcyI6WyJwMSIsInAzIl0sImp0aSI6IjM3Yjg1NTU4LWFkMmYtNGU2MS1iYjg0LTBiYmY5NWUwYmFjZCIsImNsaWVudF9pZCI6InNxX2JpX2NsaWVudCJ9.J0TkFiXIkK3Ah4SMzQSQ78YCHla2i1CvwqKoljpCB2Q",
            "token_type": "bearer",
            "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiYmlfd2ViIl0sInVzZXJfbmFtZSI6ImJpIiwic2NvcGUiOlsic3Ffc2NvcGUiXSwiYXRpIjoiMzdiODU1NTgtYWQyZi00ZTYxLWJiODQtMGJiZjk1ZTBiYWNkIiwiZXhwIjoxNjIyNDI3OTcwLCJhdXRob3JpdGllcyI6WyJwMSIsInAzIl0sImp0aSI6IjU5NGI5MzFjLTAyYTQtNGM2Zi04NzhlLTQ3NDM1NjAxYjljZCIsImNsaWVudF9pZCI6InNxX2JpX2NsaWVudCJ9.WyYnONOSDRvv6Bd-zolEJYhe7RPNCAkh45MabjcN8uQ",
            "expires_in": 7199,
            "scope": "sq_scope",
            "jti": "37b85558-ad2f-4e61-bb84-0bbf95e0bacd"
        }*/
    }

    //使用jwt token访问resource
    @Test
    public void getResourceByToken() throws IOException {
        Map<String, String> head = RootUtil.buildMap("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzb3VyY2UxIl0sInVzZXJfbmFtZSI6ImFkbWluIiwic2NvcGUiOlsic2NvcGUxIiwic2NvcGUyIl0sImV4cCI6MTU4MjcxMjk0MSwiYXV0aG9yaXRpZXMiOlsiYWRtaW4iXSwianRpIjoiYzBiMTYzOGUtOThjNS00NTU2LTk0ZmMtYTgyNTE3NDhhYzM5IiwiY2xpZW50X2lkIjoiY2xpZW50MSJ9.l_j9s5XYz3NfrvI-Hky19V-P9vRz9U4Q1Ltkep5Up9Q");
        HttpUtil.send(HttpMethod.POST,resource_server + "/admin",head,null);
    }

    @Test
    public void encodePassword(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("Deservice#*2021"));
    }
}