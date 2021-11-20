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

        HomeFragment homeF = new HomeFragment();
        FragmentManager managerF = getSupportFragmentManager();
        FragmentTransaction fragmentT = managerF.beginTransaction();
        fragmentT.add(R.id.mainContainer, homeF);
        fragmentT.commit();

    }
}