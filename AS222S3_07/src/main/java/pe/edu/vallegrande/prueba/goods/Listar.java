package pe.edu.vallegrande.prueba.goods;

import java.util.List;


import pe.edu.vallegrande.model.GoodsModel;
import pe.edu.vallegrande.service.impl.CrudGoodsService;

public class Listar {

	public static void main(String[] args) {
		try {
			CrudGoodsService service = new CrudGoodsService();
			List<GoodsModel> lista = service.getAll();
			System.out.println("Filas: " + lista.size());
			for (GoodsModel rec : lista) {
				System.out.println(rec.getCode() + "|" + rec.getAmount() + "|" + rec.getBook_value() + "|"
						+ rec.getDetails_goods() + "|" + rec.getBook_value() + "|" + rec.getDate_entry() + "|" + rec.getDate_depreciation());
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
