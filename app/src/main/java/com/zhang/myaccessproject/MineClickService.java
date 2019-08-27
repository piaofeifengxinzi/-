package com.zhang.myaccessproject;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;
import java.util.List;


public class MineClickService extends AccessibilityService {
    private final String TAG = "12345678";
    private static final String PackageName = "com.jifen.qukan";
    //奖励
     String jiangLi = "com.jifen.qukan:id/x1";
     //奖励点击过后的页面ID
     String shibai = "com.jifen.qukan:id/v2";
     //首页滑动点击的ID
     String click = "com.jifen.qukan:id/a1m";
     //文档返回
     String wenDangBack = "com.jifen.qukan:id/g6";
     //视频返回的ID
     String shiPingBack = "com.jifen.qukan:id/nk";
     //图片返回的ID
     String tuPianBack = "com.jifen.qukan:id/ii";
     //视频播放按钮
     String boFang = "com.jifen.qukan:id/ol";
     //广告的返回ID
     String guangGao = "com.jifen.qukan:id/h0";
     //取消播放
     String quXiaoBoFang = "com.jifen.qukan:id/ut";
     //点击进入或刷新首页
     String shouye = "com.jifen.qukan:id/kt";
     public boolean really = true;

    @Override
    protected void onServiceConnected(){
        super.onServiceConnected();
        Toast.makeText(this,"调用了onServiceConnected",Toast.LENGTH_SHORT).show();
    }


    /*
    *判断活动是否进行
     */
    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        // 事件页面节点信息不为空
        AccessibilityNodeInfo source = accessibilityEvent.getSource();
        int eventType = accessibilityEvent.getEventType();
        String eventText = "";

        switch (eventType) {

                //代表喀哒上的事件View一样 Button，CompoundButton等等。
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                eventText = "TYPE_VIEW_CLICKED";
                if(really){
                    really = false;
                    try {
                        startLogicClick();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //Toast.makeText(getApplicationContext(),eventText,Toast.LENGTH_SHORT).show();
                L.e(eventText);
                break;

                //表示设置a的输入焦点的事件
            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
                eventText = "TYPE_VIEW_FOCUSED";
                //Toast.makeText(getApplicationContext(),eventText,Toast.LENGTH_SHORT).show();
                L.e(eventText);
                break;

                //表示长时间点击的一个事件View一样 Button，CompoundButton等
            case AccessibilityEvent.TYPE_VIEW_LONG_CLICKED:
                eventText = "TYPE_VIEW_LONG_CLICKED";
                //Toast.makeText(getApplicationContext(),eventText,Toast.LENGTH_SHORT).show();
                L.i(eventText);
                break;

                //表示通常在a的上下文中选择项目的事件 AdapterView。
            case AccessibilityEvent.TYPE_VIEW_SELECTED:
                eventText = "TYPE_VIEW_SELECTED";
                //Toast.makeText(getApplicationContext(),eventText,Toast.LENGTH_SHORT).show();

                L.i(eventText);
                break;

                //表示更改选择的事件EditText。
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
                eventText = "TYPE_VIEW_TEXT_CHANGED";
                //Toast.makeText(getApplicationContext(),eventText,Toast.LENGTH_SHORT).show();
                L.i(eventText);
                break;

                //表示更改用户界面的视觉上不同的部分的事件。
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                eventText = "TYPE_WINDOW_STATE_CHANGED";

                L.i(eventText);
                break;

                //表示向用户显示暂时信息的事件。此信息可能是Notification或 Toast。
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
                eventText = "TYPE_NOTIFICATION_STATE_CHANGED";
                L.i(eventText);
                break;

                //表示开始触摸探索手势的事件。
            case AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_END:
                eventText = "TYPE_TOUCH_EXPLORATION_GESTURE_END";
               // Toast.makeText(getApplicationContext(),eventText,Toast.LENGTH_SHORT).show();
                L.i(eventText);
                break;

                //表示请求屏幕阅读器发布公告的应用程序事件。由于事件不具有语义含义，
            // 因此该事件仅适用于需要额外屏幕阅读器输出但其他类型的可访问性服务不需要了解更改的特殊情况。
            case AccessibilityEvent.TYPE_ANNOUNCEMENT:
                eventText = "TYPE_ANNOUNCEMENT";
                //Toast.makeText(getApplicationContext(),eventText,Toast.LENGTH_SHORT).show();
                L.i(eventText);
                break;

                //表示开始触摸探索手势的事件。
            case AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_START:
                eventText = "TYPE_TOUCH_EXPLORATION_GESTURE_START";
                //Toast.makeText(getApplicationContext(),eventText,Toast.LENGTH_SHORT).show();
                L.i(eventText);
                break;
            case AccessibilityEvent.TYPE_VIEW_HOVER_ENTER:
                eventText = "TYPE_VIEW_HOVER_ENTER";
                //Toast.makeText(getApplicationContext(),eventText,Toast.LENGTH_SHORT).show();
               L.i(eventText);
                break;
            case AccessibilityEvent.TYPE_VIEW_HOVER_EXIT:
                eventText = "TYPE_VIEW_HOVER_EXIT";
                //Toast.makeText(getApplicationContext(),eventText,Toast.LENGTH_SHORT).show();
               L.i(eventText);
                break;

                //滑动事件
            case AccessibilityEvent.TYPE_VIEW_SCROLLED:
                eventText = "TYPE_VIEW_SCROLLED";
                //Toast.makeText(getApplicationContext(),eventText,Toast.LENGTH_SHORT).show();
                L.i(eventText);
                break;

                //选择改变
            case AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED:
                eventText = "TYPE_VIEW_TEXT_SELECTION_CHANGED";
                //Toast.makeText(getApplicationContext(),eventText,Toast.LENGTH_SHORT).show();
                L.i(eventText);
                break;

                //全局窗口
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                eventText = "TYPE_WINDOW_CONTENT_CHANGED";

                //Toast.makeText(getApplicationContext(),eventText,Toast.LENGTH_SHORT).show();
                L.i(eventText);
                break;
        }
    }


/*
点击逻辑，界面跳转循环逻辑
通过寻找a1m节点判断是否回到主页面，
myGesture(int startX,int startY,int endX ,int endY )点击对应坐标，或滑动
findActivityNodeId(String ID)点击对应ID的控件
 */
private void startLogicClick() throws InterruptedException {
    boolean myflages = true;
    int i = 0;
    for(int k = 0;k<100;k++){
        if(ifIdAtPage(jiangLi)){
            break;
        }
    }
    Click(jiangLi);
    Thread.sleep(3000);
    if(ifIdAtPage(shibai)) {
        Click(shibai);
    }
    Thread.sleep(2000);
    if(ifIdAtPage(click)) {
        for (;;) {
            myflages = true;
            i = 1;
            if(ifIdAtPage(click)) {
                Thread.sleep(3000);
                Click(click);
                //这里要添加对图片浏览的支持，每滑动一次结束都要检测是否到达首页
                while (myflages) {
                    Thread.sleep(2000);
                    if (ifIdAtPage(tuPianBack)) {
                        int j = 0;
                        while (j < 4) {
                            Thread.sleep(5000);
                            myGesture(680, 640, 30, 640);
                            j++;
                        }
                        Thread.sleep(2000);
                        Click(tuPianBack);
                        myflages = false;
                    }else if (ifIdAtPage(wenDangBack)) {
                        L.e("点击文档的返回");
                        int j = 0;
                        while (j < 6) {
                            Thread.sleep(7000);
                            //myGesture(380, 960, 360, 300);
                            myGesture(380, 1700, 360, 400);
                            j++;
                        }
                        Thread.sleep(2000);
                        Click(wenDangBack);
                        myflages = false;

                    } else if (ifIdAtPage(shiPingBack)) {
                        L.e("点击视频的返回");
                        Thread.sleep(2000);
                        //wifi下不需要播放直接判断，wifi下需要更改逻辑
                        Click(boFang);
                        Thread.sleep(50000);
                        Click(shiPingBack);
                        myflages = false;
                    } else if (ifIdAtPage(guangGao)) {
                        Thread.sleep(2000);
                        Click(guangGao);
                        myflages = false;
                    }else if(ifIdAtPage(quXiaoBoFang)){
                        Thread.sleep(2000);
                        Click(quXiaoBoFang);
                        //这里在连接WiFi后需要更改逻辑
                        Thread.sleep(1000);
                        Click(shouye);
                        myflages = false;
                    }else{
                        myflages = false;
                    }
                    ++i;
                }
            }else{
                Thread.sleep(2000);
                myGesture(65,90,0,0);
            }
        Thread.sleep(3000);
        L.e("进行滑动");
        //myGesture(70, 1060, 70, 300);
        myGesture(70, 1700, 70, 400);
        Thread.sleep(2000);
            }
        }
    }



   /*
   触摸事件
   只传入前两个参数为点击（后两个参数传入0），传入四个参数为滑动，由开始点滑动到结束点，
   无return
    */
    private void myGesture(int startX,int startY,int endX ,int endY ){
        L.e("自动点击");
        Path mypath = new Path();
        if(endX == 0&&endY == 0) {
            mypath.moveTo(startX, startY);
        }else if((startY-endY)>=0){
            mypath.moveTo(startX,startY);
            mypath.lineTo(endX, endY);
        }else{
            mypath.moveTo(0,0);
            mypath.lineTo(0,0);
        }
        L.e(mypath.toString());
        GestureDescription.Builder builder = new GestureDescription.Builder();
        builder.addStroke(new GestureDescription.StrokeDescription(mypath, 100L, 1000L));
        GestureDescription gesture = builder.build();
        boolean isDispatched = dispatchGesture(gesture, new GestureResultCallback() {
            @Override
            public void onCompleted(GestureDescription gestureDescription) {
                super.onCompleted(gestureDescription);
                Log.e(TAG, "onCompleted: 完成..........");
            }
            @Override
            public void onCancelled(GestureDescription gestureDescription) {
                super.onCancelled(gestureDescription);
                Log.e(TAG, "onCompleted: 取消..........");
            }
        }, null);
    }


    /*
    点击事件,参数为对应的ID
    无return
     */
    private void Click(String ID){
        //矩形对象
        Rect newRect = findActivityNodeId(ID);
        //调用点击
        myGesture(newRect.centerX(),newRect.centerY(),0,0);
    }


/*
判断对应ID在当前页面是否存在
参数为ID，返回boolean
 */
private boolean ifIdAtPage(String ID){
    boolean flage = false;
    AccessibilityNodeInfo rootNodeInfo = getRootInActiveWindow();
    Rect myrect = new Rect();
    List<AccessibilityNodeInfo> mylist = rootNodeInfo.findAccessibilityNodeInfosByViewId(ID);
    for(AccessibilityNodeInfo myNodeInfo : mylist){
        flage = true;
    }
    return flage;
}


    /*
    寻找可点击事件，并将可点击事件的id转化为坐标矩形
    返回一个Rect
     */
public Rect findActivityNodeId(String ID){
        AccessibilityNodeInfo rootNodeInfo = getRootInActiveWindow();
        Rect myrect = new Rect();
        List<AccessibilityNodeInfo> mylist = rootNodeInfo.findAccessibilityNodeInfosByViewId(ID);
        for(AccessibilityNodeInfo temp : mylist){
            temp.getBoundsInScreen(myrect);
            String myString = Integer.toString(myrect.centerX())+"  "+Integer.toString(myrect.centerY());
            L.e(myString);
            //点击相应位置
            //myGesture(myrect.centerX(),myrect.centerY(),0,0);
        }
        return myrect;
}

/*
重写方法，当授权中断所要执行的事件
 */
    @Override
    public void onInterrupt() {
        Toast.makeText(this,"辅助服务权限被取消",Toast.LENGTH_SHORT).show();
    }


}
