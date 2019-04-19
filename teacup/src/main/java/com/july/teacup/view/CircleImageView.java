package com.july.teacup.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;


public class CircleImageView extends AppCompatImageView {

    private int width;
    private int height;
    private int radius;

    private Paint paint;

    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width =measureSpec(widthMeasureSpec);
        height = measureSpec(heightMeasureSpec);

        radius = Math.min(width, height);

        Log.e("TAG","println width width"+width+" scale:"+height+"  "+radius);

        setMeasuredDimension(radius, radius);

    }

    private int measureSpec(int spec){
        int mode=MeasureSpec.getMode(spec);
        int size=MeasureSpec.getSize(spec);

        int result=0;

        if(mode==MeasureSpec.EXACTLY){
            result=size;
        }else{
            result=200;

            if(mode==MeasureSpec.AT_MOST){
                result=Math.min(result,size);
            }
        }

        return result;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        shaderUtils();
        canvas.drawCircle(radius/2F,radius/2F,radius/2F,paint);

    }

    private void shaderUtils() {
        Drawable drawable = getDrawable();
        if (drawable == null) return;
        Bitmap bitmap=backCircleBitmap(drawable);
        BitmapShader shader=new BitmapShader(bitmap,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
        Matrix matrix=new Matrix();
        int min=Math.min(bitmap.getWidth(),bitmap.getHeight());
        float scale=(new Float(radius))/min;

        matrix.setScale(scale,scale);
        shader.setLocalMatrix(matrix);
        paint.setShader(shader);

    }

    private Bitmap backCircleBitmap(Drawable drawable) {
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        int min = Math.min(bitmap.getWidth(), bitmap.getHeight());
        Rect rect = new Rect(0, 0, min, min);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(min / 2, min / 2, min / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;

    }
}
