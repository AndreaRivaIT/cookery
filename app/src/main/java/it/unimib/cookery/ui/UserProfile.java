package it.unimib.cookery.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import it.unimib.cookery.R;
import it.unimib.cookery.costants.Costants;

public class UserProfile extends AppCompatActivity {

    private EditText profileEmail, profilePassword;
    private Button mSaveButton;
    private FirebaseUser user;

    private ImageView back_button;

    private Costants costants = new Costants();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        user = FirebaseAuth.getInstance().getCurrentUser();

        profileEmail = findViewById(R.id.profileEmail);
        profileEmail.setHint(user.getEmail());

        profilePassword = findViewById(R.id.profilePassword);

        back_button = findViewById(R.id.back_button_login_2);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String email = profileEmail.getText().toString().trim();
        String password = profilePassword.getText().toString().trim();

        mSaveButton = findViewById(R.id.saveButton_user);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.isEmpty()) {
                    profileEmail.setError("Email is required");
                    profileEmail.requestFocus();
                }else if(!(Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
                    profileEmail.setError("Please enter a valid email address");
                    profileEmail.requestFocus();
                }else if(password.isEmpty()) {
                    profilePassword.setError("Password is required");
                    profilePassword.requestFocus();
                }else if(password.length() < 9) {
                    profilePassword.setError("Minimum password lenght is 9 characters");
                } else {
                    updateUser();
                    finish();
                }
            }
        });

    }

    private void updateUser() {
        if(!(profileEmail.getText().toString().isEmpty())) {
            user.updateEmail(profileEmail.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(UserProfile.this, R.string.update_mail, Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(UserProfile.this, R.string.update_mail_fail, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } else {
            profileEmail.setError(costants.EMAIL_REQUIRED);
            profileEmail.requestFocus();
        }
        if(!(profilePassword.getText().toString().isEmpty())) {
            user.updatePassword(profilePassword.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(UserProfile.this, R.string.update_password, Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(UserProfile.this, R.string.update_mail_fail, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } else if((profilePassword.getText().toString().length()) < 9) {
            profilePassword.setError(costants.PASSWORD_LENGTH);
            profilePassword.requestFocus();
        } else {
            profilePassword.setError(costants.PASSWORD_REQUIRED);
            profilePassword.requestFocus();
        }
    }
}