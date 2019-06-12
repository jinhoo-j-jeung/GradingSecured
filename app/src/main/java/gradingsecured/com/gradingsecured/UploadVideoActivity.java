package gradingsecured.com.gradingsecured;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;

public class UploadVideoActivity extends AppCompatActivity {

    Spinner categorySpinner;
    TextInputLayout uploadUrlLayout;
    TextInputLayout descriptionLayout;
    boolean valid_url;

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_upload_video);

//        uploadUrlLayout = findViewById(R.id.upload_url);
//        descriptionLayout = findViewById(R.id.video_description);
        EditText description = findViewById(R.id.video_description);
//        final Button button = findViewById(R.id.upload_button);
//        final EditText url = uploadUrlLayout.getEditText();
//        final EditText url = findViewById(R.id.upload_url);
//        final EditText description = descriptionLayout.getEditText();

        Button btn = findViewById(R.id.upload_button);
//        btn.set2(false);
        EditText url = findViewById(R.id.upload_url);
        url.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                validate_Uri();
                if (s.length() == 0) {
                    showError(false);
                } else {
                    boolean b = validate_Uri();
                    showError(b);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Spinner category = (Spinner)findViewById(R.id.video_category);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                                    R.array.video_categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public void setVideoUrl(String videoUrl) {
        SharedPreferences sharedPreferences =
                            PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("videoUrl", videoUrl);
        editor.commit();
    }

    public String getVideoUrl(Context context) {
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        return prefs.getString("videoUrl", "");
    }


    protected void showError(boolean valid) {

        TextView helperText = findViewById(R.id.helper_text);
        EditText url = findViewById(R.id.upload_url);
        String u = url.getText().toString();
        Button btn = findViewById(R.id.upload_button);
        if (!valid) {
            valid_url = false;
            Log.e("debug", "invalid_url");
            helperText.setText("Empty video url");
//            btn.setEnabled(false);
            url.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);

        } else {
            helperText.setText("");
            btn.setEnabled(true);
            valid_url = true;
            url.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
        }

    }

    protected boolean validate_Uri() {
        EditText url = findViewById(R.id.upload_url);
        String u = url.getText().toString();
        return !u.equals("");
    }

    public void onClick(View v) {
        Log.v("INFO", "Onclick");
        if (v.getId() == R.id.upload_button) {
            Log.v("INFO", "button");
            boolean b = validate_Uri();
            showError(b);
            if (b) {
                Log.v("INFO", "Valad");
                Toast.makeText(this, "Successfully uploaded a video", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                // nothing
                Log.v("INFO", "Invalid");
                Button btn = findViewById(R.id.upload_button);

            }
        }
    }

}
