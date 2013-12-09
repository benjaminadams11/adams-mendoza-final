/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal;
import java.math.BigDecimal;

/**
 *
 * @author Ben Adams
 */
public class Product {
    private String name, description, barcode, image;
    private BigDecimal price;
    private int quantity;
    private static int count;
    
    public Product(){
        //Default Constructor
        count+=1;
    }
    
    public Product(String n, String d, double p, int q, String b, String i){
        this.name = n;
        this.description = d;
        this.price = BigDecimal.valueOf(p);
        this.quantity = q;
        this.barcode = b;
        this.image = i;
        count +=1;
    }
    
    public void buy(int x){
        if(x<=quantity){
            quantity = quantity - x;
        }
        else{
            System.out.println("Not enough in stock");
        }
    }
    
    public void sell(int x){
        quantity = quantity + x;
    }
    
    public void setName(String n){
        this.name = n;
    }
    public String getName(){
        return this.name;
    }
    
    public void setDescription(String d){
        this.description = d;
    }
    public String getDescription(){
        return this.description;
    }
    
    public void setPrice(Double p){
        this.price=BigDecimal.valueOf(p);
    }
    public BigDecimal getPrice(){
        return this.price;
    }
    
    public void setQuantity(int q){
        this.quantity = q;
    }
    public int getQuantity(){
        return this.quantity;
    }
    
    public void setBarcode(String b){
        this.barcode = b;
    }
    public String getBarcode(){
        return this.barcode;
    }
    
    public void setImage(String i){
        this.image = i;
    }
    public String getImage(){
        return this.image;
    }
    
    public static int getCount(){
        return count;
    }
    
    @Override
    public String toString(){
        return "Product Name: " + this.name + "\nProduct Description: " + this.description
                + "\nPrice: " + this.price + "\nQuantity: " + this.quantity;
    }
}