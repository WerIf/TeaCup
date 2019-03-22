package com.july.teacup.network;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class HttpRequest {


    /**
     *          GetHttpUrlConnection
     * @param requestUrl    请求路径
     * @return              请求结果
     */
    public static String GetHttpUrlConnection(String requestUrl){
        BufferedReader bf=null;
        try {
            URL url=new URL(requestUrl);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            //设置连接超时
            conn.setConnectTimeout(5000);
            //设置请求方式
            conn.setRequestMethod("GET");
            //获取请求返回代码
            int responseCode=conn.getResponseCode();

            //请求成功
            if(responseCode==200){
                InputStream input=conn.getInputStream();
                bf=new BufferedReader(new InputStreamReader(input));
                return appendStreamData(bf);
            }

            //请求失败
            return null;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *      PostHttpUrlConnection
     *      application/json
     * @param params
     * @param requestUrl
     * @return
     */
    public static String JPostHttpUrlConnection(JSONObject params,String requestUrl){
       HttpURLConnection conn;
       BufferedReader bf;
        OutputStream op;
        try {
            URL url=new URL(requestUrl);
            conn= (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type","application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            op=conn.getOutputStream();

            op.write(params.toString().getBytes());
            int responseCode=conn.getResponseCode();
            if(responseCode==200){
                bf=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                return appendStreamData(bf);
            }

            return responseCode+"";
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     *      UrlConnection
     *      application/x-www-form-urlencoded
     * @param params        请求参数
     * @param requestUrl    请求网络地址
     * @return
     */
    public static String XPostUrlConnection(Map<String,String> params,String requestUrl){
        StringBuilder sb=new StringBuilder();
        FutureTask<String> task=new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                DataOutputStream out=null;
                BufferedReader br=null;
                URLConnection conn;

                try{
                    URL postUrl=new URL(requestUrl);
                    //创建连接
                    conn= postUrl.openConnection();
                    //允许写入数据
                    conn.setDoInput(true);
                    //允许输入数据
                    conn.setDoOutput(true);
                    //设置
                    //获取输出流
                    out=new DataOutputStream(conn.getOutputStream());
                    StringBuilder builder=new StringBuilder();
                    //拼接请求参数
                    for (String key: params.keySet()) {
                        builder.append(key+"="+URLEncoder.encode(params.get(key),"UTF-8")+"&");
                    }

                    //写入数据
                    out.writeBytes(builder.toString());
                    out.flush();
                    out.close();
                    br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line;
                    while ((line=br.readLine())!=null){
                        sb.append(line);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if(br!=null){
                        br.close();
                    }

                    if(out!=null){
                        out.close();
                    }
                }
                return sb.toString();
            }
        });

        String response=null;

        new Thread(task).start();

        try {
            response=task.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return response;
    }



    /**
     * @param bufferedReader 传入字符缓冲流
     * @return                返回拼接字符串结果
     * @throws IOException
     */
    private static String appendStreamData(BufferedReader bufferedReader) throws IOException {
        String responseData=null;
        StringBuilder sb=new StringBuilder();
        while ((responseData=bufferedReader.readLine())!=null){
            sb.append(responseData);
        }
        return sb.toString();
    }
}
