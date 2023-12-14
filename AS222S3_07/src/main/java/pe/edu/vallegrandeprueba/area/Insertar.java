package pe.edu.vallegrandeprueba.area;

import pe.edu.vallegrande.model.AreaModel;
import pe.edu.vallegrande.service.impl.CrudAreaService;

public class Insertar {
	public static void main(String[] args) {
		try {
			// Datos de consulta
			AreaModel model = new AreaModel();
			model.setName("Hogar");
			model.setName_head_area("Ropa de moda");
			// Proceso
			CrudAreaService service = new CrudAreaService();
			service.insert(model); // Llama al método insert de CrudClienteService
			// Reporte
			System.out.println("Registro grabado correctamente");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
