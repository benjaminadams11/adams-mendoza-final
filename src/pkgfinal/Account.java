/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal;

/**
 *
 * @author Ben Adams
 */
public class Account {
    private String username;
    private String password;
    
    public Account(){
        
    }
    public Account(String u, String p){
        username= u;
        password = p;
    }
    
    public String getUsername(){
        return username;
    }
    public void setUsername(String u){
        username = u;
    }
    
    public String getPassword(){
        return password;
    }
    public void setPassword(String p){
        password = p;
    }
    
}
