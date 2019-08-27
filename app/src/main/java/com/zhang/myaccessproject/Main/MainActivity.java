package com.zhang.myaccessproject.Main;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.zhang.myaccessproject.L;
import com.zhang.myaccessproject.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        L.e("在函数之前无报错");
        ifOpenChoice();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onStart(){
        super.onStart();
        //ifOpenChoice();
    }

    private void ifOpenChoice() {
        if (serviceIsRunning()) {
            Toast.makeText(this, "辅助服务已开启", Toast.LENGTH_SHORT).show();
        } else {
            startAccessibilityService();
        }
    }

    //判断程序的辅助服务是否运行，实际是判断是否开启辅助权限
    //
    private boolean serviceIsRunning() {
        ActivityManager am = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> services = am.getRunningServices(Short.MAX_VALUE);
        for (ActivityManager.RunningServiceInfo info : services) {
            Log.e(TAG, info.service.toString());
            if (info.service.getClassName().equals(getPackageName() + ".MineClickService")) {
                Log.e(TAG, "返回了真");
                return true;
            }
        }
        Log.e(TAG, "返回了假");
        return false;
    }

    /**
     * 前往设置界面开启服务
     * 开启辅助的同时，自动唤起辅助的后台服务
     */
    private void startAccessibilityService() {
        new AlertDialog.Builder(this)
                .setTitle("开启辅助功能")
                .setIcon(R.mipmap.ic_launcher)
                .setMessage("使用此项功能需要您开启辅助功能")
                .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 隐式调用系统设置界面
                        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }).create().show();
    }
}
