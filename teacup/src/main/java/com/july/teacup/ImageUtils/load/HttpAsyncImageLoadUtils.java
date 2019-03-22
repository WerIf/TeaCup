package com.july.teacup.ImageUtils.load;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.july.teacup.basics.NetWorkCondition;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpAsyncImageLoadUtils {

    private static HttpAsyncImageLoadUtils INSTANCE;

    private HttpAsyncImageLoadUtils(){

    }

    public static  synchronized HttpAsyncImageLoadUtils getInstance(){
       if(INSTANCE==null){
           INSTANCE= new HttpAsyncImageLoadUtils();
       }
       return INSTANCE;

    }


    NetWorkCondition workCondition;

    public void setWorkCondition(NetWorkCondition workCondition) {
        this.workCondition = workCondition;
    }

    public void AsyncImageDownLoad(String loadUrl) {
        BufferedInputStream bf;
        try {
            URL url=new URL(loadUrl);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);

            Log.e("TAG","println result is:"+conn.getResponseCode());
            if(conn.getResponseCode()==200){
                bf=new BufferedInputStream(conn.getInputStream());
                Bitmap bitmap=BitmapFactory.decodeStream(bf);
                bf.close();

                workCondition.onSucceed(bitmap);
                return;
            }


            workCondition.onFailed(conn.getResponseCode());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            workCondition.onError(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            workCondition.onError(e.getMessage());
        }

    }

    public Bitmap SyncImageDownLoad(String loadUrl){
        BufferedInputStream bf;
        try {
            URL url=new URL(loadUrl);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);

            Log.e("TAG","println result is:"+conn.getResponseCode());
            if(conn.getResponseCode()==200){
                bf=new BufferedInputStream(conn.getInputStream());
                Bitmap bitmap=BitmapFactory.decodeStream(bf);
                bf.close();

                return bitmap;
            }

          return null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
