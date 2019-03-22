package com.july.teacup.loading;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.july.teacup.R;


public class SimplenessDialog {

    /**
     *      创建并显示dialog
     * @param context
     * @param hintText
     * @return
     */
   public static Dialog createSimpleDialog(Context context,String hintText){
       LayoutInflater inflater=LayoutInflater.from(context);
       //加载view
       View view=inflater.inflate(R.layout.simple_dialog_layout,null);
       LinearLayout layout=view.findViewById(R.id.simple_dialog_layout);

       TextView tipTextView=view.findViewById(R.id.tipTextView);
       //填充数据
       tipTextView.setText(hintText);

       Dialog simpleDialog=new Dialog(context,R.style.LoadingDialog);
       simpleDialog.setCanceledOnTouchOutside(false);
       simpleDialog.setContentView(layout,new LinearLayout.LayoutParams(
               LinearLayout.LayoutParams.MATCH_PARENT,
               LinearLayout.LayoutParams.MATCH_PARENT
       ));

       Window window=simpleDialog.getWindow();
       WindowManager.LayoutParams params=window.getAttributes();
       params.width=WindowManager.LayoutParams.MATCH_PARENT;
       params.height=WindowManager.LayoutParams.WRAP_CONTENT;
       window.setGravity(Gravity.CENTER);
       window.setAttributes(params);
       window.setWindowAnimations(R.style.DialogPopAnimStyle);

       simpleDialog.show();

       return simpleDialog;
   }

    /**
     *      关闭dialog
     * @param dialog
     */
   public static void closeSimpleDialog(Dialog dialog){
       if(dialog!=null && dialog.isShowing()){
           dialog.dismiss();
       }
   }
}
