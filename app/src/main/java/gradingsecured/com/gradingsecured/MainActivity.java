package gradingsecured.com.gradingsecured;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import gradingsecured.com.gradingsecured.fragments.MyVideoFragment;
import gradingsecured.com.gradingsecured.fragments.ProfileFragment;
import gradingsecured.com.gradingsecured.fragments.VideoFragment;

/*
 * Main activity holding 3 fragments
 */
public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    /*
     * Set greeting message to the user on action bar
     */
    ActionBar actionBar = getSupportActionBar();
    assert actionBar != null;
    actionBar.setTitle("Welcome, John!");

    /*
     * Bottom Navigation Bar
     */
    BottomNavigationView mNavigationView = findViewById(R.id.main_navbar);
    mNavigationView.setOnNavigationItemSelectedListener
        (new BottomNavigationView.OnNavigationItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
              case R.id.navbar_myvideo:
                selectedFragment = MyVideoFragment.newInstance();
                break;
              case R.id.navbar_video:
                selectedFragment = VideoFragment.newInstance();
                break;
              case R.id.navbar_profile:
                selectedFragment = ProfileFragment.newInstance();
                break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            assert selectedFragment != null;
            transaction.replace(R.id.main_fl, selectedFragment);
            transaction.commit();
            return true;
          }
        });

    // Manually displays the first fragment - one time only
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.main_fl, MyVideoFragment.newInstance());
    transaction.commit();
  }
}
