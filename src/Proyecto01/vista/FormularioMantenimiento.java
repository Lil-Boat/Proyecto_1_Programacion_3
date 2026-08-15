package Proyecto01.vista;

import Proyecto01.modelo.RegistroUsuarios;
import Proyecto01.modelo.Usuario;

import javax.swing.*;

import java.awt.*;

public class FormularioMantenimiento extends JFrame {

    protected JTextField txtNombre;
    protected JTextField txtCorreo;
    protected JTextField txtTelefono;
    protected JTextField txtEdad;
    protected JTextField txtNumeroSocio;
    protected JTextField txtIdUsuario;

    private JButton btnGuardar;

    // Botón para borrar las cajas de texto
    private JButton btnLimpiar;

    // Casilla que indica si el usuario tiene su pago al día al momento de inscribirse
    private JCheckBox chkPagoAlDia;

    public FormularioMantenimiento() {

        setTitle("Sistema de Gestión de Usuarios");

        // Dimensiones: 450 píxeles de ancho por 300 de alto
        setSize(1280, 720);

        // Al tocar la 'X', el programa se detiene por completo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Centra la ventana en la pantalla del monitor
        setLocationRelativeTo(null);


// Contenedor principal con BorderLayout (5 regiones) y 10px de separación
        JPanel panelPrincipal = new JPanel(new BorderLayout(5, 10));

// Margen interno de 15px para no pegar el contenido con el borde
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));


        // Etiqueta de texto centrada para el título del formulario
        JLabel lblTitulo = new JLabel("Inscripción Usuarios", SwingConstants.CENTER);

// Aplica tipografía en negrita (BOLD) y tamaño 18
        lblTitulo.setFont(new Font("Nunito", Font.BOLD, 30));

// Color del texto: rojo formulario (RGB: 200, 16, 46)
        lblTitulo.setForeground(new Color(0, 0, 0, 255));

// Lo coloca en la región superior (NORTH) del panel principal
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);


        JPanel panelFormulario = new JPanel(new GridLayout(7, 2, 8, 8));

// [Fila 1] Etiqueta + caja de texto para el nombre
        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

// [Fila 2] Etiqueta + caja de texto para el correo electrónico
        panelFormulario.add(new JLabel("Correo Electrónico:"));
        txtCorreo = new JTextField();
        panelFormulario.add(txtCorreo);

// [Fila 3] Etiqueta + caja de texto para el teléfono
        panelFormulario.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panelFormulario.add(txtTelefono);

        panelFormulario.add(new JLabel("Edad:"));
        txtEdad = new JTextField();
        panelFormulario.add(txtEdad);

        panelFormulario.add(new JLabel("Número de Socio:"));
        txtNumeroSocio = new JTextField();
        panelFormulario.add(txtNumeroSocio);

        panelFormulario.add(new JLabel("ID de Usuario:"));
        txtIdUsuario = new JTextField();
        panelFormulario.add(txtIdUsuario);

        panelFormulario.add(new JLabel("Pago al día:"));
        chkPagoAlDia = new JCheckBox();
        chkPagoAlDia.setSelected(true);
        panelFormulario.add(chkPagoAlDia);


// Coloca el formulario en el área central (CENTER) del panel principal
        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));

// Botón para borrar los campos (etiqueta 'Limpiar')
        btnLimpiar = new JButton("Limpiar");

// Botón para guardar la información
        btnGuardar = new JButton("Guardar Informacion");

// Agrega ambos botones al panel secundario
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnGuardar);

// Coloca el panel de botones en la región inferior (SOUTH)
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        // Agrega el panel principal al JFrame
        add(panelPrincipal);

        btnGuardar.addActionListener(e -> {

            // Lee el texto escrito en la caja del nombre
            String nombre = txtNombre.getText();

            // Lee el texto escrito en la caja del correo electrónico
            String correo = txtCorreo.getText();

            // Lee el texto escrito en la caja del teléfono
            String telefono = txtTelefono.getText();

            // Lee el texto escrito en la caja de la edad
            String edad = txtEdad.getText();

            // Lee el texto escrito en la caja del número de socio
            String numeroSocio = txtNumeroSocio.getText();

            // Lee el texto escrito en la caja del ID de usuario
            String idUsuario = txtIdUsuario.getText();

            // Valida que ningún campo esté vacío antes de guardar
            if (nombre.isEmpty() || correo.isEmpty() || telefono.isEmpty() ||
                    edad.isEmpty() || numeroSocio.isEmpty() || idUsuario.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Todos los campos son obligatorios.",
                        "Campos vacíos",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Convierte los campos numéricos a entero
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
                        "Datos inválidos",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            RegistroUsuarios registro = RegistroUsuarios.getInstancia();

            // Evita registrar dos veces el mismo número de socio
            if (registro.existeNumeroSocio(numeroSocioInt)) {
                JOptionPane.showMessageDialog(this,
                        "Ya existe un socio registrado con el número " + numeroSocioInt + ".",
                        "Número de socio duplicado",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Crea el usuario y lo guarda en el registro compartido con el sistema de acceso
            Usuario nuevoUsuario = new Usuario(nombre, edadInt, correo, telefonoInt,
                    numeroSocioInt, idUsuarioInt, chkPagoAlDia.isSelected());
            registro.registrar(nuevoUsuario);

            // Muestra un mensaje de confirmación con los datos ingresados
            JOptionPane.showMessageDialog(this,
                    "Datos guardados correctamente:\n" + nombre + "\n" + correo);
        });

        // Listener del botón Limpiar: borra el contenido de todas las cajas de texto
        btnLimpiar.addActionListener(e -> {
            txtNombre.setText("");
            txtCorreo.setText("");
            txtTelefono.setText("");
            txtEdad.setText("");
            txtNumeroSocio.setText("");
            txtIdUsuario.setText("");
            chkPagoAlDia.setSelected(true);
        });

        this.add(panelPrincipal);


    }

}