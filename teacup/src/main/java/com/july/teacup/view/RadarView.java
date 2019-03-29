package com.july.teacup.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import com.july.teacup.R;
import com.july.teacup.unittransition.DisplayUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RadarView extends View {

    private int DEFAULT_COLOR = Color.parseColor("#91d7f4");
    //圆圈和交叉线的颜色
    private int mCircleColor = DEFAULT_COLOR;
    //圆圈的圈数
    private int mCircleNum = 3;
    //扫描颜色 RadarView渐变颜色
    private int mSweepColor = DEFAULT_COLOR;
    //水滴颜色
    private int mRaindropColor = DEFAULT_COLOR;
    //水滴的数量 这里表示最多同时显示的数量
    private int mRaindropNum = 5;
    //是否显示交叉线
    private boolean isShowCross = true;
    //是否显示水滴
    private boolean isShowRaindrop = true;
    //扫描的转速
    private float mSpeed = 3.0f;
    //水滴消失速度
    private float mFlicker = 3.0f;

    //圆的画笔
    private Paint mCirclePaint;
    //扫描效果的画笔
    private Paint mSweepPaint;
    //水滴的画笔
    private Paint mRaindropPaint;

    //扫描室扫描旋转角度
    private float mDegress;
    //是否扫描
    private boolean isScanning = false;

    //水滴数据
    private List<Raindrop> mRaindrops = new ArrayList<>();


    public RadarView(Context context) {
        this(context, null);
    }

    public RadarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        getAttrs(context, attrs);

        init();
    }

    private void init() {
        /*初始化圆圈画笔*/
        mCirclePaint = new Paint();
        mCirclePaint.setColor(mCircleColor);
        mCirclePaint.setStrokeWidth(1);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setAntiAlias(true);

        mRaindropPaint = new Paint();
        mRaindropPaint.setColor(mRaindropColor);
        mRaindropPaint.setStyle(Paint.Style.FILL);
        mRaindropPaint.setAntiAlias(true);

        mSweepPaint = new Paint();
        mSweepPaint.setAntiAlias(true);
    }

    private void getAttrs(Context context, AttributeSet attrs) {

        TypedArray array = null;

        try {
            array = context.obtainStyledAttributes(attrs, R.styleable.RadarView, 0, 0);
            mCircleColor = array.getColor(R.styleable.RadarView_circleColor, DEFAULT_COLOR);
            mCircleNum = array.getInt(R.styleable.RadarView_circleNum, mCircleNum);
            if (mCircleNum < 3) mCircleNum = 3;

            mSweepColor = array.getColor(R.styleable.RadarView_sweepColor, DEFAULT_COLOR);
            mRaindropColor = array.getColor(R.styleable.RadarView_raindropColor, DEFAULT_COLOR);
            mRaindropNum = array.getInt(R.styleable.RadarView_raindropNum, mRaindropNum);

            isShowCross = array.getBoolean(R.styleable.RadarView_showCross, isShowCross);
            isShowRaindrop = array.getBoolean(R.styleable.RadarView_showRaindrop, isShowRaindrop);
            mSpeed = array.getFloat(R.styleable.RadarView_speed, mSpeed);
            if (mSpeed <= 0) {
                mSpeed = 3.0f;
            }

            mFlicker = array.getFloat(R.styleable.RadarView_flicker, mFlicker);
            if (mFlicker <= 3) {
                mFlicker = 3.0f;
            }

        } catch (Exception e) {

        } finally {
            array.recycle();
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int defaultSize = DisplayUtils.dip2px(getContext(), 150);

        setMeasuredDimension(measuredWidth(widthMeasureSpec, defaultSize), measuredHeight(heightMeasureSpec, defaultSize));
    }

    /**
     * @param measureSpec MeasureSpec.EXACTLY 父控件决定子控件大小 子空间将被限定在给定的边界里而忽略本身大小
     *                    MeasureSpec.UNSPECIFIED 父控件没有对子控件施加任何约束 子控件可以得到任意想要的大小
     *                    MeasureSpec.AT_MOST 子控件至多达到指定大小的值
     * @param defaultSize
     * @return
     */
    private int measuredWidth(int measureSpec, int defaultSize) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = defaultSize + getPaddingLeft() + getPaddingRight();

            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }

        return Math.max(result, getSuggestedMinimumWidth());
    }

    private int measuredHeight(int measureSpec, int defaultSize) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = defaultSize + getPaddingTop() + getPaddingBottom();

            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }

        return Math.max(result, getSuggestedMinimumHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);

        int width = getWidth() - getPaddingLeft() - getPaddingRight();
        int heigth = getHeight() - getPaddingTop() - getPaddingBottom();

        int radius = width / 2;

        int xCenter = getPaddingLeft() + width / 2;
        int yCenter = getPaddingTop() + heigth / 2;

        drawCircle(canvas, xCenter, yCenter, radius);

        if (isShowCross) {
            drawCross(canvas, xCenter, yCenter, radius);
        }

        if (isScanning) {
            if (isShowRaindrop) {
                drawRaindrop(canvas, xCenter, yCenter, radius);

            }

            drawSweep(canvas, xCenter, yCenter, radius);

            mDegress = (mDegress + (360 / mSpeed / 60)) % 360;

            invalidate();
        }
    }

    private void drawSweep(Canvas canvas, int xCenter, int yCenter, int radius) {
        SweepGradient sweepGradient = new SweepGradient(xCenter, yCenter,
                new int[]{Color.TRANSPARENT, changeAlpha(mSweepColor, 0), changeAlpha(mSweepColor, 168),
                        changeAlpha(mSweepColor, 255), changeAlpha(mSweepColor, 255)
                }, new float[]{0.0f, 0.6f, 0.99f, 0.998f, 1f});
        mSweepPaint.setShader(sweepGradient);
        //先旋转画布，再绘制扫描的颜色渲染，实现扫描时的旋转效果。
        canvas.rotate(-90 + mDegress, xCenter, yCenter);
        canvas.drawCircle(xCenter, yCenter, radius, mSweepPaint);

    }

    private void drawRaindrop(Canvas canvas, int xCenter, int yCenter, int radius) {
        generateRaindrop(xCenter, yCenter, radius);
        for (Raindrop raindrop : mRaindrops) {
            mRaindropPaint.setColor(raindrop.changeAlpha());
            canvas.drawCircle(raindrop.x, raindrop.y, raindrop.radius, mRaindropPaint);
            //水滴的扩散和透明的渐变效果
            raindrop.radius += 1.0f * 20 / 60 / mFlicker;
            raindrop.alpha -= 1.0f * 255 / 60 / mFlicker;
        }
        removeRaindrop();

    }

    //画交叉线
    private void drawCross(Canvas canvas, int xCenter, int yCenter, int radius) {
        //水平
        canvas.drawLine(xCenter - radius, yCenter, xCenter + radius, yCenter, mCirclePaint);
        //垂直
        canvas.drawLine(xCenter, yCenter - radius, xCenter, yCenter + radius, mCirclePaint);
    }

    //画圆
    private void drawCircle(Canvas canvas, int xCenter, int yCenter, int radius) {
        for (int i = 0; i < mCircleNum; i++) {
            canvas.drawCircle(xCenter, yCenter, radius - (radius / mCircleNum * i), mCirclePaint);
        }

    }


    private void generateRaindrop(int xCenter,int yCenter,int radius){
        if(mRaindrops.size()<mRaindropNum){
            boolean probability=(int) (Math.random() * 20) == 0;

            if(probability){

                int x=0;
                int y=0;

                int xOffset=(int) (Math.random() * (radius - 20));
                int yOffset = (int) (Math.random() * (int) Math.sqrt(1.0 * (radius - 20) * (radius - 20) - xOffset * xOffset));

                if ((int) (Math.random() * 2) == 0) {
                    x = xCenter - xOffset;
                } else {
                    x = xCenter + xOffset;
                }

                if ((int) (Math.random() * 2) == 0) {
                    y = yCenter - yOffset;
                } else {
                    y = yCenter + yOffset;
                }

                mRaindrops.add(new Raindrop(x, y, 0, mRaindropColor));

            }
        }
    }

    private void removeRaindrop(){
        Iterator<Raindrop> iterator=mRaindrops.iterator();
        while (iterator.hasNext()){
            Raindrop raindrop = iterator.next();
            if (raindrop.radius > 20 || raindrop.alpha < 0) {
                iterator.remove();
            }

        }
    }

    /**
     * 开始扫描
     */
    public void start() {
        if (!isScanning) {
            isScanning = true;
            invalidate();
        }
    }

    /**
     * 停止扫描
     */
    public void stop() {
        if (isScanning) {
            isScanning = false;
            mRaindrops.clear();
            mDegress = 0.0f;
        }
    }

    private static class Raindrop {

        int x;
        int y;
        float radius;
        int color;
        float alpha = 255;

        public Raindrop(int x, int y, float radius, int color) {
            this.x = x;
            this.y = y;
            this.radius = radius;
            this.color = color;
        }

        /**
         * 获取改变透明度后的颜色值
         *
         * @return
         */
        public int changeAlpha() {
            return RadarView.changeAlpha(color, (int) alpha);
        }

    }

    /**drawSweep
     * 改变颜色的透明度
     *
     * @param color
     * @param alpha
     * @return
     */
    private static int changeAlpha(int color, int alpha) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }


}
