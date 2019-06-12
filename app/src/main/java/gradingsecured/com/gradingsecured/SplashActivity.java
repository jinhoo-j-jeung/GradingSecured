package gradingsecured.com.gradingsecured;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

/*
 * Splash Activity to load data and check if the user is already logged in.
 */
public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /*
         * Hide the default action bar
         */
        getSupportActionBar().hide();

        /*
         * Sharedpreference to retreive the token
         */
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isLogin = prefs.getBoolean("Islogin", false);
//        boolean isLogin = false;

        /*
         * Initialize Loading spinner to provide visual feedback to the user
         */
        final ProgressBar spinner = findViewById(R.id.splash_loading_spinner);
        spinner.setVisibility(View.VISIBLE);

        /*
         * If logged in, go to MainActivity. Else, go to LoginActivity.
         */
        final Intent intent;
        if (isLogin) {
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            spinner.setVisibility(View.GONE);
            finish();
        } else {
            intent = new Intent(this, LoginActivity.class);
            /*
             * Timer to simulate data loading process
             */
            new CountDownTimer(3000, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                }

                @Override
                public void onFinish() {
                    startActivity(intent);
                    spinner.setVisibility(View.GONE);
                    finish();
                }
            }.start();
        }
    }
}
