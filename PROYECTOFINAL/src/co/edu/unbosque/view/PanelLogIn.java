package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Clase PanelLogIn.
 * Representa el panel de inicio de sesión donde el usuario ingresa sus credenciales 
 * (ID y contraseña) para acceder a la aplicación. Este panel incluye botones 
 * para iniciar sesión o regresar, y proporciona ayuda sobre el formato del ID.
 * 
 * @author Nicolas Zambrano
 * @version 1.0
 */
public class PanelLogIn extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTextField txtId;
    private JButton btnVolver, btnEntrar;
    private JLabel imgD, imgE, lblHelp;
    private JPasswordField txtPwd;

    /**
     * Constructor de la clase PanelLogIn.
     * Inicializa el panel de inicio de sesión con campos de texto, botones, 
     * etiquetas de imagen y ayuda sobre el formato del ID.
     */
    public PanelLogIn() {
        setSize(1280, 720);
        setLayout(null);
        setVisible(true);

        // Configuración de las imágenes de fondo para director y especialista
        imgD = new JLabel();
        ImageIcon imagenPortada2 = new ImageIcon("src/media/loginDirector.png");
        Image portadaRedimensionada2 = imagenPortada2.getImage().getScaledInstance(1280, 720, Image.SCALE_SMOOTH);
        imgD.setIcon(new ImageIcon(portadaRedimensionada2));
        imgD.setBounds(0, 0, 1280, 720);
        imgD.setVisible(false);

        imgE = new JLabel();
        ImageIcon imagenPortada = new ImageIcon("src/media/loginEspecialista.png");
        Image portadaRedimensionada = imagenPortada.getImage().getScaledInstance(1280, 720, Image.SCALE_SMOOTH);
        imgE.setIcon(new ImageIcon(portadaRedimensionada));
        imgE.setBounds(0, 0, 1280, 720);
        imgE.setVisible(false);

        // Configuración de los botones
        btnEntrar = new JButton("ENTRAR");
        btnEntrar.setActionCommand("ENTRAR");
        btnEntrar.setOpaque(true);
        btnEntrar.setBounds(870, 550, 230, 70);
        btnEntrar.setFont(new Font("Arial", Font.BOLD, 20));
        btnEntrar.setBackground(new Color(27, 167, 161));
        btnEntrar.setBorder(BorderFactory.createBevelBorder(1));

        btnVolver = new JButton("VOLVER");
        btnVolver.setActionCommand("VOLVER");
        btnVolver.setOpaque(true);
        btnVolver.setBounds(60, 600, 250, 70);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 20));
        btnVolver.setBackground(new Color(213, 101, 49));
        btnVolver.setBorder(BorderFactory.createBevelBorder(1));

        // Configuración de la etiqueta de ayuda
        lblHelp = new JLabel("Ingrese números, min 5 - max 10", SwingConstants.CENTER);
        lblHelp.setFont(new Font("Arial", Font.BOLD, 15));
        lblHelp.setBounds(860, 425, 250, 30);
        lblHelp.setOpaque(false);
        lblHelp.setVisible(true);

        // Configuración del campo de texto para el ID
        txtId = new JTextField("", SwingConstants.CENTER);
        txtId.setFont(new Font("SansSerif", Font.BOLD, 30));
        txtId.setForeground(Color.BLACK);
        txtId.setBackground(new Color(240, 240, 240));
        txtId.setBounds(830, 280, 300, 50);
        txtId.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));

        // Configuración del campo de texto para la contraseña
        txtPwd = new JPasswordField("", SwingConstants.CENTER);
        txtPwd.setFont(new Font("SansSerif", Font.BOLD, 30));
        txtPwd.setForeground(Color.BLACK);
        txtPwd.setBackground(new Color(240, 240, 240));
        txtPwd.setBounds(830, 450, 300, 50);
        txtPwd.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));

        // Añadir los componentes al panel
        add(btnEntrar);
        add(btnVolver);
        add(lblHelp);
        add(txtId);
        add(txtPwd);
        add(imgD);
        add(imgE);
    }

    // Métodos getter y setter para los campos de texto, botones y etiquetas de imagen

    public JTextField getTxtId() {
        return txtId;
    }

    public void setTxtId(JTextField txtId) {
        this.txtId = txtId;
    }

    public JLabel getLblHelp() {
        return lblHelp;
    }

    public void setLblHelp(JLabel lblHelp) {
        this.lblHelp = lblHelp;
    }

    public JPasswordField getTxtPwd() {
        return txtPwd;
    }

    public void setTxtPwd(JPasswordField txtPwd) {
        this.txtPwd = txtPwd;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public void setBtnVolver(JButton btnVolver) {
        this.btnVolver = btnVolver;
    }

    public JButton getBtnEntrar() {
        return btnEntrar;
    }

    public void setBtnEntrar(JButton btnEntrar) {
        this.btnEntrar = btnEntrar;
    }

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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
