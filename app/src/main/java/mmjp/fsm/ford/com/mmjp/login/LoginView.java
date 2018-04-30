package mmjp.fsm.ford.com.mmjp.app.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import mmjp.fsm.ford.com.mmjp.R;
import mmjp.fsm.ford.com.mmjp.app.forgetpasscode.ForgetPasscodeView;
import planner.mmjp.fsm.ford.com.modules.destination.DestinationView;

/**
 * Created by mfayajna on 4/3/18.
 */

public class LoginView extends AppCompatActivity implements LoginContract.View {

    LoginContract.Presenter presenter;
    @BindView(R.id.input_email) EditText mEmailView;
    @BindView(R.id.input_password) EditText mPasswordView;
    @BindView(R.id.btn_log_in) Button logInButton;
    @BindView( R.id.login_form ) View mLoginFormView;
    @BindView(R.id.forget_passcode) TextView forgetPasscodeLInk;
    @BindView(R.id.guest_login_link) TextView guestLoginLink;
    @BindView(R.id.login_progress) View mProgressView;
    @BindView(R.id.back_button) View backBtton;
    private ProgressDialog pDialog;
    private Unbinder unbinder = null;
    private UserLoginTask mAuthTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unbinder = ButterKnife.bind(this);
        presenter = new LoginPresenter(this);
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
        LoginView.this.finish();
    }

    @OnClick(R.id.back_button)
    protected void backPressed() {
        onBackPressed();
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
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a splash_welcome task to
            // perform the user login attempt.
            // Show a progress spinner, and kick off a splash_welcome task to
            // perform the user login attempt.
            showLoading();
            mAuthTask = new UserLoginTask( email, password);
            Intent intent = new Intent(LoginView.this, DestinationView.class);
            startActivity(intent);
        }
    }

    @OnClick(R.id.guest_login_link)
    protected void guestLogin() {
        Intent intent = new Intent(LoginView.this, DestinationView.class);
        startActivity(intent);
    }

    @OnClick(R.id.forget_passcode)
    protected void forgetPasscode() {
        Intent intent = new Intent(LoginView.this, ForgetPasscodeView.class);
        startActivity(intent);
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }
            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            hideLoading();
            if (success) {
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            hideLoading();
        }
    }

}