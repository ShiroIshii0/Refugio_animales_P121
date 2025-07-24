/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

/**
 *
 * @author Sony Vaio
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;


public class Main extends JFrame {

// Fondo de la interfaz
    private Image fondo;

    // Panel principal para mostrar información dinámica
    private JPanel panelMostrar;

    // Área de texto y scroll para mostrar especies
    private JTextArea areaEspecies;
    private JScrollPane scrollEspecies;

    // Botones de navegación y acciones generales
    private JButton btnLogin;
    private JButton btnAtras;

    // Botones para mostrar vistas o secciones
    private JButton btnRegistrar;
    private JButton btnEspecie;
    private JButton btnMostrarEspecies;
    private JButton btnVeterinario;
    private JButton btnAdoptanteRegistrar;
    private JButton btnMostrarAdministradores;
    private JButton btnMostrarVeterinarios;
    private JButton btnMostrarAdoptadores;
    private JButton btnMascotaRegistrar;
    private JButton btnMostrarMascota;
    private JButton btnRegistrarAdopcion;
    private JButton btnMostrarAdopcion;
    private JButton btnRegistrarHistorial;
    private JButton btnMostrarHistorial;

    // Botones para gestión CRUD
    private JButton btnAdministradoresCRUD;
    private JButton btnEspeciesCRUD;
    private JButton btnMascotasCRUD;
    private JButton btnVeterinariosCRUD;
    private JButton btnAdoptadorCRUD;
    private JButton btnAdopcionesCRUD;
    private JButton btnHistorialCRUD;

    public Main() {
        fondo = new ImageIcon(getClass().getResource("/imagenes/fondo.gif")).getImage();
        setTitle("Gestión de Refugio de Animales");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar ventana

        // PANEL PRINCIPAL con fondo
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (fondo != null) {
                    g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        panel.setLayout(new BorderLayout());
        setContentPane(panel);

        Font fuente = new Font("Times New Roman", Font.BOLD, 20);

        // PANEL BOTONES (NORTE)
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.setOpaque(false); // No tapar fondo gif
        panel.add(panelBotones, BorderLayout.NORTH);

        // PANEL DE CONTENIDO DINÁMICO (CENTRO)
        panelMostrar = new JPanel(new BorderLayout());
        panelMostrar.setOpaque(false); // No tapar fondo
        panel.add(panelMostrar, BorderLayout.CENTER);

        // ÁREA DE TEXTO para mostrar especies
        areaEspecies = new JTextArea(30, 60);
        areaEspecies.setFont(new Font("Monospaced", Font.PLAIN, 16));
        areaEspecies.setEditable(false);
        scrollEspecies = new JScrollPane(areaEspecies);

        // BOTONES
        btnRegistrar = crearBoton("Registrar Administrador", fuente, false);
        btnLogin = crearBoton("Iniciar Sesión", fuente, true);
        btnEspecie = crearBoton("Registrar Especie", fuente, false);
        btnAdoptanteRegistrar = crearBoton("Registrar Adoptante", fuente, false);
        btnVeterinario = crearBoton("Registrar Veterinario", fuente, false);
        btnAdministradoresCRUD = crearBoton("Administradores", fuente, false);
        btnEspeciesCRUD = crearBoton("Especies", fuente, false);
        btnAdoptadorCRUD = crearBoton("Adoptadores", fuente, false);
        btnVeterinariosCRUD = crearBoton("Veterinarios", fuente, false);
        btnAdopcionesCRUD = crearBoton("Adopciones", fuente, false);
        btnMascotasCRUD = crearBoton("Mascotas", fuente, false);
        btnHistorialCRUD = crearBoton("Historial Medico", fuente, false);
        btnMostrarEspecies = crearBoton("Lista de Especies", fuente, false);
        btnMostrarAdministradores = crearBoton("Mostrar Administradores", fuente, false);
        btnMostrarVeterinarios = crearBoton("Mostrar Veterinarios", fuente, false);
        btnMostrarAdoptadores = crearBoton("Mostrar Adoptadores", fuente, false);
        btnMascotaRegistrar = crearBoton("Registrar Mascota", fuente, false);
        btnMostrarMascota = crearBoton("Listar Mascota", fuente, false);
        btnRegistrarAdopcion = crearBoton("Registrar Adopcion", fuente, false);
        btnMostrarAdopcion = crearBoton("Mostrar Adopciones", fuente, false);
        btnRegistrarHistorial = crearBoton("Registrar Historial", fuente, false);
        btnMostrarHistorial = crearBoton("Mostrar Historial", fuente, false);
        btnAtras = crearBoton("<-- Atrás", fuente, false);

        // Agregar botones visibles por defecto
        panelBotones.add(btnLogin);
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnEspecie);
        panelBotones.add(btnAdoptanteRegistrar);
        panelBotones.add(btnVeterinario);
        panelBotones.add(btnAdministradoresCRUD);
        panelBotones.add(btnEspeciesCRUD);
        panelBotones.add(btnAdoptadorCRUD);
        panelBotones.add(btnVeterinariosCRUD);
        panelBotones.add(btnMascotasCRUD);
        panelBotones.add(btnAdopcionesCRUD);
        panelBotones.add(btnHistorialCRUD);
        panelBotones.add(btnMostrarEspecies);
        panelBotones.add(btnMostrarAdministradores);
        panelBotones.add(btnMostrarVeterinarios);
        panelBotones.add(btnMostrarAdoptadores);
        panelBotones.add(btnMascotaRegistrar);
        panelBotones.add(btnMostrarMascota);
        panelBotones.add(btnRegistrarAdopcion);
        panelBotones.add(btnMostrarAdopcion);
        panelBotones.add(btnRegistrarHistorial);
        panelBotones.add(btnMostrarHistorial);
        panelBotones.add(btnAtras);

        // LISTENERS
        btnLogin.addActionListener(e -> iniciarSesion());
        btnRegistrar.addActionListener(e -> registrarAdministrador());
        btnAdoptanteRegistrar.addActionListener(e -> registrarAdoptantes());
        btnVeterinario.addActionListener(e -> registrarVeterinarios());
        btnEspecie.addActionListener(e -> registrarEspecie());
        btnMostrarEspecies.addActionListener(e -> mostrarEspecies());
        btnMostrarAdministradores.addActionListener(e -> mostrarAdministradores());
        btnMostrarVeterinarios.addActionListener(e -> mostrarVeterinarios());
        btnMostrarAdoptadores.addActionListener(e -> mostrarAdoptadores());
        btnMascotaRegistrar.addActionListener(e -> registrarMascotas());
        btnMostrarMascota.addActionListener(e -> mostrarMascotas());
        btnRegistrarAdopcion.addActionListener(e -> registrarAdopciones());
        btnMostrarAdopcion.addActionListener(e -> mostrarAdopciones());
        btnRegistrarHistorial.addActionListener(e -> registrarHistorial());
        btnMostrarHistorial.addActionListener(e -> mostrarHistorial());
        btnAtras.addActionListener(e -> atras());

        // PANELES CRUD
        //en esta parte falta lo que seria el registrar para cada crud segun lo correspondiente
        btnAdministradoresCRUD.addActionListener(e -> {
            mostrarPanelAdministrador();
        });

        btnEspeciesCRUD.addActionListener(e -> {
            mostrarPanelEspecies();
        });

        btnAdoptadorCRUD.addActionListener(e -> {
            mostrarPanelAdoptadores();
        });

        btnVeterinariosCRUD.addActionListener(e -> {
            mostrarPanelVeterinarios();
        });
        btnMascotasCRUD.addActionListener(e -> {
            mostrarPanelMascotas();
        });
        btnAdopcionesCRUD.addActionListener(e -> {
            mostrarPanelAdopciones();
        });
        btnHistorialCRUD.addActionListener(e -> {
            mostrarPanelHistorial();
        });
    }

    private JButton crearBoton(String texto, Font fuente, boolean visible) {
        JButton btn = new JButton(texto);
        btn.setFont(fuente);
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.CYAN);
        btn.setFocusPainted(false);
        btn.setVisible(visible);
        return btn;
    }

    private void iniciarSesion() {
        String usuario = JOptionPane.showInputDialog(this, "Nombre de Usuario:");
        String password = JOptionPane.showInputDialog(this, "Contraseña:");

        Administrador admin = Administrador.iniciarSesion(usuario, password);

        if (admin != null) {
            JOptionPane.showMessageDialog(this, "Bienvenido " + admin.getNombre());
            //aqui hacer una forma para poner el btnAdministradoresCRUD y que dentro muestre el btnRegistrar are por cruds apartes
            btnAdministradoresCRUD.setVisible(true); // <-- correcto aquí
            btnEspeciesCRUD.setVisible(true);
            btnAdoptadorCRUD.setVisible(true);
            btnVeterinariosCRUD.setVisible(true);
            btnMascotasCRUD.setVisible(true);
            btnAdopcionesCRUD.setVisible(true);
            btnHistorialCRUD.setVisible(true);
            // Opcional: ocultar botón de login si ya no se necesita
            btnLogin.setVisible(false);

        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
        }
    }

    private void registrarAdministrador() {
        try {
            String usuario = JOptionPane.showInputDialog(this, "Nombre de Usuario:");
            String password = JOptionPane.showInputDialog(this, "Contraseña:");
            String nombre = JOptionPane.showInputDialog(this, "Nombre:");
            String paterno = JOptionPane.showInputDialog(this, "Apellido Paterno:");
            String materno = JOptionPane.showInputDialog(this, "Apellido Materno:");
            int ci = Integer.parseInt(JOptionPane.showInputDialog(this, "CI:"));

            Administrador admin = new Administrador(usuario, password, nombre, paterno, materno, ci);
            boolean registrado = admin.registrarAdministradores();

            if (registrado) {
                JOptionPane.showMessageDialog(this, "Administrador registrado.");
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar administrador.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en los datos: " + ex.getMessage());
        }
    }

    private void registrarAdoptantes() {
        try {
            String nombre = JOptionPane.showInputDialog(this, "Nombre:");
            String paterno = JOptionPane.showInputDialog(this, "Apellido Paterno:");
            String materno = JOptionPane.showInputDialog(this, "Apellido Materno:");
            String direccion = JOptionPane.showInputDialog(this, "Direccion:");
            int telefono = Integer.parseInt(JOptionPane.showInputDialog(this, "Telefono:"));
            String correo = JOptionPane.showInputDialog(this, "Correo:");
            String ocupacion = JOptionPane.showInputDialog(this, "Ocupacion:");
            int ci = Integer.parseInt(JOptionPane.showInputDialog(this, "CI:"));

            Adoptantes adop = new Adoptantes(direccion, telefono, correo, ocupacion, nombre, paterno, materno, ci);
            boolean registrado = adop.registrarAdoptante();

            if (registrado) {
                JOptionPane.showMessageDialog(this, "Adoptador registrado.");
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar Adoptador.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en los datos: " + ex.getMessage());
        }
    }

    private void registrarVeterinarios() {
        try {
            String nombre = JOptionPane.showInputDialog(this, "Nombre:");
            String paterno = JOptionPane.showInputDialog(this, "Apellido Paterno:");
            String materno = JOptionPane.showInputDialog(this, "Apellido Materno:");
            int ci = Integer.parseInt(JOptionPane.showInputDialog(this, "CI:"));
            String direccion = JOptionPane.showInputDialog(this, "Titulo Profesional:");
            int nroMatricula = Integer.parseInt(JOptionPane.showInputDialog(this, "Nro de Matricula:"));
            String correo = JOptionPane.showInputDialog(this, "Especialidad:");

            Veterinarios vete = new Veterinarios(direccion, nroMatricula, direccion, correo, nombre, paterno, materno, ci);
            boolean registrado = vete.registrarVeterinario();

            if (registrado) {
                JOptionPane.showMessageDialog(this, "Veterinario registrado.");
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar Veterinario.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en los datos: " + ex.getMessage());
        }
    }

    private void registrarEspecie() {
        try {
            String nombre = JOptionPane.showInputDialog(this, "Nombre de la Especie:");

            Especies espe = new Especies(nombre);
            boolean registrado = espe.registrarEspecies();

            if (registrado) {
                JOptionPane.showMessageDialog(this, "Especie registrado.");
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar la especie.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en los datos: " + ex.getMessage());
        }
    }

    private void registrarMascotas() {
        try {
            String nombre = JOptionPane.showInputDialog(this, "Nombre:");
            String raza = JOptionPane.showInputDialog(this, "Raza:");
            String color = JOptionPane.showInputDialog(this, "Color:");
            int edad = Integer.parseInt(JOptionPane.showInputDialog(this, "Edad:"));
            String observacion = JOptionPane.showInputDialog(this, "Observacion:");

            // Listar especies y llenar JComboBox
            List<Especies> especies = Especies.listarEspeciesCompleto();
            JComboBox<Especies> comboEspecies = new JComboBox<>();
            for (Especies e : especies) {
                comboEspecies.addItem(e);
            }

            // Mostrar diálogo con JComboBox para seleccionar especie
            int opcion = JOptionPane.showConfirmDialog(this, comboEspecies, "Selecciona una especie", JOptionPane.OK_CANCEL_OPTION);
            if (opcion != JOptionPane.OK_OPTION) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una especie");
                return;
            }

            Especies especieSeleccionada = (Especies) comboEspecies.getSelectedItem();
            if (especieSeleccionada == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una especie");
                return;
            }

            int idEspecie = especieSeleccionada.getId();

            Mascotas masco = new Mascotas(nombre, raza, color, edad, observacion, idEspecie);
            boolean registrado = masco.registrarMascota();

            if (registrado) {
                JOptionPane.showMessageDialog(this, "Mascota registrada.");
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar la Mascota.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en los datos: " + ex.getMessage());
        }
    }

    private void registrarAdopciones() {
        try {
            String fecha = JOptionPane.showInputDialog(this, "Fecha:");
            String observacion = JOptionPane.showInputDialog(this, "Observacion:");

            // Listar especies y llenar JComboBox
            List<Mascotas> mascotas = Mascotas.listarMascotasCompleto();
            JComboBox<Mascotas> comboMascotas = new JComboBox<>();
            for (Mascotas m : mascotas) {
                comboMascotas.addItem(m);
            }

            // Mostrar diálogo con JComboBox para seleccionar especie
            int opcion = JOptionPane.showConfirmDialog(this, comboMascotas, "Selecciona una mascota", JOptionPane.OK_CANCEL_OPTION);
            if (opcion != JOptionPane.OK_OPTION) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una especie");
                return;
            }

            Mascotas mascotaSeleccionada = (Mascotas) comboMascotas.getSelectedItem();
            if (mascotaSeleccionada == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una mascota");
                return;
            }

            int idMascota = mascotaSeleccionada.getIdMas();

            //para adoptadores
            List<Adoptantes> adoptantes = Adoptantes.listarAdoptantesCompleto();
            JComboBox<Adoptantes> comboAdoptantes = new JComboBox<>();
            for (Adoptantes a : adoptantes) {
                comboAdoptantes.addItem(a);
            }

            int opcionAdoptante = JOptionPane.showConfirmDialog(this, comboAdoptantes, "Selecciona un adoptante", JOptionPane.OK_CANCEL_OPTION);
            if (opcionAdoptante != JOptionPane.OK_OPTION) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un adoptante");
                return;
            }

            Adoptantes adoptanteSeleccionado = (Adoptantes) comboAdoptantes.getSelectedItem();
            int idAdoptante = adoptanteSeleccionado.getIdAdop();

            Adopciones adopcion = new Adopciones(fecha, observacion, idMascota, idAdoptante);
            boolean registrado = adopcion.registrarAdopcion();

            if (registrado) {
                JOptionPane.showMessageDialog(this, "Adopcion registrada.");
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar la adopcion.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en los datos: " + ex.getMessage());
        }
    }

    private void registrarHistorial() {
        try {
            String diagnostico = JOptionPane.showInputDialog(this, "Diagnóstico:");
            String fechaConsulta = JOptionPane.showInputDialog(this, "Fecha de Consulta:");
            String medicamentos = JOptionPane.showInputDialog(this, "Medicamentos:");

            // Seleccionar Mascota
            List<Mascotas> mascotas = Mascotas.listarMascotasCompleto();
            JComboBox<Mascotas> comboMascotas = new JComboBox<>();
            for (Mascotas m : mascotas) {
                comboMascotas.addItem(m);
            }

            int opMascota = JOptionPane.showConfirmDialog(this, comboMascotas, "Selecciona una mascota", JOptionPane.OK_CANCEL_OPTION);
            if (opMascota != JOptionPane.OK_OPTION) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una mascota");
                return;
            }

            Mascotas mascotaSeleccionada = (Mascotas) comboMascotas.getSelectedItem();
            int idMascota = mascotaSeleccionada.getIdMas();

            // Seleccionar Veterinario
            List<Veterinarios> veterinarios = Veterinarios.listarVeterinariosCompleto();
            JComboBox<Veterinarios> comboVeterinarios = new JComboBox<>();
            for (Veterinarios v : veterinarios) {
                comboVeterinarios.addItem(v);
            }

            int opVete = JOptionPane.showConfirmDialog(this, comboVeterinarios, "Selecciona un veterinario", JOptionPane.OK_CANCEL_OPTION);
            if (opVete != JOptionPane.OK_OPTION) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un veterinario");
                return;
            }

            Veterinarios veterinarioSeleccionado = (Veterinarios) comboVeterinarios.getSelectedItem();
            int idVeterinario = veterinarioSeleccionado.getIdVete();

            // Registrar historial
            Historial historial = new Historial(diagnostico, fechaConsulta, medicamentos, idMascota, idVeterinario);
            boolean registrado = historial.registrarHistorial();

            if (registrado) {
                JOptionPane.showMessageDialog(this, "Historial registrado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar el historial.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en los datos: " + ex.getMessage());
        }
    }

    private void mostrarEspecies() {
        panelMostrar.removeAll();
        areaEspecies.setText("");

        List<String> especies = Especies.listarEspecies();
        if (especies.isEmpty()) {
            areaEspecies.setText("No hay especies registradas.");
        } else {
            for (String especie : especies) {
                areaEspecies.append("• " + especie + "\n");
            }
        }

        panelMostrar.add(scrollEspecies, BorderLayout.CENTER);
        panelMostrar.revalidate();
        panelMostrar.repaint();
    }

    private void mostrarAdministradores() {
        panelMostrar.removeAll();
        areaEspecies.setText("");

        List<String> admins = Administrador.listarAdministradores();
        if (admins.isEmpty()) {
            areaEspecies.setText("No hay administradores registrados.");
        } else {
            for (String admin : admins) {
                areaEspecies.append("• " + admin + "\n");
            }
        }

        panelMostrar.add(scrollEspecies, BorderLayout.CENTER);
        panelMostrar.revalidate();
        panelMostrar.repaint();
    }

    private void mostrarVeterinarios() {
        panelMostrar.removeAll();
        areaEspecies.setText("");

        List<String> vete = Veterinarios.listarVeterinarios();
        if (vete.isEmpty()) {
            areaEspecies.setText("No hay veterinarios registrados.");
        } else {
            for (String vet : vete) {
                areaEspecies.append("• " + vet + "\n");
            }
        }

        panelMostrar.add(scrollEspecies, BorderLayout.CENTER);
        panelMostrar.revalidate();
        panelMostrar.repaint();
    }

    private void mostrarAdoptadores() {
        panelMostrar.removeAll();
        areaEspecies.setText("");

        List<String> adopta = Adoptantes.listarAdoptantes();
        if (adopta.isEmpty()) {
            areaEspecies.setText("No hay adoptadores registrados.");
        } else {
            for (String adop : adopta) {
                areaEspecies.append("• " + adop + "\n");
            }
        }

        panelMostrar.add(scrollEspecies, BorderLayout.CENTER);
        panelMostrar.revalidate();
        panelMostrar.repaint();
    }

    private void mostrarMascotas() {
        panelMostrar.removeAll();
        areaEspecies.setText("");

        List<String> mascotas = Mascotas.listarMascotas();
        if (mascotas.isEmpty()) {
            areaEspecies.setText("No hay especies registradas.");
        } else {
            for (String mascota : mascotas) {
                areaEspecies.append("• " + mascota + "\n");
            }
        }

        panelMostrar.add(scrollEspecies, BorderLayout.CENTER);
        panelMostrar.revalidate();
        panelMostrar.repaint();
    }

    private void mostrarAdopciones() {
        panelMostrar.removeAll();
        areaEspecies.setText("");

        List<String> adopciones = Adopciones.listarAdopciones();
        if (adopciones.isEmpty()) {
            areaEspecies.setText("No hay adopciones registradas.");
        } else {
            for (String adopcion : adopciones) {
                areaEspecies.append("• " + adopcion + "\n");
            }
        }

        panelMostrar.add(scrollEspecies, BorderLayout.CENTER);
        panelMostrar.revalidate();
        panelMostrar.repaint();
    }

    private void mostrarHistorial() {
        try {
            // Listar mascotas disponibles
            List<Mascotas> mascotas = Mascotas.listarMascotasCompleto();
            if (mascotas.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay mascotas registradas.");
                return;
            }

            // Crear JComboBox con las mascotas para seleccionar
            JComboBox<Mascotas> comboMascotas = new JComboBox<>();
            for (Mascotas m : mascotas) {
                comboMascotas.addItem(m);
            }

            // Mostrar diálogo para seleccionar mascota
            int opcion = JOptionPane.showConfirmDialog(this, comboMascotas, "Selecciona una mascota para ver su historial", JOptionPane.OK_CANCEL_OPTION);
            if (opcion != JOptionPane.OK_OPTION) {
                return; // Usuario canceló
            }

            // Obtener la mascota seleccionada
            Mascotas mascotaSeleccionada = (Mascotas) comboMascotas.getSelectedItem();
            if (mascotaSeleccionada == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una mascota.");
                return;
            }

            // Obtener el id de la mascota para el reporte
            int idMascota = mascotaSeleccionada.getIdMas();

            // Llamar al método que genera el reporte y muestra el historial médico
            HistorialReport.generarReporte(idMascota);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al mostrar el historial: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void atras() {
        btnAtras.setVisible(false);
        btnAdministradoresCRUD.setVisible(true);
        btnEspeciesCRUD.setVisible(true);
        btnAdoptadorCRUD.setVisible(true);
        btnVeterinariosCRUD.setVisible(true);
        btnAdopcionesCRUD.setVisible(true);
        btnMascotasCRUD.setVisible(true);
        btnHistorialCRUD.setVisible(true);
        btnEspecie.setVisible(false);
        btnAdoptanteRegistrar.setVisible(false);
        btnVeterinario.setVisible(false);
        btnRegistrar.setVisible(false);
        btnMostrarEspecies.setVisible(false);
        btnMostrarAdministradores.setVisible(false);
        btnMostrarVeterinarios.setVisible(false);
        btnMostrarAdoptadores.setVisible(false);
        btnMascotaRegistrar.setVisible(false);
        btnMostrarMascota.setVisible(false);
        btnRegistrarAdopcion.setVisible(false);
        btnMostrarAdopcion.setVisible(false);
        btnRegistrarHistorial.setVisible(false);
        btnMostrarHistorial.setVisible(false);

        // Limpiar panelMostrar
        panelMostrar.removeAll();
        panelMostrar.revalidate();
        panelMostrar.repaint();
    }

    private void mostrarPanelAdministrador() {
        btnRegistrar.setVisible(true);
        btnEspecie.setVisible(false);
        btnAdoptanteRegistrar.setVisible(false);
        btnVeterinario.setVisible(false);
        btnMostrarEspecies.setVisible(false);
        btnMostrarAdministradores.setVisible(true);
        btnMostrarVeterinarios.setVisible(false);
        btnMostrarAdoptadores.setVisible(false);
        btnMascotaRegistrar.setVisible(false);
        btnMostrarMascota.setVisible(false);
        btnRegistrarAdopcion.setVisible(false);
        btnMostrarAdopcion.setVisible(false);
        btnRegistrarHistorial.setVisible(false);
        btnMostrarHistorial.setVisible(false);

        btnAdministradoresCRUD.setVisible(false);
        btnEspeciesCRUD.setVisible(false);
        btnAdoptadorCRUD.setVisible(false);
        btnVeterinariosCRUD.setVisible(false);
        btnMascotasCRUD.setVisible(false);
        btnAdopcionesCRUD.setVisible(false);
        btnHistorialCRUD.setVisible(false);
        btnAtras.setVisible(true);

        panelMostrar.removeAll();
        panelMostrar.revalidate();
        panelMostrar.repaint();
    }

    private void mostrarPanelEspecies() {
        btnRegistrar.setVisible(false);
        btnEspecie.setVisible(true);
        btnAdoptanteRegistrar.setVisible(false);
        btnVeterinario.setVisible(false);
        btnMostrarEspecies.setVisible(true);
        btnMostrarAdministradores.setVisible(false);
        btnMostrarVeterinarios.setVisible(false);
        btnMostrarAdoptadores.setVisible(false);
        btnMascotaRegistrar.setVisible(false);
        btnMostrarMascota.setVisible(false);
        btnRegistrarAdopcion.setVisible(false);
        btnMostrarAdopcion.setVisible(false);
        btnRegistrarHistorial.setVisible(false);
        btnMostrarHistorial.setVisible(false);

        btnAdministradoresCRUD.setVisible(false);
        btnEspeciesCRUD.setVisible(false);
        btnAdoptadorCRUD.setVisible(false);
        btnVeterinariosCRUD.setVisible(false);
        btnAdopcionesCRUD.setVisible(false);
        btnMascotasCRUD.setVisible(false);
        btnHistorialCRUD.setVisible(false);
        btnAtras.setVisible(true);

        panelMostrar.removeAll();
        panelMostrar.revalidate();
        panelMostrar.repaint();
    }

    private void mostrarPanelAdoptadores() {
        btnRegistrar.setVisible(false);
        btnEspecie.setVisible(false);
        btnAdoptanteRegistrar.setVisible(true);
        btnVeterinario.setVisible(false);
        btnMostrarEspecies.setVisible(false);
        btnMostrarAdministradores.setVisible(false);
        btnMostrarVeterinarios.setVisible(false);
        btnAdopcionesCRUD.setVisible(false);
        btnMostrarAdoptadores.setVisible(true);
        btnMascotaRegistrar.setVisible(false);
        btnMostrarMascota.setVisible(false);
        btnRegistrarAdopcion.setVisible(false);
        btnMostrarAdopcion.setVisible(false);
        btnRegistrarHistorial.setVisible(false);
        btnMostrarHistorial.setVisible(false);

        btnAdministradoresCRUD.setVisible(false);
        btnEspeciesCRUD.setVisible(false);
        btnAdoptadorCRUD.setVisible(false);
        btnVeterinariosCRUD.setVisible(false);
        btnMascotasCRUD.setVisible(false);
        btnHistorialCRUD.setVisible(false);
        btnAtras.setVisible(true);

        panelMostrar.removeAll();
        panelMostrar.revalidate();
        panelMostrar.repaint();
    }

    private void mostrarPanelVeterinarios() {
        btnRegistrar.setVisible(false);
        btnEspecie.setVisible(false);
        btnAdoptanteRegistrar.setVisible(false);
        btnVeterinario.setVisible(true);
        btnMostrarEspecies.setVisible(false);
        btnMostrarAdministradores.setVisible(false);
        btnMostrarVeterinarios.setVisible(true);
        btnMostrarAdoptadores.setVisible(false);
        btnMascotaRegistrar.setVisible(false);
        btnMostrarMascota.setVisible(false);
        btnRegistrarAdopcion.setVisible(false);
        btnMostrarAdopcion.setVisible(false);
        btnRegistrarHistorial.setVisible(false);
        btnMostrarHistorial.setVisible(false);

        btnAdministradoresCRUD.setVisible(false);
        btnEspeciesCRUD.setVisible(false);
        btnAdoptadorCRUD.setVisible(false);
        btnVeterinariosCRUD.setVisible(false);
        btnAdopcionesCRUD.setVisible(false);
        btnMascotasCRUD.setVisible(false);
        btnHistorialCRUD.setVisible(false);
        btnAtras.setVisible(true);

        panelMostrar.removeAll();
        panelMostrar.revalidate();
        panelMostrar.repaint();
    }

    private void mostrarPanelMascotas() {
        btnRegistrar.setVisible(false);
        btnEspecie.setVisible(false);
        btnAdoptanteRegistrar.setVisible(false);
        btnVeterinario.setVisible(false);
        btnMostrarEspecies.setVisible(false);
        btnMostrarAdministradores.setVisible(false);
        btnMostrarVeterinarios.setVisible(false);
        btnMostrarAdoptadores.setVisible(false);
        btnMascotaRegistrar.setVisible(true);
        btnMostrarMascota.setVisible(true);
        btnRegistrarAdopcion.setVisible(false);
        btnMostrarAdopcion.setVisible(false);
        btnRegistrarHistorial.setVisible(false);
        btnMostrarHistorial.setVisible(false);

        btnAdministradoresCRUD.setVisible(false);
        btnEspeciesCRUD.setVisible(false);
        btnAdoptadorCRUD.setVisible(false);
        btnVeterinariosCRUD.setVisible(false);
        btnAdopcionesCRUD.setVisible(false);
        btnMascotasCRUD.setVisible(false);
        btnHistorialCRUD.setVisible(false);
        btnAtras.setVisible(true);

        panelMostrar.removeAll();
        panelMostrar.revalidate();
        panelMostrar.repaint();
    }

    private void mostrarPanelAdopciones() {
        btnRegistrar.setVisible(false);
        btnEspecie.setVisible(false);
        btnAdoptanteRegistrar.setVisible(false);
        btnVeterinario.setVisible(false);
        btnMostrarEspecies.setVisible(false);
        btnMostrarAdministradores.setVisible(false);
        btnMostrarVeterinarios.setVisible(false);
        btnMostrarAdoptadores.setVisible(false);
        btnMascotaRegistrar.setVisible(false);
        btnMostrarMascota.setVisible(false);
        btnRegistrarAdopcion.setVisible(true);
        btnMostrarAdopcion.setVisible(true);
        btnRegistrarHistorial.setVisible(false);
        btnMostrarHistorial.setVisible(false);

        btnAdministradoresCRUD.setVisible(false);
        btnEspeciesCRUD.setVisible(false);
        btnAdoptadorCRUD.setVisible(false);
        btnVeterinariosCRUD.setVisible(false);
        btnAdopcionesCRUD.setVisible(false);
        btnMascotasCRUD.setVisible(false);
        btnHistorialCRUD.setVisible(false);
        btnAtras.setVisible(true);

        panelMostrar.removeAll();
        panelMostrar.revalidate();
        panelMostrar.repaint();
    }

    private void mostrarPanelHistorial() {
        btnRegistrar.setVisible(false);
        btnEspecie.setVisible(false);
        btnAdoptanteRegistrar.setVisible(false);
        btnVeterinario.setVisible(false);
        btnMostrarEspecies.setVisible(false);
        btnMostrarAdministradores.setVisible(false);
        btnMostrarVeterinarios.setVisible(false);
        btnMostrarAdoptadores.setVisible(false);
        btnMascotaRegistrar.setVisible(false);
        btnMostrarMascota.setVisible(false);
        btnRegistrarAdopcion.setVisible(false);
        btnMostrarAdopcion.setVisible(false);
        btnRegistrarHistorial.setVisible(true);
        btnMostrarHistorial.setVisible(true);

        btnAdministradoresCRUD.setVisible(false);
        btnEspeciesCRUD.setVisible(false);
        btnAdoptadorCRUD.setVisible(false);
        btnVeterinariosCRUD.setVisible(false);
        btnAdopcionesCRUD.setVisible(false);
        btnMascotasCRUD.setVisible(false);
        btnHistorialCRUD.setVisible(false);
        btnAtras.setVisible(true);

        panelMostrar.removeAll();
        panelMostrar.revalidate();
        panelMostrar.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main gui = new Main();
            gui.setVisible(true);
        });
    }
}
