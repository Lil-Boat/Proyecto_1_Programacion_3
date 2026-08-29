package modelo;

public class Usuario extends Persona {

    protected int numeroUsuario;
    protected int idUsuario;
    protected String contactoEmergencia;
    protected String condicionesMedicas;

    public Usuario() {
    }

    public Usuario(String nombreCompleto, int edad, String correoElectronico, int telefono, int numeroUsuario, int idUsuario) {
        super(nombreCompleto, edad, correoElectronico, telefono);
        this.numeroUsuario = numeroUsuario;
        this.idUsuario = idUsuario;
    }

    public Usuario(int idUsuario, String nombreCompleto, int telefono, String correoElectronico, String contactoEmergencia, String condicionesMedicas) {
        super();
        this.idUsuario = idUsuario;
        this.numeroUsuario = idUsuario;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.contactoEmergencia = contactoEmergencia;
        this.condicionesMedicas = condicionesMedicas;
    }

    public void setNumeroSocio(int numeroUsuario) {
        this.numeroUsuario = numeroUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getNumeroUsuario() {
        return numeroUsuario;
    }

    public String getContactoEmergencia() {
        return contactoEmergencia;
    }

    public void setContactoEmergencia(String contactoEmergencia) {
        this.contactoEmergencia = contactoEmergencia;
    }

    public String getCondicionesMedicas() {
        return condicionesMedicas;
    }

    public void setCondicionesMedicas(String condicionesMedicas) {
        this.condicionesMedicas = condicionesMedicas;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombreCompleto='" + nombreCompleto + '\'' +
                ", numeroSocio=" + numeroUsuario +
                ", edad=" + edad +
                ", idUsuario=" + idUsuario +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", telefono='" + telefono + '\'' +
                ", contactoEmergencia='" + contactoEmergencia + '\'' +
                ", condicionesMedicas='" + condicionesMedicas + '\'' +
                '}';
    }
}