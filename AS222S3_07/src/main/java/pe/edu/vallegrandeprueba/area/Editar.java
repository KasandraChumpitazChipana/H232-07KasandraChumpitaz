package pe.edu.vallegrandeprueba.area;

import pe.edu.vallegrande.model.AreaModel;
import pe.edu.vallegrande.service.impl.CrudAreaService;

public class Editar {
	public static void main(String[] args) {
		try {
			// Datos del cliente a editar
			int clienteId = 2;
			// Consultar el cliente actual
			CrudAreaService consultaService = new CrudAreaService();
			AreaModel cliente = consultaService.getById(clienteId);

			if (cliente != null) {
				// Modificar los datos del cliente
				cliente.setName("j");
				cliente.setName_head_area(" name_head_area");

				// Actualizar el cliente
				CrudAreaService servicio = new CrudAreaService();
				servicio.update(cliente);

				// Reporte
				System.out.println("Cliente actualizado con éxito");
			} else {
				System.out.println("El cliente con ID " + clienteId + " no existe.");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}