package mmjp.fsm.ford.com.profile.registration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import mmjp.fsm.ford.com.R;
import mmjp.fsm.ford.com.profile.registration.accounts.AccountLinkingView;
import mmjp.fsm.ford.com.planner.modules.destination.DestinationView;

/**
 * Created by userpmp on 2/15/18.
 */

public class RegistrationView extends AppCompatActivity implements RegistrationContract.View{

    RegistrationContract.Presenter presenter;

    @BindView( R.id.input_full_name ) EditText fullnameEditText;
    @BindView( R.id.input_email ) EditText emailEditText;
    @BindView( R.id.input_password ) EditText passwordEditText;
    @BindView( R.id.input_reenter_password ) EditText reenterEditText;
    @BindView( R.id.btn_sign_up ) Button signupButton;
    @BindView(R.id.guest_link) TextView guestLoginLink;
    @BindView(R.id.btn_fb) ImageView facebookButton;
    @BindView(R.id.btn_google) ImageView googlePlusButton;
    @BindView(R.id.back_button) ImageView backButton;

    private ProgressDialog pDialog;
    private Unbinder unbinder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        unbinder = ButterKnife.bind(this);
        presenter = new RegistrationPresenter(this);

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
        RegistrationView.this.finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        hideLoading();
    }

    @OnClick(R.id.btn_sign_up)
    protected void attemptSignUp() {
        Intent intent = new Intent(RegistrationView.this, AccountLinkingView.class);
        startActivity(intent);
    }

    @OnClick(R.id.guest_link)
    protected void guestLogin() {
        Intent intent = new Intent(RegistrationView.this, DestinationView.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_fb)
    protected void facebookLogin() {
        Toast.makeText(RegistrationView.this, "FB Button Clicked", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.btn_google)
    protected void googleLogin() {
        Toast.makeText(RegistrationView.this, "Google Plus Button Clicked", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.back_button)
    protected void backPressed() {
        onBackPressed();
    }

    private void loadData() {
        presenter.onCreate();
    }

}