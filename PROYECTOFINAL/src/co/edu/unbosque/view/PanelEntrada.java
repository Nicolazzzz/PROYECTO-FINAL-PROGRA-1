package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Clase PanelEntrada.
 * Representa un panel de bienvenida en la interfaz gráfica con opciones para seleccionar
 * entre diferentes tipos de usuarios: Director, Especialista y Paciente.
 * 
 * Extiende JPanel y contiene una imagen de fondo y tres botones que redirigen al usuario
 * dependiendo de su rol.
 * 
 * @author Nicolas Zambrano
 * @version 1.0
 */
public class PanelEntrada extends JPanel {

    private static final long serialVersionUID = 1L;

    // Elementos gráficos del panel
    private JLabel img;
    private JButton btnDirector, btnEspecialista, btnPaciente;

    /**
     * Constructor de PanelEntrada.
     * Inicializa el panel de entrada con su imagen de fondo y los botones de selección
     * de usuario.
     */
    public PanelEntrada() {
        // Configuración del panel
        setSize(1280, 720);
        setLayout(null);
        setVisible(true);

        // Inicialización y configuración de la imagen de fondo
        img = new JLabel();
        ImageIcon imagenPortada2 = new ImageIcon("src/media/welcome.png");
        Image portadaRedimensionada2 = imagenPortada2.getImage().getScaledInstance(1280, 720, Image.SCALE_SMOOTH);
        img.setIcon(new ImageIcon(portadaRedimensionada2));
        img.setBounds(0, 0, 1280, 720);
        img.setVisible(false);

        // Configuración del botón de Director
        btnDirector = new JButton("DIRECTOR");
        btnDirector.setActionCommand("DIRECTOR");
        btnDirector.setOpaque(true);
        btnDirector.setBounds(850, 220, 250, 70);
        btnDirector.setFont(new Font("Arial", Font.BOLD, 20));
        btnDirector.setBackground(new Color(27, 167, 161));
        btnDirector.setBorder(BorderFactory.createBevelBorder(1));

        // Configuración del botón de Especialista
        btnEspecialista = new JButton("ESPECIALISTA");
        btnEspecialista.setActionCommand("ESPECIALISTA");
        btnEspecialista.setOpaque(true);
        btnEspecialista.setBounds(850, 370, 250, 70);
        btnEspecialista.setFont(new Font("Arial", Font.BOLD, 20));
        btnEspecialista.setBackground(new Color(27, 167, 161));
        btnEspecialista.setBorder(BorderFactory.createBevelBorder(1));

        // Configuración del botón de Paciente
        btnPaciente = new JButton("PACIENTE");
        btnPaciente.setActionCommand("PACIENTE");
        btnPaciente.setOpaque(true);
        btnPaciente.setBounds(850, 520, 250, 70);
        btnPaciente.setFont(new Font("Arial", Font.BOLD, 20));
        btnPaciente.setBackground(new Color(27, 167, 161));
        btnPaciente.setBorder(BorderFactory.createBevelBorder(1));

        // Añadir los componentes al panel
        add(btnDirector);
        add(btnEspecialista);
        add(btnPaciente);
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
     * Obtiene el botón de Director.
     * 
     * @return JButton botón de Director.
     */
    public JButton getBtnDirector() {
        return btnDirector;
    }

    /**
     * Configura el botón de Director.
     * 
     * @param btnDirector JButton que representa el botón de Director.
     */
    public void setBtnDirector(JButton btnDirector) {
        this.btnDirector = btnDirector;
    }

    /**
     * Obtiene el botón de Especialista.
     * 
     * @return JButton botón de Especialista.
     */
    public JButton getBtnEspecialista() {
        return btnEspecialista;
    }

    /**
     * Configura el botón de Especialista.
     * 
     * @param btnEspecialista JButton que representa el botón de Especialista.
     */
    public void setBtnEspecialista(JButton btnEspecialista) {
        this.btnEspecialista = btnEspecialista;
    }

    /**
     * Obtiene el botón de Paciente.
     * 
     * @return JButton botón de Paciente.
     */
    public JButton getBtnPaciente() {
        return btnPaciente;
    }

    /**
     * Configura el botón de Paciente.
     * 
     * @param btnPaciente JButton que representa el botón de Paciente.
     */
    public void setBtnPaciente(JButton btnPaciente) {
        this.btnPaciente = btnPaciente;
    }

    /**
     * Obtiene el serialVersionUID.
     * 
     * @return long serialVersionUID de la clase.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}