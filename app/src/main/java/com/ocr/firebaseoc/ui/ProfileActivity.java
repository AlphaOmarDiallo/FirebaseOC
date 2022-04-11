package com.ocr.firebaseoc.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.ocr.firebaseoc.BaseActivity;
import com.ocr.firebaseoc.databinding.ActivityProfileBinding;

public class ProfileActivity extends BaseActivity<ActivityProfileBinding> {

    @Override
    protected ActivityProfileBinding getViewBinding() {
        return ActivityProfileBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupListeners();
    }

    private void setupListeners() {
        binding.updateButton.setOnClickListener(view -> {
        });
        binding.signOutButton.setOnClickListener(view -> {
        });
        binding.deleteButton.setOnClickListener(view -> {
        });
    }
}
