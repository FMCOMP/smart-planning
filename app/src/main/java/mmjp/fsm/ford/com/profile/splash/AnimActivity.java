package mmjp.fsm.ford.com.profile.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import mmjp.fsm.ford.com.R;
import mmjp.fsm.ford.com.profile.login.LoginView;

/**
 * Created by userpmp on 2/6/18.
 */

public class AnimActivity extends AppCompatActivity implements Animation.AnimationListener {

    Animation animBounce;
    private LinearLayout llSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        llSplash = (LinearLayout) findViewById(R.id.ll_splash);
        animBounce = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bounce);
        // set animation listener
        animBounce.setAnimationListener(AnimActivity.this);
        llSplash.startAnimation(animBounce);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        startActivity(new Intent(AnimActivity.this, LoginView.class));
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}

