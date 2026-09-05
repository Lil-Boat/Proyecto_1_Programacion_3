package Proyecto01.vista;

import Proyecto01.servicio.GestorMembresias;
import Proyecto01.servicio.IGestorMembresias;

import javax.swing.*;

/**
 * Ventana principal de la aplicación: contiene las pestañas de
 * Sistema de Acceso, Mantenimiento de Socios y Gestión de Membresías.
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

        // Panel de membresías: asigna planes, registra pagos y consulta
        // membresías e historial de pagos.
        IGestorMembresias gestorMembresias = new GestorMembresias();
        PanelMembresias panelMembresias = new PanelMembresias(gestorMembresias);
        panelMembresias.actualizarSocios();

        pestanas.addTab("Control de Acceso", panelAcceso);
        pestanas.addTab("Mantenimiento de Socios", panelMantenimiento);
        pestanas.addTab("Membresías", panelMembresias);

        setContentPane(pestanas);
    }
}
