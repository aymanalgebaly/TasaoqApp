package com.compubase.tasaoq.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.compubase.tasaoq.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.lin_one)
    LinearLayout linOne;
    @BindView(R.id.ed_username)
    EditText edUsername;
    @BindView(R.id.ed_password)
    EditText edPassword;
    @BindView(R.id.txt_forgot)
    TextView txtForgot;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.txt_register)
    TextView txtRegister;
    @BindView(R.id.img_instagram)
    CircleImageView imgInstagram;
    @BindView(R.id.img_twitter)
    CircleImageView imgTwitter;
    @BindView(R.id.img_facebook)
    CircleImageView imgFacebook;
    @BindView(R.id.img_google_plus)
    CircleImageView imgGooglePlus;
    @BindView(R.id.lin_two)
    LinearLayout linTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    @OnClick({R.id.txt_forgot, R.id.btn_login, R.id.txt_register, R.id.img_instagram, R.id.img_twitter, R.id.img_facebook, R.id.img_google_plus})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_forgot:
                break;
            case R.id.btn_login:
                startActivity(new Intent(MainActivity.this,HomeActivity.class));
                break;
            case R.id.txt_register:
                break;
            case R.id.img_instagram:
                break;
            case R.id.img_twitter:
                break;
            case R.id.img_facebook:
                break;
            case R.id.img_google_plus:
                break;
        }
    }
}
