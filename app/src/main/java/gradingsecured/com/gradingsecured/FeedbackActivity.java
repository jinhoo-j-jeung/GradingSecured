package gradingsecured.com.gradingsecured;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
import gradingsecured.com.gradingsecured.view_adapters.FeedbackGridAdapter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class FeedbackActivity extends AppCompatActivity {
  final static int CONFIRMED = 2;
  String TAG;
  ArrayList<String> categories = new ArrayList<>();
  AlertDialog dialog;
  YouTubePlayer youTubePlayer;
  int seekTime;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_feedback);

    /*
     * Retrieve video related data
     */
    Bundle bundle = getIntent().getExtras();
    assert bundle != null;
    final String title = bundle.getString("title");
    final String uploader = bundle.getString("uploader");
    final String thumbnail_url = bundle.getString("thumbnail_url");
    final String posted_date = "2018-12-03";
    final int number_feedbacks = 4;

    /*
     * Set title on action bar
     */
    ActionBar actionBar = getSupportActionBar();
    assert actionBar != null;
    actionBar.setTitle(uploader+"'s video");

    TextView titleTextView = findViewById(R.id.feedback_title_tv);
    titleTextView.setText(title);

    TextView postedTextView = findViewById(R.id.feedback_posted_tv);
    postedTextView.setText(posted_date);

    TextView numFeedTextView = findViewById(R.id.feedback_num_tv);
    numFeedTextView.setText(String.valueOf(number_feedbacks));

    /*
     * Retreive Video Id from thumbnail url
     */
    assert thumbnail_url != null;
    final String video_id = thumbnail_url.replace("http://img.youtube.com/vi/", "").replace("/0.jpg", "");

    /*
     * Initialize Youtube Support Fragment
     */
    final YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();

    youTubePlayerFragment.initialize("AIzaSyDo9akZVdgEsbXZzArkneQ8Hbqf9LuoX80", new OnInitializedListener() {
      @Override
      public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        youTubePlayer = player;
        youTubePlayer.cueVideo(video_id);
      }
      @Override
      public void onInitializationFailure(Provider arg0, YouTubeInitializationResult arg1) {
        Toast.makeText(FeedbackActivity.this, "Failed to load the video", Toast.LENGTH_SHORT).show();
      }
    });
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.add(R.id.feedback_fl, youTubePlayerFragment).commit();

    /*
     * Shared Preferences
     */
    TAG = video_id+"Information";
    final SharedPreferences mSharedPreferences = this.getSharedPreferences(TAG, MODE_PRIVATE);
    final SharedPreferences.Editor mEditor = mSharedPreferences.edit();

    /*
     * Add relevant data to ArrayLists
     */
    categories.add("Content");
    categories.add("Language");
    categories.add("Visual");
    categories.add("Clarity");
    categories.add("Brevity");

    /*
     * Initialize a gridview.
     */
    GridView mGridView = findViewById(R.id.feedback_gv);

    /*
     * Initialize an adapter with data
     */
    FeedbackGridAdapter mAdapter= new FeedbackGridAdapter(this, categories);
    mAdapter.notifyDataSetChanged();

    /*
     * Link the view with the adapter.
     */
    mGridView.setAdapter(mAdapter);

    /*
     * Open a custom dialog when an item is clicked
     */
    mGridView.setOnItemClickListener(new OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        /*
         * Pause the video and retrieve the timestamp of the video
         */
        youTubePlayer.pause();
        seekTime = youTubePlayer.getCurrentTimeMillis();
        String timeStamp = String.format("%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(seekTime),
            TimeUnit.MILLISECONDS.toSeconds(seekTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(seekTime))
        );

        /*
         * Initialize AlertDialog and inflate the view
         */
        AlertDialog.Builder builder = new AlertDialog.Builder(FeedbackActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.fragment_feedback, null);
        builder.setView(dialogView);
        TextView titleTextView = dialogView.findViewById(R.id.feeddialog_title);
        final RatingBar mRatingBar = dialogView.findViewById(R.id.feeddialog_rb);
        final EditText mEditText = dialogView.findViewById(R.id.feeddialog_et);
        dialog = builder.create();

        /*
         * Set input data
         */
        final String category = categories.get(i);
        titleTextView.setText(category);
        mRatingBar.setRating(mSharedPreferences.getInt(category+"grade", 0));
        mEditText.setText(mSharedPreferences.getString(category+"feedback", "")+"\n"+timeStamp+" ");

        /*
         * Set Save Button. It saves changes on data to the sharedpreference when clicked.
         */
        builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            mEditor.putInt(category+"grade", (int) mRatingBar.getRating());
            mEditor.putString(category+"feedback", mEditText.getText().toString());
            mEditor.apply();
            Toast.makeText(FeedbackActivity.this, "Change Saved", Toast.LENGTH_SHORT).show();
            youTubePlayer.play();
            dialog.dismiss();
          }
        });

        /*
         * Set Discard Button
         */
        builder.setNegativeButton("DISCARD", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            youTubePlayer.play();
            dialog.dismiss();
          }
        });

        /*
         * Make the dialog not cancelable forcing the user to make a choice
         */
        builder.setCancelable(false);
        builder.show();
      }
    });
  }

  /*
   * onClick function that sends feedback data to OverallGradeActivity.
   */
  public void feedbackSubmit(View view) {
    Intent intent = new Intent(FeedbackActivity.this, OverallGradeActivity.class);
    intent.putExtra("tag", TAG);
    startActivityForResult(intent, 1);
  }

  /*
   * Callback function to close this activity as well as OverallGradeActivity at the same time
   */
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data)
  {
    super.onActivityResult(requestCode, resultCode, data);
    // resultCode will be send from OverallGradeActivity
    if(resultCode == CONFIRMED)
    {
      finish();
    }
  }

  /*
   * Custom onBackPressed function to clear sharedpreference
   */
  @Override
  public void onBackPressed()
  {
    final SharedPreferences mSharedPreferences = this.getSharedPreferences(TAG, MODE_PRIVATE);
    final SharedPreferences.Editor mEditor = mSharedPreferences.edit();
    mEditor.clear();
    mEditor.apply();
    super.onBackPressed();
  }
}