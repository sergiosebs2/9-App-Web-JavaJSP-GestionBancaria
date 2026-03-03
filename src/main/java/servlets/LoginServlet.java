package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio.NegocioUsuario;
import NegocioInterfaz.INegocioUsuario;

import Entidades.Cliente;
import Entidades.CuentaBancaria;
import NegocioInterfaz.INegocioCliente;
import NegocioInterfaz.INegocioCuentaBancaria;
import Negocio.NegocioCliente;
import Negocio.NegocioCuentaBancaria;           

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnIngresar") != null)
		{
			String nombreUsuario = request.getParameter("txtUsuario");
			String contrasena = request.getParameter("txtContra");
			char tipoUsuario = request.getParameter("TipoUsuario").charAt(0);
			String inicioDenegado = null;
			
			INegocioUsuario negocioUsuario = new NegocioUsuario();
			if(negocioUsuario.inicioSesion(nombreUsuario, contrasena, tipoUsuario))
			{
				request.getSession().invalidate();
				if(tipoUsuario == 'C')
				{
					INegocioCliente negocioCliente = new NegocioCliente();
					INegocioCuentaBancaria negocioCuentaBancaria = new NegocioCuentaBancaria();
					Cliente cliente = negocioCliente.buscarClientePorUsuario(nombreUsuario);
					if(cliente.getUsuario().isEstado())
					{
						ArrayList<CuentaBancaria> cuentasBancarias = negocioCuentaBancaria.buscarCuentasBancariasPorClienteAsignado(String.valueOf(cliente.getCodCliente()));
						request.getSession().setAttribute("clienteSesion", cliente);
		                request.getSession().setAttribute("nombre", cliente.getNombre());
		                request.getSession().setAttribute("apellido", cliente.getApellido());
		                request.getSession().setAttribute("cuentasBancarias", cuentasBancarias);
		                response.sendRedirect("ClienteInicio.jsp");
					}
					else {
						inicioDenegado = "DATOS INCORRECTOS, intente nuevamente.";
						request.getSession().setAttribute("inicioDenegado", inicioDenegado);
						request.getRequestDispatcher("/Login.jsp").forward(request, response);
					}
					
				}
				else if(tipoUsuario == 'A')
				{
					response.sendRedirect("InicioAdmin.jsp");
				}
			}
			else {
				inicioDenegado = "DATOS INCORRECTOS, intente nuevamente.";
				request.getSession().setAttribute("inicioDenegado", inicioDenegado);
				request.getRequestDispatcher("/Login.jsp").forward(request, response);
			}
		}
	}
}
