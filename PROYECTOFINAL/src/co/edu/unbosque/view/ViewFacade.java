package co.edu.unbosque.view;

public class ViewFacade {

	private ViewConsole con;
	private VentanaPrincipal vp;

	public ViewFacade() {
		con = new ViewConsole();
		vp = new VentanaPrincipal();
	}

	public ViewConsole getCon() {
		return con;
	}

	public void setCon(ViewConsole con) {
		this.con = con;
	}

	public VentanaPrincipal getVp() {
		return vp;
	}

	public void setVp(VentanaPrincipal vp) {
		this.vp = vp;
	}

}
