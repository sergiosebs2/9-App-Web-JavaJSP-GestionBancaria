package Entidades;
import java.time.LocalDateTime;
import java.util.Date;

public class Cliente {
	private int codCliente;
    private Usuario usuario;
    private Nacionalidad nacionalidad;
    private Provincia provincia;
    private Localidad localidad;
    private String dni;
    private String cuil;
    private String nombre;
    private String apellido;
    private char sexo;
    private String direccion;
    private Date fechaNacimiento;
    private String telefono;
    private String correoElectronico;
    private boolean estado;
    private LocalDateTime fechaDadoAlta;
    
	public Cliente() {
		codCliente = 0;
		usuario = new Usuario();
		nacionalidad = new Nacionalidad();
		provincia = new Provincia();
		localidad = new Localidad();
		dni = "";
		cuil = "";
		nombre = "";
		apellido = "";
		sexo = 'x';
		direccion = "";
		fechaNacimiento = null;
		telefono = "";
		correoElectronico = "";
		estado = false;
		fechaDadoAlta = null;
	}
	
	public int getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Nacionalidad getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(Nacionalidad nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getCuil() {
		return cuil;
	}
	public void setCuil(String cuil) {
		this.cuil = cuil;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public LocalDateTime getFechaDadoAlta() {
		return fechaDadoAlta;
	}
	public void setFechaDadoAlta(LocalDateTime fechaDadoAlta) {
		this.fechaDadoAlta = fechaDadoAlta;
	}
    
}
