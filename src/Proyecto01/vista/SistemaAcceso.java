package Proyecto01.vista;

import Proyecto01.modelo.RegistroUsuarios;
import Proyecto01.modelo.Usuario;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class SistemaAcceso extends JFrame {


    protected JTextField txtNumeroUsuario;

    private JButton btnIngresar;

    // Botón para borrar las cajas de texto
    private JButton btnLimpiar;

    // Botón para abrir el formulario de mantenimiento de usuarios
    private JButton btnMantenimiento;

    // Etiqueta que muestra el resultado de la verificación en tiempo real
    private JLabel lblResultado;

    public SistemaAcceso() {

        setTitle("Sistema de Acceso");

        // Dimensiones: 1280 píxeles de ancho por 720 de alto
        setSize(600, 300);

        // Al tocar la 'X', el programa se detiene por completo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Centra la ventana en la pantalla del monitor
        setLocationRelativeTo(null);


        // Contenedor principal con BorderLayout (5 regiones) y 10px de separación
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));

        // Margen interno de 15px para no pegar el contenido con el borde
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Etiqueta de texto centrada para el título del formulario
        JLabel lblTitulo = new JLabel("Simulación de Acceso", SwingConstants.CENTER);

        // Aplica tipografía en negrita (BOLD) y tamaño 30
        lblTitulo.setFont(new Font("Nunito", Font.BOLD, 18));

        // Color del texto: negro
        lblTitulo.setForeground(new Color(200, 16, 46));

        // Lo coloca en la región superior (NORTH) del panel principal
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);


        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 8, 8));

        // [Fila 1] Etiqueta + caja de texto para el número de socio
        panelFormulario.add(new JLabel("Número de Socio:"));
        txtNumeroUsuario = new JTextField();
        panelFormulario.add(txtNumeroUsuario);

        // [Fila 2] Etiqueta + resultado de la verificación del pago
        panelFormulario.add(new JLabel("Estado del Pago:"));
        lblResultado = new JLabel("Ingrese su número de socio", SwingConstants.CENTER);
        lblResultado.setFont(new Font("Nunito", Font.BOLD, 18));
        lblResultado.setForeground(Color.GRAY);
        panelFormulario.add(lblResultado);


        // Coloca el formulario en el área central (CENTER) del panel principal
        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        // Botón para borrar el campo (etiqueta 'Limpiar')
        btnLimpiar = new JButton("Limpiar");

        // Botón para verificar el acceso
        btnIngresar = new JButton("Ingresar");

        // Botón para abrir el registro de usuarios (mantenimiento)
        btnMantenimiento = new JButton("Inscripción de Usuarios");

        // Agrega los botones al panel secundario
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnIngresar);
        panelBotones.add(btnMantenimiento);

        // Coloca el panel de botones en la región inferior (SOUTH)
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        // Agrega el panel principal al JFrame
        add(panelPrincipal);


        // Verificación en tiempo real: cada vez que se escribe o borra un dígito
        // se vuelve a consultar el registro y se actualiza la etiqueta de resultado
        txtNumeroUsuario.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                verificarAcceso();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                verificarAcceso();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                verificarAcceso();
            }
        });

        // Listener del botón Ingresar: muestra el resultado en una ventana
        btnIngresar.addActionListener(e -> {
            verificarAcceso();
            JOptionPane.showMessageDialog(this,
                    lblResultado.getText(),
                    "Resultado de la verificación",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        // Listener del botón Limpiar: borra la caja de texto y restablece el estado
        btnLimpiar.addActionListener(e -> restablecerEstado());

        // Listener del botón Mantenimiento: abre el formulario de registro de usuarios
        btnMantenimiento.addActionListener(e -> {
            FormularioMantenimiento formulario = new FormularioMantenimiento();

            // Al cerrar el formulario secundario solo se oculta, sin detener la aplicación
            formulario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            formulario.setVisible(true);
        });


    }

    // Verifica el número de socio digitado y actualiza la etiqueta de resultado
    private void verificarAcceso() {

        String texto = txtNumeroUsuario.getText().trim(); //trim elimina espacios en blanco

        lblResultado.setFont(new Font("Nunito", Font.BOLD, 22));

        // Campo vacío: aún no hay nada que verificar
        if (texto.isEmpty()) {
            restablecerEstado();
            return;
        }

        int numeroUsuario;

        try {
            numeroUsuario = Integer.parseInt(texto);
        } catch (NumberFormatException ex) {
            lblResultado.setText("Número de socio inválido");
            lblResultado.setForeground(new Color(255, 140, 0));
            return;
        }

        // Busca al socio en el registro compartido con el formulario de mantenimiento
        Usuario usuario = RegistroUsuarios.getInstancia().buscarPorNumeroUsuario(numeroUsuario);

        // El socio no está registrado en el sistema
        if (usuario == null) {
            lblResultado.setText("Acceso Denegado: socio no registrado");
            lblResultado.setForeground(new Color(255, 140, 0));
            return;
        }

        // Pago al día: se permite la entrada
        if (usuario.isPagoAlDia()) {
            lblResultado.setText("Acceso Permitido");
            lblResultado.setForeground(new Color(0, 150, 0));
        } else {
            // Pago vencido: se deniega la entrada por morosidad
            lblResultado.setText("Acceso Denegado por Morosidad");
            lblResultado.setForeground(new Color(200, 16, 46));
        }
    }

    // Restablece la etiqueta de resultado a su mensaje inicial
    private void restablecerEstado() {
        txtNumeroUsuario.setText("");
        lblResultado.setFont(new Font("Nunito", Font.BOLD, 22));
        lblResultado.setText("Ingrese su número de socio");
        lblResultado.setForeground(Color.GRAY);
    }


}
