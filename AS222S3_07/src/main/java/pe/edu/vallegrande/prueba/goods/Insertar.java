package pe.edu.vallegrande.prueba.goods;

import pe.edu.vallegrande.model.GoodsModel;

import pe.edu.vallegrande.service.impl.CrudGoodsService;

public class Insertar {
	public static void main(String[] args) {
		try {
			// Datos de consulta
			GoodsModel model = new GoodsModel();
			model.setCode("EQ-002");
			model.setDetails_goods("Mesa color rojo");
			model.setName("l");
			model.setState("Alta");
			// Proceso
			CrudGoodsService service = new CrudGoodsService();
			service.insert(model); // Llama al método insert de CrudClienteService
			// Reporte
			System.out.println("Registro grabado correctamente");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
