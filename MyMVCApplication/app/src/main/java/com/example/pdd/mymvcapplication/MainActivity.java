package com.example.pdd.mymvcapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 添加butterlnife  -android-view诸如框架
 * 1.https://github.com/JakeWharton/butterknife   github地址
 * 2.build-gradle当中添加
 *   implementation 'com.jakewharton:butterknife:8.3.0'
 *   annotationProcessor 'com.jakewharton:butterknife-compiler:8.3.0'
 * 3.modules的build.gradle当中添加
 *   classpath 'com.jakewharton:butterknife-gradle-plugin:8.3.0'
 * 4.Android Studio 3.0以后的版本与butterknife高版本冲突所以在进行选取的butterknife的版本的时候需要将其版本选取在8.4及一下的版本
 */
public class MainActivity extends Activity {
    //逻辑判断，UI操作
    String TAG = "=======myMVCActvity=======";
    @BindView(R.id.rd_1)
    RadioGroup rd1;
    @BindView(R.id.rd_2)
    RadioGroup rd2;
    @BindView(R.id.rd_3)
    RadioGroup rd3;
    @BindView(R.id.edit1)
    EditText edit1;
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.text1)
    TextView text1;
    @BindString(R.string.app_name) //绑定字符串@BindString
    String str1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);
        initView();
        Toast.makeText(MainActivity.this,"onCreate"+str1,Toast.LENGTH_SHORT).show();
        registerListener();
    }

    private void registerListener() {
        //逻辑控制
        //实际上就只要监听提交按钮即可，因为其他的按钮只是获取数据，不需要按下后立即更改UI

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = edit1.getText().toString();
                if (TextUtils.isEmpty(key)) {
                    Toast.makeText(MainActivity.this, "key不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                String num = rd1.getCheckedRadioButtonId() == R.id.Rd_bt1 ? "5" : "7";
                String type = null;
                switch (rd2.getCheckedRadioButtonId()) {
                    case R.id.Rd_bt3:
                        type = "1";
                        break;
                    case R.id.Rd_bt4:
                        type = "2";
                        break;
                    case R.id.Rd_bt5:
                        type = "3";
                        break;
                    case R.id.Rd_bt6:
                        type = "4";
                        break;
                    case R.id.Rd_bt7:
                        type = "5";
                        break;
                }
                String yy = null;
                switch (rd3.getCheckedRadioButtonId()) {
                    case R.id.Rd_bt8:
                        yy = "1";
                        break;
                    case R.id.Rd_bt9:
                        yy = "2";
                        break;
                    case R.id.Rd_bt10:
                        yy = "3";
                        break;
                }
                final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
                dialog.setTitle("提示");
                dialog.setMessage("开始请求");
                dialog.show();

                //请求数据
                CangTouShiModel model = new CangTouShiModel();
                //OkHttp的异步请求，在子线程中
                model.doRequest(num, type, yy, key, new ICangTouShi.Bean<CangTouShiBean>() {
                    @Override
                    public void onError(String msg) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                                Log.d(TAG, "get msg failed");
                                Toast.makeText(MainActivity.this, "msg", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onSuccess(final CangTouShiBean bean) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                                List<String> list = (List<String>) bean.getShowapi_res_body().getList();
                                text1.setText("");
                                System.out.println(bean.getShowapi_res_body().toString());
                                for (String s : list) {
                                    text1.append(s + "\n");
                                }

                            }
                        });

                    }
                });
            }
        });
    }

    private void initView() {
        rd1.check(R.id.Rd_bt1);
        rd2.check(R.id.Rd_bt3);
        rd3.check(R.id.Rd_bt8);
    }
}