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

public class Adoptantes extends Persona {

    private String direccion;
    private int telefono;
    private String correo;
    private String ocupacion;
    private int idAdop;

    public Adoptantes(String direccion, int telefono, String correo, String ocupacion, String nombre, String paterno, String materno, int ci) {
        super(nombre, paterno, materno, ci);
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.ocupacion = ocupacion;
    }

    public Adoptantes(String direccion, int telefono, String correo, String ocupacion, int idAdop, String nombre, String paterno, String materno, int ci) {
        super(nombre, paterno, materno, ci);
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.ocupacion = ocupacion;
        this.idAdop = idAdop;
    }

    public boolean registrarAdoptante() {
        int idPersona = super.registrarPersonas(); // Debe retornar el ID insertado
        if (idPersona == -1) {
            return false; // Si falló el registro
        }
        String sqlAdmin = "INSERT INTO adoptantes (direccion, telefono,correo,ocupacion, idper) VALUES (?, ?, ?,?,?)";

        try (Connection con = conexion.conectar(); PreparedStatement psAdmin = con.prepareStatement(sqlAdmin)) {
            psAdmin.setString(1, direccion);
            psAdmin.setInt(2, telefono);
            psAdmin.setString(3, correo);
            psAdmin.setString(4, ocupacion);
            psAdmin.setInt(5, idPersona); // Aquí se asigna correctamente

            psAdmin.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al registrar adoptador: " + e.getMessage());
            return false;
        }
    }

    public static List<String> listarAdoptantes() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT a.direccion, a.telefono, a.correo, a.ocupacion, p.nombre, p.paterno, p.materno, p.ci "
                + "FROM adoptantes a JOIN personas p ON a.idper = p.idp";

        try (Connection con = conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String paterno = rs.getString("paterno");
                String materno = rs.getString("materno");
                int ci = rs.getInt("ci");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String correo = rs.getString("correo");
                String ocupacion = rs.getString("ocupacion");

                String resultado = "Nombre: " + nombre + " " + paterno + " " + materno
                        + " | CI: " + ci + " |Direccion: " + direccion + " |Telefono: " + telefono
                        + " |Correo: " + correo + " |Ocupacion: " + ocupacion;

                lista.add(resultado);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar adoptadores: " + e.getMessage());
        }
        return lista;
    }

    // IMPORTANTE: para que el JComboBox muestre el nombre, override toString()
@Override
    public String toString() {
        return getNombre() + " " + getPaterno() + " " + getMaterno();
    }


    public static List<Adoptantes> listarAdoptantesCompleto() {
        List<Adoptantes> lista = new ArrayList<>();
        String sql = "SELECT a.idper, a.direccion, a.telefono, a.correo, a.ocupacion, "
                + "p.nombre, p.paterno, p.materno, p.ci, p.idp "
                + "FROM adoptantes a JOIN personas p ON a.idper = p.idp";

        try (Connection con = conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Adoptantes a = new Adoptantes(
                        rs.getString("direccion"),
                        rs.getInt("telefono"),
                        rs.getString("correo"),
                        rs.getString("ocupacion"),
                        rs.getInt("idper"),
                        rs.getString("nombre"),
                        rs.getString("paterno"),
                        rs.getString("materno"),
                        rs.getInt("ci")
                );
                lista.add(a);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar adoptantes: " + e.getMessage());
        }

        return lista;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public int getIdAdop() {
        return idAdop;
    }

    public void setIdAdop(int idAdop) {
        this.idAdop = idAdop;
    }

}
