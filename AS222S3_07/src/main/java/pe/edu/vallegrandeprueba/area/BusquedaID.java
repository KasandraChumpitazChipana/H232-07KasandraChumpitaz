package pe.edu.vallegrandeprueba.area;

import pe.edu.vallegrande.model.AreaModel;
import pe.edu.vallegrande.service.impl.CrudAreaService;

public class BusquedaID {
	public static void main(String[] args) {
		try {
			// Datos de consulta
			Integer id = 2;
			// Proceso
			CrudAreaService service = new CrudAreaService();
			AreaModel rec = service.getById(id);
			// Reporte
			System.out.println(rec.getId() + " - " + rec.getName());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
