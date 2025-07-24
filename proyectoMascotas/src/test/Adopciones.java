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

public class Adopciones {

    private String fecha;
    private String observacion;
    private int idMascota;
    private int idAdoptador;

    public Adopciones(String fecha, String observacion, int idMascota, int idAdoptador) {
        this.fecha = fecha;
        this.observacion = observacion;
        this.idMascota = idMascota;
        this.idAdoptador = idAdoptador;
    }

    public boolean registrarAdopcion() {

        String sqlMas = "INSERT INTO adopciones (fecha, obs, idmas, idadop) VALUES (?, ?, ?, ?)";

        try (Connection con = conexion.conectar(); PreparedStatement psMas = con.prepareStatement(sqlMas)) {
            psMas.setString(1, fecha);
            psMas.setString(2, observacion);
            psMas.setInt(3, idMascota);
            psMas.setInt(4, idAdoptador); // Aquí se asigna correctamente

            psMas.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al registrar la adopcion: " + e.getMessage());
            return false;
        }
    }

    public static List<String> listarAdopciones() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT a.ida, a.fecha, a.obs, p.nombre, p.paterno, p.materno, p.ci, m.nombre, m.raza, m.edad, e.nombre "
                + "FROM adopciones a "
                + "JOIN adoptantes aa ON a.idadop = aa.idper "
                + "JOIN personas p ON aa.idper = p.idp "
                + "JOIN mascotas m ON a.idmas = m.idm "
                + "JOIN especies e ON e.ide = m.idesp";

        try (Connection con = conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String nombre = rs.getString("p.nombre");
                String paterno = rs.getString("paterno");
                String materno = rs.getString("materno");
                int ci = rs.getInt("ci");
                String nombreMas = rs.getString("m.nombre");
                String raza = rs.getString("raza");
                int edad = rs.getInt("edad");
                String especie = rs.getString("e.nombre");
                String observacion = rs.getString("obs");
                String fecha = rs.getString("fecha");

                String resultado = "Nombre: " + nombre + " " + paterno + " " + materno + " | CI: " + ci
                        + "Nombre Mascota: " + nombreMas + " | Raza: " + raza
                        + " | Edad: " + edad + " |Especie: " + especie
                        + " | Observacion: " + observacion + " | Fecha Adopcion: " + fecha;

                lista.add(resultado);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar adopciones: " + e.getMessage());
        }
        return lista;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public int getIdAdoptador() {
        return idAdoptador;
    }

    public void setIdAdoptador(int idAdoptador) {
        this.idAdoptador = idAdoptador;
    }

}
