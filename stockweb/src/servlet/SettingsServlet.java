package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import settings.ChartInterface;
import settings.ChartInterfaceDynamic;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

public class SettingsServlet extends HttpServlet {

	private static final long serialVersionUID = 5577916863342570717L;

	public SettingsServlet() {
		super();
	}

	@Override
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		Map<String, String> mapParameters = new HashMap<String, String>();
		mapParameters.put("stock", request.getParameter("stock"));
		if((request.getParameter("chart") != null) && (request.getParameter("chart").equals("dynamic"))){
			String strategyName = request.getParameter("strategyname");
			String pathname = this.getServletContext().getRealPath("/") + "resources\\templates\\";
			ChartInterfaceDynamic chartInterface = new ChartInterfaceDynamic(pathname, strategyName, mapParameters);
			out.println(chartInterface.loadSettings());
		}else{			
			String pathname = this.getServletContext().getRealPath("/") + "resources\\";
			ChartInterface chartInterface = new ChartInterface(pathname, mapParameters);
			out.println(chartInterface.loadSettings());
		}
		
		out.flush();
		out.close();		
	}

	@Override
	public void init() throws ServletException {
		// Put your code here
	}

}
