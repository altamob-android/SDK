package com.altamob.android.sdkdemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private int time;
    private Runnable runnable;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initProgressDialog();
    }

    protected void initProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(BaseActivity.this);
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setTitle("提示");
//            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage("加载中...");
        }
    }

    protected void showDialog() {
        time = 0;
        if (progressDialog != null) {
            progressDialog.show();
            runnable = new Runnable() {
                @Override
                public void run() {
                    time++;
                    progressDialog.setMessage("加载计时:" + time + "s");
                    handler.postDelayed(this, 1000);
                }
            };
            handler.postDelayed(runnable, 1000);
        }
    }

    protected void hideDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            handler.removeCallbacks(runnable);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideDialog();
    }
}
