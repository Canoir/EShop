package ir.justdev.lab.myeshop.Models.Model;

import java.util.Date;

public class Product {
    public String name;
    public String imageAddress;
    public int price;
    public int seen;
    public byte off;
    public Date setDate;
    public Brand brand;

    public Product(String name, String imageAddress, int price, int seen, byte off, Date setDate, Brand brand) {
        this.imageAddress = imageAddress;
        this.name = name;
        this.price = price;
        this.seen = seen;
        this.off = off;
        this.setDate = setDate;
        this.brand = brand;
    }
}
