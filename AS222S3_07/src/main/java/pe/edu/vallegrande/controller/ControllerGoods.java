package pe.edu.vallegrande.controller;

import java.io.IOException;




import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import java.util.List;
import pe.edu.vallegrande.model.GoodsModel;
import pe.edu.vallegrande.service.impl.CrudGoodsService;

@WebServlet({ "/GoodsBuscar", "/GoodsInactivos", "/GoodsProcesar" }) // Mapea el servlet a la
																					// URL raíz
public class ControllerGoods extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CrudGoodsService goodsService = new CrudGoodsService();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		switch (path) {
		case "/GoodsBuscar":
			buscar(request, response);
			break;
		case "/GoodsInactivos":
			inactivos(request, response);
			break;
		case "/GoodsProcesar":
			procesar(request, response);
			break;

		}

	}

	private void procesar(HttpServletRequest request, HttpServletResponse response) {
		// Datos
		String accion = request.getParameter("accion");
		GoodsModel bean = new GoodsModel();
		String idStr = request.getParameter("id");
		Integer id = Integer.parseInt(idStr);

		bean.setCode(request.getParameter("code"));
		bean.setArea_name(request.getParameter("area_name"));
		bean.setDetails_goods(request.getParameter("details_goods"));
		bean.setName(request.getParameter("name"));
		bean.setDescriptions_good(request.getParameter("descriptions_good"));
		bean.setBook_value(Double.parseDouble(request.getParameter("book_value")));
		bean.setDate_entry(request.getParameter("date_entry"));
		bean.setState(request.getParameter("state"));
		// Proceso
		try {
			String code;
			switch (accion) {
			case ControllerUtil.CRUD_NUEVO:
				goodsService.insert(bean);
				break;
			case ControllerUtil.CRUD_EDITAR:
				goodsService.update(bean);
				break;
			case ControllerUtil.CRUD_ELIMINAR:
			    goodsService.delete(request.getParameter("code"));
			    break;
			case ControllerUtil.CRUD_ACTIVAR:
			    goodsService.activar(request.getParameter("code"));
			    break;



			default:
				throw new IllegalArgumentException("Unexpected value: " + accion);
			}
			ControllerUtil.responseJson(response, "Proceso ok.");
		} catch (Exception e) {
			ControllerUtil.responseJson(response, e.getMessage());
		}
	}

	private void buscar(HttpServletRequest request, HttpServletResponse response) {
		// Datos
		String nombre = request.getParameter("nombre");
		String area_nombre = request.getParameter("area_nombre");
		// Proceso
		GoodsModel model = new GoodsModel();
		model.setName(nombre);
		model.setArea_name(area_nombre);
		List<GoodsModel> lista = goodsService.get(model);
		// Convertir lista en JSON
		Gson gson = new Gson();
		String data = gson.toJson(lista);
		// Reporte
		ControllerUtil.responseJson(response, data);
	}

	private void inactivos(HttpServletRequest request, HttpServletResponse response) {
		// Datos
		String nombre = request.getParameter("nombre");
		String area_nombre = request.getParameter("area_nombre");
		// Proceso
		GoodsModel model = new GoodsModel();
		model.setName(nombre);
		model.setArea_name(area_nombre);
		List<GoodsModel> lista = goodsService.getInactive(model);
		// Convertir lista en JSON
		Gson gson = new Gson();
		String data = gson.toJson(lista);
		// Reporte
		ControllerUtil.responseJson(response, data);
	}
}