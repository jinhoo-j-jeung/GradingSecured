package gradingsecured.com.gradingsecured;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

	private TextInputLayout usernameLayout;
	private TextInputLayout passwordLayout;


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		/*
		 * Hide the default action bar
		 */
		getSupportActionBar().hide();

		usernameLayout = findViewById( R.id.input_email_layout);
		passwordLayout = findViewById( R.id.input_password_layout);

		usernameLayout.getEditText().addTextChangedListener(new onTextChangeListener(usernameLayout, getString(R.string.invalid_email)));
		passwordLayout.getEditText().addTextChangedListener(new onTextChangeListener(passwordLayout, getString(R.string.empty_password)));
	}

	/* Returns uid of the user. Returns -1 if credentials are incorrect	 */
	private long authenticate(String username, String password) {
		// if username == teacher1 return 1;
		// if username == student1 return 2;

		return 1;
	}

	private int saveUidToSharedPreferences(long uid) {

		String tempSharedPrefName = "com.gradingsecured.gradingsecured";
		SharedPreferences sharedPref = this.getSharedPreferences(tempSharedPrefName, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putLong(getString(R.string.spref_uid), uid);
		editor.commit();
		return 0;
	}


	public void onClick(View v) {
		if (v.getId() == R.id.button_login) {

			usernameLayout = findViewById( R.id.input_email_layout);
			passwordLayout = findViewById( R.id.input_password_layout);

			usernameLayout.getEditText().addTextChangedListener(new onTextChangeListener(usernameLayout, getString(R.string.invalid_email)));
			passwordLayout.getEditText().addTextChangedListener(new onTextChangeListener(passwordLayout, getString(R.string.empty_password)));

			EditText usernameView = usernameLayout.getEditText();
			EditText passwordView = passwordLayout.getEditText();
			String username = usernameView.getText().toString();
			String password = passwordView.getText().toString();

			boolean valid_username = verify_username(username);
			boolean valid_password = verify_password(password);
			usernameLayout.setErrorEnabled(false);
			passwordLayout.setErrorEnabled(false);
			if ( valid_username && valid_password ) {

				SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
				prefs.edit().putBoolean("Islogin", true).apply();

				long uid = authenticate(username, password);
				if (uid == -1) {
					Toast.makeText(this, R.string.incorrect_crendentials, Toast.LENGTH_SHORT).show();
				} else {
					saveUidToSharedPreferences(uid);
					//Toast.makeText(this, R.string.successful_login, Toast.LENGTH_SHORT).show();
//					finish();
					Intent homeIntent = new Intent(this, MainActivity.class);
					startActivity(homeIntent);
					Intent returnIntent = new Intent(this, SplashActivity.class);
					returnIntent.putExtra("LOGIN_SUCCESS", "1");
					setResult(0, returnIntent);
					finish();
				}
			} else {
//				Toast.makeText(this, R.string.incorrect_crendentials, Toast.LENGTH_LONG).show();
				if (!valid_username) {
					showError(usernameLayout, getString(R.string.invalid_email));
				}
				if (!valid_password) {
					showError(passwordLayout, getString(R.string.empty_password));
				}
			}

		}

		else if (v.getId() == R.id.button_register) {
			Intent intent = new Intent(this, RegisterActivity.class);
			startActivity(intent);
		}
		else if (v.getId() == R.id.button_forgot) {
			Intent intent = new Intent(this, ForgotPasswordActivity.class);
			startActivity(intent);
		}

	}


	private boolean verify_username(String username) {
		return !username.equals("");
	}

	private boolean verify_password(String password) {
		return !password.equals("");
	}


	private void showError(TextInputLayout t, String error) {
		t.setError(error);
		EditText editText = t.getEditText();
		editText.setFocusable(true);
		t.setFocusableInTouchMode(true);
		t.requestFocus();
	}


    class onTextChangeListener implements TextWatcher {
        private TextInputLayout textLayout;
        private String errmsg;
        onTextChangeListener(TextInputLayout t, String e) {
            textLayout = t;
            errmsg = e;
        };
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (textLayout.getEditText().getText().toString().length() == 0) {
                showError(textLayout,errmsg);
            } else {
                textLayout.setErrorEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

}
