package com.example.pdd.myontoucheventapplication;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
/**
 *  触摸事件的应用   在是实现控件拖动的时候，需要使用view.layout(left, top, right, bottom)
 *  onTouchEvent方法是专门用来处理事件分发的，它一定存在Activity、View和ViewGroup这三者中
 *  onTouch方法是View设置了触摸监听事件后，需要重写的方法，是OnTouchListener接口中的方法
 *
 */
public class MainActivity extends Activity implements View.OnTouchListener{
    long Press_time;
    int  Presscount = 0;
    long Last_time;
    float Press_x;
    float Press_y;
    float source_x = 1920;
    float source_y = 0;
    final String TAG = "onTouchEvent";

    float currentdistance  = 0;
    float lastcurrentdistance = 0;
    ImageView imageView;
    private SharedPreferences sp;

    private int sx;
    private int sy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.image);
        imageView.setOnTouchListener(this);
        //sp = this.getSharedPreferences("config", Context.MODE_PRIVATE);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        int action = event.getAction();
        switch(action){
            case MotionEvent.ACTION_DOWN:          //连续点击（1920，0）这个坐标点4次实现连续点击事件
                 Press_time = System.currentTimeMillis();
                 Log.d(TAG, "onTouchEvent: Action_Down");
                 Press_x = event.getX();
                 Press_y = event.getY();
                 float Move_xsize = Math.abs(Press_x - source_x);
                 float Move_ysize = Math.abs(Press_y - source_y);
                 Log.d(TAG, "Press_time,"+"," + Press_time+"," + "Last_Time," + Last_time +","+ "Move_xsize," + Move_xsize +","+ "Move_ysize," + Move_ysize);
                 if((Move_xsize<=30)&&(Move_ysize<=30)&&((Press_time-Last_time)<=5000)){
                     Presscount++;
                     Log.d(TAG, "===================================="+Presscount);
                     if(Presscount==4){
                         Toast.makeText(MainActivity.this,"continous_Press",Toast.LENGTH_SHORT).show();
                         Presscount = 0;
                         Last_time = 0;
                         break;
                     }
                 }
                 Last_time = Press_time;
                 break;
            case MotionEvent.ACTION_UP:       //长按（1920,0）这个坐标点实现长按会或者短按事件事件
                Log.d(TAG, "onTouchEvent: Action_up");
                float size_y = Math.abs(Press_y-source_y);
                float size_x = Math.abs(Press_x-source_x);
                if(size_x<=10&&size_y<=10){
                long Move_time = System.currentTimeMillis();
                Log.d(TAG, "onTouchEvent: "+  Move_time);
                boolean longPressFlag = isLongPressed(Press_time,Move_time);
                if(longPressFlag == true){
                    Toast.makeText(MainActivity.this,"Long Press",Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onTouchEvent: this is long PressEvent");
                }else{
                    Toast.makeText(MainActivity.this,"Short Press",+Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onTouchEvent: this is short PressEvent");
                }
                }
                break;
            case MotionEvent.ACTION_MOVE:     //多点触摸事件（是实现的是两点触摸事件，实现方式相同），实现图片的放大或者缩小，
                Log.d(TAG, "onTouchEvent: Action_move");
                if(event.getPointerCount()>=2){
                    Log.d(TAG, "onTouchEvent: have two touchEvent");
                    float move_x = event.getX(0)-event.getX(1);
                    float move_y = event.getY(0)-event.getY(1);

                    currentdistance = (float)Math.sqrt(move_x*move_x+move_y*move_y);

                    if(lastcurrentdistance<=0){
                        lastcurrentdistance = currentdistance;
                    }else{
                        if(currentdistance-lastcurrentdistance>8){
                            Log.d(TAG, "onTouchEvent: ++放大");
                            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) imageView.getLayoutParams();
                           // ViewGroup.LayoutParams lp = imageView.getLayoutParams();
                            lp.width = (int)(1.1*imageView.getWidth());
                            lp.height = (int)(1.1*imageView.getHeight());
                            imageView.setLayoutParams(lp);
                            lastcurrentdistance = currentdistance;
                        }
                        if(lastcurrentdistance-currentdistance>8){
                            Log.d(TAG, "onTouchEvent: --缩小");
                            LinearLayout.LayoutParams lp =  (LinearLayout.LayoutParams) imageView.getLayoutParams();
                            lp.width = (int) (0.9*imageView.getWidth());
                            lp.height = (int) (0.9*imageView.getHeight());
                            imageView.setLayoutParams(lp);
                        }
                        lastcurrentdistance = currentdistance;
                    }
                }
                break;
        }
        return true;
    }
    public  boolean isLongPressed(float Press_x,float Press_y,float Move_x,float Move_y,long Press_time,long Move_time){
        Log.d(TAG, "isLongPressed: "+ Press_x +","+ Press_y+"," + Press_time+"," + Move_x +"," + Move_y+"," + Move_time);
        float run_x = Math.abs(Move_x - Press_x);
        float run_y = Math.abs(Move_y - Move_y);
        Log.d(TAG, "isLongPressed: runsize"+run_x+ run_y);
        long  time = Move_time-Press_time;
        Log.d(TAG, "isLongPressed: runtime "+time);
        if(run_x<=10&&run_y<=10&&time>5000){
            return true;
        }else{
            return false;
        }

    }
    public  boolean isLongPressed(long Press_time,long Move_time){
        long  time = Move_time-Press_time;
        Log.d(TAG, "isLongPressed: runtime "+time);
        if(time>5000){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public boolean onTouch(View v, MotionEvent event){
        switch(v.getId()){
            case R.id.image:
                int action = event.getAction();
                switch(action){
                    case MotionEvent.ACTION_DOWN:
                        sx = (int) event.getRawX();
                        sy = (int) event.getRawY();
                        Log.d(TAG, "image onTouch: Action-down");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.d(TAG, "image onTouch: Action-move");
                        int x = (int) event.getRawX();
                        int y = (int) event.getRawY();
                        // 获取手指移动的距离
                        int dx = x - sx;
                        int dy = y - sy;
                        // 得到imageView最开始的各顶点的坐标
                        int l = imageView.getLeft();
                        int r = imageView.getRight();
                        int t = imageView.getTop();
                        int b = imageView.getBottom();
                        // 更改imageView在窗体的位置
                        imageView.layout(l + dx, t + dy, r + dx, b + dy);
                        // 获取移动后的位置
                        sx = (int) event.getRawX();
                        sy = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d(TAG, "image onTouch: Action-up");
                        int lasty = imageView.getTop();
                        int lastx = imageView.getLeft();
//                        //imageView.setImageResource(R.drawable.next);
//                        SharedPreferences.Editor editor = sp.edit();
//                        editor.putInt("lasty", lasty);
//                        editor.putInt("lastx", lastx);
//                        editor.commit();
                        break;
                }
                break;
        }

        return true;
    }
}
