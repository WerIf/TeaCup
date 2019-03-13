package com.lily.gitlijar.ImageUtils.load;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.LruCache;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lily.gitlijar.ImageUtils.ImgUtilsType;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageLoadUtils implements ImageLoadInterface {

    /**
     * 因为在的版本中android 后台更加频繁的调用GC机制
     * 所以使用LruCache 用来存储已下载的bitmap
     * LruCache中使用强引用来缓存一定数量的值
     * 每当一个值被访问是，这个值就会被移动到队列的头部
     * 如果当插入数据时发现缓存不够使，就会将队列中访问次数最少的数据删掉
     * maxMemory 为设置的缓存大小
     */
    LruCache<String,Bitmap> bitmapLruCache;
    private ImageLoadUtils(){
        //设置图片总共占有的最大内存
        int maxMemory= (int) (Runtime.getRuntime().maxMemory()/4);
        bitmapLruCache=new LruCache<String,Bitmap>(maxMemory);
    }

    public static ImageLoadUtils getInstance(){
        return ImageUtils.getInstance;
    }

    @Override
    public void ImageLoad(Context mContext, ImageView mImageView, String url) {
        Glide.with(mContext)
                .load(url)
                .into(mImageView);
    }

    @Override
    public void ImageLoad(Context mContext, ImageView mImageView, String url, int mErrorPath,ImgUtilsType loadType) {

        RequestOptions mOptions=new RequestOptions();
        switch (loadType.getValue()){
            case 0:
                mOptions.error(mErrorPath);
                break;
            case 1:
                mOptions.placeholder(mErrorPath);
                break;
        }

        Glide.with(mContext)
                .load(url)
                .apply(mOptions)
                .into(mImageView);
    }

    @Override
    public void ImageLoad(Context mContext, ImageView mImageView, String url, int mErrorPath, int placeHolderPath) {

        RequestOptions mOptions=new RequestOptions()
                .error(mErrorPath)
                .placeholder(placeHolderPath);

        Glide.with(mContext)
                .load(url)
                .apply(mOptions)
                .into(mImageView);
    }


    @Override
    public Bitmap ImageDownLoad(String loadUrl) {
        BufferedInputStream bf;
        try {
            URL url=new URL(loadUrl);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
           if(conn.getResponseCode()==200){
               bf=new BufferedInputStream(conn.getInputStream());
               Bitmap bitmap=BitmapFactory.decodeStream(bf);
               bf.close();
               return bitmap;
           }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void ImageTaskDownLoad(ImageView mImageView){
        ImageLoadTask imageTask = new ImageLoadTask(mImageView);
        imageTask.execute("http://s7.sinaimg.cn/mw690/001m1Utdzy6ZLnVyRxQe6&690");
    }

    /**
     *      单例模式回调ImageUtils
     */
    static class ImageUtils{
        private static ImageLoadUtils getInstance=new ImageLoadUtils();
    }

    /**
     *      通过异步下载图片
     */
    class ImageLoadTask extends AsyncTask<String,Integer,Bitmap> {

        ImageView mImageView;
        public ImageLoadTask(ImageView imageView){
            mImageView=imageView;
        }

        /**
         *  线程执行最开始调用，在UI线程中执行
         *  一般执行一些预操作 比如显示进度条
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         *      在onPreExecute方法调用后立即调用
         *      用来执行一些耗时的后台操作
         * @param strings
         * @return
         */
        @Override
        protected Bitmap doInBackground(String... strings) {
            return haveBitmapFromUrl(strings[0]);
        }

        /**
         *      在后台操作结束后接受返回结果，在UI线程中调用
         * @param bitmap
         */
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            if(bitmap!=null)mImageView.setImageBitmap(bitmap);
        }

        /**
         *      更新进度条，调用publishProgress之后被调用
         * @param values
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        /**
         *      取消异步线程，当UI线程调用AsyncTask的cancel方法是调用，在UI线程中运行
         *      比如这里如果再加载图片的过程中用户不想加载了，点击取消加载图片(加入加载有点缓慢)
         *      这个时候就会触发UI线程的cancel方法从而嗲用此方法，做一些善后的处理
         * @param bitmap
         */
        @Override
        protected void onCancelled(Bitmap bitmap) {
            super.onCancelled(bitmap);
        }
    }

    /**
     *      判断bitmapLruCache 中是否存在bitmap对象
     * @param loadUrl
     * @return
     */
    private Bitmap haveBitmapFromUrl(String loadUrl){

        if(bitmapLruCache.get(loadUrl)==null){
            Bitmap bitmap = ImageDownLoad(loadUrl);
            bitmapLruCache.put(loadUrl,bitmap);
        }

        return bitmapLruCache.get(loadUrl);
    }


}
