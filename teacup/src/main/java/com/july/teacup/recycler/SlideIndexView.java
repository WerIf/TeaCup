package com.july.teacup.recycler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.july.teacup.R;


public class SlideIndexView extends View {

    private String[] indexArr = {"A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y",
            "Z"};

    private Paint paint;
    private float width;
    private float height;

    public SlideIndexView(Context context) {
        this(context, null);
    }

    public SlideIndexView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideIndexView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(getResources().getColor(R.color.colorAccent));
        //将文本绘制中心设置为文本底边中心
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(40);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        width = getMeasuredWidth();

        height = getMeasuredHeight() * 1f / indexArr.length;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        for (int i = 0; i < indexArr.length; i++) {
            float x = width / 2;

            paint.setColor(lastIndex==i? getResources().getColor(R.color.colorBlack):getResources().getColor(R.color.colorAccent));

            Rect bounds = new Rect();
            paint.getTextBounds(indexArr[i], 0, 1, bounds);
            float y = height / 2 + bounds.height() / 2 + i * height;

            canvas.drawText(indexArr[i], x, y, paint);
        }
    }

    private int lastIndex=-1;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float y=event.getY();
                int index= (int) (y/height);
                if(lastIndex!=index){

                    if(index>=0 && index<indexArr.length)
                    {
                        if(onTouchLetterListener!=null){
                            onTouchLetterListener.onTouchLetter(indexArr[index]);
                        }
                    }
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                lastIndex=-1;
                invalidate();
                break;
        }
        return true;
    }

    /**
     * 触摸字母的监听器
     */
    public interface OnTouchLetterListener{
        void onTouchLetter(String latter);
    }

    private OnTouchLetterListener onTouchLetterListener;

    public void setOnTouchLetterListener(OnTouchLetterListener onTouchLetterListener) {
        this.onTouchLetterListener = onTouchLetterListener;
    }
}
