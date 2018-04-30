package mmjp.fsm.ford.com.mmjp.app.forgetpasscode;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import mmjp.fsm.ford.com.mmjp.R;
import mmjp.fsm.ford.com.mmjp.app.login.LoginView;

/**
 * Created by mfayajna on 4/3/18.
 */

public class ForgetPasscodeView extends AppCompatActivity implements ForgetPasscodeContract.View {

     ForgetPasscodeContract.Presenter presenter;
    @BindView(R.id.btn_reset_password) Button resetPassword;
    @BindView(R.id.input_email) EditText emailEditText;
    @BindView(R.id.back_button) ImageView backButton;
    private ProgressDialog pDialog;
    private Unbinder unbinder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_passcode);
        unbinder = ButterKnife.bind(this);
        presenter = new ForgetPasscodePresenter(this);
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
        ForgetPasscodeView.this.finish();
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
    @OnClick(R.id.btn_reset_password)
    protected void attemptLogin() {
        Intent intent = new Intent(ForgetPasscodeView.this, LoginView.class);
        startActivity(intent);
    }

    @OnClick(R.id.back_button)
    protected void backPressed() {
        onBackPressed();
    }
}
