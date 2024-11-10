package co.edu.unbosque.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Clase PanelHomeEspecialista.
 * Representa un panel para la interfaz gráfica del especialista. Actualmente, solo contiene 
 * una etiqueta (JLabel) para mostrar una imagen de fondo o contenido visual asociado al especialista.
 * 
 * @author Nicolas Zambrano
 * @version 1.0
 */
public class PanelHomeEspecialista extends JPanel {

    private static final long serialVersionUID = 1L;

    // Elemento gráfico del panel: una etiqueta para la imagen
    private JLabel img;

    /**
     * Constructor de la clase PanelHomeEspecialista.
     * Inicializa el panel de inicio para el especialista, configurando los componentes visuales básicos.
     */
    public PanelHomeEspecialista() {
        // Configuración del panel y componentes
        setSize(1280, 720);
        setLayout(null);
        setVisible(true);
    }

    /**
     * Obtiene la etiqueta de la imagen.
     * 
     * @return JLabel etiqueta que representa la imagen del especialista.
     */
    public JLabel getImg() {
        return img;
    }

    /**
     * Configura la etiqueta de la imagen.
     * 
     * @param img JLabel que representa la imagen del especialista.
     */
    public void setImg(JLabel img) {
        this.img = img;
    }
}