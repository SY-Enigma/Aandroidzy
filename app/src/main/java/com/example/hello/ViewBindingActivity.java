package com.example.hello;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hello.databinding.ActivityInformationBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViewBindingActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityInformationBinding binding;
    private boolean isRight = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 获取视图绑定对象
        binding = ActivityInformationBinding.inflate(getLayoutInflater());
        LinearLayout root = binding.getRoot();
        setContentView(root);

        binding.btnLogin1.setOnClickListener(this);
        binding.etPhone.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                String phone = binding.etPhone.getText().toString();
                if (!TextUtils.isEmpty(phone) && !validatePhone(phone)) {
                    binding.phoneLayout.setError("请输入正确的手机号");
                    binding.etPhone.setText("");
                    isRight = false;
                } else {
                    isRight = true;
                    binding.phoneLayout.setError("");
                }
            }
        });
        // 获取传递的值
        final Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        if (!TextUtils.isEmpty(username)) {
            binding.etName.setText(username);
        }

    }


    public String getCheckBoxText() {
        String result = "";
        if (binding.ch1.isChecked()) {
            result += binding.ch1.getText() + ",";
        }
        if (binding.ch2.isChecked()) {
            result += binding.ch2.getText() + ",";
        }
        if (binding.ch3.isChecked()) {
            result += binding.ch3.getText() + ",";
        }
        if (binding.ch4.isChecked()) {
            result += binding.ch4.getText() + ",";
        }
        return result.substring(0, result.length() - 1);
    }

    @Override
    public void onClick(View v) {
        int id = binding.sex.getCheckedRadioButtonId();
        String sex = binding.man.getText().toString();
        if (id == R.id.woman) {
            sex = binding.woman.getText().toString();
        }
        if (isRight) {
            String info = "用户名：" + binding.etName.getText() + "，手机号：" + binding.etPhone.getText()
                    + "，性别：" + sex + "\n喜欢的课程：" + getCheckBoxText();
            Snackbar.make(binding.llMain, info, Snackbar.LENGTH_SHORT).setAction("确认", null).show();
        }
    }

    private static final String PHONE_PATTERN = "^1[3-9]\\d{9}$";
    private boolean validatePhone(String phone) {
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}