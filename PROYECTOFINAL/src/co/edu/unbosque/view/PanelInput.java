package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Clase PanelInput.
 * Representa un panel de entrada en la interfaz gráfica de la aplicación, 
 * donde se ingresan los datos de un usuario (director, especialista o paciente).
 * 
 * El panel incluye campos de texto para ingresar datos como ID, nombre, edad, 
 * correo electrónico, contraseñas, género y especialidad. También incluye botones
 * para guardar la información o volver al panel anterior.
 * 
 * @author Juan de la Hoz
 * @version 1.0
 */
public class PanelInput extends JPanel {

    private static final long serialVersionUID = 1L;

    // Elementos gráficos del panel: etiquetas para las imágenes de fondo
    private JLabel imgD, imgE, imgP;
    
    // Campos de texto para la entrada de datos
    private JTextField txtId, txtName, txtEdad, txtGmail, txtPswd, txtConfirmPswd;
    
    // Listas desplegables para seleccionar género y especialidad
    private JComboBox<String> cmbxGenero, cmbxEspecialidad;
    
    // Botones para interactuar con el panel
    private JButton btnVolver, btnGuardar;

    /**
     * Constructor de la clase PanelInput.
     * Inicializa el panel de entrada para los datos del usuario, configurando
     * todos los elementos gráficos, incluyendo imágenes, campos de texto, 
     * listas desplegables y botones.
     */
    public PanelInput() {
        setSize(1280, 720);
        setLayout(null);
        setVisible(true);

        // Configuración de las imágenes de fondo para cada tipo de usuario
        imgD = new JLabel();
        ImageIcon imagenPortada2 = new ImageIcon("src/media/inputDirector.png");
        Image portadaRedimensionada2 = imagenPortada2.getImage().getScaledInstance(1280, 720, Image.SCALE_SMOOTH);
        imgD.setIcon(new ImageIcon(portadaRedimensionada2));
        imgD.setBounds(0, 0, 1280, 720);
        imgD.setVisible(false);

        imgE = new JLabel();
        ImageIcon imagenPortada = new ImageIcon("src/media/inputEspecialista.png");
        Image portadaRedimensionada = imagenPortada.getImage().getScaledInstance(1280, 720, Image.SCALE_SMOOTH);
        imgE.setIcon(new ImageIcon(portadaRedimensionada));
        imgE.setBounds(0, 0, 1280, 720);
        imgE.setVisible(false);

        imgP = new JLabel();
        ImageIcon imagenPortada1 = new ImageIcon("src/media/inputPaciente.png");
        Image portadaRedimensionada1 = imagenPortada1.getImage().getScaledInstance(1280, 720, Image.SCALE_SMOOTH);
        imgP.setIcon(new ImageIcon(portadaRedimensionada1));
        imgP.setBounds(0, 0, 1280, 720);
        imgP.setVisible(false);

        // Configuración de los campos de texto
        txtId = new JTextField("", SwingConstants.CENTER);
        txtId.setFont(new Font("SansSerif", Font.BOLD, 30));
        txtId.setForeground(Color.BLACK);
        txtId.setBackground(new Color(240, 240, 240));
        txtId.setBounds(720, 120, 300, 50);
        txtId.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));

        txtName = new JTextField("", SwingConstants.CENTER);
        txtName.setFont(new Font("SansSerif", Font.BOLD, 30));
        txtName.setForeground(Color.BLACK);
        txtName.setBackground(new Color(240, 240, 240));
        txtName.setBounds(720, 250, 300, 50);
        txtName.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));

        txtEdad = new JTextField("", SwingConstants.CENTER);
        txtEdad.setFont(new Font("SansSerif", Font.BOLD, 30));
        txtEdad.setForeground(Color.BLACK);
        txtEdad.setBackground(new Color(240, 240, 240));
        txtEdad.setBounds(995, 360, 100, 50);
        txtEdad.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));

        txtGmail = new JTextField("", SwingConstants.CENTER);
        txtGmail.setFont(new Font("SansSerif", Font.BOLD, 25));
        txtGmail.setForeground(Color.BLACK);
        txtGmail.setBackground(new Color(240, 240, 240));
        txtGmail.setBounds(730, 490, 300, 50);
        txtGmail.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));

        txtPswd = new JTextField("", SwingConstants.CENTER);
        txtPswd.setFont(new Font("SansSerif", Font.BOLD, 30));
        txtPswd.setForeground(Color.BLACK);
        txtPswd.setBackground(new Color(240, 240, 240));
        txtPswd.setBounds(110, 350, 300, 50);
        txtPswd.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));

        txtConfirmPswd = new JTextField("", SwingConstants.CENTER);
        txtConfirmPswd.setFont(new Font("SansSerif", Font.BOLD, 30));
        txtConfirmPswd.setForeground(Color.BLACK);
        txtConfirmPswd.setBackground(new Color(240, 240, 240));
        txtConfirmPswd.setBounds(110, 500, 300, 50);
        txtConfirmPswd.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));

        // Configuración de los combos de especialidad y género
        cmbxEspecialidad = new JComboBox<String>();
        cmbxEspecialidad.addItem("");
        cmbxEspecialidad.setFont(new Font("SansSerif", Font.BOLD, 15));
        cmbxEspecialidad.setBounds(160, 200, 200, 50);
        cmbxEspecialidad.setOpaque(true);
        cmbxEspecialidad.setForeground(Color.BLACK);
        cmbxEspecialidad.setBackground(new Color(240, 240, 240));
        cmbxEspecialidad.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));

        cmbxGenero = new JComboBox<String>();
        cmbxGenero.addItem("");
        cmbxGenero.addItem("Masculino");
        cmbxGenero.addItem("Femenino");
        cmbxGenero.setFont(new Font("SansSerif", Font.BOLD, 15));
        cmbxGenero.setBounds(600, 360, 200, 50);
        cmbxGenero.setOpaque(true);
        cmbxGenero.setForeground(Color.BLACK);
        cmbxGenero.setBackground(new Color(240, 240, 240));
        cmbxGenero.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));

        // Configuración de los botones
        btnVolver = new JButton("VOLVER");
        btnVolver.setActionCommand("VOLVERINPUT");
        btnVolver.setOpaque(true);
        btnVolver.setBounds(30, 600, 180, 70);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 20));
        btnVolver.setBackground(new Color(213, 101, 49));
        btnVolver.setBorder(BorderFactory.createBevelBorder(1));

        btnGuardar = new JButton("GUARDAR");
        btnGuardar.setActionCommand("GUARDAR");
        btnGuardar.setOpaque(true);
        btnGuardar.setBounds(1050, 600, 200, 70);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 20));
        btnGuardar.setBackground(new Color(27, 167, 161));
        btnGuardar.setBorder(BorderFactory.createBevelBorder(1));

        // Añadir todos los componentes al panel
        add(btnGuardar);
        add(btnVolver);
        add(txtId);
        add(txtName);
        add(txtEdad);
        add(txtGmail);
        add(txtPswd);
        add(txtConfirmPswd);
        add(cmbxEspecialidad);
        add(cmbxGenero);
        add(imgE);
        add(imgD);
        add(imgP);
    }

    // Métodos getter y setter para los campos de texto, botones y etiquetas de imagen

    public JLabel getImgD() {
        return imgD;
    }

    public void setImgD(JLabel imgD) {
        this.imgD = imgD;
    }

    public JLabel getImgE() {
        return imgE;
    }

    public void setImgE(JLabel imgE) {
        this.imgE = imgE;
    }

    public JLabel getImgP() {
        return imgP;
    }

    public void setImgP(JLabel imgP) {
        this.imgP = imgP;
    }

    public JTextField getTxtId() {
        return txtId;
    }

    public void setTxtId(JTextField txtId) {
        this.txtId = txtId;
    }

    public JTextField getTxtName() {
        return txtName;
    }

    public void setTxtName(JTextField txtName) {
        this.txtName = txtName;
    }

    public JTextField getTxtEdad() {
        return txtEdad;
    }

    public void setTxtEdad(JTextField txtEdad) {
        this.txtEdad = txtEdad;
    }

    public JTextField getTxtGmail() {
        return txtGmail;
    }

    public void setTxtGmail(JTextField txtGmail) {
        this.txtGmail = txtGmail;
    }

    public JTextField getTxtPswd() {
        return txtPswd;
    }

    public void setTxtPswd(JTextField txtPswd) {
        this.txtPswd = txtPswd;
    }

    public JTextField getTxtConfirmPswd() {
        return txtConfirmPswd;
    }

    public void setTxtConfirmPswd(JTextField txtConfirmPswd) {
        this.txtConfirmPswd = txtConfirmPswd;
    }

    public JComboBox<String> getCmbxGenero() {
        return cmbxGenero;
    }

    public void setCmbxGenero(JComboBox<String> cmbxGenero) {
        this.cmbxGenero = cmbxGenero;
    }

    public JComboBox<String> getCmbxEspecialidad() {
        return cmbxEspecialidad;
    }

    public void setCmbxEspecialidad(JComboBox<String> cmbxEspecialidad) {
        this.cmbxEspecialidad = cmbxEspecialidad;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public void setBtnVolver(JButton btnVolver) {
        this.btnVolver = btnVolver;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(JButton btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
