package pe.edu.vallegrande.controller;

import java.io.IOException;






import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import java.util.List;
import pe.edu.vallegrande.model.AreaModel;
import pe.edu.vallegrande.service.impl.CrudAreaService;

@WebServlet({ "/AreaBuscar", "/AreaInactivos", "/AreaProcesar" }) // Mapea el servlet a la
																					// URL raíz
public class ControllerArea extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CrudAreaService areaService = new CrudAreaService();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		switch (path) {
		case "/AreaBuscar":
			buscarArea(request, response);
			break;
		case "/AreaProcesar":
			procesar(request, response);
			break;

		}

	}

	private void procesar(HttpServletRequest request, HttpServletResponse response) {
		// Datos
		String accion = request.getParameter("accion");
		AreaModel bean = new AreaModel();
		String idStr = request.getParameter("id");
		Integer id = Integer.parseInt(idStr);

		bean.setName(request.getParameter("name"));
		bean.setName_head_area(request.getParameter("name_head_area"));
		bean.setLastname_head_area(request.getParameter("lastname_head_area"));
		// Proceso
		try {
			switch (accion) {
			case ControllerUtil.CRUD_NUEVO:
				areaService.insert(bean);
				break;
			case ControllerUtil.CRUD_EDITAR:
				areaService.update(bean);
				break;
			case ControllerUtil.CRUD_ELIMINAR:
				areaService.delete(id);
				break;
			case ControllerUtil.CRUD_ACTIVAR:
				areaService.activar(id);
				break;

			default:
				throw new IllegalArgumentException("Unexpected value: " + accion);
			}
			ControllerUtil.responseJson(response, "Proceso ok.");
		} catch (Exception e) {
			ControllerUtil.responseJson(response, e.getMessage());
		}
	}

	private void buscarArea(HttpServletRequest request, HttpServletResponse response) {
		// Datos
		String idStr = request.getParameter("id");
		Integer id = null;
		if (idStr != null && !idStr.isEmpty()) {
			id = Integer.parseInt(idStr);
		}
		String nombre = request.getParameter("nombre");
		// Proceso
		AreaModel model = new AreaModel();
		model.setId(id);
		model.setName(nombre);
		List<AreaModel> lista = areaService.get(model);
		// Convertir lista en JSON
		Gson gson = new Gson();
		String data = gson.toJson(lista);
		// Reporte
		ControllerUtil.responseJson(response, data);
	}


}