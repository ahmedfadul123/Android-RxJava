package com.example.javadi.ui.main.post;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.javadi.SessionManager;
import com.example.javadi.models.Post;
import com.example.javadi.network.main.MainApi;
import com.example.javadi.ui.auth.AuthResource;
import com.example.javadi.ui.main.Resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PostFragmentviewModel extends ViewModel {
    private SessionManager sessionManager;
    private MainApi mainApi;
    private MediatorLiveData<Resource<List<Post>>> posts;

    @Inject
    public PostFragmentviewModel(MainApi mainApi,SessionManager sessionManager) {
        this.mainApi = mainApi;
        this.sessionManager = sessionManager;
    }


    public LiveData<Resource<List<Post>>> observePosts(){
        if (posts == null){
            posts = new MediatorLiveData<>();
            posts.setValue(Resource.loading((List<Post>) null));

            final LiveData<Resource<List<Post>>> source = LiveDataReactiveStreams.fromPublisher(
                    mainApi.getPostsFromUser(sessionManager.getCachedUser().getValue().data.getId())
                    .onErrorReturn(new Function<Throwable, List<Post>>() {
                        @Override
                        public List<Post> apply( Throwable throwable) throws Exception {
                            Post post = new Post();
                            post.setId(-1);
                            ArrayList<Post> posts = new ArrayList<>();
                            posts.add(post);
                            return posts;
                        }
                    })
                    .map(new Function<List<Post>, Resource<List<Post>>>() {
                        @Override
                        public Resource<List<Post>> apply( List<Post> posts) throws Exception {
                            if (posts.size() > 0){
                                if (posts.get(0).getId() == -1){
                                    return Resource.error("Something went wrong", null);
                                }
                            }
                            return Resource.success(posts);
                        }
                    })

                    .subscribeOn(Schedulers.io())
                     );
                    posts.addSource(source, new Observer<Resource<List<Post>>>() {
                        @Override
                        public void onChanged(Resource<List<Post>> listResource) {
                            posts.setValue(listResource);
                            posts.removeSource(source);

                        }
                    });

        }

        return posts;
    }
}
