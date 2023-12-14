package pe.edu.vallegrande.service.spec;

import java.util.List;

public interface CrudServiceGoodsSpec<T> {

	/**
	 * Consulta todos los registros de la tabla.
	 *
	 * @return Retorna una lista de objetos.
	 */
	List<T> getAll();

	/**
	 * Permite consultar un registro especifico de la tabla en base de su ID.
	 *
	 * @param id El ID del registro a consultas.
	 * @return Retorna el registro segun el ID.
	 */
	T getByCode(String code);

	/**
	 * Consulta registros en base a un filtro.
	 *
	 * @param bean
	 * @return RETORNA UNA LISTA
	 */
	List<T> get(T bean);

	/**
	 *
	 * @param bean
	 * @return
	 */
	T insert(T bean);

	/**
	 *
	 * @param bean
	 * @return
	 */
	T update(T bean);

	/**
	 *
	 * @param id
	 */
	void delete(String code);

	/**
	 *
	 * @param id
	 */

	List<T> getAllInac();

	/**
	 *
	 * @param id
	 */
	List<T> getInactive(T bean);

	/**
	 *
	 * @param bean
	 * @return
	 */

	void activar(String code);

	/**
	 *
	 * @param id
	 */
}