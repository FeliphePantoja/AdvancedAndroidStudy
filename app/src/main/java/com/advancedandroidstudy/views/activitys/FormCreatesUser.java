package com.advancedandroidstudy.views.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.advancedandroidstudy.R;
import com.advancedandroidstudy.models.User;
import com.advancedandroidstudy.repository.Resource;
import com.advancedandroidstudy.repository.UserRepository;
import com.advancedandroidstudy.views.viewmodel.FormUserViewModel;
import com.advancedandroidstudy.views.viewmodel.factory.FormUserViewModelFactory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import static com.advancedandroidstudy.helpers.Constant.CHAVE_APP;
import static com.advancedandroidstudy.helpers.Constant.POSICAO_INVALIDA;
import static com.advancedandroidstudy.helpers.Constant.POSICAO_LISTA;

public class FormCreatesUser extends AppCompatActivity {

    private EditText form_user_name, form_user_age;
    private Button buttonDelete;
    private FormUserViewModel formUserViewModel;
    private int positionReceived;
    private User userReceived = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_creates_user);

        initializesComponents();
        setAttributes();
        checksIfThereIsReceivedData();
    }

    private void initializesComponents() {
        this.form_user_name = findViewById(R.id.form_user_name);
        this.form_user_age = findViewById(R.id.form_user_age);
        this.buttonDelete = findViewById(R.id.buttonDelete);
    }

    private void setAttributes() {
        FormUserViewModelFactory formUserViewModelFactory = new FormUserViewModelFactory(new UserRepository(this));
        this.formUserViewModel = new ViewModelProvider(this, formUserViewModelFactory).get(FormUserViewModel.class);

    }

    private void checksIfThereIsReceivedData() {
        Intent receivedData = getIntent();
        if (receivedData.hasExtra(CHAVE_APP)) {
            this.userReceived = (User) receivedData.getSerializableExtra(CHAVE_APP);
            positionReceived = receivedData.getIntExtra(POSICAO_LISTA, POSICAO_INVALIDA);
            setFields(this.userReceived);
        }
    }

    private void setFields(User userReceived) {
        this.form_user_name.setText(userReceived.getName());
        this.form_user_age.setText(String.valueOf(userReceived.getAge()));
        this.buttonDelete.setVisibility(View.VISIBLE);
    }

    public void ClickFormSave(View view) {
        checkMode();
    }

    private void checkMode() {
        if (checkFields(new EditText[]{this.form_user_name, this.form_user_age})) {

            if (this.userReceived != null) {
                edit();
            } else {
                save();
            }
        }
    }

    private void edit() {

        this.userReceived.setName(this.form_user_name.getText().toString());
        this.userReceived.setAge(Integer.parseInt(this.form_user_age.getText().toString()));

        this.formUserViewModel.editUser(this.userReceived).observe(this, new Observer<Resource<Void>>() {
            @Override
            public void onChanged(Resource<Void> voidResource) {

                String failure = voidResource.failure;
                if (failure != null) {
                    Toast.makeText(FormCreatesUser.this, failure, Toast.LENGTH_SHORT).show();
                } else {
                    onBackPressed();
                }
            }
        });
    }

    private void save() {
        String user = this.form_user_name.getText().toString();
        int age = Integer.parseInt(this.form_user_age.getText().toString());
        User userCreate = new User(user, age);

        this.formUserViewModel.saveUser(userCreate).observe(this, new Observer<Resource<Long>>() {
            @Override
            public void onChanged(Resource<Long> voidResource) {

                Long success = voidResource.success;
                if (success != null) {
                    Toast.makeText(FormCreatesUser.this, "Save id= " + success, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean checkFields(EditText[] fields) {
        for (EditText currentField : fields) {
            if (currentField.getText().toString().length() <= 0) {
                return false;
            }
        }
        return true;
    }

    public void ClickFormDelete(View view) {
        if (this.userReceived != null) {

            this.formUserViewModel.deleteUser(this.userReceived).observe(this, new Observer<Resource<Void>>() {
                @Override
                public void onChanged(Resource<Void> voidResource) {
                    String failure = voidResource.failure;
                    if (failure != null) {
                        Toast.makeText(FormCreatesUser.this, failure, Toast.LENGTH_SHORT).show();
                    } else {
                        onBackPressed();
                    }
                }
            });
        }
    }
}