package com.example.javadi.ui.auth;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;
import com.example.javadi.R;
import com.example.javadi.models.User;
import com.example.javadi.ui.main.MainActivity;
import com.example.javadi.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {
    private static final String TAG = "AuthActivity";
    
    EditText userId;

    @Inject
    ViewModelProviderFactory providerFactory;


 @Inject
 AuthViewModel viewModel;

    @Inject
    Drawable Logo;

    @Inject
    RequestManager requestManager;

    ProgressBar progress_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        userId = findViewById(R.id.userId);
        progress_bar = findViewById(R.id.progress_bar);

        viewModel = ViewModelProviders.of(this,providerFactory).get(AuthViewModel.class);

        findViewById(R.id.login_btn).setOnClickListener(this);

        setLogo();

        subscribeObservers();

    }


    private void setLogo(){
        requestManager.load(Logo)
                .into((ImageView)findViewById(R.id.login_logo));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_btn:
                attempLogin();
                break;
        }

    }

    public void subscribeObservers(){
        viewModel.observeUser().observe(this,new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null){
                    switch (userAuthResource.status){
                        case LOADING: {
                            showProgressBar(true);
                            break;
                        }
                        case AUTHENTICATED:{
                            showProgressBar(false);
                            onLoginSuccess();
                            break;
                        }

                        case ERROR:{
                            showProgressBar(false);
                            break;
                        }

                        case NOT_AUTHENTICATED:{
                            showProgressBar(false);
                            break;
                        }

                    }
                }

            }
        });
    }

    private void onLoginSuccess(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void showProgressBar(Boolean isVisible){
        if (isVisible){
            progress_bar.setVisibility(View.VISIBLE);
        }else {
            progress_bar.setVisibility(View.GONE);
        }
    }

    private void attempLogin() {
        if (TextUtils.isEmpty(userId.getText().toString())){
            return;
        }

        viewModel.authenticateWithId(Integer.parseInt(userId.getText().toString()));
    }
}