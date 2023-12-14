package pe.edu.vallegrandeprueba.area;

import pe.edu.vallegrande.service.impl.CrudAreaService;

public class Activar {
	public static void main(String[] args) {
		try {
			// Datos de consulta
			Integer id = 1;
			// Proceso
			CrudAreaService service = new CrudAreaService();
			service.activar(id);
			// Reporte
			System.out.println("Registro activado logicamente.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}