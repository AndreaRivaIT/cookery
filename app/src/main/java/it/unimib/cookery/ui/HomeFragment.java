package it.unimib.cookery.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Adapter;
import android.widget.AdapterView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import it.unimib.cookery.R;
import it.unimib.cookery.adapters.RecipeAdapter;
import it.unimib.cookery.models.Recipe;


public class HomeFragment extends Fragment {

    private RecipeAdapter recipeAdapter;
    private RecyclerView recipeContainer;
    ArrayList<Recipe> recipeArrayList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View homeView = inflater.inflate(R.layout.fragment_home, container, false);

        recipeContainer = homeView.findViewById(R.id.recyclerView1);

        // TEST_ARRAY

        recipeArrayList.add(new Recipe("TEST_FOOD_NAME_1", "TEST_FOOD_CATEGORY", R.drawable.test_food_img));
        recipeArrayList.add(new Recipe("TEST_FOOD_NAME_2", "TEST_FOOD_CATEGORY", R.drawable.test_food_img));
        recipeArrayList.add(new Recipe("TEST_FOOD_NAME_3", "TEST_FOOD_CATEGORY", R.drawable.test_food_img));
        recipeArrayList.add(new Recipe("TEST_FOOD_NAME_4", "TEST_FOOD_CATEGORY", R.drawable.test_food_img));
        recipeArrayList.add(new Recipe("TEST_FOOD_NAME_5", "TEST_FOOD_CATEGORY", R.drawable.test_food_img));
        recipeArrayList.add(new Recipe("TEST_FOOD_NAME_6", "TEST_FOOD_CATEGORY", R.drawable.test_food_img));

        // END OF TEST_ARRAY

        recipeAdapter = new RecipeAdapter(getContext(), recipeArrayList);

        recipeContainer.setAdapter(recipeAdapter);

        return homeView;
    }

}