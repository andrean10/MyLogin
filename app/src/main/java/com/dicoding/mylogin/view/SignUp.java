package com.dicoding.mylogin.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dicoding.mylogin.R;
import com.dicoding.mylogin.databinding.ActivitySignUpBinding;
import com.dicoding.mylogin.viewmodel.GoAppsViewModel;

public class SignUp extends AppCompatActivity implements View.OnClickListener{
    private ActivitySignUpBinding binding;
    private GoAppsViewModel goAppsViewModel;
    private static final String MUST_NOT_NULL = "Field tidak boleh kosong!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        goAppsViewModel = new GoAppsViewModel(SignUp.this);

        binding.btnCreateAccount.setOnClickListener(this);
        binding.tvSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_createAccount:
                userCreateAccount();
                break;
            case R.id.tv_signIn:
                userLogin();
                break;
        }
    }

    private void userCreateAccount() {
        String username = binding.edtUsername.getText().toString().trim();
        String password = binding.edtPassword.getText().toString().trim();
        String email = binding.edtEmail.getText().toString().trim();
        String phoneNumber = binding.edtPhoneNumber.getText().toString().trim();
        String fullname = binding.edtFullname.getText().toString().trim();
        boolean state = false;

        if (username.isEmpty()) {
            state = true;
            binding.edtUsername.setError(MUST_NOT_NULL);
        }

        if (password.isEmpty()) {
            state = true;
            binding.edtPassword.setError(MUST_NOT_NULL);
        }

        if (email.isEmpty()) {
            state = true;
            binding.edtEmail.setError(MUST_NOT_NULL);
        }

        if (phoneNumber.isEmpty()) {
            state = true;
            binding.edtPhoneNumber.setError(MUST_NOT_NULL);
        }

        if (fullname.isEmpty()) {
            state = true;
            binding.edtFullname.setError(MUST_NOT_NULL);
        }

        if (!state) {
            goAppsViewModel.register(username, password, email, phoneNumber, fullname);
        }
    }

    private void userLogin() {
        Intent intent = new Intent(SignUp.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
