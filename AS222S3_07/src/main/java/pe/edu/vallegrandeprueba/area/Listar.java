package pe.edu.vallegrandeprueba.area;

import java.util.List;


import pe.edu.vallegrande.model.AreaModel;
import pe.edu.vallegrande.service.impl.CrudAreaService;

public class Listar {

	public static void main(String[] args) {
		try {
			CrudAreaService service = new CrudAreaService();
			List<AreaModel> lista = service.getAll();
			System.out.println("Filas: " + lista.size());
			for (AreaModel rec : lista) {
				System.out.println(rec.getId() + "|" + rec.getName() + "|" + rec.getName_head_area()+ "|" + rec.getLastname_head_area());
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
