package Proyecto01.vista;

import javax.swing.*;

/**
 * Ventana principal de la aplicación: contiene las pestañas de
 * Sistema de Acceso y Mantenimiento de Socios.
 */
public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        super("Gimnasio - Sistema de Gestión");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane pestanas = new JTabbedPane();

        PanelUsuarios panelMantenimiento = new PanelUsuarios();

        // El botón "Inscripción de Usuarios" del SistemaAcceso cambia a la
        // pestaña de Mantenimiento en lugar de abrir una ventana nueva
        SistemaAcceso panelAcceso = new SistemaAcceso(() -> pestanas.setSelectedIndex(1));

        pestanas.addTab("Control de Acceso", panelAcceso);
        pestanas.addTab("Mantenimiento de Socios", panelMantenimiento);

        setContentPane(pestanas);
    }
}
