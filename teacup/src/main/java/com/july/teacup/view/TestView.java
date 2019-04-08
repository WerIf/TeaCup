package com.july.teacup.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

public class TestView extends View {

    private Paint strokePaint;
    private Paint fillPaint;

    private String text = "50%";
    private Paint textPaint;
    private int height;
    private int width;
    private RectF rectF;
    private float tempCount;

    UpdateAnimation updateAnimation;

    public TestView(Context context) {
        this(context, null);
    }

    public TestView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint();
    }

    private void initPaint() {
        strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(10);
        strokePaint.setColor(Color.BLUE);
        strokePaint.setStrokeCap(Paint.Cap.ROUND);

        fillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fillPaint.setStyle(Paint.Style.FILL);
        fillPaint.setColor(Color.BLUE);

        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(15);

        updateAnimation = new UpdateAnimation();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

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

        width = w;
        height = h;

        rectF = new RectF();
        rectF.set(10 / 2f, 10 / 2f, width - 10 / 2f, height - 10 / 2f);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawArc(rectF, 0f, tempCount, false, strokePaint);

        canvas.drawCircle(100, 100, 40, fillPaint);

        Rect rectF = new Rect();
        textPaint.getTextBounds(text, 0, text.length(), rectF);

        canvas.drawText(text, 100 - rectF.width() / 2, 100 + rectF.height() / 4, textPaint);
    }

    public void start() {
//        updateAnimation.start();

        startAnimation(updateAnimation);
    }

    public class UpdateAnimation extends Animation {
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);


            Log.e("TAG","println result interpolatedTime:"+interpolatedTime);

            if (interpolatedTime < 1.0f) {
                tempCount = 180f * interpolatedTime;
                postInvalidate();
            } else {
                tempCount = 180f;
            }
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);

            setDuration(1000);
            setFillAfter(true);
            tempCount = 0;
            setInterpolator(new LinearInterpolator());
        }
    }
}
