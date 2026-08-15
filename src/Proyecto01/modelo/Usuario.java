package Proyecto01.modelo;

public class Usuario extends Persona {

    protected int numeroSocio;
    protected int idUsuario;

    // Indica si el socio tiene su pago al día (true) o presenta morosidad (false)
    protected boolean pagoAlDia;

    public Usuario() {
    }

    public Usuario(String nombreCompleto, int edad, String correoElectronico, int telefono, int numeroSocio, int idUsuario) {
        super(nombreCompleto, edad, correoElectronico, telefono);
        this.numeroSocio = numeroSocio;
        this.idUsuario = idUsuario;
    }

    public Usuario(String nombreCompleto, int edad, String correoElectronico, int telefono, int numeroSocio, int idUsuario, boolean pagoAlDia) {
        super(nombreCompleto, edad, correoElectronico, telefono);
        this.numeroSocio = numeroSocio;
        this.idUsuario = idUsuario;
        this.pagoAlDia = pagoAlDia;
    }

    public void setNumeroSocio(int numeroSocio) {
        this.numeroSocio = numeroSocio;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getNumeroSocio() {
        return numeroSocio;
    }

    public boolean isPagoAlDia() {
        return pagoAlDia;
    }

    public void setPagoAlDia(boolean pagoAlDia) {
        this.pagoAlDia = pagoAlDia;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombreCompleto='" + nombreCompleto + '\'' +
                ", numeroSocio=" + numeroSocio +
                ", edad=" + edad +
                ", idUsuario=" + idUsuario +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", telefono=" + telefono +
                ", pagoAlDia=" + pagoAlDia +
                '}';
    }
}