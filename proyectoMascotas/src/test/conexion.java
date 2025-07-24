/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;
import java.sql.*;
/**
 *
 * @author Sony Vaio
 */
public class conexion {
    static String url="jdbc:mysql://localhost:3306/huellitas";
    static String user="root";
    static String pass="";
    public static Connection conectar(){
        Connection con=null;
        try {
            con=DriverManager.getConnection(url,user,pass);
            System.out.println("Conexion Exitosa");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
