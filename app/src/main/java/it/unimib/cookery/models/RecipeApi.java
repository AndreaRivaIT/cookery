package it.unimib.cookery.models;

public class RecipeApi {


    private int id;
    private String title;
    private String image;

    public RecipeApi(){}

    public RecipeApi(int id, String title, String image){

        this.id=id;
        this.title = title;
        this.image = image;
    }

    @Override
    public String toString() {
        return "RecipeApi{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                '}';
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



}
