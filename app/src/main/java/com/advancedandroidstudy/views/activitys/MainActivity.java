package com.advancedandroidstudy.views.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.advancedandroidstudy.R;
import com.advancedandroidstudy.models.User;
import com.advancedandroidstudy.repository.UserRepository;
import com.advancedandroidstudy.views.adapters.Listener.OnItemClickListener;
import com.advancedandroidstudy.views.adapters.UserAdapter;
import com.advancedandroidstudy.views.viewmodel.ListUsersViewModel;
import com.advancedandroidstudy.views.viewmodel.factory.ListUserViewModelFactory;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import static com.advancedandroidstudy.helpers.Constant.CHAVE_APP;
import static com.advancedandroidstudy.helpers.Constant.CODIGO_REQUISICAO_ALTERA;
import static com.advancedandroidstudy.helpers.Constant.POSICAO_LISTA;

public class MainActivity extends AppCompatActivity {

    private RecyclerView myRecyclerView;
    private UserAdapter userAdapter;
    private ListUsersViewModel listUsersViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializesComponents();
        setAttributes();
        getUseresDataBase();
    }

    private void initializesComponents() {
        this.myRecyclerView = findViewById(R.id.recyclerMain);
        this.myRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void setAttributes() {
        this.userAdapter = new UserAdapter(this);
        this.myRecyclerView.setAdapter(this.userAdapter);
        UserRepository userRepository = new UserRepository(this);

        ListUserViewModelFactory listUserViewModelFactory = new ListUserViewModelFactory(userRepository);
        this.listUsersViewModel = new ViewModelProvider(this, listUserViewModelFactory).get(ListUsersViewModel.class);
    }

    private void getUseresDataBase() {
        this.listUsersViewModel.getUserTable().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (users != null) {
                    userAdapter.setListForAdpter(users);
                    onItemClick();
                }
            }
        });
    }

    private void onItemClick() {

        this.userAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Object object, final int posicao) {
                User userModel = (User) object;
                Intent intent = new Intent(MainActivity.this, FormCreatesUser.class);
                intent.putExtra(CHAVE_APP, userModel);
                intent.putExtra(POSICAO_LISTA, posicao);
                startActivityForResult(intent, CODIGO_REQUISICAO_ALTERA);
            }
        });
    }

    public void ClickInsertUser(View view) {
        startActivity(new Intent(this, FormCreatesUser.class));
    }
}