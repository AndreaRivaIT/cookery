package it.unimib.cookery.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import it.unimib.cookery.R;

import it.unimib.cookery.adapters.RecipeAdapter;
import it.unimib.cookery.adapters.RecipeAdapterSubcard;
import it.unimib.cookery.jsonParser.JsonParser;
import it.unimib.cookery.models.Recipe;
import it.unimib.cookery.repository.RecipeRepository;
import it.unimib.cookery.utils.ResponseCallbackApi;


public class HomeFragment extends Fragment implements ResponseCallbackApi {

    ArrayList<Recipe> recipeArrayList;
    private RecyclerView recyclerViewRTC;
    private RecyclerView recyclerViewHome2;
    private RecyclerView recyclerViewHome3;
    private RecyclerView recyclerViewHome4;
    private RecipeRepository recipeRepository = new RecipeRepository(this);
    private JsonParser jsonParser = new JsonParser();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);


        recyclerViewRTC = view.findViewById(R.id.recyclerViewRTC);
        recyclerViewHome2 = view.findViewById(R.id.recyclerViewHome2);
        recyclerViewHome3 = view.findViewById(R.id.recyclerViewHome3);
        recyclerViewHome4 = view.findViewById(R.id.recyclerViewHome4);

        recipeRepository.getRandomRecipe();
        // TEST_ARRAY recipe adapter

        recipeArrayList = new ArrayList<>();
        recipeArrayList.add(new Recipe("Orange", "TEST_FOOD_CATEGORY", R.drawable.test_food_img));
        recipeArrayList.add(new Recipe("Orange", "TEST_FOOD_CATEGORY", R.drawable.test_food_img));
        recipeArrayList.add(new Recipe("Orange", "TEST_FOOD_CATEGORY", R.drawable.test_food_img));
        recipeArrayList.add(new Recipe("Orange", "TEST_FOOD_CATEGORY", R.drawable.test_food_img));
        recipeArrayList.add(new Recipe("Orange", "TEST_FOOD_CATEGORY", R.drawable.test_food_img));
        recipeArrayList.add(new Recipe("Orange", "TEST_FOOD_CATEGORY", R.drawable.test_food_img));

        // END OF TEST_ARRAY

        LinearLayoutManager linearLayoutManagerRTC = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManagerHome2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManagerHome3 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManagerHome4 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewRTC.setLayoutManager(linearLayoutManagerRTC);
        recyclerViewHome2.setLayoutManager(linearLayoutManagerHome2);
        recyclerViewHome3.setLayoutManager(linearLayoutManagerHome3);
        recyclerViewHome4.setLayoutManager(linearLayoutManagerHome4);

        RecipeAdapter recipeAdapter = new RecipeAdapter(getContext(), recipeArrayList);
        RecipeAdapterSubcard recipeAdapterSubcard = new RecipeAdapterSubcard(getContext(), recipeArrayList);
        recyclerViewRTC.setAdapter(recipeAdapter);
        recyclerViewHome2.setAdapter(recipeAdapterSubcard);
        recyclerViewHome3.setAdapter(recipeAdapterSubcard);
        recyclerViewHome4.setAdapter(recipeAdapterSubcard);


        return view;
    }


    @Override
    public void onResponseRandomRecipe(String jsonFile) {

        JsonParser.parseRandomRecipe(jsonFile);

        // RecipeArrayList = jasonParser.parseRandomRecipe(jsonFile);
        // scommentare codice glide
    }

    @Override
    public void onResponseGetStep(String jsonFile) {

    }

    @Override
    public void onFailure(int errorMessage) {

        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }
}