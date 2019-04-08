package com.july.teacup.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.support.annotation.Px;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.july.teacup.R;
import com.july.teacup.unittransition.DisplayUtils;

public class WaveView extends View {

    private int waveCount;
    private Paint wavePaint;
    private Paint imgPaint;

    /*漂浮图片*/
    private int flotageImage =R.mipmap.one;
    /*流速*/
    private int waveSpeed = 20;
    /*波峰*/
    private int waveHeight = 100;
    /*一个波长*/
    private int waveLength = 1000;
    /*水颜色*/
    private int waveColor = Color.BLUE;
    /*界面宽度*/
    private int mScreenWidth;
    /*界面高度*/
    private int mScreenHeight;
    /*路径*/
    private Path wavePath;

    private float mOffset;
    private int mCenterY;

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = null;
        try {
            array = context.obtainStyledAttributes(attrs, R.styleable.WaveView);

            waveHeight = array.getInt(R.styleable.WaveView_waveHeight, waveHeight);

            waveLength = array.getInt(R.styleable.WaveView_waveLength, waveLength);

            waveColor = array.getColor(R.styleable.WaveView_waterColor, Color.BLUE);

            waveSpeed = array.getInteger(R.styleable.WaveView_waveSpeed, waveSpeed);

            flotageImage = array.getResourceId(R.styleable.WaveView_flotageImage, flotageImage);
        } finally {
            array.recycle();
        }


        init();
    }

    private void init() {

        wavePaint = new Paint();
        wavePaint.setColor(waveColor);
        wavePaint.setAntiAlias(true);
        wavePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        wavePath = new Path();


        imgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //Shader.TileMode.CLAMP为拉伸图片铺满
        BitmapShader bitmapShader = new BitmapShader(getBitmap(DisplayUtils.dp2px(getContext(),80),flotageImage)
                , Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        imgPaint.setShader(bitmapShader);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(measureSpec(widthMeasureSpec), measureSpec(heightMeasureSpec));
    }

    private int measureSpec(int measureParam) {

        int result;

        int size = MeasureSpec.getSize(measureParam);
        int mode = MeasureSpec.getMode(measureParam);

        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = 200;

            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mScreenHeight = h;
        mScreenWidth = w;
        waveCount = (int) Math.round(mScreenWidth / waveLength + 1.5);
        mCenterY =mScreenHeight*2/3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        wavePath.reset();
        wavePath.moveTo(-waveLength+mOffset, mCenterY);

        for (int i=0;i<waveCount;i++){
            wavePath.quadTo((-waveLength*3/4)+i*waveLength+mOffset,mCenterY+60,(-waveLength/2)+i*waveLength+mOffset,mCenterY);
            wavePath.quadTo((-waveLength/4)+i*waveLength+mOffset,mCenterY-60,i*waveLength+mOffset,mCenterY);
        }

        wavePath.lineTo(mScreenWidth,mScreenHeight);
        wavePath.lineTo(0,mScreenHeight);
        wavePath.close();

        canvas.drawCircle(DisplayUtils.dp2px(getContext(),50), DisplayUtils.dp2px(getContext(),50), DisplayUtils.dp2px(getContext(),40), imgPaint);

        canvas.drawPath(wavePath,wavePaint);

    }

    private Bitmap getBitmap(int width,int resourceId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), resourceId, options);
        options.inJustDecodeBounds = false;
        //设置位图的屏幕密度,即每英寸有多少个像素
        options.inDensity = options.outWidth;
        //设置位图被画出来时的目标像素密度
        //与options.inDensity配合使用,可对图片进行缩放
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(getResources(), R.mipmap.one, options);
    }

    public void start(){
        ValueAnimator animator=ValueAnimator.ofInt(0,waveLength);
        animator.setDuration(1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mOffset = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });

        animator.start();
    }
}
