package it.unimib.cookery.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "ingredient_api")
public class IngredientApi implements Parcelable {
    @PrimaryKey
    private int id;
    private String name;
    @Ignore
    private double amount;
    @Ignore
    private String unit;

    public IngredientApi(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Ignore
    public IngredientApi() {
    }

    @Ignore
    protected IngredientApi(Parcel in) {
        id = in.readInt();
        name = in.readString();
        amount = in.readDouble();
        unit = in.readString();
    }


    public static final Parcelable.Creator<IngredientApi> CREATOR = new Parcelable.Creator<IngredientApi>() {
        @Override
        public IngredientApi createFromParcel(Parcel in) {
            return new IngredientApi(in);
        }

        @Override
        public IngredientApi[] newArray(int size) {
            return new IngredientApi[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }


    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "IngredientApi{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", unit='" + unit + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeDouble(amount);
        dest.writeString(unit);
    }

}

