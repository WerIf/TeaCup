package com.july.teacup.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class BazierView extends View {

    private Paint mPaint;

    private int centerX,centerY;

    private PointF start,end,control1,control2;

    private boolean chooseTag=true;

    public BazierView(Context context) {
        this(context,null);
    }

    public BazierView(Context context,AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BazierView(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mPaint=new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(60);
        mPaint.setStyle(Paint.Style.STROKE);

        start=new PointF(0,0);
        end=new PointF(0,0);
        control1=new PointF(0,0);
        control2=new PointF(0,0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        centerX=w/2;
        centerY=h/2;

        //初始化数据点和控制点的位置
        start.x=centerX-300;
        start.y=centerY;

        end.x=centerX+300;
        end.y=centerY;

        control1.x=centerX-100;
        control1.y=centerY-100;


        control2.x=centerX+100;
        control2.y=centerY-100;
    }

    public void chooseOther(boolean tag){
        chooseTag=tag;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

       if(chooseTag){
           control1.x=event.getX();

           control1.y=event.getY();
       }else{
           control2.x=event.getX();

           control2.y=event.getY();
       }

        invalidate();

        return true;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制数据点和控制点
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(start.x,start.y,mPaint);
        canvas.drawPoint(end.x,end.y,mPaint);
        canvas.drawPoint(control1.x,control1.y,mPaint);
        canvas.drawPoint(control2.x,control2.y,mPaint);

        //绘制辅助线
        mPaint.setStrokeWidth(4);
        canvas.drawLine(start.x,start.y,control1.x,control1.y,mPaint);
        canvas.drawLine(control1.x,control1.y,control2.x,control2.y,mPaint);
        canvas.drawLine(control2.x,control2.y,end.x,end.y,mPaint);

        //绘制贝赛尔曲线
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(8);

        Path path=new Path();
        path.moveTo(start.x,start.y);
        //一个控制点
        //path.quadTo();
        //两个个控制点
        path.cubicTo(control1.x,control1.y,control2.x,control2.y,end.x,end.y);

        canvas.drawPath(path,mPaint);
    }
}
