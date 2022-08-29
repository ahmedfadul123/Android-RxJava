package com.example.javadi.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.javadi.BaseActivity;
import com.example.javadi.R;
import com.example.javadi.SessionManager;
import com.example.javadi.ui.main.post.PostFragment;
import com.example.javadi.ui.main.profile.ProfileFragment;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tesrFragment();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout_item:
                sessionManager.logOut();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


    private void tesrFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new PostFragment()).commit();
    }


}