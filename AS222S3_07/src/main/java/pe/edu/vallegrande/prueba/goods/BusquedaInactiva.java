package pe.edu.vallegrande.prueba.goods;

import java.util.List;



import pe.edu.vallegrande.model.GoodsModel;
import pe.edu.vallegrande.service.impl.CrudGoodsService;

public class BusquedaInactiva {
	public static void main(String[] args) {
		try {
			// Datos de consulta
			GoodsModel model = new GoodsModel();
			model.setCode("");
			model.setArea_name(".");
			// Proceso
			CrudGoodsService service = new CrudGoodsService();
			List<GoodsModel> lista = service.getInactive(model);
			// Reporte
			System.out.println("LISTADO");
			System.out.println("Registros: " + lista.size());
			for (GoodsModel rec : lista) {
				System.out.println(rec.getCode() + "|" + rec.getArea_name() + "|" + rec.getAmount()
								+ "|" + rec.getDetails_goods() + "|" + rec.getName() + "|" + rec.getDescriptions_good()+ 
								"|" + rec.getBook_value()+ "|" + rec.getDate_entry()+ "|" + rec.getState());
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}