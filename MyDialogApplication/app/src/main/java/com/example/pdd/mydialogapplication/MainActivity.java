package com.example.pdd.mydialogapplication;

import android.app.AliasActivity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * dialog的几种实现方式：
 * 1.实现普通Dialog对话框的方法 dialog.setPositiveButton - 积极按钮  dialog.setNegativeButton -消极 dialog.setNeutralButton中立按钮
 * 2.实现列表Dialog对话框的方法  dialog.setItems方法
 * 3.实现单选Dialog对话框的的方法  setSingleChoiceItems+setPositiveButton 的方法
 * 4.实现多选Dialog对话框的方法   setSingleChoiceItems+setPositiveButton 以及ArrayList的方法实现
 * 5.实现等待Dialog的方法    ProgressDialog
 * 6.实现进度条Dialog的方法  ProgressDialog + 多线程模拟网络延迟实现进度条
 * 7.自定义Dialog  @setView 装入自定义View  view 当中存在一个EditText
 */


public class MainActivity extends AppCompatActivity {
    private Button button01;
    private String TAG = "MyDialog test";
    private int mychioce;
    ProgressDialog waitingDialog;
    ProgressDialog progressDialog;

    private ArrayList<Integer>yourChoices = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button01 = findViewById(R.id.button01);
        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ShowDialog();
                //ShowDialog01();
                ShowDialog02();
                //ShowDialog03();
                //ShowDialog04();

                //ShowDialog05();
                //ShowDialog06();

            }
        });
    }

    public void ShowDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("我是普通Dialog");
        dialog.setIcon(R.drawable.ic_launcher_background);
        dialog.setMessage("选择要点击的按钮");

        dialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "onClick: confirm");
                    }
                });

        dialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "onClick: cancel");
                    }
                });
        dialog.setNeutralButton("中立",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "onClick: neutral");
                    }
                });
        dialog.show();
    }

    public void ShowDialog01(){
        final String str[] = {"列表1","列表2","列表3"};
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("列表Dialog'");

        dialog.setIcon(R.drawable.ic_launcher_background);
        dialog.setItems(str, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(TAG, "onClick: list"+str[which]);
            }
        });
        dialog.show();
    }
    /**复写Builder的create和show函数，可以在Dialog显示前实现必要设置
     * 例如初始化列表、默认选项等
     * @create 第一次创建时调用
     * @show 每次显示时调用
     */
    public void ShowDialog02(){
        final String str[] = {"单选1","单选2","单选3"};

        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this){
            @Override
            public AlertDialog create(){
                str[0] = "heihei";
                return super.create();
            }

        };
        dialog.setTitle("单选");

        dialog.setSingleChoiceItems(str, 3, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mychioce = which;
                Log.d(TAG, "onClick: chioce" + mychioce);
            }
        });

        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(TAG, "confirm: "+str[mychioce]);
            }
        });

        dialog.show();
     }

     public void ShowDialog03(){
        final String str[] = {"列表1","列表2","列表3","列表4"};
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("多选");
        boolean bool[] ={false,false,false,false};
        yourChoices.clear();
        AlertDialog.Builder multiChoiceDialog =
                 new AlertDialog.Builder(MainActivity.this);
         multiChoiceDialog.setTitle("我是一个多选Dialog");
         multiChoiceDialog.setMultiChoiceItems(str, bool,
                 new DialogInterface.OnMultiChoiceClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which,
                                         boolean isChecked) {
                         if (isChecked) {
                             yourChoices.add(which);
                         } else {
                             yourChoices.remove(which);
                         }
                     }
                 });
         multiChoiceDialog.setPositiveButton("确定",
                 new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         int size = yourChoices.size();
                         String str01 = "";
                         for (int i = 0; i < size; i++) {
                             str01 += str[yourChoices.get(i)] + " ";
                         }
                         Toast.makeText(MainActivity.this,
                                 "你选中了" + str01,
                                 Toast.LENGTH_SHORT).show();
                     }
                 });
         multiChoiceDialog.show();


     }

     public void ShowDialog04(){

         waitingDialog= new ProgressDialog(MainActivity.this);
         waitingDialog.setTitle("等待Dialog");
         waitingDialog.setMessage("等待中...");
         waitingDialog.setIndeterminate(true);
         waitingDialog.setCancelable(false);
         waitingDialog.show();
         ThreadTest thread = new ThreadTest();

         thread.start();      //模拟耗时操作,耗时操作不应该放置在main线程当中

     }

     public void ShowDialog05(){

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("进度条Dialog");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(100);
        progressDialog.setProgress(0);
        progressDialog.show();

        ThreadTest01 threadTest01 = new ThreadTest01();
        threadTest01.start();


     }

     public void ShowDialog06(){

        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        final View dialogView = LayoutInflater.from(MainActivity.this)
                 .inflate(R.layout.dialog_cusmized,null);
        dialog.setTitle("自定义dialog");
        dialog.setView(dialogView);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText edit = dialogView.findViewById(R.id.edit_text);
                Log.d(TAG, "ShowDialog06: "+edit.getText().toString());
            }
        });

        dialog.show();

     }

    class ThreadTest extends Thread{
        @Override
        public void run(){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            waitingDialog.dismiss();
        }

    }
    class ThreadTest01 extends Thread {
        private boolean flag = true;
        private int num = 0;
        @Override
        public void run() {
            while (this.flag) {
                if (num >= 101) {
                    this.flag = !this.flag;
                    num = 0;
                    return;
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progressDialog.setProgress(num++);
            }
        }

    }
}





