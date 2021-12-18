package it.unimib.cookery.models;

public class StepApi {


    private int number;
    private String step;


    public StepApi() {
    }

    public StepApi(int number, String step) {
        this.number = number;
        this.step = step;
    }


    @Override
    public String toString() {
        return "StepApi{" +
                "number=" + number +
                ", steps='" + step + '\'' +
                '}';
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String steps) {
        this.step = steps;
    }


}
