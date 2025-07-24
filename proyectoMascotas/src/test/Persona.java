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
public class Persona {

    private String nombre;
    private String paterno;
    private String materno;
    private int ci;

    public Persona(String nombre, String paterno, String materno, int ci) {
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.ci = ci;
    }

    public int registrarPersonas() {
        String sql = "INSERT INTO personas (nombre, paterno, materno, ci) VALUES (?, ?, ?, ?)";

        try (Connection con = conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, getNombre());
            ps.setString(2, getPaterno());
            ps.setString(3, getMaterno());
            ps.setInt(4, getCi());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // Retornar el ID generado
            }

        } catch (SQLException e) {
            System.out.println("Error al registrar persona: " + e.getMessage());
        }

        return -1; // Error
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

}
