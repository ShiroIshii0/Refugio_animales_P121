/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

/**
 *
 * @author Sony Vaio
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Mascotas {

    private int idMas;
    private String nombre;
    private String raza;
    private String color;
    private int edad;
    private String observacion;
    private int idEspecie;

    public Mascotas(String nombre, String raza, String color, int edad, String observacion, int idEspecie) {
        this.nombre = nombre;
        this.raza = raza;
        this.color = color;
        this.edad = edad;
        this.observacion = observacion;
        this.idEspecie = idEspecie;
    }

    public Mascotas(int idMas, String nombre, String raza) {
        this.idMas = idMas;
        this.nombre = nombre;
        this.raza = raza;
    }

    public boolean registrarMascota() {
        /*int idEspecie = Especie; // Debe retornar el ID insertado
        if (idEspecie == -1) {
            return false; // Si falló el registro
        }*/
        String sqlMas = "INSERT INTO mascotas (nombre, raza, color, edad, obs, idesp) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = conexion.conectar(); PreparedStatement psMas = con.prepareStatement(sqlMas)) {
            psMas.setString(1, nombre);
            psMas.setString(2, raza);
            psMas.setString(3, color);
            psMas.setInt(4, edad);
            psMas.setString(5, observacion);
            psMas.setInt(6, idEspecie); // Aquí se asigna correctamente

            psMas.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al registrar mascota: " + e.getMessage());
            return false;
        }
    }

    public static List<String> listarMascotas() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT m.nombre, m.raza, m.color, m.edad, m.obs, e.nombre "
                + "FROM mascotas m JOIN especies e ON m.idesp = e.ide";

        try (Connection con = conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String nombreMas = rs.getString("m.nombre");
                String raza = rs.getString("raza");
                String color = rs.getString("color");
                int edad = rs.getInt("edad");
                String observacion = rs.getString("obs");
                String nombreEsp = rs.getString("e.nombre");

                String resultado = "Nombre Mascota: " + nombreMas + " | Raza: " + raza
                        + " | Color: " + color + " | Edad: " + edad
                        + " | Observacion: " + observacion + " | Especie: " + nombreEsp;

                lista.add(resultado);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar mascotas: " + e.getMessage());
        }
        return lista;
    }

    // IMPORTANTE: para que el JComboBox muestre el nombre, override toString()
    @Override
    public String toString() {
        return nombre + " - " + raza;
    }

    public static List<Mascotas> listarMascotasCompleto() {
        List<Mascotas> lista = new ArrayList<>();
        String sql = "SELECT idm, nombre, raza, color, edad, obs FROM mascotas";

        try (Connection con = conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Mascotas(
                        rs.getInt("idm"),
                        rs.getString("nombre"),
                        rs.getString("raza")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar mascotas: " + e.getMessage());
        }
        return lista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }

    public int getIdMas() {
        return idMas;
    }

    public void setIdMas(int idMas) {
        this.idMas = idMas;
    }

}
