package gradingsecured.com.gradingsecured;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity {

    TextInputLayout inputEmailLayout;
    TextInputLayout inputCodeLayout;
    TextInputLayout inputPasswdLayout;
    TextInputLayout inputPasswd2Layout;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        inputEmailLayout = findViewById(R.id.input_email_layout);
        inputCodeLayout = findViewById(R.id.input_code_layout);
        inputPasswdLayout = findViewById(R.id.input_password_layout);
        inputPasswd2Layout = findViewById(R.id.input_password2_layout);


        class onPasswdChangeListener implements TextWatcher{
            private TextInputLayout passwdLayout;
            private String errmsg;
            onPasswdChangeListener(TextInputLayout t, String e) {
                passwdLayout = t;
                errmsg = e;
            };
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Button btn = findViewById(R.id.button_reset_password);

                if (passwdLayout.getEditText().getText().toString().length() == 0) {
                    showError(passwdLayout,errmsg);
//                    btn.setEnabled(false);
                } else {
//                    btn.setEnabled(true);
                    passwdLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        }

        inputCodeLayout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Button btn = findViewById(R.id.button_reset_password);
                if (TextUtils.isEmpty(editable.toString())) {
                    showError(inputCodeLayout, "Invalid verification code");
                    btn.setEnabled(false);
                } else {
                    inputCodeLayout.setErrorEnabled(false);
                    inputPasswdLayout.setVisibility(View.VISIBLE);
                    inputPasswdLayout.setEnabled(true);
                    inputPasswd2Layout.setVisibility(View.VISIBLE);
                    inputPasswd2Layout.setEnabled(true);
                    btn.setEnabled(true);
                }

            }
        });

        inputPasswd2Layout.getEditText().addTextChangedListener(new onPasswdChangeListener(inputPasswd2Layout, getString(R.string.empty_password)));
        inputPasswdLayout.getEditText().addTextChangedListener(new onPasswdChangeListener(inputPasswdLayout, getString(R.string.empty_password)));
        inputEmailLayout.getEditText().addTextChangedListener(new onPasswdChangeListener(inputEmailLayout, getString(R.string.invalid_email)));

    }


    public void onClick(View v) {

        inputEmailLayout.setErrorEnabled(false);
        inputCodeLayout.setErrorEnabled(false);
        inputPasswdLayout.setErrorEnabled(false);
        inputPasswd2Layout.setErrorEnabled(false);

        if (v.getId() == R.id.button_send) {
            EditText inputEmailView = inputEmailLayout.getEditText();
            if (!verify_email()) {
//                Toast.makeText(this, R.string.invalid_email, Toast.LENGTH_SHORT).show();
                showError(inputEmailLayout, getString(R.string.invalid_email));
            } else {
                Toast.makeText(this, R.string.resend_verification_message, Toast.LENGTH_SHORT).show();
            }
        }
        else if ( v.getId() == R.id.button_reset_password) {
            boolean b1, b2, b3; // sorry
            b1 = verify_email();
            b2 = verify_code();
            b3 = verify_password();
            if (!b1) {
//                Toast.makeText(this, R.string.invalid_email, Toast.LENGTH_SHORT).show();
                showError(inputEmailLayout, getString(R.string.invalid_email));

            }

            if (!b2) {
//                Toast.makeText(this, "Incorrect verification code", Toast.LENGTH_SHORT).show();
                showError(inputCodeLayout, "Invalid verification code");

            }

            if (!b3) {
//                Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show();

            }
            if (b1 && b2 && b3) {
                Toast.makeText(this, R.string.password_reset_success, Toast.LENGTH_SHORT).show();
                finish();
            }



        }

    }

    private boolean verify_code() {
        EditText verificationCodeView = inputCodeLayout.getEditText();
        String code =  verificationCodeView.getText().toString();
        if (!code.equals("")) {
            return true;
        } else return false;
    }

    private boolean verify_password() {
        EditText passwdView1 = inputPasswdLayout.getEditText();
        EditText passwdView2 = inputPasswd2Layout.getEditText();

        String passwd1 = passwdView1.getText().toString();
        String passwd2 = passwdView2.getText().toString();

        if (passwd1.equals("") || passwd2.equals("")) {
            showError(inputPasswdLayout, getString(R.string.empty_password));
            showError(inputPasswd2Layout, getString(R.string.empty_password));
            return false;
        }

        if (passwd1.equals(passwd2)) {
            return true;
        } else {
            showError(inputPasswdLayout, getString(R.string.mismatch_password));
            showError(inputPasswd2Layout, getString(R.string.mismatch_password));
            return false;
        }
    }

    private boolean verify_email() {
        EditText verificationCodeView = inputEmailLayout.getEditText();
        String code =  verificationCodeView.getText().toString();
        if (!code.equals("")) {
            return true;
        } else return false;
    }


    private void showError(TextInputLayout t, String error) {
        t.setError(error);
        EditText editText = t.getEditText();
        editText.setFocusable(true);
        t.setFocusableInTouchMode(true);
//        t.requestFocus();
    }


}



