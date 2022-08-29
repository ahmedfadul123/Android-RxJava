package com.example.javadi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.example.javadi.models.User;
import com.example.javadi.ui.auth.AuthActivity;
import com.example.javadi.ui.auth.AuthResource;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class BaseActivity extends DaggerAppCompatActivity {

    @Inject
   public SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribeObservers();
    }


    private void subscribeObservers(){
        sessionManager.getCachedUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {

                if (userAuthResource != null) {
                    switch (userAuthResource.status) {
                        case LOADING: {
                            break;
                        }
                        case AUTHENTICATED: {
                            break;
                        }

                        case ERROR: {

                            break;
                        }

                        case NOT_AUTHENTICATED: {
                            navLogin();
                            break;
                        }

                    }
                }



        }

});
    }


    private void navLogin(){
        Intent intent = new Intent(this,AuthActivity.class);
        startActivity(intent);
        finish();
    }
}


