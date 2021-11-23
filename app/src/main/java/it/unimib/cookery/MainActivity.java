package it.unimib.cookery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* questo codice serve solo per caricare il fragmnet che sto progettando*/

        AlimentarPreferenceFragment f1 = new AlimentarPreferenceFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.mainContainer, f1);
        ft.commit();

        /* fine codice per caricare fragment */


    }
}