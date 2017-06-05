package com.example.okhttptest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TEST_LOG = "庆神庆神，这里是中how收到请回复嗷！";
    /**
     * 在这里填入ip地址
     **/
    private String get_path = "";
    private String post_path = "";
    private final static int POST = 1 ;
    private final static int GET = 2 ;
    private EditText edit_msg;
    private TextView txt_show;
    private String send_msg = "";
    private String got_msg = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_send).setOnClickListener(this);
        txt_show = (TextView) findViewById(R.id.txt_msg);
        edit_msg =(EditText)findViewById(R.id.edit_msg);

        new Thread(new Runnable() {
            @Override
            public void run() {


                while (Thread.currentThread().isInterrupted()) {

                    Message rmsg = new Message();
                    rmsg.what = GET;
                    mHandler.sendMessage(rmsg);
                    try {
                        got_msg += HttpTool.GetStr(get_path);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        Toast.makeText(MainActivity.this, "接收线程中断，请查看报错", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }

    //生成一个handler
    Handler mHandler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case POST:
                    //调用工具类httptool的静态方法postStr
                    HttpTool.PostStr(TEST_LOG,post_path);
                    break;
                case GET:
                    //调用工具类httptool的静态方法postStr
                    txt_show.setText(got_msg);
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_send:
                // TODO: 2017/6/5  在这里执行按钮
//                if (edit_msg.getText().equals("")) {
//                    send_msg = edit_msg.getText().toString();
//                    edit_msg.setText("");
//                    HttpTool.PostStr(send_msg, post_path);
//                }
//                else {
//                    Toast.makeText(this, "您的输入为空", Toast.LENGTH_SHORT).show();
//                }
                Message smsg = new Message();
                smsg.what = POST;
                mHandler.sendMessage(smsg);

                break;
            default:
                break;
        }
    }
}
