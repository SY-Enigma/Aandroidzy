package com.example.hello;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.hello.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
//    private TextView et_tv_contacts;

    private LinearLayout et_Info;
    private  LinearLayout et_Contacts;
    private  LinearLayout et_Watch;
    private  LinearLayout et_Dynamic;



    private  static  final String ID_BASIC = "basic";
    private  static  final  String ID_HIGH  = "high";
    private ActivityMainBinding binding;
    public static  final  String[] NOTIFICATION_STYLES = {"---请选择----","基本样式","悬浮样式","长文本样式","大图样式","收件箱样式","自定义View"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化布局
        et_Info =findViewById(R.id.info);
        et_Contacts =  findViewById(R.id.contacts);
        et_Watch =findViewById(R.id.watch);
        et_Dynamic = findViewById(R.id.dynamic);

        et_Dynamic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivityForResult(intent,1);
            }
        });

//        et_tv_contacts=findViewById(R.id.tv_contacts);
//        et_tv_contacts.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });



        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        setContentView(root);

        // 创建两个通知通道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChannel(ID_BASIC,"一般消息", NotificationManager.IMPORTANCE_DEFAULT);
            createNotificationChannel(ID_HIGH,"订阅消息",NotificationManager.IMPORTANCE_HIGH);
        }
        //调用基本样式的方法
        showBasicNotification();

        //给Spinner控件设置数据适配器
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,NOTIFICATION_STYLES);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spStyle.setAdapter(adapter);
//        binding.spStyle.setOnItemSelectedListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName , int importance) {
        final NotificationChannel channel = new NotificationChannel(channelId,channelName,importance);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
    }
//
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        //实现方法
//        String style = NOTIFICATION_STYLES[position];
//        binding.tvDescription.setText(style);
//        if("基本样式".equals(style)){
//
//        }
//
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
    //基本样式的方法
    public void  showBasicNotification(){
        final Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 ,intent, PendingIntent.FLAG_CANCEL_CURRENT);
        //
        final Notification notification = new NotificationCompat.Builder(this , ID_BASIC)
                .setSmallIcon(R.drawable.ic_message)
                .setContentTitle("QQ消息")
                .setContentText("好友消息")
                .setContentIntent(pendingIntent)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.pic))
                .build();

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(100,notification);
    }

    @Override
    public void onClick(View v) {

    }
}