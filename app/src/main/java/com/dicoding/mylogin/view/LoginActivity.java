package com.dicoding.mylogin.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dicoding.mylogin.R;
import com.dicoding.mylogin.databinding.ActivityLoginBinding;
import com.dicoding.mylogin.databinding.ActivityMainBinding;
import com.dicoding.mylogin.viewmodel.GoAppsViewModel;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityLoginBinding binding;
    private GoAppsViewModel goAppsViewModel;
    private static final String MUST_NOT_NULL = "Field tidak boleh kosong!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        goAppsViewModel = new GoAppsViewModel(LoginActivity.this);

        binding.btnLogin.setOnClickListener(this);
        binding.tvSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                userLogin();
                break;
            case R.id.tv_signUp:
                userRegister();
                break;
            default:
                break;
        }
    }

    private void userLogin() {
        String username = binding.edtUsername.getText().toString().trim();
        String password = binding.edtPassword.getText().toString().trim();
        boolean state = false;

        if (username.isEmpty()) {
            state = true;
            binding.edtUsername.setError(MUST_NOT_NULL);
        }

        if (password.isEmpty()) {
            state = true;
            binding.edtPassword.setError(MUST_NOT_NULL);
        }

        if (!state) {
            goAppsViewModel.login(username, password);
        }
    }

    private void userRegister() {
        Intent moveSignUp = new Intent(LoginActivity.this, SignUp.class);
        startActivity(moveSignUp);
    }
}
