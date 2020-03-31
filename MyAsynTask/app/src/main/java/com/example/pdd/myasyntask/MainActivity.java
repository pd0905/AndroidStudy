package com.example.pdd.myasyntask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
//import android.os.SystemProperties;
/*
   子类在重写Activity生命周期的方法的时候需要调用父类的方法（super关键字），否则将会抛出原因还在查看
 */
public class MainActivity extends AppCompatActivity{

    private Button button1,button2,button3;
    private TextView textView01;
    ProgressBar progressBar;
    private String str = "MyAsyncTest";

    final String TAG = "Test my AsyncTask";



    public class MyAsynTest extends AsyncTask<String, Integer, String>{

        //方法1
        //线程执行前的操作
        @Override
        protected void onPreExecute(){
            textView01.setText("加载中");

        }
        //方法2
        //接收输入参数、执行任务中的耗时操作、返回 线程任务执行的结果doInBackground();
        @Override
        protected String doInBackground(String... params){
            try {

                int count = 0;
                int length = 1;
                while (count<99) {

                    count += length;
                    // 可调用publishProgress（）显示进度, 之后将执行onProgressUpdate（）
                    publishProgress(count);
                    // 模拟耗时任务
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        // 方法3：onProgressUpdate（）
        // 作用：在主线程 显示线程任务执行的进度
        // 注：根据需求复写
        @Override
        protected void onProgressUpdate(Integer... progresses) {
            progressBar.setProgress(progresses[0]);
            textView01.setText("loading..." + progresses[0] + "%");

        }

        // 方法4：onPostExecute（）
        // 作用：接收线程任务执行结果、将执行结果显示到UI组件
        // 注：必须复写，从而自定义UI操作
        @Override
        protected void onPostExecute(String result) {

            textView01.setText("加载完成");

        }

        // 方法5：onCancelled()
        // 作用：将异步任务设置为：取消状态
        @Override
        protected void onCancelled() {

            textView01.setText("以取消");
            progressBar.setProgress(0);

        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.getProperties().setProperty(this.str,"1");
        Log.d(TAG, "onCreate: MyAsyncTask====");
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button)  findViewById(R.id.button3);
        textView01 = (TextView) findViewById(R.id.text);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        //匿名内部类的方式实现
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MyAsynTest myAsynTest = new MyAsynTest();
                myAsynTest.execute();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MyAsynTest myAsynTest = new MyAsynTest();
                myAsynTest.cancel(true);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView01.setText("未加载");
                progressBar.setProgress(0);
            }
        });

    }
    @Override
    protected void onStop(){
        super.onStop();
        System.getProperties().setProperty(this.str,"3");
        Log.d(TAG, "onStop: MyAsyncTask====");
    }
    @Override
    protected void onStart(){
        super.onStart();
        System.getProperties().setProperty(this.str,"5");
        Log.d(TAG, "onStart: MyAsyncTask====");
    }
    @Override
    protected void onResume(){
        super.onResume();
        System.getProperties().setProperty(this.str,"2");
        Log.d(TAG, "onResume: :MyAsyncTask====");

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        System.getProperties().setProperty(this.str,"0");
        Log.d(TAG, "onDestroy: MyAsyncTask====");

    }
    @Override
    protected void onRestart(){
        super.onRestart();
        System.getProperties().setProperty(this.str,"4");
        Log.d(TAG, "onRestart: MyAsyncTask====");
    }
    @Override
    protected  void onPause(){
        super.onPause();
        System.getProperties().setProperty(this.str,"5");
        Log.d(TAG, "onPause: MyAsyncTask====");
    }
   @Override
   public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
   }

   @Override
   public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
   }

}
