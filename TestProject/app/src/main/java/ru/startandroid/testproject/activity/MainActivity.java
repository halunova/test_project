package ru.startandroid.testproject.activity;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.startandroid.testproject.R;
import ru.startandroid.testproject.adapter.RepoAdapter;
import ru.startandroid.testproject.api.AccessToken;
import ru.startandroid.testproject.api.UserRepo;
import ru.startandroid.testproject.api.GithubAPI;
import ru.startandroid.testproject.controller.Controller;
import ru.startandroid.testproject.viewmodel.LiveDataRepoListViewModel;

public class MainActivity extends AppCompatActivity {
    private String clientId = "b0a3552b65ffe5311162";
    private String clientSecret = "0904d3e58d9141bc9aeafefa3aef405802f5dde2";
    private String redirectUri = "test-application://callback";
    private String access_token = null;
    RecyclerView recyclerView;
    RepoAdapter adapter;
    List<UserRepo> my_repos, inv_repos;
    GithubAPI api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Uri uri = getIntent().getData();
        if (uri != null && uri.toString().startsWith(redirectUri)) {

            my_repos = new ArrayList<>();
            inv_repos = new ArrayList<>();

            recyclerView = findViewById(R.id.repos_recycle_view);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            adapter = new RepoAdapter(my_repos);
            recyclerView.setAdapter(adapter);

            final TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
            tabHost.setup();

            TabHost.TabSpec tabSpec;

            tabSpec = tabHost.newTabSpec("my");
            tabSpec.setIndicator("My repos");
            tabSpec.setContent(R.id.repos_recycle_view);
            tabHost.addTab(tabSpec);

            tabSpec = tabHost.newTabSpec("other");
            tabSpec.setIndicator("Invited at");
            tabSpec.setContent(R.id.repos_recycle_view);
            tabHost.addTab(tabSpec);

            tabHost.setCurrentTabByTag("my");

           String code = uri.getQueryParameter("code");

            api = Controller.getApi("https://github.com");

            api.getAccessToken(clientId, clientSecret, code).enqueue(new Callback<AccessToken>() {
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                    access_token = response.body().getAccessToken();

                   api = Controller.getApi();

                    api.getUserRepos(access_token).enqueue(new Callback<List<UserRepo>>() {
                        @Override
                        public void onResponse(Call<List<UserRepo>>call, Response<List<UserRepo>> response) {
                            if (response.isSuccessful()){
                                for(UserRepo repo:response.body()){
                                    if(repo.getPermission().getAdmin())
                                        my_repos.add(repo);
                                    else inv_repos.add(repo);

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<List<UserRepo>> call, Throwable t) {
                            Log.d("My_logs", "error");
                        }
                    });

                }

                @Override
                public void onFailure(Call<AccessToken> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Internet error!", Toast.LENGTH_SHORT).show();
                }
            });
            adapter.setRepos(my_repos);
            recyclerView.getAdapter().notifyDataSetChanged();

            // обработчик переключения вкладок
            tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
                public void onTabChanged(String tabId) {
                    if(tabId.equals("my")){
                        adapter.setRepos(my_repos);}
                    else adapter.setRepos(inv_repos);
                    recyclerView.getAdapter().notifyDataSetChanged();
                }
            });
        }
    }
}
