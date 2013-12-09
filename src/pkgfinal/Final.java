/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JApplet;
import java.util.*;
import javax.swing.Box;
import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author Ben Adams
 */
public class Final extends JApplet implements ActionListener {
    ArrayList<Account> accounts = new ArrayList<Account>();
    ArrayList<Product> product = new ArrayList<Product>();
    ArrayList<String> log = new ArrayList<String>();
    ArrayList<Product> productToBuy = new ArrayList<Product>();
    String currentProducts;
    int currentAccountIndex;
    Recipt recipt = new Recipt();
    
    JTextArea transaction, products;
    JButton create, login, register, buy, sell, add, print, logout, record, backToMenu, backToLogin;
    JTextField uName, pWord, itemInput, quantityInput, addItemInput, addPriceInput, addQuantityInput;
    JTextArea displayRecord= new JTextArea("");
    JLabel username = new JLabel("Username: ");
    JLabel password = new JLabel("Password: ");
    JLabel empty = new JLabel("");
    JLabel emptyLogout = new JLabel("");
    JLabel emptyRecord = new JLabel("");
    JLabel emptyMenu = new JLabel("");
    JLabel emptyPrint = new JLabel("");
    JLabel emptyAdd = new JLabel("");
    JLabel emptyBuy = new JLabel("");
    JLabel emptySell = new JLabel("");
    JLabel title = new JLabel("Market");
    
    GroupLayout layout, layoutCreate, layoutProduct;
    Container contentPane;
    
    JPanel transactionPanel = new JPanel(new GridLayout(2,1));
    JPanel addPanel = new JPanel(new GridLayout(3,1));
    
    DecimalFormat money = new DecimalFormat("##0.00");
    
    public void setup(){
        //initial account and available products
        accounts.add(new Account("Ben", "1234"));
        Product grape = new Product();
        grape.setName("Grape");
        grape.setPrice(1.00);
        grape.setQuantity(20);
        Product apple = new Product();
        apple.setName("Apple");
        apple.setPrice(1.00);
        apple.setQuantity(20);
        product.add(grape);
        product.add(apple);
        
        //recipt
        recipt.setup();
        
        //JPanel for buy/sell
        itemInput = new JTextField();
        quantityInput = new JTextField();
        transactionPanel.add(new JLabel("Item:"));
        transactionPanel.add(itemInput);
        transactionPanel.add(new JLabel("Quantity:"));
        transactionPanel.add(quantityInput);
        
        //JPanel for adding products
        addItemInput = new JTextField();
        addQuantityInput = new JTextField();
        addPriceInput = new JTextField();
        addPanel.add(new JLabel("Item:"));
        addPanel.add(addItemInput);
        addPanel.add(new JLabel("Quantity:"));
        addPanel.add(addQuantityInput);
        addPanel.add(new JLabel("Price:"));
        addPanel.add(addPriceInput);
        
    }
    
    public void init(){
        //setup
        setup();
        
        //set layout of JApplet
        contentPane = getContentPane();
        layout = new GroupLayout(contentPane);
        layoutCreate = new GroupLayout(contentPane);
        layoutProduct = new GroupLayout(contentPane);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        //set initial menu
        for(int i =0; i<product.size(); i++){
            Product p = product.get(i);
            currentProducts+= p.getName()+ "  $" + money.format(p.getPrice())+ "   Stock: "+ p.getQuantity()+ "\n";
        }
        currentProducts= currentProducts.substring(4,currentProducts.length());
        
        //set text on components
        login = new JButton("Login");
        register = new JButton("Register");
        create = new JButton("Create");
        buy = new JButton("Buy Item");
        sell = new JButton("Return Item");
        add = new JButton("Add Product");
        print = new JButton("Print Recipt");
        logout = new JButton("Logout");
        record = new JButton("Activity Log");
        backToMenu = new JButton("Back");
        backToLogin = new JButton("Back");
        uName = new JTextField();
        pWord = new JTextField();
        transaction = new JTextArea("Current Order:"+ "\n");
        products = new JTextArea("Menu:"+ "\n" + currentProducts);
        
        //initial horizontal layout
        GroupLayout.Group horizontalAuthenticate =layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(LEADING)
                .addComponent(title)
                .addComponent(username)
                .addComponent(password))
            .addGroup(layout.createParallelGroup(LEADING)
                .addComponent(emptyLogout)
                .addComponent(uName)
                .addComponent(pWord))
            .addGroup(layout.createParallelGroup(LEADING)
                .addComponent(emptyRecord)
                .addComponent(login)
                .addComponent(register));
        
        //initial Vertical layout
        GroupLayout.Group verticalAuthenticate=layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(BASELINE)
                .addComponent(title)
                .addComponent(emptyLogout)
                .addComponent(emptyRecord))
            .addGroup(layout.createParallelGroup(BASELINE)
                .addComponent(username)
                .addComponent(uName)
                .addComponent(login))
            .addGroup(layout.createParallelGroup(BASELINE)
                .addComponent(password)
                .addComponent(pWord)
                .addComponent(register));
        
        layout.setVerticalGroup(verticalAuthenticate);
        layout.setHorizontalGroup(horizontalAuthenticate);
        
        contentPane.setLayout(layout);
        setSize(500,650);
        
        
        //ActionListener
        login.addActionListener(this);
        create.addActionListener(this);
        buy.addActionListener(this);
        sell.addActionListener(this);
        print.addActionListener(this);
        add.addActionListener(this);
        register.addActionListener(this);
        logout.addActionListener(this);
        record.addActionListener(this);
        backToMenu.addActionListener(this);
        backToLogin.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==login){
            //check if account exists
            boolean check = false;
            for(int i = 0; i<accounts.size(); i++){
                if(accounts.get(i).getUsername().equals(uName.getText()) && accounts.get(i).getPassword().equals(pWord.getText())){
                    check = true;
                    currentAccountIndex=i;
                }
            }
            if(check==true){
                layout.replace(username, transaction);
                layout.replace(password, products);
                layout.replace(uName, buy);
                layout.replace(pWord, sell);
                layout.replace(login, add);
                layout.replace(register, print);
                layout.replace(emptyLogout, logout);
                layout.replace(emptyRecord, record);
            }
            else{
                JOptionPane.showMessageDialog(null, "Invalid Username or Password");
            }
        }
        if(ae.getSource()==register){
            uName.setText(null);
            pWord.setText(null);
            layout.replace(register, backToLogin);
            layout.replace(login, create);
        }
        if(ae.getSource()==create){
            //check if username and password are not null, add account
            if(uName.getText().length()>0&&pWord.getText().length()>0){
                Account temp = new Account(uName.getText(),pWord.getText());
                accounts.add(temp);
                uName.setText(null);
                pWord.setText(null);
                layout.replace(create, login);
                layout.replace(empty, register);
            }
        }
        if(ae.getSource()==backToLogin){
            layout.replace(register, backToLogin);
            layout.replace(login, create);
            uName.setText(null);
            pWord.setText(null);
        }
        if(ae.getSource()==buy){
            //enter desired item
            int result = JOptionPane.showConfirmDialog(null, transactionPanel, 
               "Enter item and quantity desired", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION){
                boolean check = false;
                //check if entered product matches product on menu
                int index = 0;
                for(int i = 0; i<product.size();i++){
                    if(itemInput.getText().equalsIgnoreCase(product.get(i).getName())&&product.get(i).getQuantity()>=Integer.parseInt(quantityInput.getText())){
                        check = true;
                        index = i;
                    }
                }
                if(check==true){
                    recipt.addItem(product.get(index).getName(),product.get(index).getPrice(),Integer.parseInt(quantityInput.getText()));
                    recipt.prepare();
                    transaction.setText(recipt.formatForPrinter());
                    
                    //change quantity on menu
                    int old= product.get(index).getQuantity();
                    product.get(index).setQuantity(old-Integer.parseInt(quantityInput.getText()));
                    
                    //reset menu
                    currentProducts=null;
                    for(int i =0; i<product.size(); i++){
                        Product p = product.get(i);
                        currentProducts+= p.getName()+ "  $" + money.format(p.getPrice())+ "   Stock: "+ p.getQuantity()+ "\n";
                    }
                    currentProducts= currentProducts.substring(4,currentProducts.length());
                    products.setText("Menu:"+ "\n" + currentProducts);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Item and/or quantity does not exist");
                }
            }
        }
        if(ae.getSource()==print){
            //print recipt
            recipt.print();
            
            //record user activity
            String record = "User: "+ accounts.get(currentAccountIndex).getUsername();
            record+=recipt.record();
            log.add(record);
            
            //reset recipt and current transaction
            recipt.reset();
            transaction.setText("Current Order:" + "\n");
        }
        if(ae.getSource()==add){
            int result = JOptionPane.showConfirmDialog(null, addPanel, 
               "Enter item, quantity, and price", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION){
                Product newP = new Product();
                newP.setName(addItemInput.getText());
                newP.setPrice(Double.parseDouble(addPriceInput.getText()));
                newP.setQuantity(Integer.parseInt(addQuantityInput.getText()));
                product.add(newP);
                
                //reset Menu
                currentProducts=null;
                for(int i =0; i<product.size(); i++){
                    Product p = product.get(i);
                    currentProducts+= p.getName()+ "  $" + money.format(p.getPrice())+ "   Stock: "+ p.getQuantity()+ "\n";
                }
                currentProducts= currentProducts.substring(4,currentProducts.length());
                products.setText("Menu:"+ "\n" + currentProducts);
            }
            
        }
        if(ae.getSource()==sell){
            int result = JOptionPane.showConfirmDialog(null, transactionPanel, 
               "Enter item and quantity desired", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION){
                //check if item exists on recipt
                if (recipt.checkIfExists(itemInput.getText(),Integer.parseInt(quantityInput.getText()))==true){
                    //remove item from recipt
                    recipt.remove(itemInput.getText(),Integer.parseInt(quantityInput.getText()));
                    recipt.prepare();
                    transaction.setText(recipt.formatForPrinter());
                    
                    //add item back to menu
                    int index=0;
                    for(int i =0; i<product.size();i++){
                        if(product.get(i).getName().equalsIgnoreCase(itemInput.getText())){
                            index = i;
                        }
                    }
                    int oldQuant = product.get(index).getQuantity();
                    product.get(index).setQuantity(oldQuant - Integer.parseInt(quantityInput.getText()));
                    
                    //reset menu
                    currentProducts=null;
                    for(int i =0; i<product.size(); i++){
                        Product p = product.get(i);
                        currentProducts+= p.getName()+ "  $" + money.format(p.getPrice())+ "   Stock: "+ p.getQuantity()+ "\n";
                    }
                    currentProducts= currentProducts.substring(4,currentProducts.length());
                    products.setText("Menu:"+ "\n" + currentProducts);
                }
                
                else{
                    JOptionPane.showMessageDialog(null, "Item does not exist or quantity is too high");
                }
            }
            
        }
        if(ae.getSource()==logout){
            //reset input fields
            uName.setText(null);
            pWord.setText(null);
            transaction.setText(null);
            //change layout
            layout.replace(transaction, username);
            layout.replace(products, password);
            layout.replace(buy, uName);
            layout.replace(sell, pWord);
            layout.replace(add, login);
            layout.replace(print, register);
            layout.replace(logout,emptyLogout);
            layout.replace(record, emptyRecord);
        }
        if(ae.getSource()==record){
            //change layout
            layout.replace(transaction, emptyMenu);
            layout.replace(products, displayRecord);
            layout.replace(buy, emptyBuy);
            layout.replace(sell, emptySell);
            layout.replace(add, emptyAdd);
            layout.replace(print, emptyPrint);
            layout.replace(logout,emptyLogout);
            layout.replace(record, backToMenu);
            
            //display record
            String record="";
            for(int i = 0; i <log.size();i++){
                record+=log.get(i);
            }
            displayRecord.setText(record);
        }
        if(ae.getSource()==backToMenu){
            //change interface
            layout.replace(emptyMenu, transaction);
            layout.replace(displayRecord, products);
            layout.replace(emptyBuy, buy);
            layout.replace(emptySell, sell);
            layout.replace(emptyAdd, add);
            layout.replace(emptyPrint, print);
            layout.replace(emptyLogout, logout);
            layout.replace(backToMenu, record);
        }
    }

}
