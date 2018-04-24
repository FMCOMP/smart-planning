package mmjp.fsm.ford.com.mmjp.app.profile.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import mmjp.fsm.ford.com.mmjp.R;
import mmjp.fsm.ford.com.mmjp.app.profile.home.HomeContract;
import mmjp.fsm.ford.com.mmjp.app.profile.login.LoginView;
import mmjp.fsm.ford.com.mmjp.app.profile.registration.RegistrationView;
import mmjp.fsm.ford.com.mmjp.app.trip.modules.destination.DestinationView;

/**
 * Created by mfayajna on 4/2/18.
 */

public class HomeView extends AppCompatActivity implements HomeContract.View {

    HomeContract.Presenter presenter;
    @BindView(R.id.btn_log_in) Button logInButton;
    @BindView(R.id.btn_sign_up) Button signUpButton;
    @BindView(R.id.guest_link) TextView guestLoginLink;
    @BindView(R.id.btn_fb) ImageView facebookButton;
    @BindView(R.id.btn_google) ImageView googlePlusButton;
    private ProgressDialog pDialog;
    private Unbinder unbinder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_login);
        unbinder = ButterKnife.bind(this);
        presenter = new mmjp.fsm.ford.com.mmjp.app.profile.home.HomePresenter(this);
        loadData();
    }

    @Override
    public void showLoading() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    public void hideLoading() {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public void onBackPressed() {
        HomeView.this.finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hideLoading();
    }

    private void loadData() {
        presenter.onCreate();
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    @OnClick(R.id.btn_log_in)
    protected void attemptLogin() {
        Intent intent = new Intent(HomeView.this, LoginView.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_sign_up)
    protected void attemptSignUp() {
        Intent intent = new Intent(HomeView.this, RegistrationView.class);
        startActivity(intent);
    }

    @OnClick(R.id.guest_link)
    protected void guestLogin() {
        Intent intent = new Intent(HomeView.this, DestinationView.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_fb)
    protected void facebookLogin() {
        Toast.makeText(HomeView.this, "FB Button Clicked", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.btn_google)
    protected void googleLogin() {
        Toast.makeText(HomeView.this, "Google Plus Button Clicked", Toast.LENGTH_LONG).show();
    }

}