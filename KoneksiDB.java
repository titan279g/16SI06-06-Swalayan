package Koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
    
public class KoneksiDB {
    private Connection koneksi;
    private ResultSet rs;
    private PreparedStatement ps;
    
    public Connection koneksiDb(){
        if (koneksi == null){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                try{
                    koneksi =  DriverManager.getConnection("jdbc:mysql://localhost:3306/dbswalayan","root","");
                    System.out.println("Koneksi Sukses");
                }catch(SQLException se) {
                    System.out.println("Koneksi gagal " + se);
                    System.exit(0);             
                }
            }catch(ClassNotFoundException cnfe) {
                System.out.println("Class tidak ditemukan " + cnfe);
                System.exit(0);
            }
        }
        return koneksi;
    }
    
    public ResultSet getRs(){
        return rs;
    }
    
    public boolean eksekusi(String query, boolean baris){
        try {
            ps = koneksi.prepareStatement(query);
            if (baris){
                rs = ps.executeQuery();
            }else{
                ps.executeUpdate();
            }
            return true;
        } catch (SQLException se) {
            return false;
        }
    }

    public static void main(String[] args) {
        new KoneksiDB().koneksiDb();
    }
    
}
