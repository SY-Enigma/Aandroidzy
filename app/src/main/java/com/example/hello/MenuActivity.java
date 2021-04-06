package com.example.hello;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hello.databinding.ActivityMainBinding;

public class MenuActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        final LinearLayout root = binding.getRoot();
        setContentView(root);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}