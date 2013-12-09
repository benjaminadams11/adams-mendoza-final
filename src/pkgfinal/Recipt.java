/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal;
import java.util.*;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;
import javax.swing.JTextArea;
import java.awt.Font;
import java.math.BigDecimal;
/**
 *
 * @author Ben Adams
 */
public class Recipt {
    static ArrayList <Double> prices = new ArrayList<Double>();
    static ArrayList <Integer> quantity = new ArrayList<Integer>();
    static ArrayList <String> item = new ArrayList<String>();
    static String nameOfBusiness, address, contact, payment, date, time, city;
    static double tip, subtotal, total, paid;
    static double change = 0.0;
    static int reciptNum;
    
    
    /**
     * @param args the command line arguments
     */
    public void prepare() {
        

        DecimalFormat money = new DecimalFormat("##0.00");
        for(int x = 0; x < item.size(); x++)
        {
            subtotal = subtotal + quantity.get(x)* prices.get(x);
        }
        
        total = subtotal;
    }
    
    public void print(){
        processPaymentType();
        Printer.printToPrinter(formatJText());
    }
    public void setup(){
        nameOfBusiness = "Market";
        address = "123 Main St.";
        contact = "222-222-2222";
        time = "2:00 PM";
        city = "San Francisco, Ca 22122";
        date = "May, 1 2013";
    }
    
    public void addItem(String i, BigDecimal p, int q){
        prices.add(Double.valueOf(p.doubleValue()));
        item.add(i);
        quantity.add(q);
        
    }
    
    public boolean checkIfExists(String n, int q){
        boolean check = false;
        for(int i = 0; i <item.size(); i++){
            if(item.get(i).equalsIgnoreCase(n)&&quantity.get(i)>=q){
                check = true;
            }
        }
        return check;
    }
    
    public void remove(String n, int q){
        int index=0;
        for(int i = 0;i<item.size();i++){
            if(item.get(i).equalsIgnoreCase(n)&&quantity.get(i)>=q){
                index = i;
            }
        }
        if(quantity.get(index)==q){
            item.remove(index);
            quantity.remove(index);
            prices.remove(index);
        }
        else{
            int old = quantity.get(index);
            int set = quantity.set(index,old-q);
        }
    }
    
    public void reset(){
        prices.clear();
        item.clear();
        quantity.clear();
        subtotal = 0.0;
        total = 0.0;
        tip = 0.0;
        paid = 0.0;
        change = 0.0;
        reciptNum = 0;
        
    }
    
    public String record(){
        DecimalFormat money = new DecimalFormat("##0.00");
        String record = "";
        for(int x = 0; x < item.size(); x++){
            record += "\n" + String.format("%-25s %s",quantity.get(x) + " X " +item.get(x), "$" +money.format(prices.get(x)*quantity.get(x)));
        }
        record += "\n" + "-------------------------------";
        record += "\n" + String.format("%-25s %s","Subtotal w/Tax", "$" + money.format(subtotal));
        record += "\n" + String.format("%-25s %s","Tip", "$" + money.format(tip));
        record += "\n" + String.format("%-25s %s","Total", "$" + money.format(total));
        record += "\n" + "===============================";
        return record;
    }
    
    public void addItem()
    {
        item.add(JOptionPane.showInputDialog(null, "Enter name of item"));
        prices.add(Double.parseDouble(JOptionPane.showInputDialog(null, "Enter Price of item")));
        quantity.add(Integer.parseInt(JOptionPane.showInputDialog(null, "Enter quantity of item")));
    }
       
    public void processPaymentType(){
        DecimalFormat money = new DecimalFormat("##0.00");
        tip = Double.parseDouble(JOptionPane.showInputDialog(null, "Subtotal is " + money.format(subtotal) + ". Enter Tip:"));
        total=tip+total;
        payment = JOptionPane.showInputDialog(null, "Enter Payment Type(cash, visa, etc.)");
        if(payment.equalsIgnoreCase("cash"))
        {
            paid = Double.parseDouble(JOptionPane.showInputDialog(null, "Total is " + money.format(total) +".  Enter cash paid:"));
            change = paid - total;
        }
        else
        {
            paid = total;
        }
        
    }
    
    public String formatForPrinter(){
        
        DecimalFormat money = new DecimalFormat("##0.00");
        String output = "";
        if(payment!=null){
            output+=nameOfBusiness + "\n" + String.format("%-25s %s",address, date) + "\n" + String.format("%-29s %s",city, time) + "\n" + contact;
            output += "\n" + "-------------------------------";
        }
        for(int x = 0; x < item.size(); x++){
            output += "\n" + String.format("%-25s %s",quantity.get(x) + " X " +item.get(x), "$" +money.format(prices.get(x)*quantity.get(x)));
        }       
        output += "\n" + "-------------------------------";
        output += "\n" + String.format("%-25s %s","Subtotal w/Tax", "$" + money.format(subtotal));
        if(tip!=0.0){
            output += "\n" + String.format("%-25s %s","Tip", "$" + money.format(tip));
        }
        output += "\n" + "-------------------------------";
        output += "\n" + String.format("%-25s %s","Total", "$" + money.format(total));
        if(payment!=null){
            output += "\n" + String.format("%-25s %s",payment, "$" + money.format(paid));
        }
        if(change != 0.0){
            output += String.format("%-25s %s","\n" + "Change", "$" + money.format(change));
        }
        output += "\n" + "-------------------------------";
        return output;
    }
    
    public JTextArea formatJText(){
        Font font = new Font("Courier", Font.PLAIN, 12);
        JTextArea recipt = new JTextArea(formatForPrinter());
        recipt.setFont(font);
        return recipt;
    }
}
