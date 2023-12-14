package pe.edu.vallegrande.prueba.goods;

import java.util.List;


import pe.edu.vallegrande.model.GoodsModel;
import pe.edu.vallegrande.service.impl.CrudGoodsService;

public class ListarInactivos {

	public static void main(String[] args) {
		try {
			CrudGoodsService service = new CrudGoodsService();
			List<GoodsModel> lista = service.getAllInac();
			System.out.println("Filas: "+ lista.size());
			for (GoodsModel rec : lista) {
				System.out.println(rec.getCode() + "|" + rec.getAmount() + "|" + rec.getDetails_goods() + "|"
						+ rec.getBook_value() + "|" + rec.getDate_entry() + "|" + rec.getDate_depreciation() + "|" + rec.getState());
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}