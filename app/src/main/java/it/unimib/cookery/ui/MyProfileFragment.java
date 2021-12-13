package it.unimib.cookery.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.unimib.cookery.R;


public class MyProfileFragment extends Fragment {

    private Button saveButton;
    private boolean changed = false;
    String newUsername = "";
    String newName = "";
    String newSurname = "";
    String newPassword = "";
    String newMail = "";


    public MyProfileFragment() {
        // Required empty public constructor
    }


    private void saveButtonEnabledTrue(Button b) {
        b.setEnabled(true);
    }

    private void saveButtonEnabledFalse(Button b) {
        b.setEnabled(false);
    }


    private static boolean checkPass(String password) {

        if (password.length() >= 8) {
            Pattern letter = Pattern.compile("[a-zA-z]");
            Pattern digit = Pattern.compile("[0-9]");
            Pattern special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
            Matcher hasLetter = letter.matcher(password);
            Matcher hasDigit = digit.matcher(password);
            Matcher hasSpecial = special.matcher(password);

            return hasLetter.find() && hasDigit.find() && hasSpecial.find();

        } else
            return false;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Button changeImageButton;
        EditText editTextUserName;
        EditText editTextName;
        EditText editTextSurname;
        EditText editTextMail;
        EditText editTextPassword;

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);

        changeImageButton = view.findViewById(R.id.changeImageButton);
        editTextUserName = view.findViewById(R.id.editTextUserName);
        editTextName = view.findViewById(R.id.editTextName);
        editTextSurname = view.findViewById(R.id.editTextSurname);
        editTextMail = view.findViewById(R.id.editTextMail);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        saveButton = view.findViewById(R.id.saveButton);
        //da fare query al database e avendo le risposte
        //settare i vari contenuti delle EditText


        changeImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("premuto", "changeImageButton");
            }
        });

        editTextUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("cambiato", "edit text User1");
                if (!changed) {
                    changed = true;
                    saveButtonEnabledTrue(saveButton);
                }
                newUsername = s.toString();
            }
        });


        editTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("cambiato", "edit text User1");
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("cambiato", "edit text User1");
                if (!changed) {
                    changed = true;
                    saveButtonEnabledTrue(saveButton);
                }
                newName = s.toString();
            }
        });


        editTextSurname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("cambiato", "edit text User1");
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("cambiato", "edit text User1");
                if (!changed) {
                    changed = true;
                    saveButtonEnabledTrue(saveButton);
                }
                newSurname = s.toString();
            }
        });


        editTextMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("cambiato", "edit text User1");
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("cambiato", "edit text User1");
                if (!changed) {
                    changed = true;
                    saveButtonEnabledTrue(saveButton);
                }
                newMail = s.toString();
            }
        });


        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("cambiato", "edit text User1");
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("cambiato", "edit text User1");
                if (!changed) {
                    changed = true;
                    saveButtonEnabledTrue(saveButton);
                }
                newPassword = s.toString();
            }
        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPass(newPassword)) {
                    Log.d("premuto", "changeImageButton");
                    Log.d("modifica", "newUsername: " + newUsername);
                    Log.d("modifica", "newName: " + newName);
                    Log.d("modifica", "newSurname: " + newSurname);
                    Log.d("modifica", "newMail: " + newMail);
                    Log.d("modifica", "newPassword: " + newPassword);
                    saveButtonEnabledFalse(saveButton);
                    //da fare l'update del database
                } else {
                    Toast.makeText(requireActivity().getBaseContext(), R.string.passError, Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }
}