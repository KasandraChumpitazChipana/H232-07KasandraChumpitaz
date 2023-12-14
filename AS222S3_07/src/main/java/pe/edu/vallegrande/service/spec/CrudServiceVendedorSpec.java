package pe.edu.vallegrande.service.spec;

import java.util.List;

public interface CrudServiceVendedorSpec<T> {

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
	T getById(Integer id);

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
	void delete(Integer id);

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

	void activar(Integer id);

	/**
	 *
	 * @param id
	 */
}