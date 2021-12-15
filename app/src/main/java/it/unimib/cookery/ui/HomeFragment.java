package it.unimib.cookery.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import it.unimib.cookery.R;

import it.unimib.cookery.adapters.RecipeAdapter;
import it.unimib.cookery.adapters.RecipeAdapterSubcard;
import it.unimib.cookery.jsonParser.JsonParser;
import it.unimib.cookery.models.Recipe;
import it.unimib.cookery.models.RecipeApi;
import it.unimib.cookery.repository.RecipeRepository;
import it.unimib.cookery.utils.ResponseCallbackApi;


public class HomeFragment extends Fragment implements ResponseCallbackApi {

   private ArrayList<RecipeApi> recipeArrayListReadyToCoock;
    private ArrayList<RecipeApi> recipeArrayListDessert;
    private ArrayList<RecipeApi> recipeArrayListMainCourse;
    private ArrayList<RecipeApi> recipeArrayListFirstCourse;



    private RecyclerView recyclerViewRTC;
    private RecyclerView recyclerViewHome2;
    private RecyclerView recyclerViewHome3;
    private RecyclerView recyclerViewHome4;
    private RecipeRepository recipeRepository = new RecipeRepository(this);



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // da recuperare e passare le preferenze dell'utente per inserire le
        // preferenze
        // da mettere tag a tutti metodi


        recipeArrayListReadyToCoock = new ArrayList<>();
        recipeArrayListDessert = new ArrayList<>();
        recipeArrayListMainCourse = new ArrayList<>();
        recipeArrayListFirstCourse = new ArrayList<>();


        // ho problemi di concorrenza
       // recipeRepository.getRandomRecipe("");
        recipeRepository.getRandomRecipeFirstCourse("");
       // recipeRepository.getRandomRecipeMainCourse("");
       // recipeRepository.getRandomRecipeDessert("");






    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);


        recyclerViewRTC = view.findViewById(R.id.recyclerViewRTC);
        recyclerViewHome2 = view.findViewById(R.id.recyclerViewHome2);
        recyclerViewHome3 = view.findViewById(R.id.recyclerViewHome3);
        recyclerViewHome4 = view.findViewById(R.id.recyclerViewHome4);







        // TEST_ARRAY recipe adapter
/*
        ArrayList<Recipe> recipeArrayList = new ArrayList<>();

        recipeArrayList.add(new Recipe("Orange", "TEST_FOOD_CATEGORY", R.drawable.test_food_img));
        recipeArrayList.add(new Recipe("Orange", "TEST_FOOD_CATEGORY", R.drawable.test_food_img));
        recipeArrayList.add(new Recipe("Orange", "TEST_FOOD_CATEGORY", R.drawable.test_food_img));
        recipeArrayList.add(new Recipe("Orange", "TEST_FOOD_CATEGORY", R.drawable.test_food_img));
        recipeArrayList.add(new Recipe("Orange", "TEST_FOOD_CATEGORY", R.drawable.test_food_img));
        recipeArrayList.add(new Recipe("Orange", "TEST_FOOD_CATEGORY", R.drawable.test_food_img));*/

        // END OF TEST_ARRAY

        LinearLayoutManager linearLayoutManagerRTC = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManagerHome2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManagerHome3 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManagerHome4 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewRTC.setLayoutManager(linearLayoutManagerRTC);
        recyclerViewHome2.setLayoutManager(linearLayoutManagerHome2);
        recyclerViewHome3.setLayoutManager(linearLayoutManagerHome3);
        recyclerViewHome4.setLayoutManager(linearLayoutManagerHome4);



        return view;
    }


    @Override
    public void onResponseRandomRecipe(List<RecipeApi> recipes) {

        //recipeArrayListReadyToCoock = JsonParser.parseRandomRecipe(jsonFile);
        recipeArrayListReadyToCoock.clear();
        recipeArrayListReadyToCoock.addAll(recipes);



        RecipeAdapter recipeAdapter = new RecipeAdapter(getContext(), recipeArrayListReadyToCoock);
        recyclerViewRTC.setAdapter(recipeAdapter);

    }

    @Override
    public void onResponseRandomRecipeDessert(List<RecipeApi> recipes) {

        //recipeArrayListDessert = JsonParser.parseRandomRecipe(jsonFile);
        recipeArrayListDessert.clear();
        recipeArrayListDessert.addAll(recipes);

        RecipeAdapterSubcard recipeAdapterSubcard1 = new RecipeAdapterSubcard(getContext(), recipeArrayListDessert);
        recyclerViewHome4.setAdapter(recipeAdapterSubcard1);

    }

    @Override
    public void onResponseRandomRecipeMainCourse(List<RecipeApi> recipes) {
       // recipeArrayListMainCourse = JsonParser.parseRandomRecipe(jsonFile);
        recipeArrayListMainCourse.clear();
        recipeArrayListMainCourse.addAll(recipes);
        RecipeAdapterSubcard recipeAdapterSubcard2 = new RecipeAdapterSubcard(getContext(), recipeArrayListMainCourse);
        recyclerViewHome3.setAdapter(recipeAdapterSubcard2);

    }

    @Override
    public void onResponseRandomRecipeFirstCourse(List<RecipeApi> recipes) {

        //recipeArrayListFirstCourse = JsonParser.parseRandomRecipe(jsonFile);
        recipeArrayListFirstCourse.clear();
        recipeArrayListFirstCourse.addAll(recipes);

        for(RecipeApi r: recipeArrayListFirstCourse)
            Log.d("Gson", "FirstCourse " +r.toString());

        RecipeAdapterSubcard recipeAdapterSubcard3 = new RecipeAdapterSubcard(getContext(), recipeArrayListFirstCourse);
        recyclerViewHome2.setAdapter(recipeAdapterSubcard3);

    }



    @Override
    public void onResponseGetStep(String jsonFile) {

    }

    @Override
    public void onFailure(int errorMessage) {

        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }
}