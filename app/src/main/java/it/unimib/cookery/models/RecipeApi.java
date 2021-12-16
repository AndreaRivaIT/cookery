package it.unimib.cookery.models;

import java.util.ArrayList;
import java.util.List;

public class RecipeApi {


    private int id;
    private String title;
    private String image;
    private List<StepList> analyzedInstructions;

    public RecipeApi(){}

    public RecipeApi(int id, String title, String image, List<StepList> analyzedInstructions){

        this.id=id;
        this.title = title;
        this.image = image;
        this.analyzedInstructions = analyzedInstructions;
    }

    @Override
    public String toString() {
        return "RecipeApi{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public List<StepList> getAnalyzedInstructions() {
        return analyzedInstructions;
    }

    public void setAnalyzedInstructions(List<StepList> analyzedInstructions) {
        this.analyzedInstructions = analyzedInstructions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<String> extractSteps(){

        ArrayList<String> steps = new ArrayList<>();

        ArrayList<StepList> stepLists = (ArrayList<StepList>) analyzedInstructions;

        for(int i=0; i< stepLists.size(); i++){

            StepList list = stepLists.get(i);

            ArrayList<StepApi> stepApi = (ArrayList<StepApi>) list.getSteps();


                for(int j=0; j< stepApi.size(); j++){

                    steps.add(stepApi.get(j).getStep());

            }
        }



        return steps;

    }



}
