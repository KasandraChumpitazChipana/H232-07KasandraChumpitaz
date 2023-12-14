package pe.edu.vallegrande.model;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsModel {
	private String code;
	private String area_name;
	private Integer amount;
	private String details_goods;
	private String name;
	private String descriptions_good;
	private Double book_value;
	private String date_entry;
	private String date_depreciation;
	private String state;
}
