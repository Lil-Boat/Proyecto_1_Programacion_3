package vista;

import servicio.IGestorMembresias;
import servicio.IGestorUsuario;
import modelo.*;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class PanelMembresias extends JPanel{
    private final IGestorUsuario gestorUsuario;
    private final IGestorMembresias gestorMembresias;
    private final JComboBox<Usuario> comboUsuario = new JComboBox<>();
    private final JComboBox<Plan> comboPlanes = new JComboBox<>();
    private final JLabel etiquetaResultado = new JLabel(" ");
    private final DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public PanelMembresias(IGestorUsuario gestorUsuario, IGestorMembresias gestorMembresias) {
        this.gestorUsuario = gestorUsuario;
        this.gestorMembresias = gestorMembresias;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        comboPlanes.addItem(new PlanMensual());
        comboPlanes.addItem(new PlanAnual());
        comboPlanes.addItem(new PlanVIP());

        JPanel formulario = new JPanel(new GridLayout(3, 2, 5, 5));
        formulario.add(new JLabel("Socio:"));
        formulario.add(comboUsuario);
        formulario.add(new JLabel("Plan:"));
        formulario.add(comboPlanes);

        JButton botonAsignar = new JButton("Asignar plan y cobrar");
        botonAsignar.addActionListener(e -> asignarPlan());
        JButton botonRenovar = new JButton("Registrar pago de renovación");
        botonRenovar.addActionListener(e -> registrarPago());

        formulario.add(botonAsignar);
        formulario.add(botonRenovar);

        add(formulario, BorderLayout.NORTH);
        add(etiquetaResultado, BorderLayout.CENTER);
    }

    public void actualizarSocios() {
        comboUsuario.removeAllItems();
        for (Usuario usuario : gestorUsuario.listarUsuarios()) {
            comboUsuario.addItem(usuario);
        }
    }

    private void asignarPlan() {
        Usuario usuario = (Usuario) comboUsuario.getSelectedItem();
        Plan plan = (Plan) comboPlanes.getSelectedItem();
        if (usuario == null || plan == null) {
            JOptionPane.showMessageDialog(this, "Registre al menos un socio primero.");
            return;
        }
        Membresia membresia = gestorMembresias.asignarPlanYCobrar(usuario, plan);
        etiquetaResultado.setText("Plan " + plan.getNombre() + " asignado a " + usuario.getNombreCompleto()
                + ". Vence: " + membresia.getFechaVencimiento().format(formato));
    }

    private void registrarPago() {
        Usuario usuario = (Usuario) comboUsuario.getSelectedItem();
        if (usuario == null) {
            JOptionPane.showMessageDialog(this, "Registre al menos un socio primero.");
            return;
        }
        try {
            gestorMembresias.registrarPago(usuario);
            Membresia membresia = gestorMembresias.buscarMembresiaPorUsuario(usuario);
            etiquetaResultado.setText("Pago registrado para " + usuario.getNombreCompleto()
                    + ". Nuevo vencimiento: " + membresia.getFechaVencimiento().format(formato));
        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Sin membresía", JOptionPane.WARNING_MESSAGE);
        }
    }
}
