package gradingsecured.com.gradingsecured;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import gradingsecured.com.gradingsecured.image_adapters.CircularImageView;
import java.util.ArrayList;

public class MyVideoStatsActivity extends AppCompatActivity {
    final static int CONFIRMED = 2;
    String TAG;
    ArrayList<String> categories = new ArrayList<>();
    YouTubePlayer youTubePlayer;
    int seekTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_video_stats);

        /*
         * Retrieve video related data
         */
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        final String profile_url = bundle.getString("profile_url");
        final String title = bundle.getString("title");
        final String uploader = bundle.getString("uploader");
        final String thumbnail_url = bundle.getString("thumbnail_url");


        /*
         * Retreive Video Id from thumbnail url
         */
        assert thumbnail_url != null;
        final String video_id = thumbnail_url.replace("http://img.youtube.com/vi/", "").replace("/0.jpg", "");

        /*
         * Initialize Youtube Support Fragment
         */
        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();

        youTubePlayerFragment.initialize("AIzaSyDo9akZVdgEsbXZzArkneQ8Hbqf9LuoX80", new OnInitializedListener() {
            @Override
            public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
                youTubePlayer = player;
                player.cueVideo(video_id);
            }
            @Override
            public void onInitializationFailure(Provider arg0, YouTubeInitializationResult arg1) {

            }
        });
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.stats_fl, youTubePlayerFragment).commit();

        /*
         * Shared Preferences
         */
        TAG = video_id+"Information";
        final SharedPreferences mSharedPreferences = this.getSharedPreferences(TAG, MODE_PRIVATE);
        final SharedPreferences.Editor mEditor = mSharedPreferences.edit();

        final Button time1 = findViewById(R.id.time_button1);
        time1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                youTubePlayer.seekToMillis(130000);
                youTubePlayer.play();
            }
        });

        final Button time2 = findViewById(R.id.time_button2);
        time2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                youTubePlayer.seekToMillis(260000);
                youTubePlayer.play();
            }
        });


    }



}