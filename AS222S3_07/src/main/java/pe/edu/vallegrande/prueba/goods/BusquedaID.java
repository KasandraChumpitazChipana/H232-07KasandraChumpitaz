package pe.edu.vallegrande.prueba.goods;

import pe.edu.vallegrande.model.AreaModel;
import pe.edu.vallegrande.model.GoodsModel;
import pe.edu.vallegrande.service.impl.CrudGoodsService;

public class BusquedaID {
	public static void main(String[] args) {
		try {
			// Datos de consulta
			String code = "EQ-002";
			// Proceso
			CrudGoodsService service = new CrudGoodsService();
			GoodsModel rec = service.getByCode(code);
			//REPORTE
			System.out.println( rec.getCode() + "|" + rec.getArea_name() + "|" + rec.getAmount()
							+ "|" + rec.getDetails_goods() + "|" + rec.getName() + "|" + rec.getDescriptions_good()+ "|" + rec.getBook_value()+ "|" + rec.getDate_entry()+ "|" + rec.getState());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
