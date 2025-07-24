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

public class Veterinarios extends Persona {

    private String tituloProfesional;
    private int nroMatricula;
    private String especialidad;
    private String estado;
    private int idVete;

    public Veterinarios(String tituloProfesional, int nroMatricula, String especialidad, String estado, String nombre, String paterno, String materno, int ci) {
        super(nombre, paterno, materno, ci);
        this.tituloProfesional = tituloProfesional;
        this.nroMatricula = nroMatricula;
        this.especialidad = especialidad;
        this.estado = estado;
    }

    public Veterinarios(String tituloProfesional, int nroMatricula, String especialidad, String estado, int idVete, String nombre, String paterno, String materno, int ci) {
        super(nombre, paterno, materno, ci);
        this.tituloProfesional = tituloProfesional;
        this.nroMatricula = nroMatricula;
        this.especialidad = especialidad;
        this.estado = estado;
        this.idVete = idVete;
    }

    public boolean registrarVeterinario() {
        int idPersona = super.registrarPersonas(); // Debe retornar el ID insertado
        if (idPersona == -1) {
            return false; // Si falló el registro
        }
        String sqlVete = "INSERT INTO veterinarios (tituloProfesional, nroMatricula, especialidad, idper, estado) VALUES (?, ?, ?, ?, 'activo')";

        try (Connection con = conexion.conectar(); PreparedStatement psVete = con.prepareStatement(sqlVete)) {
            psVete.setString(1, tituloProfesional);
            psVete.setInt(2, nroMatricula);
            psVete.setString(3, especialidad);
            psVete.setInt(4, idPersona); // Aquí se asigna correctamente

            psVete.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al registrar veterinario: " + e.getMessage());
            return false;
        }
    }

    public static List<String> listarVeterinarios() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT v.tituloProfesional, v.nroMatricula, v.especialidad, v.estado, p.nombre, p.paterno, p.materno, p.ci "
                + "FROM veterinarios v JOIN personas p ON v.idper = p.idp";

        try (Connection con = conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String paterno = rs.getString("paterno");
                String materno = rs.getString("materno");
                int ci = rs.getInt("ci");
                String tituloProfesional = rs.getString("tituloProfesional");
                int nroMatricula = rs.getInt("nroMatricula");
                String especialidad = rs.getString("especialidad");
                String estado = rs.getString("estado");

                String resultado = "Nombre: " + nombre + " " + paterno + " " + materno
                        + " | CI: " + ci + " | Titulo Profesional: " + tituloProfesional + " | Nro de Matricula: " + nroMatricula
                        + " | Especialidad: " + especialidad + " | Estado: " + estado;

                lista.add(resultado);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar administradores: " + e.getMessage());
        }
        return lista;
    }

    // IMPORTANTE: para que el JComboBox muestre el nombre, override toString()
    @Override
    public String toString() {
        return getNombre() + " " + getPaterno() + " (" + especialidad + ")";
    }

    public static List<Veterinarios> listarVeterinariosCompleto() {
        List<Veterinarios> lista = new ArrayList<>();
        String sql = "SELECT v.idper, v.tituloProfesional, v.nroMatricula, v.especialidad, v.estado, "
                + "p.nombre, p.paterno, p.materno, p.ci "
                + "FROM veterinarios v JOIN personas p ON v.idper = p.idp";

        try (Connection con = conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Veterinarios v = new Veterinarios(
                        rs.getString("tituloProfesional"),
                        rs.getInt("nroMatricula"),
                        rs.getString("especialidad"),
                        rs.getString("estado"),
                        rs.getInt("idper"),
                        rs.getString("nombre"),
                        rs.getString("paterno"),
                        rs.getString("materno"),
                        rs.getInt("ci")
                );
                lista.add(v);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar veterinarios: " + e.getMessage());
        }

        return lista;
    }

    public String getTituloProfesional() {
        return tituloProfesional;
    }

    public void setTituloProfesional(String tituloProfesional) {
        this.tituloProfesional = tituloProfesional;
    }

    public int getNroMatricula() {
        return nroMatricula;
    }

    public void setNroMatricula(int nroMatricula) {
        this.nroMatricula = nroMatricula;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdVete() {
        return idVete;
    }

    public void setIdVete(int idVete) {
        this.idVete = idVete;
    }

}
