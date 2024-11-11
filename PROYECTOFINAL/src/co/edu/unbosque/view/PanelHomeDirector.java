package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Clase PanelHomeDirector.
 * Representa el panel principal de la interfaz gráfica para el director, donde se muestra 
 * la información personal del director y varias opciones para gestionar pacientes, 
 * especialistas, y especialidades. Además, permite la modificación y eliminación del perfil 
 * del director.
 * 
 * @author Nicolas Zambrano
 * @version 1.0
 */
public class PanelHomeDirector extends JPanel {

    private static final long serialVersionUID = 1L;

    // Elementos gráficos del panel
    private JLabel img;
    private JTextField txtName, txtGmail, txtId;
    private JButton btnModificar, btnEliminar, btnPacientes, btnEspecialistas, btnEspecialidades, btnVolver;

    /**
     * Constructor de la clase PanelHomeDirector.
     * Inicializa todos los componentes visuales del panel de inicio para el director, 
     * como los campos de texto, botones y la imagen de fondo.
     */
    public PanelHomeDirector() {
        setSize(1280, 720);
        setLayout(null);
        setVisible(true);

        // Inicialización y configuración de la imagen de fondo
        img = new JLabel();
        ImageIcon imagenPortada2 = new ImageIcon("src/media/homeDirector.png");
        Image portadaRedimensionada2 = imagenPortada2.getImage().getScaledInstance(1280, 720, Image.SCALE_SMOOTH);
        img.setIcon(new ImageIcon(portadaRedimensionada2));
        img.setBounds(0, 0, 1280, 720);
        img.setVisible(true);

        // Configuración de los campos de texto
        txtId = new JTextField("", SwingConstants.CENTER);
        txtId.setFont(new Font("SansSerif", Font.BOLD, 25));
        txtId.setForeground(Color.BLACK);
        txtId.setBackground(new Color(240, 240, 240));
        txtId.setBounds(60, 220, 350, 50);
        txtId.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        txtId.setEditable(false);

        txtName = new JTextField("", SwingConstants.CENTER);
        txtName.setFont(new Font("SansSerif", Font.BOLD, 25));
        txtName.setForeground(Color.BLACK);
        txtName.setBackground(new Color(240, 240, 240));
        txtName.setBounds(60, 360, 350, 50);
        txtName.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        txtName.setEditable(false);

        txtGmail = new JTextField("", SwingConstants.CENTER);
        txtGmail.setFont(new Font("SansSerif", Font.BOLD, 25));
        txtGmail.setForeground(Color.BLACK);
        txtGmail.setBackground(new Color(240, 240, 240));
        txtGmail.setBounds(60, 510, 350, 50);
        txtGmail.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        txtGmail.setEditable(false);

        // Configuración de los botones
        btnVolver = new JButton("VOLVER");
        btnVolver.setActionCommand("VOLVERHOMEDIRECTOR");
        btnVolver.setOpaque(true);
        btnVolver.setBounds(30, 600, 180, 70);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 20));
        btnVolver.setBackground(new Color(213, 101, 49));
        btnVolver.setBorder(BorderFactory.createBevelBorder(1));

        btnEliminar = new JButton("ELIMINAR PERFIL");
        btnEliminar.setActionCommand("ELIMINARPERFIL");
        btnEliminar.setOpaque(true);
        btnEliminar.setBounds(930, 100, 270, 70);
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 20));
        btnEliminar.setBackground(new Color(213, 101, 49));
        btnEliminar.setBorder(BorderFactory.createBevelBorder(1));

        btnModificar = new JButton("MODIFICAR PERFIL");
        btnModificar.setActionCommand("MODIFICARPERFIL");
        btnModificar.setOpaque(true);
        btnModificar.setBounds(930, 200, 270, 70);
        btnModificar.setFont(new Font("Arial", Font.BOLD, 20));
        btnModificar.setBackground(new Color(27, 167, 161));
        btnModificar.setBorder(BorderFactory.createBevelBorder(1));

        btnPacientes = new JButton("APARTADO PACIENTES");
        btnPacientes.setActionCommand("PACIENTES");
        btnPacientes.setOpaque(true);
        btnPacientes.setBounds(930, 300, 270, 70);
        btnPacientes.setFont(new Font("Arial", Font.BOLD, 18));
        btnPacientes.setBackground(new Color(27, 167, 161));
        btnPacientes.setBorder(BorderFactory.createBevelBorder(1));

        btnEspecialistas = new JButton("APARTADO ESPECIALISTAS");
        btnEspecialistas.setActionCommand("ESPECIALISTAS");
        btnEspecialistas.setOpaque(true);
        btnEspecialistas.setBounds(930, 400, 270, 70);
        btnEspecialistas.setFont(new Font("Arial", Font.BOLD, 18));
        btnEspecialistas.setBackground(new Color(27, 167, 161));
        btnEspecialistas.setBorder(BorderFactory.createBevelBorder(1));

        btnEspecialidades = new JButton("APARTADO ESPECIALIDADES");
        btnEspecialidades.setActionCommand("ESPECIALIDADES");
        btnEspecialidades.setOpaque(true);
        btnEspecialidades.setBounds(930, 500, 270, 70);
        btnEspecialidades.setFont(new Font("Arial", Font.BOLD, 18));
        btnEspecialidades.setBackground(new Color(27, 167, 161));
        btnEspecialidades.setBorder(BorderFactory.createBevelBorder(1));

        // Añadir los componentes al panel
        add(btnVolver);
        add(btnEliminar);
        add(btnModificar);
        add(btnPacientes);
        add(btnEspecialistas);
        add(btnEspecialidades);
        add(txtId);
        add(txtName);
        add(txtGmail);
        add(img);
    }

    /**
     * Obtiene la etiqueta de la imagen de fondo.
     * 
     * @return JLabel imagen de fondo.
     */
    public JLabel getImg() {
        return img;
    }

    /**
     * Configura la etiqueta de la imagen de fondo.
     * 
     * @param img JLabel que representa la imagen de fondo.
     */
    public void setImg(JLabel img) {
        this.img = img;
    }

    /**
     * Obtiene el campo de texto para el nombre.
     * 
     * @return JTextField campo de texto para el nombre.
     */
    public JTextField getTxtName() {
        return txtName;
    }

    /**
     * Configura el campo de texto para el nombre.
     * 
     * @param txtName JTextField que representa el campo de texto para el nombre.
     */
    public void setTxtName(JTextField txtName) {
        this.txtName = txtName;
    }

    /**
     * Obtiene el campo de texto para el correo Gmail.
     * 
     * @return JTextField campo de texto para el correo Gmail.
     */
    public JTextField getTxtGmail() {
        return txtGmail;
    }

    /**
     * Configura el campo de texto para el correo Gmail.
     * 
     * @param txtGmail JTextField que representa el campo de texto para el correo Gmail.
     */
    public void setTxtGmail(JTextField txtGmail) {
        this.txtGmail = txtGmail;
    }

    /**
     * Obtiene el campo de texto para el ID.
     * 
     * @return JTextField campo de texto para el ID.
     */
    public JTextField getTxtId() {
        return txtId;
    }

    /**
     * Configura el campo de texto para el ID.
     * 
     * @param txtId JTextField que representa el campo de texto para el ID.
     */
    public void setTxtId(JTextField txtId) {
        this.txtId = txtId;
    }

    /**
     * Obtiene el botón para modificar el perfil.
     * 
     * @return JButton botón para modificar el perfil.
     */
    public JButton getBtnModificar() {
        return btnModificar;
    }

    /**
     * Configura el botón para modificar el perfil.
     * 
     * @param btnModificar JButton que representa el botón para modificar el perfil.
     */
    public void setBtnModificar(JButton btnModificar) {
        this.btnModificar = btnModificar;
    }

    /**
     * Obtiene el botón para eliminar el perfil.
     * 
     * @return JButton botón para eliminar el perfil.
     */
    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    /**
     * Configura el botón para eliminar el perfil.
     * 
     * @param btnEliminar JButton que representa el botón para eliminar el perfil.
     */
    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    /**
     * Obtiene el botón para el apartado de pacientes.
     * 
     * @return JButton botón para acceder al apartado de pacientes.
     */
    public JButton getBtnPacientes() {
        return btnPacientes;
    }

    /**
     * Configura el botón para el apartado de pacientes.
     * 
     * @param btnPacientes JButton que representa el botón para acceder al apartado de pacientes.
     */
    public void setBtnPacientes(JButton btnPacientes) {
        this.btnPacientes = btnPacientes;
    }

    /**
     * Obtiene el botón para el apartado de especialistas.
     * 
     * @return JButton botón para acceder al apartado de especialistas.
     */
    public JButton getBtnEspecialistas() {
        return btnEspecialistas;
    }

    /**
     * Configura el botón para el apartado de especialistas.
     * 
     * @param btnEspecialistas JButton que representa el botón para acceder al apartado de especialistas.
     */
    public void setBtnEspecialistas(JButton btnEspecialistas) {
        this.btnEspecialistas = btnEspecialistas;
    }

    /**
     * Obtiene el botón para el apartado de especialidades.
     * 
     * @return JButton botón para acceder al apartado de especialidades.
     */
    public JButton getBtnEspecialidades() {
        return btnEspecialidades;
    }

    /**
     * Configura el botón para el apartado de especialidades.
     * 
     * @param btnEspecialidades JButton que representa el botón para acceder al apartado de especialidades.
     */
    public void setBtnEspecialidades(JButton btnEspecialidades) {
        this.btnEspecialidades = btnEspecialidades;
    }

    /**
     * Obtiene el botón para volver al inicio.
     * 
     * @return JButton botón para volver al inicio.
     */
    public JButton getBtnVolver() {
        return btnVolver;
    }

    /**
     * Configura el botón para volver al inicio.
     * 
     * @param btnVolver JButton que representa el botón para volver al inicio.
     */
    public void setBtnVolver(JButton btnVolver) {
        this.btnVolver = btnVolver;
    }

    /**
     * Obtiene el identificador único de la clase.
     * 
     * @return serialVersionUID identificador único de la clase.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
