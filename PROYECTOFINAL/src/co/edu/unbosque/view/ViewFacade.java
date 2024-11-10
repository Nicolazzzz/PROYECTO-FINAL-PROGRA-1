package co.edu.unbosque.view;

/**
 * La clase ViewFacade actúa como un intermediario entre la vista de consola (ViewConsole) 
 * y la ventana principal (VentanaPrincipal). Su propósito es proporcionar acceso a 
 * ambas vistas a través de una única interfaz, simplificando la interacción con la interfaz de usuario.
 * @author Nicolas Zambrano
 * @version 1.0
 */
public class ViewFacade {

    private ViewConsole con;
    private VentanaPrincipal vp;

    /**
     * Constructor que inicializa las vistas de consola (ViewConsole) y la ventana principal (VentanaPrincipal).
     */
    public ViewFacade() {
        con = new ViewConsole();
        vp = new VentanaPrincipal();
    }

    /**
     * Obtiene la vista de consola (ViewConsole).
     * 
     * @return El objeto de tipo ViewConsole.
     */
    public ViewConsole getCon() {
        return con;
    }

    /**
     * Establece la vista de consola (ViewConsole).
     * 
     * @param con El objeto de tipo ViewConsole a establecer.
     */
    public void setCon(ViewConsole con) {
        this.con = con;
    }

    /**
     * Obtiene la ventana principal (VentanaPrincipal).
     * 
     * @return El objeto de tipo VentanaPrincipal.
     */
    public VentanaPrincipal getVp() {
        return vp;
    }

    /**
     * Establece la ventana principal (VentanaPrincipal).
     * 
     * @param vp El objeto de tipo VentanaPrincipal a establecer.
     */
    public void setVp(VentanaPrincipal vp) {
        this.vp = vp;
    }

}