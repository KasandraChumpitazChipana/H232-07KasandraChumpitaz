package pe.edu.vallegrande.prueba.goods;

import pe.edu.vallegrande.model.GoodsModel;

import pe.edu.vallegrande.service.impl.CrudGoodsService;

public class Editar {
	public static void main(String[] args) {
		try {
			// Datos del cliente a editar
			int goodsCode = 2;
			// Consultar el cliente actual
			CrudGoodsService consultaService = new CrudGoodsService();
			GoodsModel goods = consultaService.getByCode(goodsCode);

			if (goods != null) {
				// Modificar los datos del cliente
				goods.setAmount(1);
				goods.setDetails_goods("4.0");
				goods.setName("color azul");
				goods.setDescriptions_good("2024-09-30");
				goods.setState("Alta");

				// Actualizar el cliente
				CrudGoodsService servicio = new CrudGoodsService();
				servicio.update(goods);

				// Reporte
				System.out.println("Cliente actualizado con éxito");
			} else {
				System.out.println("El cliente con ID " + goodsCode + " no existe.");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}