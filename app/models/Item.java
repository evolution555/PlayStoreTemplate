package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.*;

/**
 * Created by evan_ on 24/06/2017.
 */
@Entity
public class Item extends Model{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_gen")
    private String itemId;
    private String title;
    private double cost;
    private String description;
    private String catagory;
    private String address;
    private String image;


    public static Finder<String, Item> find = new Finder<String, Item>(Item.class);

    public static List<Item> findAll(){
        return Item.find.all();
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getImage(){
        return image;
    }

    public void setImage(String image){
        this.image = image;
    }
}
