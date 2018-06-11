package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.*;


@Entity
public class Order extends Model{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_gen")
    private String orderId;
    //list of product ids
    private double cost;
    private double shippingCost;
    private string shipping address;
    private boolean discountApplied;
    private int discountAmmount;
    //email address many orders to one email. one email to any order

    //Finders
    public static Finder<String, Order> find = new Finder<String, Order>(Order.class);

    public static List<Item> findAll(){
        return Order.find.all();
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public string getShipping() {
        return shipping;
    }

    public void setShipping(string shipping) {
        this.shipping = shipping;
    }

    public boolean isDiscountApplied() {
        return discountApplied;
    }

    public void setDiscountApplied(boolean discountApplied) {
        this.discountApplied = discountApplied;
    }

    public int getDiscountAmmount() {
        return discountAmmount;
    }

    public void setDiscountAmmount(int discountAmmount) {
        this.discountAmmount = discountAmmount;
    }

}
