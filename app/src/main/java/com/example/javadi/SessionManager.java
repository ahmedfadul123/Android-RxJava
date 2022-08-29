package com.example.javadi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.example.javadi.models.User;
import com.example.javadi.ui.auth.AuthResource;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionManager {

    private MediatorLiveData<AuthResource<User>> cachedUser = new MediatorLiveData<>() ;

    @Inject
    public SessionManager() {

    }

    public void createUserSession(LiveData<AuthResource<User>> source){

        if (cachedUser != null){
            cachedUser.setValue(AuthResource.loading((User)null));
            cachedUser.addSource(source, new Observer<AuthResource<User>>() {
                @Override
                public void onChanged(AuthResource<User> userAuthResource) {
                    cachedUser.setValue(userAuthResource);
                    cachedUser.removeSource(source);

                }
            });

        }

    }


   public void logOut(){
        cachedUser.setValue(AuthResource.logout());
   }



public LiveData<AuthResource<User>> getCachedUser(){
        return cachedUser;
}

public void setLoadingStataus(){
    cachedUser.setValue(AuthResource.loading((User)null));

}


}
