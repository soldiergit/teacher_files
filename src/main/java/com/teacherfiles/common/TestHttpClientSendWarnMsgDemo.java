package com.teacherfiles.common;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Program: teacher_files
 * @Author: soldier
 * @Email： 583403411@qq.com
 * @Create: 2019-06-09 20:37
 * @Describe：
 **/
public class TestHttpClientSendWarnMsgDemo {

    public static final String SAVE_WARN_MESSAGE_URI = "http://134.175.16.4:8080/teacher_files_war/api/saveWarnMessage.action";

    public static void main(String[] args) throws IOException {

        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
        list.add(new BasicNameValuePair("lal", "TestDemo"));
        list.add(new BasicNameValuePair("peopleNum", "1"));
        list.add(new BasicNameValuePair("positionName", "TestDemo"));
        list.add(new BasicNameValuePair("videoCode", "TestDemo"));
        list.add(new BasicNameValuePair("videoIp", "TestDemo"));
        list.add(new BasicNameValuePair("videoUrl", "TestDemo"));
        list.add(new BasicNameValuePair("msgGrade", "1"));
        list.add(new BasicNameValuePair("msgStatus", "1"));
        list.add(new BasicNameValuePair("msgContent", "TestDemo"));
        list.add(new BasicNameValuePair("areaCode", "TestDemo"));
        saveWarnMessage(list,SAVE_WARN_MESSAGE_URI);
    }



    public static void saveWarnMessage(List<BasicNameValuePair> params, String uri) throws ClientProtocolException, IOException{
        //1、创建HttpClient
        CloseableHttpClient clients = HttpClients.createDefault();
        //2、封装请求参数

        //3、转化参数
        UrlEncodedFormEntity refe = new UrlEncodedFormEntity(params, Consts.UTF_8);

        //4、创建HttpPost
        HttpPost post = new HttpPost(uri);

        //5、设置参数
        post.setEntity(refe);
        //3、执行请求
        CloseableHttpResponse response = clients.execute(post);

        //4获取实体
        HttpEntity entity = response.getEntity();

        //将实体转换成字符串
        System.out.println(EntityUtils.toString(entity));

        response.close();

    }


}
