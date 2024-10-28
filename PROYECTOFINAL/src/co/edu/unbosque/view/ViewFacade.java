package co.edu.unbosque.view;

public class ViewFacade {

	private ViewConsole con;

	public ViewFacade() {
		con = new ViewConsole();
	}

	public ViewConsole getCon() {
		return con;
	}

	public void setCon(ViewConsole con) {
		this.con = con;
	}

}
