package it.unimib.cookery.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayoutManager;

import it.unimib.cookery.R;

import it.unimib.cookery.adapters.IngredientChipAdapter;
import it.unimib.cookery.models.Ingredient;
import it.unimib.cookery.models.Pantry;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PantryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PantryFragment extends Fragment {
    private RecyclerView rcvChips;
    private IngredientChipAdapter  ingredientChipAdapter;
    private Pantry pantry;
    private View root;
    private Ingredient pasta;
    private Ingredient pomodoro;
    private Ingredient carciofi;
    private Ingredient sale;
    private Ingredient pepeA;
    private Ingredient pepeB;
    private Ingredient besciamella;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PantryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PantryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PantryFragment newInstance(String param1, String param2) {
        PantryFragment fragment = new PantryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
        setPantry();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_pantry, container, false);
        createChips(root.findViewById(R.id.rcv_pantry));
        createChips(root.findViewById(R.id.rcv_fridge));
        return root;
    }

    private void createChips(RecyclerView Rvc){
        // inflate chips utilizzado il FlexboxLayoutManager per non avere l'impedimento delle colonne
        RecyclerView rcvChips = Rvc;
        ingredientChipAdapter = new IngredientChipAdapter();
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager( getContext());
        rcvChips.setLayoutManager(flexboxLayoutManager);
        rcvChips.setFocusable(false);
        rcvChips.setNestedScrollingEnabled(false);
        ingredientChipAdapter.setData(pantry.getIngredientList());
        rcvChips.setAdapter(ingredientChipAdapter);
    }
    //da cancellare solo per lo sviluppo
    public void setPantry(){
        //inizializare gli oggetto ricetta, riceverà id e poi verra fatta una chiamata api con ID

        // inizializzazione oggetti ingredienti
        pasta = new Ingredient(1,"pasta",180);
        pomodoro = new Ingredient(2,"pomodoro",80);
        carciofi = new Ingredient(3,"carciofi",120);
        sale = new Ingredient(4,"sale",4);
        pepeA = new Ingredient(5,"pepe",8);
        pepeB = new Ingredient(5,"pepe",8);
        besciamella = new Ingredient(6, "besciamella", 158);
        pantry = new Pantry(pasta);

        Log.d("test",pasta.getIngredientName());

        pantry.setIngredient(pomodoro);
        pantry.setIngredient(carciofi);
        pantry.setIngredient(sale);
        pantry.setIngredient(pepeA);
        pantry.setIngredient(pepeB);
        pantry.setIngredient(besciamella);
    }
}