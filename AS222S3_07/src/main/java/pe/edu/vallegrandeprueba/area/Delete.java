package pe.edu.vallegrandeprueba.area;

import pe.edu.vallegrande.service.impl.CrudAreaService;

public class Delete {
	public static void main(String[] args) {
		try {
			// Datos de consulta
			Integer id = 3;
			// Proceso
			CrudAreaService service = new CrudAreaService();
			service.delete(id);
			// Reporte
			System.out.println("Registro eliminado logicamente.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}