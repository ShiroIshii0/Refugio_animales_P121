/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sony Vaio
 */
public class Especies {

    private int id;       // Agrega id (ide)
    private String nombre;

    public Especies(String nombre) {
        this.nombre = nombre;
    }
    //este constructor sirve para asociar con mascosta
    public Especies(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    

    public boolean registrarEspecies() {
        String sql = "INSERT INTO especies(nombre) VALUES (?)";
        try (Connection con = conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al registrar la especie " + e.getMessage());
            return false;
        }
    }

    public static List<String> listarEspecies() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT nombre FROM especies";

        try (Connection con = conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                lista.add("Especie: " + nombre);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar especies: " + e.getMessage());
        }
        return lista;
    }

    // IMPORTANTE: para que el JComboBox muestre el nombre, override toString()
    @Override
    public String toString() {
        return nombre;
    }

    public static List<Especies> listarEspeciesCompleto() {
        List<Especies> lista = new ArrayList<>();
        String sql = "SELECT ide, nombre FROM especies";

        try (Connection con = conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Especies(rs.getInt("ide"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar especies: " + e.getMessage());
        }
        return lista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
