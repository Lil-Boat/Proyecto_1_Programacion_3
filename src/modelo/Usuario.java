package modelo;

public class Usuario extends Persona {

    protected int numeroSocio;
    protected int idUsuario;

    public Usuario() {
    }

    public Usuario(String nombreCompleto, int edad, String correoElectronico, int telefono, int numeroSocio, int idUsuario) {
        super(nombreCompleto, edad, correoElectronico, telefono);
        this.numeroSocio = numeroSocio;
        this.idUsuario = idUsuario;
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

    @Override
    public String toString() {
        return "Usuario{" +
                "nombreCompleto='" + nombreCompleto + '\'' +
                ", numeroSocio=" + numeroSocio +
                ", edad=" + edad +
                ", idUsuario=" + idUsuario +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", telefono=" + telefono +
                '}';
    }
}