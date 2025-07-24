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
public class Administrador extends Persona {

    private String nombreUsuario;
    private String password;

    public Administrador(String nombreUsuario, String password, String nombre, String paterno, String materno, int ci) {
        super(nombre, paterno, materno, ci);
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }

    public boolean registrarAdministradores() {
        int idPersona = super.registrarPersonas(); // Debe retornar el ID insertado
        if (idPersona == -1) {
            return false; // Si falló el registro
        }
        String sqlAdmin = "INSERT INTO administrador (nombreUsuario, password, idper) VALUES (?, ?, ?)";

        try (Connection con = conexion.conectar(); PreparedStatement psAdmin = con.prepareStatement(sqlAdmin)) {
            psAdmin.setString(1, nombreUsuario);
            psAdmin.setString(2, password);
            psAdmin.setInt(3, idPersona); // Aquí se asigna correctamente

            psAdmin.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al registrar administrador: " + e.getMessage());
            return false;
        }
    }

    public static List<String> listarAdministradores() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT a.nombreUsuario, p.nombre, p.paterno, p.materno, p.ci "
                + "FROM administrador a JOIN personas p ON a.idper = p.idp";

        try (Connection con = conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String usuario = rs.getString("nombreUsuario");
                String nombre = rs.getString("nombre");
                String paterno = rs.getString("paterno");
                String materno = rs.getString("materno");
                int ci = rs.getInt("ci");

                String resultado = "Usuario: " + usuario
                        + " | Nombre: " + nombre + " " + paterno + " " + materno
                        + " | CI: " + ci;

                lista.add(resultado);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar administradores: " + e.getMessage());
        }
        return lista;
    }

    public static Administrador iniciarSesion(String usuario, String password) {
        String sql = "SELECT a.nombreUsuario, a.password, p.nombre, p.paterno, p.materno, p.ci "
                + "FROM administrador a JOIN personas p ON a.idper = p.idp "
                + "WHERE a.nombreUsuario = ? AND a.password = ?";

        try (Connection con = conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Administrador(
                        rs.getString("nombreUsuario"),
                        rs.getString("password"),
                        rs.getString("nombre"),
                        rs.getString("paterno"),
                        rs.getString("materno"),
                        rs.getInt("ci")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error al iniciar sesión: " + e.getMessage());
            return null;
        }
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
