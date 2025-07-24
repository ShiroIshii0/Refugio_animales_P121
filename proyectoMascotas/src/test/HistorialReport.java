/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

/**
 *
 * @author Sony Vaio
 */
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class HistorialReport {

    public static void generarReporte(int idMascota) {
        try {
            Connection conn = conexion.conectar(); // Tu clase de conexión ya creada

            // Ruta al archivo .jasper (compilado)
            String rutaReporte = "C:/Users/Sony Vaio/Documents/NetBeansProjects/proyectoHuellitas/src/reportes/reportesHuellitas.jasper";

            // Parámetros
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("idmascotas", idMascota);

            // Cargar y llenar reporte
            JasperPrint jp = JasperFillManager.fillReport(rutaReporte, parametros, conn);

            // Mostrar visor
            JasperViewer viewer = new JasperViewer(jp, false);
            viewer.setTitle("Historial Médico de la Mascota");
            viewer.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
