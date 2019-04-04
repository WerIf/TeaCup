package com.july.teacup.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

import java.util.List;

public class FristTest extends View {

    private float startX,startY;

    private float width,height;

    private Paint paint;

    private ShapeHeart[] heartList=new ShapeHeart[1];
    private float mInterpolatedTime;


    public FristTest(Context context) {
        this(context,null);
    }

    public FristTest(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FristTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {

        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);

        for (int a=0;a<heartList.length;a++){
            heartList[a]=new ShapeHeart();
            heartList[a].intPoint();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        width=getWidth();
        height=getHeight();

        startX=width/2;
        startY=height;



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


//        for (int a=0;a<heartList.length;a++){
            Path path=new Path();
            path.cubicTo(startX,startY,heartList[0].controlX,heartList[0].controlY,heartList[0].endX,heartList[0].endY);
//
//            canvas.
//        }




    }

    class ShapeHeart{

        float controlX,controlY,endX,endY;

        public void intPoint(){
            controlX=(float) (width/2-Math.random()*(width/2));
            controlY=(float) (Math.random()*height);

            double size=Math.random();


            endX= size>0.5?(float)(width/2-size*(width/2)):(float)(width/2+size*(width/2));
            endY= 0;
        }
    }

    private class MoveAnimation extends Animation {

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            mInterpolatedTime = interpolatedTime;
            invalidate();
        }
    }

    public void startAnimation() {
        paint.reset();
        mInterpolatedTime = 0;
        MoveAnimation move = new MoveAnimation();
        move.setDuration(2000);
        move.setInterpolator(new LinearInterpolator());
        //move.setRepeatCount(Animation.INFINITE);
        //move.setRepeatMode(Animation.REVERSE);
        startAnimation(move);
    }


}
