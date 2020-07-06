/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

/**
 *
 * @author Nikx
 */
public class users {
    public String username, password, nama;
    
    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }
    
    public void setNama(String nama){
        this.nama = nama;
    }
 
    public String getUsername() {
        return username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public String getNama() {
        return nama;
    }
}
