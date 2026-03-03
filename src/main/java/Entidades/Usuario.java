package Entidades;

public class Usuario {
	private int idUsuario;
	private TipoUsuario tipoUsuario;
	private String nombreUsuario;
	private String contrasena;
	private boolean estado;
	private int clienteAsociado;
	public int getClienteAsociado() {
			return clienteAsociado;
		}

		public void setClienteAsociado(int clienteAsociado) {
			this.clienteAsociado = clienteAsociado;
		}

	
	public Usuario()
	{
		idUsuario = 0;
		tipoUsuario = new TipoUsuario();
		nombreUsuario = "";
		contrasena = "";
		estado = false;
	}
	
	public Usuario(int idUsuario, TipoUsuario TipoUsuario, String nombreUsuario, String constrasena, boolean estado) {
		this.idUsuario = idUsuario;
		this.tipoUsuario = TipoUsuario;
		this.nombreUsuario = nombreUsuario;
		this.contrasena = constrasena;
		this.estado = estado;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}
