package test1;

import java.util.Scanner;
public class Product {
    private String name;
    private double price;
    private double tax;
    public void nhapThongTin(){
        Scanner nhap=new Scanner(System.in);
    }
    public void xuatThongTin(){
        
    }
    public double getTaxPrice(double price,double tax){
        return price*tax;
    }
    public Product(String name,double price,double tax){
        this.name=name;
        this.price=price;
        this.tax=tax;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price=price;
    }
    public double getTax(){
        return tax;
    }
    public void setTax(double tax){
        this.tax=tax;
    }
    public static void main(String[] args){
        System.out.println("hello");
    }
}
