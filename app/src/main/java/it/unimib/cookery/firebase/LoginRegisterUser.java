package it.unimib.cookery.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import it.unimib.cookery.R;
import it.unimib.cookery.ui.MainActivity;

public class LoginRegisterUser extends AppCompatActivity implements View.OnClickListener {

    private TextView register;
    private EditText loginEmail, loginPassword;
    private Button loginButton;
    private ProgressBar progressBar;
    private ImageView back;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_register_activity);

        register = findViewById(R.id.register);
        register.setOnClickListener(this);

        loginEmail = findViewById(R.id.login_mail);
        loginPassword = findViewById(R.id.login_password);

        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(this);

        progressBar = findViewById(R.id.login_progress);

        mAuth = FirebaseAuth.getInstance();

        back = findViewById(R.id.back_button_login);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                startActivity(new Intent(this, RegisterUser.class));
                break;

            case R.id.login_button:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String email = loginEmail.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();

        if(email.isEmpty()) {
            loginEmail.setError("Email is required");
            loginEmail.requestFocus();
            return;
        }

        if(!(Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
            loginEmail.setError("Please enter a valid Email address");
            loginEmail.requestFocus();
            return;
        }

        if(password.isEmpty()) {
            loginPassword.setError("Password is required");
            loginPassword.requestFocus();
            return;
        }

        if(password.length() < 9) {
            loginPassword.setError("Minimum password length is 9 characters");
            loginPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if(user.isEmailVerified()) {
                        //redirect to user activity
                        startActivity(new Intent(LoginRegisterUser.this, MainActivity.class));
                        MainActivity.setLogged(true);
                        finish();
                    } else {
                        user.sendEmailVerification();
                        Toast.makeText(LoginRegisterUser.this, "check your email to verify your account", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }

                } else {
                    Toast.makeText(LoginRegisterUser.this, "failed to login, check your credentials", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);

                }
            }
        });
    }

}