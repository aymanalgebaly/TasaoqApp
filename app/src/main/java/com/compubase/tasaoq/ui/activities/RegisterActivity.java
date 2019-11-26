package com.compubase.tasaoq.ui.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.compubase.tasaoq.R;
import com.compubase.tasaoq.data.API;
import com.compubase.tasaoq.helper.RetrofitClient;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.img_register)
    CircleImageView imgRegister;
    @BindView(R.id.input_username)
    EditText inputUsername;
    @BindView(R.id.input_mail)
    EditText inputMail;
    @BindView(R.id.input_mobile_number)
    EditText inputMobileNumber;
    @BindView(R.id.input_password)
    EditText inputPassword;
    @BindView(R.id.btn_register)
    Button btnRegister;
    private String m_Text,username,email,phone,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_register, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_register:

                break;
            case R.id.btn_register:
                validate();

                break;
        }
    }

    private void validate() {

        username = inputUsername.getText().toString();
        email = inputMail.getText().toString();
        phone = inputMobileNumber.getText().toString();
        pass = inputPassword.getText().toString();

        if (TextUtils.isEmpty(username)){
            inputUsername.setError("اسم المستخدم مطلوب");
        }else if (TextUtils.isEmpty(email)){
            inputMail.setError("البريد الالكتروني مطلوب");
        }else if (TextUtils.isEmpty(phone)){
            inputMobileNumber.setError("رقم الهاتف مطلوب");
        }else if (TextUtils.isEmpty(pass)){
            inputPassword.setError("كلمة المرور مطلوبه");
        }else {

            Retrofit retrofit = RetrofitClient.getInstant();
            API api = retrofit.create(API.class);
            Call<ResponseBody> responseBodyCall = api.register("1", username, email, pass, phone, "img");
            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    try {
                        assert response.body() != null;
                        String string = response.body().string();

                        if (string.equals("True")){

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

    private void sendMail() {
        Retrofit retrofit = RetrofitClient.getInstant();
        API api = retrofit.create(API.class);
        Call<ResponseBody> responseBodyCall = api.SendSMS(email);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    assert response.body() != null;
                    String string = response.body().string();
                    if (string.equals("True")) {

                        enterCode();
                        openDialog();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void enterCode() {
        Retrofit retrofit = RetrofitClient.getInstant();
        API api = retrofit.create(API.class);
        Call<ResponseBody> responseBodyCall = api.EnterCode(m_Text);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String string = response.body().string();
                    if (string.equals("True")) {

                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Confirm Code");

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                enterCode();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
