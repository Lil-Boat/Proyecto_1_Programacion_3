package vista;

import servicio.IGestorUsuario;
import modelo.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;



public class PanelSocios extends JPanel {
    private final IGestorUsuario gestor;
    private final DefaultTableModel modeloTabla;
    private JTable tablaSocios;

    private final JTextField campoNombre = new JTextField(15);
    private final JTextField campoTelefono = new JTextField(10);
    private final JTextField campoCorreo = new JTextField(15);
    private final JTextField campoContactoEmergencia = new JTextField(15);
    private final JTextField campoCondicionesMedicas = new JTextField(15);

    public PanelSocios(IGestorUsuario gestor){
        this.gestor = gestor;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(construirFormulario(), BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(new Object[]{"N° Usuario", "Nombre", "Telefono", "Correo", "Contacto Emergencia", "Condiciones Medicas"}, 0){

            //Indica si una celda de la tabla puede editarse directamente
            //Retorna siempre false, ya que la tabla es de solo lectura
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        tablaSocios = new JTable(modeloTabla);
        add(new JScrollPane(tablaSocios), BorderLayout.CENTER);


        JButton botonEliminar = new JButton("Eliminar Socio");
        botonEliminar.addActionListener(e -> eliminarSocio());
        add(botonEliminar, BorderLayout.SOUTH);
    }

    //Se arma el formulario para registrar un nuevo socio
    //Retorna un JPanel con los campos de texto y el boton de registrar
    private JPanel construirFormulario(){
        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        panel.add(new JLabel("Nombre:"));
        panel.add(campoNombre);
        panel.add(new JLabel("Telefono:"));
        panel.add(campoTelefono);
        panel.add(new JLabel("Correo:"));
        panel.add(campoCorreo);
        panel.add(new JLabel("Contacto Emergencia:"));
        panel.add(campoContactoEmergencia);
        panel.add(new JLabel("Condiciones Medicas:"));
        panel.add(campoCondicionesMedicas);
        JButton botonRegistrar = new JButton("Registrar Socio");
        botonRegistrar.addActionListener(e -> registrarSocio());
        panel.add(new JLabel());
        panel.add(botonRegistrar);
        return panel;
    }

    //Valida los datos ingresados y registra el socio
    private void registrarSocio() {
        try {
            Usuario usuario = gestor.registrarUsuario(
                    campoNombre.getText().trim(),
                    Integer.parseInt(campoTelefono.getText().trim()),
                    campoCorreo.getText().trim(),
                    campoContactoEmergencia.getText().trim(),
                    campoCondicionesMedicas.getText().trim());
            modeloTabla.addRow(new Object[]{
                    usuario.getNumeroUsuario(),
                    usuario.getNombreCompleto(),
                    usuario.getTelefono(),
                    usuario.getCorreoElectronico(),
                    usuario.getContactoEmergencia(),
                    usuario.getCondicionesMedicas()});
            limpiarFormulario();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void eliminarSocio(){
        int fila = tablaSocios.getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(this, "Seleccione un socio de la tabla primero");
            return;
        }
        int numeroUsuario = (int) modeloTabla.getValueAt(fila, 0);
        Usuario usuario = gestor.buscarUsuarioPorId(numeroUsuario);
        if(usuario != null) gestor.eliminarUsuario(numeroUsuario);
        modeloTabla.removeRow(fila);
    }

    private void limpiarFormulario(){
        campoNombre.setText("");
        campoTelefono.setText("");
        campoCorreo.setText("");
        campoContactoEmergencia.setText("");
        campoCondicionesMedicas.setText("");
    }


}
