package pe.edu.vallegrande.prueba.goods;

import pe.edu.vallegrande.service.impl.CrudGoodsService;

public class Delete {
	public static void main(String[] args) {
		try {
			// Datos de consulta
			String code = "EQ-002";
			// Proceso
			CrudGoodsService service = new CrudGoodsService();
			service.delete(code);
			// Reporte
			System.out.println("Registro eliminado logicamente.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}