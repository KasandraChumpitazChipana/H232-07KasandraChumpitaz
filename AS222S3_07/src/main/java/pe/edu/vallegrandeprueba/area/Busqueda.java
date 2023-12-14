package pe.edu.vallegrandeprueba.area;

import java.util.List;


import pe.edu.vallegrande.model.AreaModel;
import pe.edu.vallegrande.service.impl.CrudAreaService;

public class Busqueda {
	public static void main(String[] args) {
		try {
			// Datos de consulta
			AreaModel model = new AreaModel();
			model.setId(2);
			model.setName("");
			// Proceso
			CrudAreaService service = new CrudAreaService();
			List<AreaModel> lista = service.get(model);
			// Reporte
			System.out.println("LISTADO");
			System.out.println("Registros: " + lista.size());
			for (AreaModel rec : lista) {
				System.out.println(rec.getId() + " - " + rec.getName());
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}