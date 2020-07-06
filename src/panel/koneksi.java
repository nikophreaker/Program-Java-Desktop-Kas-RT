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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author NikxPhreaker
 */
public class koneksi {
    private Connection koneksi;
    public Connection connect(){
    try{
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Berhasil Koneksi");
    }catch (ClassNotFoundException ex){
        System.out.println("Gagal Koneksi "+ex);
    }
    String url = "jdbc:mysql://localhost/db_kas";
    try {
        koneksi = DriverManager.getConnection(url,"root","");
        System.out.println("Berhasil Koneksi Database");
    }catch (SQLException ex) {
        System.out.println("Gagal Koneksi Database"+ex);
    }
    return koneksi;
    }
}

//    Connection conn;
//    Statement stm;
//    
//    public void config(){
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            conn=DriverManager.getConnection("jdbc:mysql://localhost/malasngoding", "root", "");
//            stm = conn.createStatement();
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "koneksi gagal "+e.getMessage());
//        }
//    }
 // private Connection conn;
//    public Connection connect(){
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//                    System.out.println("Berhasil Koneksi");       
//            }catch (ClassNotFoundException ex) {
//                System.out.println("Gagal Koneksi "+ex);
//        }
//        String url = "jdbc:mysql://localhost/gaji";
//        try {
//            conn = DriverManager.getConnection(url,"root","");
//            System.out.println("Berhasil Koneksi Database");
//        }catch (SQLException ex) {
//            System.out.println("Gagal KOneksi Database");
//        }
//        return conn;
//    }

//    public Connection openkoneksi() throws ClassNotFoundException{
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost/gaji?user=root&pass=");
//            return conn;
//        }catch(SQLException e){
//            JOptionPane.showMessageDialog(null, "Tidak ada koneksi yang terbuka.");
//            return null;
//        }
//    }
//    
//    public void closekoneksi() throws SQLException{
//        try{
//            if(conn != null){
//                System.out.print("Tutup Koneksi\n");
//            }
//        }catch(Exception ex){
//        }
//    }
    
    
    
    
    
    
