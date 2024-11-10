package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Clase PanelApartado.
 * Representa un panel personalizado que incluye botones de acción, etiquetas de imagen
 * y una tabla con scroll para mostrar datos en una interfaz gráfica.
 * 
 * Esta clase extiende JPanel y contiene varios elementos gráficos,
 * tales como botones para ver, agregar, modificar y eliminar datos,
 * además de un botón para volver al panel anterior.
 * 
 * Se define un modelo de tabla que no permite la edición de las celdas.
 * También incluye tres imágenes de fondo, cada una asignada a una etiqueta JLabel.
 * 
 * @author Mario Rodriguez
 * @version 1.0
 */
public class PanelApartado extends JPanel {

    private static final long serialVersionUID = 1L;
    
    // Etiquetas de imagen
    private JLabel imgP, imgE, imgA;
    
    // Tabla y barra de desplazamiento
    private JTable tabla;
    private JScrollPane scroll;
    
    // Botones de acción
    private JButton btnVer, btnAgregar, btnMod, btnDelete, btnVolver;

    /**
     * Constructor de PanelApartado.
     * Inicializa el panel con todos sus componentes gráficos y los añade a la interfaz.
     */
    public PanelApartado() {
        // Configuración de tamaño y disposición
        setSize(1280, 720);
        setLayout(null);
        setVisible(true);

        // Inicialización y configuración de imágenes
        imgP = new JLabel();
        ImageIcon imagenPortada2 = new ImageIcon("src/media/apartadoPacientes.png");
        Image portadaRedimensionada2 = imagenPortada2.getImage().getScaledInstance(1280, 720, Image.SCALE_SMOOTH);
        imgP.setIcon(new ImageIcon(portadaRedimensionada2));
        imgP.setBounds(0, 0, 1280, 720);
        imgP.setVisible(true);

        imgE = new JLabel();
        ImageIcon imagenPortada = new ImageIcon("src/media/apartadoEspecialistas.png");
        Image portadaRedimensionada = imagenPortada.getImage().getScaledInstance(1280, 720, Image.SCALE_SMOOTH);
        imgE.setIcon(new ImageIcon(portadaRedimensionada));
        imgE.setBounds(0, 0, 1280, 720);
        imgE.setVisible(true);

        imgA = new JLabel();
        ImageIcon imagenPortada1 = new ImageIcon("src/media/apartadoEspecialidades.png");
        Image portadaRedimensionada1 = imagenPortada1.getImage().getScaledInstance(1280, 720, Image.SCALE_SMOOTH);
        imgA.setIcon(new ImageIcon(portadaRedimensionada1));
        imgA.setBounds(0, 0, 1280, 720);
        imgA.setVisible(true);

        // Modelo de tabla que evita la edición de celdas
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Inicialización de la tabla y la barra de desplazamiento
        tabla = new JTable(model);
        tabla.setEnabled(true);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.getTableHeader().setReorderingAllowed(true);

        scroll = new JScrollPane(tabla);
        scroll.setBounds(80, 200, 700, 350);
        scroll.setVisible(true);
        scroll.setOpaque(true);

        // Configuración de botones
        btnVer = new JButton("VER");
        btnVer.setActionCommand("APARTADOVER");
        btnVer.setOpaque(true);
        btnVer.setBounds(930, 100, 270, 70);
        btnVer.setFont(new Font("Arial", Font.BOLD, 18));
        btnVer.setBackground(new Color(27, 167, 161));
        btnVer.setBorder(BorderFactory.createBevelBorder(1));

        btnAgregar = new JButton("AGREGAR");
        btnAgregar.setActionCommand("APARTADOADD");
        btnAgregar.setOpaque(true);
        btnAgregar.setBounds(930, 200, 270, 70);
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 18));
        btnAgregar.setBackground(new Color(27, 167, 161));
        btnAgregar.setBorder(BorderFactory.createBevelBorder(1));

        btnMod = new JButton("MODIFICAR");
        btnMod.setActionCommand("APARTADOMOD");
        btnMod.setOpaque(true);
        btnMod.setBounds(930, 300, 270, 70);
        btnMod.setFont(new Font("Arial", Font.BOLD, 18));
        btnMod.setBackground(new Color(27, 167, 161));
        btnMod.setBorder(BorderFactory.createBevelBorder(1));

        btnDelete = new JButton("ELIMINAR");
        btnDelete.setActionCommand("APARTADODEL");
        btnDelete.setOpaque(true);
        btnDelete.setBounds(930, 400, 270, 70);
        btnDelete.setFont(new Font("Arial", Font.BOLD, 18));
        btnDelete.setBackground(new Color(27, 167, 161));
        btnDelete.setBorder(BorderFactory.createBevelBorder(1));

        btnVolver = new JButton("VOLVER");
        btnVolver.setActionCommand("APARTADOBACK");
        btnVolver.setOpaque(true);
        btnVolver.setBounds(930, 500, 270, 70);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 18));
        btnVolver.setBackground(new Color(213, 101, 49));
        btnVolver.setBorder(BorderFactory.createBevelBorder(1));

        // Añadir componentes al panel
        add(btnAgregar);
        add(btnDelete);
        add(btnMod);
        add(btnVer);
        add(btnVolver);
        add(imgA);
        add(imgE);
        add(imgP);
        add(scroll);
    }

    // Getters y setters para cada componente gráfico

    public JTable getTabla() {
        return tabla;
    }

    public void setTabla(JTable tabla) {
        this.tabla = tabla;
    }

    public JScrollPane getScroll() {
        return scroll;
    }

    public void setScroll(JScrollPane scroll) {
        this.scroll = scroll;
    }

    public JLabel getImgP() {
        return imgP;
    }

    public void setImgP(JLabel imgP) {
        this.imgP = imgP;
    }

    public JLabel getImgE() {
        return imgE;
    }

    public void setImgE(JLabel imgE) {
        this.imgE = imgE;
    }

    public JLabel getImgA() {
        return imgA;
    }

    public void setImgA(JLabel imgA) {
        this.imgA = imgA;
    }

    public JButton getBtnVer() {
        return btnVer;
    }

    public void setBtnVer(JButton btnVer) {
        this.btnVer = btnVer;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public void setBtnAgregar(JButton btnAgregar) {
        this.btnAgregar = btnAgregar;
    }

    public JButton getBtnMod() {
        return btnMod;
    }

    public void setBtnMod(JButton btnMod) {
        this.btnMod = btnMod;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(JButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public void setBtnVolver(JButton btnVolver) {
        this.btnVolver = btnVolver;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}