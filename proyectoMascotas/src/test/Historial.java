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

public class Historial {

    private String diagnostico;
    private String fechaConsulta;
    private String medicamentos;
    private int idmas;
    private int idvete;

    public Historial(String diagnostico, String fechaConsulta, String medicamentos, int idmas, int idvete) {
        this.diagnostico = diagnostico;
        this.fechaConsulta = fechaConsulta;
        this.medicamentos = medicamentos;
        this.idmas = idmas;
        this.idvete = idvete;
    }

    public boolean registrarHistorial() {

        String sqlMas = "INSERT INTO historial (diagnostico, fechaConsulta, medicamentos, idmas, idvete) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = conexion.conectar(); PreparedStatement psMas = con.prepareStatement(sqlMas)) {
            psMas.setString(1, diagnostico);
            psMas.setString(2, fechaConsulta);
            psMas.setString(3, medicamentos);
            psMas.setInt(4, idmas);
            psMas.setInt(5, idvete); // Aquí se asigna correctamente

            psMas.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al registrar el historial: " + e.getMessage());
            return false;
        }
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(String fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public int getIdmas() {
        return idmas;
    }

    public void setIdmas(int idmas) {
        this.idmas = idmas;
    }

    public int getIdvete() {
        return idvete;
    }

    public void setIdvete(int idvete) {
        this.idvete = idvete;
    }

}
