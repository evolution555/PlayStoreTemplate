package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.*;


@Entity
public class Item extends Model{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_gen")
    private String itemId;
    private String title;
    private double cost;
    private String description;
    private String catagory;
    private boolean availability;
    private int quantity;
    private String image; //change to list
    private double productionCost;
    private double shippingCost;
    private double profit;

    //Finders
    public static Finder<String, Item> find = new Finder<String, Item>(Item.class);

    public static List<Item> findAll(){
        return Item.find.all();
    }


    //ItemId
    //get current itemId
    public String getItemId() {
        return itemId;
    }
//
//    //Set item id
//    public void setItemId(String itemId) {
//        this.itemId = itemId;
//    }


    //Title
    //Get current title
    public String getTitle() {
        return title;
    }

    //Set title
    public void setTitle(String title) {
        this.title = title;
    }

    //Selling price
    //Get selling price
    public double getCost() {
        return cost;
    }

    //Set selling price
    public void setCost(double cost) {
        this.cost = cost;
    }


    //Description
    //Gets current description
    public String getDescription() {
        return description;
    }

    //Sets description
    public void setDescription(String description) {
        this.description = description;
    }


    //Catagory
    //Returns the current quantity
    public String getCatagory() {
        return catagory;
    }

    //Sets catagory
    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }


    //Availability
    //Returns the current avialibility
    public void getAvailability() {
        if (getQuantity() <= 0) {
            availability = false;
        } else {
            availability = true;
        }
    }

    //Image
    //Returns the current image url
    public String getImage(){
        return image;
    }

    //Sets image url
    public void setImage(String image){
        this.image = image;
    }


    //Quantity
    //call when X quantity of item is purchased
    public void reduceQuantity(int quant){
        if(quantity - quant >= 0){
            getAvailability();
        }
    }

    //Sets quantity to value
    public void getQuantity(int quantity) {
        this.quantity = quantity;
        getAvailability();
    }

    //Returns the current quantity
    public int getQuantity() {
        return quantity;
    }

    //Production Cost
    //Get Prod cost
    public double getProductionCost() {
        return productionCost;
    }

    //Set prod cost
    public void setProductionCost(double productionCost) {
        this.productionCost = productionCost;
    }


    //Shipping cost
    //get shipping cost
    public double getShippingCost() {
        return shippingCost;
    }

    //set Shipping cost
    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }


    //Profit
    //Get profit
    public double getProfit() {
         profit = getCost()- getProductionCost() - getShippingCost();
        return profit;
    }
}
