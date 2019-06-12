package gradingsecured.com.gradingsecured;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class OverallGradeActivity extends AppCompatActivity {
  private String TAG;
  private RatingBar content;
  private RatingBar language;
  private RatingBar visual;
  private RatingBar clarity;
  private RatingBar brevity;
  AlertDialog dialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_overallgrade);

    /*
     * Set greeting message to the user on action bar
     */
    ActionBar actionBar = getSupportActionBar();
    assert actionBar != null;
    actionBar.setTitle("Overall Grades");

    /*
     * Get this video's sharedpreference tag
     */
    Bundle bundle = getIntent().getExtras();
    assert bundle != null;
    TAG = bundle.getString("tag");
    final SharedPreferences mSharedPreferences = this.getSharedPreferences(TAG, MODE_PRIVATE);

    /*
     * Initialize ratingbars with grades assigned at FeedbackActivity
     */
    content = findViewById(R.id.overall_content_rb);
    language = findViewById(R.id.overall_language_rb);
    visual = findViewById(R.id.overall_visual_rb);
    clarity = findViewById(R.id.overall_clarity_rb);
    brevity = findViewById(R.id.overall_brevity_rb);
    content.setRating((float) mSharedPreferences.getInt("Contentgrade", 0));
    language.setRating((float) mSharedPreferences.getInt("Languagegrade", 0));
    visual.setRating((float) mSharedPreferences.getInt("Visualgrade", 0));
    clarity.setRating((float) mSharedPreferences.getInt("Claritygrade", 0));
    brevity.setRating((float) mSharedPreferences.getInt("Brevitygrade", 0));

  }

  /*
   * onClick function
   */
  public void overallSubmit(View view) {
    final SharedPreferences mSharedPreferences = this.getSharedPreferences(TAG, MODE_PRIVATE);
    final SharedPreferences.Editor mEditor = mSharedPreferences.edit();

    /*
     * The back-end is missing so nothing to do with the final data.
     */

    AlertDialog.Builder builder = new AlertDialog.Builder(OverallGradeActivity.this);
    builder.setMessage("Once submitted, you're unable to make any change. Would you like to continue?")
        .setTitle("Confirm Submission");
    dialog = builder.create();
    /*
     * Set YES button
     */
    builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        /*
         * Close OverallGradeAcitivity and FeedbackActivity together
         */
        Intent intent=new Intent();
        setResult(2,intent);
        dialog.cancel();
        Toast.makeText(OverallGradeActivity.this.getApplicationContext(), "Successfully Submitted", Toast.LENGTH_LONG).show();
        finish();
      }
    });

    /*
     * Set No Button
     */
    builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        dialog.cancel();
      }
    });

    /*
     * Make the dialog not cancelable forcing the user to make a choice
     */
    builder.show();
  }
}