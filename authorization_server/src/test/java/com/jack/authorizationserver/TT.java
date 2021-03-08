package com.jack.authorizationserver;

import com.gargoylesoftware.htmlunit.HttpMethod;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
        //http://127.0.0.1:53020/oauth/authorize?client_id=client1&response_type=code&scope=scope1&redirect_uri=http://www.baidu.com

        //重定向结果
        //https://www.baidu.com/?code=Ss754z

        String[] params = new String[]{
                "client_id", "client1",
                "client_secret", "123456",
                "grant_type", "authorization_code",
                "code", "Ss754z",
                "redirect_uri", "http://www.baidu.com"
        };
        HttpUtil.send(HttpMethod.POST, authorization_server + "oauth/token", null, null, params);
        //{"access_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzb3VyY2UxIl0sInVzZXJfbmFtZSI6ImphY2siLCJzY29wZSI6WyJzY29wZTEiXSwiZXhwIjoxNjE0NjQ5NzAwLCJhdXRob3JpdGllcyI6WyJwMSIsInAzIl0sImp0aSI6IjAwZDRiZWRhLTY1YmQtNGE4YS1iNjkyLTNkMDFhZmRkOGNjOSIsImNsaWVudF9pZCI6ImNsaWVudDEifQ.fw9wU0zb3hodVQRhQx0WVjl29xWWYIFCES0oruh24hk",
        // "token_type":"bearer",
        // "refresh_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzb3VyY2UxIl0sInVzZXJfbmFtZSI6ImphY2siLCJzY29wZSI6WyJzY29wZTEiXSwiYXRpIjoiMDBkNGJlZGEtNjViZC00YThhLWI2OTItM2QwMWFmZGQ4Y2M5IiwiZXhwIjoxNjE0NjUwOTAwLCJhdXRob3JpdGllcyI6WyJwMSIsInAzIl0sImp0aSI6IjViNDYwNDZhLTA4MDUtNGJjNS05ZTUxLTljZDgxZTg2MzIwMyIsImNsaWVudF9pZCI6ImNsaWVudDEifQ.BGOaHk03q9BEUsHbDFXrpg53ZroZGFg1VI7EynXc-QU",
        // "expires_in":299,"scope":"scope1","jti":"00d4beda-65bd-4a8a-b692-3d01afdd8cc9"}
    }

    //简单模式
    @Test
    public void simpleMode() {
        //这个一般给纯前端的项目用,没有后台的
        //浏览器访问
        //http://127.0.0.1:53020/oauth/authorize?client_id=client1&response_type=token&scope=scope1&redirect_uri=http://www.baidu.com

        //重定向结果
        //https://www.baidu.com/#access_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzb3VyY2UxIl0sInVzZXJfbmFtZSI6ImFkbWluIiwic2NvcGUiOlsic2NvcGUxIl0sImV4cCI6MTYxNDU5NDMzMywiYXV0aG9yaXRpZXMiOlsiYWRtaW4iXSwianRpIjoiMTdkMDc2ZDYtN2JlNC00ZjU2LTlhMGItZDExOWRkNTMxNmUxIiwiY2xpZW50X2lkIjoiY2xpZW50MSJ9.Fgl17CvXC9yLggPxv16HJkvi--l96FyvI4F-Z9PP65I&token_type=bearer&expires_in=299&jti=17d076d6-7be4-4f56-9a0b-d119dd5316e1
    }

    //获取jwt token,把用户信息加密成token,服务端不存储token
    //密码模式
    @Test
    public void getTokenByPassword() throws IOException {
        String[] params = new String[]{
                "client_id", "client1",
                "client_secret", "123456",
                "grant_type", "password",
                "username", "test",
                "password", "123456"
        };
        HttpUtil.send(HttpMethod.POST, authorization_server + "oauth/token", null, null, params);
        /*
        {
            "access_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzb3VyY2UxIl0sInVzZXJfbmFtZSI6ImFkbWluIiwic2NvcGUiOlsic2NvcGUxIiwic2NvcGUyIl0sImV4cCI6MTU4MjcwMzYxMywiYXV0aG9yaXRpZXMiOlsiYWRtaW4iXSwianRpIjoiYjA5MDFlZDYtOTNjMC00MjhjLTg2MzEtMTBiZWY4ZmJkZTYzIiwiY2xpZW50X2lkIjoiY2xpZW50MSJ9.kogkaxd1x-XkfqIR8avqSL5y6caa0Kk_DFWjepjVO70",
            "token_type":"bearer",
            "refresh_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzb3VyY2UxIl0sInVzZXJfbmFtZSI6ImFkbWluIiwic2NvcGUiOlsic2NvcGUxIiwic2NvcGUyIl0sImF0aSI6ImIwOTAxZWQ2LTkzYzAtNDI4Yy04NjMxLTEwYmVmOGZiZGU2MyIsImV4cCI6MTU4MjcwNDgxMywiYXV0aG9yaXRpZXMiOlsiYWRtaW4iXSwianRpIjoiOThmYTc3NDQtNTU4ZS00MjI0LThmYjEtZThiNGY3ZjNlNGE5IiwiY2xpZW50X2lkIjoiY2xpZW50MSJ9.Ex8rX6eMfXr7_pmC6sftIfIvFExyjx4_lzRYZqWHeII",
            "expires_in":299,
            "scope":"scope1 scope2",
            "jti":"b0901ed6-93c0-428c-8631-10bef8fbde63"
        }*/
    }

    //客户端模式
    @Test
    public void getTokenByClient() throws IOException {
        //直接用客户端id去申请权限
        String[] params = new String[]{
                "client_id", "client1",
                "client_secret", "123456",
                "grant_type", "client_credentials"
        };
        HttpUtil.send(HttpMethod.POST, authorization_server + "oauth/token", null, null, params);
        //{"access_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzb3VyY2UxIl0sInNjb3BlIjpbInNjb3BlMSIsInNjb3BlMiJdLCJleHAiOjE2MTQ2NDk5MzgsImp0aSI6IjJhMTdiNjZjLTc5OGUtNGM1OS1hNjQwLWI1NDdlNjNhMzhlMiIsImNsaWVudF9pZCI6ImNsaWVudDEifQ.tG-uuFqsC9uC-mib3OQOlGDXt_KzkZHiEaBlSOxGFjI",
        // "token_type":"bearer","expires_in":299,"scope":"scope1 scope2","jti":"2a17b66c-798e-4c59-a640-b547e63a38e2"}
    }

    //验证jwt token
    @Test
    public void checkToken() throws IOException {
        String[] params = new String[]{
                "token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzb3VyY2UxIl0sInNjb3BlIjpbInNjb3BlMSJdLCJleHAiOjE2MTQ3NTgxNjIsImp0aSI6Ijc5YzQzMTg2LWVkNmYtNDdkOC04Y2Y2LWY2N2ViYmNmNjBjZSIsImNsaWVudF9pZCI6ImNsaWVudDEifQ.IozZgdCSzYgjJZNDd3vcqMeCoFnEZGDsYrwtSJp2ph0",
        };
        HttpUtil.send(HttpMethod.POST, authorization_server + "oauth/check_token", null, null, params);
        //{"aud":["resource1"],"user_name":"admin","scope":["scope1","scope2"],"active":true,"exp":1582703613,"authorities":["admin"],"jti":"b0901ed6-93c0-428c-8631-10bef8fbde63","client_id":"client1"}
    }

    //使用jwt token访问resource
    @Test
    public void getResourceByToken() throws IOException {
        Map<String, String> head = RootUtil.buildMap("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzb3VyY2UxIl0sInVzZXJfbmFtZSI6ImFkbWluIiwic2NvcGUiOlsic2NvcGUxIiwic2NvcGUyIl0sImV4cCI6MTU4MjcxMjk0MSwiYXV0aG9yaXRpZXMiOlsiYWRtaW4iXSwianRpIjoiYzBiMTYzOGUtOThjNS00NTU2LTk0ZmMtYTgyNTE3NDhhYzM5IiwiY2xpZW50X2lkIjoiY2xpZW50MSJ9.l_j9s5XYz3NfrvI-Hky19V-P9vRz9U4Q1Ltkep5Up9Q");
        HttpUtil.send(HttpMethod.POST,resource_server + "/admin",head,null);
    }
}