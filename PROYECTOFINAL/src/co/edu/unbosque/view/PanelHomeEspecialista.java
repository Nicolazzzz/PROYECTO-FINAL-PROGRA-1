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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Clase PanelHomeEspecialista. Representa un panel para la interfaz gráfica del
 * especialista. Actualmente, solo contiene una etiqueta (JLabel) para mostrar
 * una imagen de fondo o contenido visual asociado al especialista.
 * 
 * @author Nicolas Zambrano
 * @version 1.0
 */
public class PanelHomeEspecialista extends JPanel {

	private static final long serialVersionUID = 1L;

	// Elemento gráfico del panel: una etiqueta para la imagen
	private JLabel img;
	private JButton btnVer, btnSolicitarE, btnEnviarRM, btnGenerarD, btnGenerarT, btnChange, btnVolver, btnEnviar;
	private JTextArea txtEntrada;
	private JTable tabla;
	private JTextField txtName, txtGmail, txtId;
	private JLabel actionTxt;
	private JScrollPane scroll, scrollP;

	public PanelHomeEspecialista() {
		setSize(1280, 720);
		setLayout(null);
		setVisible(true);

		img = new JLabel();
		ImageIcon imagenPortada2 = new ImageIcon("src/media/homeEspecialista.png");
		Image portadaRedimensionada2 = imagenPortada2.getImage().getScaledInstance(1280, 720, Image.SCALE_SMOOTH);
		img.setIcon(new ImageIcon(portadaRedimensionada2));
		img.setBounds(0, 0, 1280, 720);
		img.setVisible(true);

		actionTxt = new JLabel("Generando diagnostico", SwingConstants.CENTER);
		actionTxt.setFont(new Font("SansSerif", Font.BOLD, 30));
		actionTxt.setForeground(Color.RED);
		actionTxt.setBounds(480, 315, 380, 50);
		actionTxt.setVisible(false);

		txtEntrada = new JTextArea(10, 30);
		txtEntrada.setLineWrap(true);
		txtEntrada.setWrapStyleWord(true);
		txtEntrada.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtEntrada.setVisible(true);

		scroll = new JScrollPane(txtEntrada);
		scroll.setBounds(470, 360, 400, 200);
		scroll.setVisible(false);
		scroll.setOpaque(false);

		tabla = new JTable();
		tabla.setEnabled(true);
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabla.getTableHeader().setReorderingAllowed(true);

		scrollP = new JScrollPane(tabla);
		scrollP.setBounds(470, 360, 400, 200);
		scrollP.setVisible(true);
		scrollP.setOpaque(true);

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

		btnEnviar = new JButton("ENVIAR");
		btnEnviar.setActionCommand("ENVIARC");
		btnEnviar.setOpaque(false);
		btnEnviar.setBounds(570, 600, 200, 70);
		btnEnviar.setFont(new Font("Arial", Font.BOLD, 20));
		btnEnviar.setBackground(new Color(64, 135, 5));
		btnEnviar.setBorder(BorderFactory.createBevelBorder(1));
		btnEnviar.setVisible(false);

		btnVolver = new JButton("VOLVER");
		btnVolver.setActionCommand("VOLVERHOMEESPECIALISTA");
		btnVolver.setOpaque(true);
		btnVolver.setBounds(30, 600, 180, 70);
		btnVolver.setFont(new Font("Arial", Font.BOLD, 20));
		btnVolver.setBackground(new Color(213, 101, 49));
		btnVolver.setBorder(BorderFactory.createBevelBorder(1));

		btnVer = new JButton("VER TURNOS");
		btnVer.setActionCommand("VERTURNOS");
		btnVer.setOpaque(true);
		btnVer.setBounds(940, 100, 270, 70);
		btnVer.setFont(new Font("Arial", Font.BOLD, 20));
		btnVer.setBackground(new Color(27, 167, 161));
		btnVer.setBorder(BorderFactory.createBevelBorder(1));

		btnSolicitarE = new JButton("SOLICITAR EXAMENES");
		btnSolicitarE.setActionCommand("SOLICITARE");
		btnSolicitarE.setOpaque(true);
		btnSolicitarE.setBounds(940, 200, 270, 70);
		btnSolicitarE.setFont(new Font("Arial", Font.BOLD, 20));
		btnSolicitarE.setBackground(new Color(27, 167, 161));
		btnSolicitarE.setBorder(BorderFactory.createBevelBorder(1));

		btnEnviarRM = new JButton("ENVIAR RESULTADOS");
		btnEnviarRM.setActionCommand("ENVIARR");
		btnEnviarRM.setOpaque(true);
		btnEnviarRM.setBounds(940, 300, 270, 70);
		btnEnviarRM.setFont(new Font("Arial", Font.BOLD, 18));
		btnEnviarRM.setBackground(new Color(27, 167, 161));
		btnEnviarRM.setBorder(BorderFactory.createBevelBorder(1));

		btnGenerarD = new JButton("GENERAR DIAGNOSTICO");
		btnGenerarD.setActionCommand("GENERARD");
		btnGenerarD.setOpaque(true);
		btnGenerarD.setBounds(940, 400, 270, 70);
		btnGenerarD.setFont(new Font("Arial", Font.BOLD, 18));
		btnGenerarD.setBackground(new Color(27, 167, 161));
		btnGenerarD.setBorder(BorderFactory.createBevelBorder(1));

		btnGenerarT = new JButton("GENERAR TRATAMIENTO");
		btnGenerarT.setActionCommand("GENERART");
		btnGenerarT.setOpaque(true);
		btnGenerarT.setBounds(940, 500, 270, 70);
		btnGenerarT.setFont(new Font("Arial", Font.BOLD, 18));
		btnGenerarT.setBackground(new Color(27, 167, 161));
		btnGenerarT.setBorder(BorderFactory.createBevelBorder(1));

		btnChange = new JButton("CAMBIAR TURNO");
		btnChange.setActionCommand("CAMBIART");
		btnChange.setOpaque(true);
		btnChange.setBounds(940, 500, 270, 70);
		btnChange.setFont(new Font("Arial", Font.BOLD, 18));
		btnChange.setBackground(new Color(27, 167, 161));
		btnChange.setBorder(BorderFactory.createBevelBorder(1));

		add(btnVolver);
		add(btnVer);
		add(btnSolicitarE);
		add(btnEnviarRM);
		add(btnGenerarT);
		add(btnGenerarD);
		add(btnChange);
		add(btnEnviar);
		add(actionTxt);
		add(txtId);
		add(txtName);
		add(txtGmail);
		add(txtId);
		add(scroll);
		add(img);
		add(scrollP);
	}

	public JTable getTabla() {
		return tabla;
	}

	public void setTabla(JTable tabla) {
		this.tabla = tabla;
	}

	public JLabel getActionTxt() {
		return actionTxt;
	}

	public void setActionTxt(JLabel actionTxt) {
		this.actionTxt = actionTxt;
	}

	public JScrollPane getScrollP() {
		return scrollP;
	}

	public void setScrollP(JScrollPane scrollP) {
		this.scrollP = scrollP;
	}

	public JButton getBtnEnviar() {
		return btnEnviar;
	}

	public void setBtnEnviar(JButton btnEnviar) {
		this.btnEnviar = btnEnviar;
	}

	public JTextField getTxtName() {
		return txtName;
	}

	public void setTxtName(JTextField txtName) {
		this.txtName = txtName;
	}

	public JTextField getTxtGmail() {
		return txtGmail;
	}

	public void setTxtGmail(JTextField txtGmail) {
		this.txtGmail = txtGmail;
	}

	public JButton getBtnVer() {
		return btnVer;
	}

	public void setBtnVer(JButton btnVer) {
		this.btnVer = btnVer;
	}

	public JButton getBtnSolicitarE() {
		return btnSolicitarE;
	}

	public void setBtnSolicitarE(JButton btnSolicitarE) {
		this.btnSolicitarE = btnSolicitarE;
	}

	public JButton getBtnEnviarRM() {
		return btnEnviarRM;
	}

	public void setBtnEnviarRM(JButton btnEnviarRM) {
		this.btnEnviarRM = btnEnviarRM;
	}

	public JButton getBtnGenerarD() {
		return btnGenerarD;
	}

	public void setBtnGenerarD(JButton btnGenerarD) {
		this.btnGenerarD = btnGenerarD;
	}

	public JButton getBtnGenerarT() {
		return btnGenerarT;
	}

	public void setBtnGenerarT(JButton btnGenerarT) {
		this.btnGenerarT = btnGenerarT;
	}

	public JButton getBtnChange() {
		return btnChange;
	}

	public void setBtnChange(JButton btnChange) {
		this.btnChange = btnChange;
	}

	public JButton getBtnVolver() {
		return btnVolver;
	}

	public void setBtnVolver(JButton btnVolver) {
		this.btnVolver = btnVolver;
	}

	public JTextArea getTxtEntrada() {
		return txtEntrada;
	}

	public void setTxtEntrada(JTextArea txtEntrada) {
		this.txtEntrada = txtEntrada;
	}

	public JTextField getTxtId() {
		return txtId;
	}

	public void setTxtId(JTextField txtId) {
		this.txtId = txtId;
	}

	public JScrollPane getScroll() {
		return scroll;
	}

	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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