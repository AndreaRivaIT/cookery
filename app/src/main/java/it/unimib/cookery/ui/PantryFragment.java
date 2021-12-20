package it.unimib.cookery.ui;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.inputmethod.InputMethodManager;

import android.widget.Button;

import android.widget.LinearLayout;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.material.bottomsheet.BottomSheetBehavior;


import it.unimib.cookery.R;


import it.unimib.cookery.adapters.IngredientChipAdapterPantry;
import it.unimib.cookery.adapters.SearchChipAdapter;
import it.unimib.cookery.models.Ingredient;
import it.unimib.cookery.models.IngredientPantry;

import it.unimib.cookery.repository.DatabasePantryRepository;
import it.unimib.cookery.utils.ResponseCallbackDb;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class PantryFragment extends Fragment implements ResponseCallbackDb {

   // private static IngredientChipAdapter ingredientPantryAdapter;
    private SearchChipAdapter searchChipAdapter = new SearchChipAdapter();
    //buttom sheet
    private BottomSheetBehavior bottomSheetBehavior;
    private Button btnClose;
    private SearchView searchView;
    private int quantità = 0;

    private View root;
    static private DatabasePantryRepository db;
    static private IngredientPantry ingredientAdd;
    private List<IngredientPantry> list;
    static  private List<IngredientPantry> pantry = new ArrayList();
    static  private List<IngredientPantry> fridge = new ArrayList();
    static  private List<IngredientPantry> freezer = new ArrayList();

    private RecyclerView rvPantry;
    private RecyclerView rvFridge;
    private RecyclerView rvFreezer;
    private RecyclerView rcvIngredientSearch;
    private List<Ingredient> ingredientApiSearch = new ArrayList();

    private static IngredientChipAdapterPantry ingredientPantryAdapter = new IngredientChipAdapterPantry();
    private static IngredientChipAdapterPantry ingredientFridgeAdapter = new IngredientChipAdapterPantry();
    private static IngredientChipAdapterPantry ingredientFreezeAdapter = new IngredientChipAdapterPantry();

    private LinearLayout designBottomSheet;
    //test ricerca e aggiunta di ricetta
    private Ingredient primo;
    private Ingredient secondo;
    private Ingredient terzo;
    public PantryFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        primo = new Ingredient(5,"Pomodoro",6);
        secondo = new Ingredient(5,"Pelati",7);
        terzo = new Ingredient(5,"Pomodorini",5);
        ingredientApiSearch.add(primo);
        ingredientApiSearch.add(secondo);
        ingredientApiSearch.add(terzo);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_pantry, container, false);
        db = new DatabasePantryRepository(requireActivity().getApplication(),this);
        db.readAllIngredientPantry();

        FlexboxLayoutManager flexboxLayoutManagerPantry = new FlexboxLayoutManager( getContext());
        rvPantry = root.findViewById(R.id.rcv_pantry);
        rvPantry.setLayoutManager(flexboxLayoutManagerPantry);
        rvPantry.setFocusable(false);
        rvPantry.setNestedScrollingEnabled(false);

        FlexboxLayoutManager flexboxLayoutManagerFridgee = new FlexboxLayoutManager( getContext());
        rvFridge = root.findViewById(R.id.rcv_fridge);
        rvFridge.setLayoutManager(flexboxLayoutManagerFridgee);
        rvFridge.setFocusable(false);
        rvFridge.setNestedScrollingEnabled(false);

        FlexboxLayoutManager flexboxLayoutManagerFreezer = new FlexboxLayoutManager( getContext());
        rvFreezer =root.findViewById(R.id.rcv_freezer);
        rvFreezer.setLayoutManager(flexboxLayoutManagerFreezer);
        rvFreezer.setFocusable(false);
        rvFreezer.setNestedScrollingEnabled(false);


        //Ricerca dell'ingrediente in basso
        FlexboxLayoutManager flexboxLayoutManagerSearch = new FlexboxLayoutManager( getContext());
        rcvIngredientSearch = root.findViewById(R.id.rcv_ingredient_search);
        rcvIngredientSearch.setLayoutManager(flexboxLayoutManagerSearch);
        rcvIngredientSearch.setFocusable(false);
        rcvIngredientSearch.setNestedScrollingEnabled(false);

        rvPantry.setAdapter(ingredientPantryAdapter);
        rvFridge.setAdapter(ingredientFridgeAdapter);
        rvFreezer.setAdapter(ingredientFreezeAdapter);
        rcvIngredientSearch.setAdapter(searchChipAdapter);

        searchView = root.findViewById(R.id.sv_ingredient);

        designBottomSheet = root.findViewById(R.id.design_bottom_sheet);
        LinearLayout linearLayout = root.findViewById(R.id.design_bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);
        btnClose = root.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                closeKeyboard();
                searchView.clearFocus();
                InputMethodManager imm = (InputMethodManager) v.getContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                rvPantry.removeAllViews();


            }
        });
        //al focus della  search box espande la bottom sheet
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        createSearchChips(ingredientApiSearch);
        designBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.clearFocus();
            }
        });
        return root;
    }

    private void closeKeyboard() {
        // this will give us the view which is currently focus in this layout
        View view = this.getActivity().getCurrentFocus();
        // if nothing is currently focus then this will protect the app from crash
        if (view != null) {
            // now assign the system service to InputMethodManager
            InputMethodManager manager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public static void  savedb(IngredientPantry ingredientPantry){
        Log.d("test"," Save db: "+ ingredientPantry.getIngredientName() +" "+ingredientPantry.getQuantity());
        db.create(ingredientPantry);
        //db.readAllIngredientPantry();
       if(ingredientPantry.pantryId == 1){
            pantry.add(ingredientPantry);
            ingredientPantryAdapter.setData(pantry);
       }else if(ingredientPantry.pantryId == 2){
            fridge.add(ingredientPantry);
            ingredientFridgeAdapter.setData(fridge);
       }else if(ingredientPantry.pantryId == 3){
            freezer.add(ingredientPantry);
           ingredientFreezeAdapter.setData(freezer);
       }
    }

    @Override
    public void onResponse(Object obj) {
        if (obj != null) {
            if( obj instanceof List){
                list = (List) obj;
                Log.d("test"," OnResponse: "+list.size());
                for(int k = 0; k < list.size(); k++){
                    Log.d("test"," OnResponse: " + list.get(k).getIngredientName() +" - "+ list.get(k).getQuantity()+" -- idPantry:" +list.get(k).pantryId);
                }
                Log.d("test"," OnResponse: -----------------------------------------");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pantry.clear();
                        fridge.clear();
                        freezer.clear();
                        for (int i = 0; i < list.size(); i++) {
                            if( list.get(i).pantryId == 1) {
                                pantry.add(list.get(i));
                            }else if( list.get(i).pantryId == 2) {
                                fridge.add(list.get(i));
                            }
                            else if( list.get(i).pantryId == 3) {
                                freezer.add(list.get(i));
                            }
                        }
                        createPantry(pantry);
                        createFridge(fridge);
                        createFreeze(freezer);
                    }
                });
            }
        }

    }
    private void createPantry(List<IngredientPantry> listData){
        Log.d("test"," createPantry: ingredientPantryAdapter-----------------------------------------");
        for(int k=0 ; k <listData.size(); k++){
            Log.d("test"," createPantry: " +listData.get(k).getIngredientName());
        }
        ingredientPantryAdapter.setData(listData);
    }
    private void createFridge(List<IngredientPantry> listData){
        Log.d("test"," CreateFridge: ingredientfreezeAdapter-----------------------------------------");
        for(int k=0 ; k <listData.size(); k++){
            Log.d("test"," CreateFridge: " +listData.get(k).getIngredientName());
        }
        ingredientFridgeAdapter.setData(listData);
    }
    private void createFreeze(List<IngredientPantry> listData){
        Log.d("test"," createFreeze: ingredientFridgeAdapter-----------------------------------------");
        for(int k=0 ; k <listData.size(); k++){
            Log.d("test"," createFreeze: " +listData.get(k).getIngredientName());
        }
        ingredientFreezeAdapter.setData(listData);
    }

    private void createSearchChips(List listData){
        searchChipAdapter.setData(listData);
    }
    @Override
    public void onUpdate(Object obj) {
    }

    @Override
    public void onFailure(String errorMessage) {
    }
}