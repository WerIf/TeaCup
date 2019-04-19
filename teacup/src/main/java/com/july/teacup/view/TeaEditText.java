package com.july.teacup.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.july.teacup.R;

@SuppressLint("AppCompatCustomView")
public class TeaEditText extends RelativeLayout implements View.OnTouchListener, TextWatcher, View.OnFocusChangeListener {

    private int inputType;
    private int onFocusBack;

    private int defaultColor;
    private String hintText;

    private EditText editText;
    private Drawable drawable;

    public TeaEditText(Context context) {
        this(context, null);
    }

    public TeaEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TeaEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        View view = LayoutInflater.from(context).inflate(R.layout.tea_edit_layout, this);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TeaEditText, 0, 0);
        try {
            hintText = typedArray.getString(R.styleable.TeaEditText_TEdit_text);
            onFocusBack = typedArray.getResourceId(R.styleable.TeaEditText_TEdit_onFocus, 0);
            inputType = typedArray.getInteger(R.styleable.TeaEditText_android_inputType, 0);
        } finally {
            typedArray.recycle();
        }

        initView();
    }

    private void initView() {

        editText = findViewById(R.id.teaEdit);

        drawable = getResources().getDrawable(R.mipmap.icon_cancel);
        drawable.setBounds(0, 0, 45, 45);

        editText.setOnTouchListener(this);

        editText.addTextChangedListener(this);

        editText.setOnFocusChangeListener(this);

        editText.setHint(hintText);

        if (inputType != 0) {
            editText.setInputType(inputType);
        }

        if (onFocusBack != 0) {
            editText.setBackground(getResources().getDrawable(onFocusBack));
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Drawable drawable = editText.getCompoundDrawablesRelative()[2];
        //如果drawableEnd 图片为空 不作处理
        if (drawable == null) return false;
        //如果不是按下事件 不作处理
        if (motionEvent.getAction() != MotionEvent.ACTION_UP)
            return false;

        if (motionEvent.getX() > editText.getWidth()
                - editText.getPaddingRight()
                - drawable.getIntrinsicWidth()) {
            editText.setText("");
        }

        return false;
    }

    public boolean isInputEmpty() {
        return TextUtils.isEmpty(editText.getText().toString());
    }

    public String getInputText(){
        if(!isInputEmpty()){
            return editText.getText().toString();
        }

        throw new NullPointerException("EditText don't have data");
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

        if (TextUtils.isEmpty(editText.getText()))
            editText.setCompoundDrawablesRelative(null, null, null, null);
        else
            editText.setCompoundDrawablesRelative(null, null, drawable, null);
    }

    @Override
    public void onFocusChange(View view, boolean b) {

        if (b) {
            //弹出输入键盘
            InputMethodManager inputManager = (InputMethodManager) editText
                    .getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(editText, 0);
        }

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
