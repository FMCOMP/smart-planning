package mmjp.fsm.ford.com.profile.registration.accounts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uber.sdk.android.core.auth.LoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import mmjp.fsm.ford.com.R;
import mmjp.fsm.ford.com.enroute.lyft.rider.LyftRiderView;
import mmjp.fsm.ford.com.enroute.uber.modules.rider.UberRiderView;

public class AccountLinkingView extends AppCompatActivity {

    String provider;
    Boolean hasToken = false;
    private Unbinder unbinder;
    private static final int CUSTOM_BUTTON_REQUEST_CODE = 1113;

    @BindView(R.id.uber_button_white) LoginButton uberAuthButton;

    @BindView(R.id.lyft_button_text)TextView lyftButtonText;
    @BindView(R.id.ola_button_text) TextView olaButtonText;

    @BindView(R.id.uber_button) RelativeLayout uberButton;
    @BindView(R.id.lyft_button) RelativeLayout lyftButton;
    @BindView(R.id.ola_button) RelativeLayout olaButton;

    @BindView(R.id.uber_linked_check) ImageView uberCheckButton;
    @BindView(R.id.lyft_linked_check) ImageView lyftCheckButton;
    @BindView(R.id.ola_linked_check) ImageView olaCheckButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_linking);

        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();

        if(intent.hasExtra("uber_token")){
            uberButton.setBackground(getResources().getDrawable(R.drawable.button_border_success));

            uberAuthButton.setText("LINKED");
            uberCheckButton.setVisibility(View.VISIBLE);
        } else if(intent.hasExtra("lyft_token")){
            lyftButton.setBackground(getResources().getDrawable(R.drawable.button_border_success));

            lyftButtonText.setText("LINKED");
            lyftCheckButton.setVisibility(View.VISIBLE);
        } else if(intent.hasExtra("ola_token")){
            olaButton.setBackground(getResources().getDrawable(R.drawable.button_border_success));

            olaButtonText.setText("LINKED");
            olaCheckButton.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.uber_button_white)
    protected void uberAuth() {
        Boolean hasToken = getIntent().hasExtra("uber_token");

        if(!hasToken){
            provider = "uber";
            Intent intent = new Intent(AccountLinkingView.this, UberRiderView.class);
            startActivity(intent);
        }
    }

    @OnClick(R.id.lyft_button)
    protected void lyftAuth() {
        Boolean hasToken = getIntent().hasExtra("lyft_token");

        if(!hasToken) {
            provider = "lyft";
            Intent intent = new Intent(AccountLinkingView.this, LyftRiderView.class);
            startActivity(intent);
        }
    }

    @OnClick(R.id.ola_button)
    protected void olaAuth() {
        Boolean hasToken = getIntent().hasExtra("ola_token");

        if(!hasToken){
            provider = "ola";
            Intent intent = new Intent(AccountLinkingView.this, UberRiderView.class);

            startActivity(intent);
        }
    }

    @OnClick(R.id.other_button)
    protected void otherAuth() {
        if(!hasToken){
            Intent intent = new Intent(AccountLinkingView.this, UberRiderView.class);

            startActivity(intent);
        }
    }
}
