package com.ocr.firebaseoc.ui;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseUser;
import com.ocr.firebaseoc.BaseActivity;
import com.ocr.firebaseoc.R;
import com.ocr.firebaseoc.databinding.ActivityProfileBinding;
import com.ocr.firebaseoc.manager.UserManager;

public class ProfileActivity extends BaseActivity<ActivityProfileBinding> {

    private final UserManager userManager = UserManager.getInstance();

    @Override
    protected ActivityProfileBinding getViewBinding() {
        return ActivityProfileBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateUIWithUserData();
        setupListeners();
    }

    private void setupListeners() {
        binding.updateButton.setOnClickListener(view -> {
        });
        binding.signOutButton.setOnClickListener(view -> userManager.signOut(this).addOnSuccessListener(aVoid -> finish()));
        binding.deleteButton.setOnClickListener(view -> new AlertDialog.Builder(this)
                .setMessage(R.string.popup_message_confirmation_delete_account)
                .setPositiveButton(R.string.popup_message_choice_yes, (dialogInterface, i) ->
                        userManager.deleteUser(ProfileActivity.this)
                                .addOnSuccessListener(aVoid -> finish()
                                )
                )
                .setNegativeButton(R.string.popup_message_choice_no, null)
                .show());
    }

    private void updateUIWithUserData() {
        if (userManager.isCurrentUserLogged()) {
            FirebaseUser user = userManager.getCurrentUser();

            if (user.getPhotoUrl() != null) {
                setProfilePicture(user.getPhotoUrl());
            }
            setTextUserData(user);
        }
    }

    private void setProfilePicture(Uri profilePictureUrl) {
        Glide.with(this)
                .load(profilePictureUrl)
                .circleCrop()
                .into(binding.profileImageView);
    }

    private void setTextUserData(FirebaseUser user) {
        //Get email & username from User
        String email = TextUtils.isEmpty(user.getEmail()) ? getString(R.string.info_no_email_found) : user.getEmail();
        String username = TextUtils.isEmpty(user.getDisplayName()) ? getString(R.string.info_no_username_found) : user.getDisplayName();
        //Update views with data
        binding.usernameEditText.setText(username);
        binding.emailTextView.setText(email);
    }

}
