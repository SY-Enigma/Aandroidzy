package com.example.hello;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//注册界面方法
public class RegisterActivity extends AppCompatActivity {
    private EditText et_name;
    private  EditText et_password;
    private  EditText et_psw;
    private  String userName,password,psw;
    private RadioGroup rgSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //获取按钮点击事件
        Button btn_register = findViewById(R.id.btn_register);
        //初始化按钮
        et_name = findViewById(R.id.et_name1);
        et_password = findViewById(R.id.et_password1);
        et_psw =findViewById(R.id.et_psw1);
        rgSex = findViewById(R.id.sex1);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入值
                userName = et_name.getText().toString().trim();
                password = et_password.getText().toString().trim();
                psw = et_psw.getText().toString().trim();

              int sex;
              int sexId = rgSex.getCheckedRadioButtonId();
              switch (sexId){
                  case R.id.woman1 : sex = 0; break;
                  case  R.id.man1 : sex =1; break;
                  default:
                      sex = -1; break;
              }
              if (TextUtils.isEmpty(userName)) {
                  Toast.makeText(RegisterActivity.this,"请输入用户名",Toast.LENGTH_SHORT).show();;
              }else  if (TextUtils.isEmpty(password)){
                  Toast.makeText(RegisterActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
              }else if (TextUtils.isEmpty(psw)){
                  Toast.makeText(RegisterActivity.this,"请再次输入密码",Toast.LENGTH_SHORT).show();
              }else if (sex <0){
                  Toast.makeText(RegisterActivity.this,"请选择性别",Toast.LENGTH_SHORT).show();
              }else if (!password.equals(psw)){
                  Toast.makeText(RegisterActivity.this,"输入的两次密码不一样",Toast.LENGTH_SHORT).show();
              }else  if (isExistUserName(userName)){
                  Toast.makeText(RegisterActivity.this,"此用户名已存在",Toast.LENGTH_SHORT).show();
              }else {
                  Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();

                  saveRegisterInfo(userName, psw);
                  Intent data = new Intent();
                  data.putExtra("userName", userName);
                  setResult(RESULT_OK, data);
                  RegisterActivity.this.finish();
              }

            }
        });
    }
    private boolean  isExistUserName(String userName){
        boolean has_userName = false;
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        String spPassword = sp.getString(userName,"");
        if (!TextUtils.isEmpty(spPassword)){
            has_userName = true;
        }
        return has_userName;
    }

    private  void  saveRegisterInfo(String userName, String password){
      SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
      SharedPreferences.Editor editor = sp.edit();
      editor.putString(userName,password);
      editor.apply();
    }
}