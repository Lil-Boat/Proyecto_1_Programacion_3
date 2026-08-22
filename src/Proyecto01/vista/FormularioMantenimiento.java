package Proyecto01.vista;

import Proyecto01.modelo.RegistroUsuarios;
import Proyecto01.modelo.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Ventana de Mantenimiento de Socios del gimnasio.
 * Permite ingresar, editar, eliminar y listar socios, incluyendo su
 * contacto de emergencia y sus condiciones médicas básicas.
 */
public class FormularioMantenimiento extends JFrame {

    // Campos de texto con los datos personales del socio
    protected JTextField txtNombre;
    protected JTextField txtCorreo;
    protected JTextField txtTelefono;
    protected JTextField txtEdad;
    protected JTextField txtNumeroSocio;
    protected JTextField txtIdUsuario;
    protected JTextField txtContactoEmergencia;
    protected JTextField txtCondicionesMedicas;

    // Casilla que indica si el socio tiene su pago al día
    private JCheckBox chkPagoAlDia;

    // Botones de acción del formulario
    private JButton btnGuardar;
    private JButton btnLimpiar;
    private JButton btnEditar;
    private JButton btnEliminar;

    // Tabla y modelo que muestran los socios registrados
    private JTable tablaSocios;
    private DefaultTableModel modeloTabla;

    // Número de socio que se está editando; null significa modo "nuevo ingreso"
    private Integer numeroSocioEnEdicion = null;

    // Crea la ventana, construye la interfaz y carga la lista de socios
    public FormularioMantenimiento() {
        setTitle("Mantenimiento de Socios - Gimnasio");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Contenedor principal con BorderLayout y margen interno
        JPanel panelPrincipal = new JPanel(new BorderLayout(5, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Título de la ventana
        JLabel lblTitulo = new JLabel("Mantenimiento de Socios", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Nunito", Font.BOLD, 30));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        // Panel central que divide el espacio entre formulario y tabla
        JPanel panelCentral = new JPanel(new GridLayout(2, 1, 5, 10));

        // ----- Formulario de datos del socio -----
        JPanel panelFormulario = new JPanel(new GridLayout(9, 2, 8, 8));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del socio"));

        // [Fila 1] Nombre
        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        // [Fila 2] Correo electrónico
        panelFormulario.add(new JLabel("Correo Electrónico:"));
        txtCorreo = new JTextField();
        panelFormulario.add(txtCorreo);

        // [Fila 3] Teléfono
        panelFormulario.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panelFormulario.add(txtTelefono);

        // [Fila 4] Edad
        panelFormulario.add(new JLabel("Edad:"));
        txtEdad = new JTextField();
        panelFormulario.add(txtEdad);

        // [Fila 5] Número de socio: etiqueta y caja juntas en la misma celda
        JPanel filaNumeroSocio = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        filaNumeroSocio.add(new JLabel("Número de Socio:"));
        txtNumeroSocio = new JTextField();
        txtNumeroSocio.setColumns(8);   // Ancho acorde al tamaño de un número de socio
        filaNumeroSocio.add(envolverCampoCorto(txtNumeroSocio));
        panelFormulario.add(filaNumeroSocio);
        panelFormulario.add(new JLabel("")); // La celda de la derecha queda libre

        // [Fila 6] ID de usuario
        panelFormulario.add(new JLabel("ID de Usuario:"));
        txtIdUsuario = new JTextField();
        txtIdUsuario.setColumns(8);     // Ancho acorde al tamaño de un ID
        panelFormulario.add(envolverCampoCorto(txtIdUsuario));

        // [Fila 7] Pago al día
        panelFormulario.add(new JLabel("Pago al día:"));
        chkPagoAlDia = new JCheckBox();
        chkPagoAlDia.setSelected(true);
        panelFormulario.add(chkPagoAlDia);

        // [Fila 8] Contacto de emergencia
        panelFormulario.add(new JLabel("Contacto de Emergencia:"));
        txtContactoEmergencia = new JTextField();
        panelFormulario.add(txtContactoEmergencia);

        // [Fila 9] Condiciones médicas
        panelFormulario.add(new JLabel("Condiciones Médicas:"));
        txtCondicionesMedicas = new JTextField();
        panelFormulario.add(txtCondicionesMedicas);

        panelCentral.add(panelFormulario);

        // ----- Tabla de socios registrados -----
        modeloTabla = new DefaultTableModel(
                new String[]{"Nº Socio", "ID", "Nombre", "Edad", "Pago al día",
                        "Contacto de emergencia", "Condiciones médicas"}, 0);
        tablaSocios = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaSocios);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Socios registrados"));
        panelCentral.add(scrollTabla);

        panelPrincipal.add(panelCentral, BorderLayout.CENTER);

        // ----- Panel de botones -----
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        btnLimpiar = new JButton("Limpiar");
        btnGuardar = new JButton("Guardar");
        btnEditar = new JButton("Editar seleccionado");
        btnEliminar = new JButton("Eliminar seleccionado");
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);

        // Conecta los eventos de los botones y de la tabla
        configurarListeners();

        // Muestra los socios que ya están registrados
        recargarTabla(  );
    }

    // Registra los listeners de los botones y de la selección de la tabla
    private void configurarListeners() {
        // Al hacer clic en Guardar se valida y registra o actualiza al socio
        btnGuardar.addActionListener(e -> guardarSocio());

        // Al hacer clic en Limpiar se vacían los campos y se sale del modo edición
        btnLimpiar.addActionListener(e -> limpiarFormulario());

        // Al hacer clic en Editar se cargan los datos de la fila seleccionada
        btnEditar.addActionListener(e -> cargarSocioSeleccionado());

        // Al hacer clic en Eliminar se borra al socio de la fila seleccionada
        btnEliminar.addActionListener(e -> eliminarSocioSeleccionado());

        // Al seleccionar una fila de la tabla se rellenan los campos para editarla
        tablaSocios.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarSocioSeleccionado();
            }
        });
    }

    // Lee y valida los campos del formulario; luego guarda (nuevo o actualización)
    private void guardarSocio() {
        String nombre = txtNombre.getText().trim();
        String correo = txtCorreo.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String edad = txtEdad.getText().trim();
        String numeroSocio = txtNumeroSocio.getText().trim();
        String idUsuario = txtIdUsuario.getText().trim();
        String contacto = txtContactoEmergencia.getText().trim();
        String condiciones = txtCondicionesMedicas.getText().trim();

        // Valida que los campos personales obligatorios no estén vacíos
        if (nombre.isEmpty() || correo.isEmpty() || telefono.isEmpty() || edad.isEmpty()
                || numeroSocio.isEmpty() || idUsuario.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Los campos personales son obligatorios (contacto y condiciones pueden quedar vacíos).",
                    "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Convierte los campos numéricos y atrapa entradas no numéricas
        int edadInt;
        int telefonoInt;
        int numeroSocioInt;
        int idUsuarioInt;
        try {
            edadInt = Integer.parseInt(edad);
            telefonoInt = Integer.parseInt(telefono);
            numeroSocioInt = Integer.parseInt(numeroSocio);
            idUsuarioInt = Integer.parseInt(idUsuario);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Edad, Teléfono, Número de Socio e ID de Usuario deben ser valores numéricos.",
                    "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Rechaza edades fuera de un rango razonable
        if (edadInt < 0 || edadInt > 120) {
            JOptionPane.showMessageDialog(this,
                    "La edad debe estar entre 0 y 120 años.",
                    "Edad inválida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        RegistroUsuarios registro = RegistroUsuarios.getInstancia();

        // Al ingresar un socio nuevo, evita duplicar el número de socio
        if (numeroSocioEnEdicion == null && registro.existeNumeroSocio(numeroSocioInt)) {
            JOptionPane.showMessageDialog(this,
                    "Ya existe un socio registrado con el número " + numeroSocioInt + ".",
                    "Número de socio duplicado", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Construye el socio con toda la información del formulario
        Usuario socio = new Usuario(nombre, edadInt, correo, telefonoInt,
                numeroSocioInt, idUsuarioInt, chkPagoAlDia.isSelected(),
                contacto, condiciones);

        // Si no se está editando, registra el socio; si se edita, lo reemplaza
        if (numeroSocioEnEdicion == null) {
            registro.registrar(socio);
        } else {
            registro.actualizar(numeroSocioEnEdicion, socio);
        }

        limpiarFormulario();
        recargarTabla();
        JOptionPane.showMessageDialog(this,
                "Datos guardados correctamente: " + nombre);
    }
// Envuelve un campo en un panel FlowLayout para que conserven su ancho
    // preferido en lugar de estirarse con el GridLayout.
    // Recibe el campo a envolver y lo devuelve dentro de un panel angosto,
    // conservando el tamaño lógico que indiquen sus columnas.
    private JPanel envolverCampoCorto(JComponent campo) {
        JPanel contenedor = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        contenedor.add(campo);
        return contenedor;
    }

    // Carga en el formulario los datos del socio de la fila seleccionada
    private void cargarSocioSeleccionado() {
        int fila = tablaSocios.getSelectedRow();
        if (fila < 0) {
            return;
        }

        int numeroSocio = (Integer) modeloTabla.getValueAt(fila, 0);
        int idUsuario = (Integer) modeloTabla.getValueAt(fila, 1);
        String nombre = (String) modeloTabla.getValueAt(fila, 2);
        int edad = (Integer) modeloTabla.getValueAt(fila, 3);

        txtNombre.setText(nombre);
        txtEdad.setText(String.valueOf(edad));
        txtNumeroSocio.setText(String.valueOf(numeroSocio));
        txtIdUsuario.setText(String.valueOf(idUsuario));

        // Toma el resto de datos desde el registro para completar el formulario
        Usuario socio = RegistroUsuarios.getInstancia().buscarPorNumeroSocio(numeroSocio);
        if (socio == null) {
            return;
        }
        txtCorreo.setText(socio.getCorreoElectronico());
        txtTelefono.setText(String.valueOf(socio.getTelefono()));
        chkPagoAlDia.setSelected(socio.isPagoAlDia());
        txtContactoEmergencia.setText(socio.getContactoEmergencia());
        txtCondicionesMedicas.setText(socio.getCondicionesMedicas());

        // Activa el modo edición recordando el número de socio original
        numeroSocioEnEdicion = numeroSocio;
    }

    // Elimina al socio de la fila seleccionada tras la confirmación del usuario
    private void eliminarSocioSeleccionado() {
        int fila = tablaSocios.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione un socio en la tabla para eliminarlo.",
                    "Sin selección", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int numeroSocio = (Integer) modeloTabla.getValueAt(fila, 0);
        int opcion = JOptionPane.showConfirmDialog(this,
                "¿Desea eliminar al socio número " + numeroSocio + "?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION) {
            RegistroUsuarios.getInstancia().eliminarPorNumeroSocio(numeroSocio);
            limpiarFormulario();
            recargarTabla();
        }
    }

    // Vuelve a llenar la tabla con todos los socios del registro
    private void recargarTabla() {
        modeloTabla.setRowCount(0);
        for (Usuario u : RegistroUsuarios.getInstancia().getUsuarios()) {
            modeloTabla.addRow(new Object[]{
                    u.getNumeroSocio(),
                    u.getIdUsuario(),
                    u.getNombreCompleto(),
                    u.getEdad(),
                    u.isPagoAlDia() ? "Sí" : "No",
                    u.getContactoEmergencia(),
                    u.getCondicionesMedicas()
            });
        }
    }

    // Vacía los campos del formulario y sale del modo edición
    private void limpiarFormulario() {
        txtNombre.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
        txtEdad.setText("");
        txtNumeroSocio.setText("");
        txtIdUsuario.setText("");
        txtContactoEmergencia.setText("");
        txtCondicionesMedicas.setText("");
        chkPagoAlDia.setSelected(true);
        tablaSocios.clearSelection();
        numeroSocioEnEdicion = null;
    }
}