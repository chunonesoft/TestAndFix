package com.chunsoft.testandfix;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String APATCH_PATH = "/hotfix.apatch";

    private Button btn_show,btn_hotfix;
    private TextView tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_show = (Button) findViewById(R.id.btn_show);
        btn_hotfix = (Button) findViewById(R.id.btn_hotfix);

        tv_content = (TextView) findViewById(R.id.tv_content);

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //下载补丁
                DownloadManager.Request request = new DownloadManager.Request(
                        Uri.parse("https://github.com/chunonesoft/BlogContent/raw/master/hotfix.apatch"));
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
                //设置通知栏标题
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
                request.setTitle("下载");
                request.setDescription("补丁正在下载");
                request.setAllowedOverRoaming(false);
                //设置文件存放目录
                request.setDestinationInExternalFilesDir(MainActivity.this, Environment.getExternalStorageDirectory().getAbsolutePath(), "hotfix.apatch");
                DownloadManager downManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
                long id= downManager.enqueue(request);
                showContent();
            }
        });

        btn_hotfix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String patchFileStr = Environment.getExternalStorageDirectory().getAbsolutePath() + APATCH_PATH;
                Log.e("--------------",patchFileStr);
                try {
                    MainApplication.mPatchManager.addPatch(patchFileStr);
                    Log.e("--------------","ok");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //显示TextView内容
    private void showContent() {
        tv_content.setText("before patch");
        Toast.makeText(this,"before patch",Toast.LENGTH_SHORT).show();
    }
}
