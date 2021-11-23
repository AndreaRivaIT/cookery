package it.unimib.cookery.model;

public class RecipeStep {
    private int nStep;
    private String description;

    public RecipeStep(int nStep, String description) {
        this.nStep = nStep;
        this.description = description;
    }

    public int getnStep() {
        return nStep;
    }

    public String getDescription() {
        return description;
    }

    public void setnStep(int nStep) {
        this.nStep = nStep;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
