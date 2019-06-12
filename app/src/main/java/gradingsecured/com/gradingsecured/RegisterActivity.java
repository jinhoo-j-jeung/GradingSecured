package gradingsecured.com.gradingsecured;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


/* TODO: INPUT VALIDATION & PROMPT ON INPUT*/
public class RegisterActivity extends AppCompatActivity {

    Spinner mRoleSpinner;
    /* TODO: Replace hardcoded role-id pairs with map */
    /*
    static final int [] role_id = { 0, 1, 2, 3  };*/


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mRoleSpinner = findViewById(R.id.spinner_role);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, roles);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.user_roles, android.R.layout.simple_spinner_dropdown_item);
        mRoleSpinner.setAdapter(adapter);
    }


    public void onClick(View v) {
//        Toast.makeText(this, R.string.invalid_registration, Toast.LENGTH_LONG).show();
        if (v.getId() == R.id.button_register) {

            TextInputLayout usernameLayout = findViewById(R.id.input_email_layout);
            TextInputLayout realnameLayout = findViewById(R.id.input_realname_layout);
            TextInputLayout passwordLayout = findViewById(R.id.input_password_layout);
            TextInputLayout password2Layout = findViewById(R.id.input_password2_layout);

            usernameLayout.setErrorEnabled(false);
            realnameLayout.setErrorEnabled(false);
            passwordLayout.setErrorEnabled(false);
            password2Layout.setErrorEnabled(false);

            EditText usernameView = usernameLayout.getEditText();
            EditText realnameView = realnameLayout.getEditText();
            EditText passwordView = passwordLayout.getEditText();
            EditText password2View = password2Layout.getEditText();

            Spinner roleView = findViewById(R.id.spinner_role);

            String username = usernameView.getText().toString();
            String realname = realnameView.getText().toString();
            String password = passwordView.getText().toString();
            String password2 = password2View.getText().toString();
            long roleId = mRoleSpinner.getSelectedItemId();


            if (verify_username(username) && verify_realname(realname) && verify_password(password) && password.equals(password2)) {
                commit_registration(username, password, realname, roleId);
                Toast.makeText(this, R.string.successful_registration, Toast.LENGTH_SHORT).show();

                finish();
            }
            else {
//                Toast.makeText(this, R.string.invalid_registration, Toast.LENGTH_LONG).show();
                if (!verify_username(username)) {
                    showError(usernameLayout, getString(R.string.invalid_email));
                }
                if (!verify_realname(realname) ) {
                    showError(realnameLayout, getString(R.string.required_field));
                }
                if (!password.equals(password2)) {
                    showError(passwordLayout, getString(R.string.mismatch_password));
                    showError(password2Layout, getString(R.string.mismatch_password));
                }
                if (!verify_password(password)) {
                    showError(passwordLayout, ("Invalid password"));
                }
                if (!verify_password(password2)) {
                    showError(password2Layout, ("Invalid password"));
                }

            }

        }

    }

    private boolean verify_username(String username) {
        return !username.equals("");
    }

    private boolean verify_password(String password) {
        return !password.equals("");
    }

    private boolean verify_realname(String realname) {
        return !realname.equals("");
    }

    private boolean commit_registration(String username, String password, String realname, long roleId) {
        return true;
    }


    private void showError(TextInputLayout t, String error) {
        t.setError(error);
        EditText editText = t.getEditText();
        editText.setFocusable(true);
        t.setFocusableInTouchMode(true);
        t.requestFocus();
    }




}
