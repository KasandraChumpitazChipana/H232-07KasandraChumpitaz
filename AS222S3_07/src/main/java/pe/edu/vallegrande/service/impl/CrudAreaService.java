package pe.edu.vallegrande.service.impl;

import java.sql.Connection;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.vallegrande.db.AccesoDB;
import pe.edu.vallegrande.model.AreaModel;
import pe.edu.vallegrande.model.GoodsModel;
import pe.edu.vallegrande.service.spec.CrudServiceAreaSpec;

public class CrudAreaService implements CrudServiceAreaSpec<AreaModel> {
	// LISTAR LOS Categoria de productos ACTIVOS
	@Override
	public List<AreaModel> getAll() {
		List<AreaModel> lista = new ArrayList<>();
		Connection cn = null;
		AreaModel rec = null;
		try {
			cn = AccesoDB.getConnection();
			String sql = "SELECT id, name, name_head_area, lastname_head_area FROM area";
			PreparedStatement pstm = cn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				rec = new AreaModel();
				rec.setId(rs.getInt("id"));
				rec.setName(rs.getString("name"));
				rec.setName_head_area(rs.getString("name_head_area"));
				lista.add(rec);
			}
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("Error en el proceso");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return lista;
	}

	// listar inactivos
	@Override
	public List<AreaModel> getAllInac() {
		List<AreaModel> lista = new ArrayList<>();
		Connection cn = null;
		AreaModel rec = null;
		try {
			cn = AccesoDB.getConnection();
			String sql = "SELECT id, name, name_head_area, lastname_head_area From area " ;
			PreparedStatement pstm = cn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				rec = new AreaModel();
				rec.setId(rs.getInt("id"));
				rec.setName(rs.getString("name"));
				rec.setName_head_area(rs.getString("name_head_area"));
				//rec.setActive(rs.getString("active"));
				lista.add(rec);
			}
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("Error en el proceso");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return lista;
	}

	@Override
	public AreaModel getById(Integer id) {
		// preparando los datos
		Connection cn = null;
		AreaModel bean = null;
		// proceso
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id, name, name_head_area, lastname_head_area from area ";
			sql += "where id = ?";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				bean = new AreaModel();
				bean.setId(rs.getInt("id"));
				bean.setName(rs.getString("name"));
				bean.setName_head_area(rs.getString("name_head_area"));
				bean.setLastname_head_area(rs.getString("lastname_head_area"));
			}
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("Error en el proceso");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return bean;
	}

	@Override
	public List<AreaModel> get(AreaModel bean) {
		// preparando los datos
		Integer id = bean.getId();
		String idString = (id != null) ? "%" + id.toString().trim() + "%" : "%%";
		String name = bean.getName() != null ? "%" + bean.getName().trim() + "%" : "%%";
		List<AreaModel> lista = new ArrayList<>();
		Connection cn = null;
		AreaModel rec = null;
		// proceso
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id, name, name_head_area, lastname_head_area from area ";
			sql += "where id like ? and name like ?";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, idString);
			pstm.setString(2, name);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				rec = new AreaModel();
				rec.setId(rs.getInt("id"));
				rec.setName(rs.getString("name"));
				rec.setName_head_area(rs.getString("name_head_area"));
				rec.setLastname_head_area(rs.getString("lastname_head_area"));
				lista.add(rec);
			}
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("Error en el proceso");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return lista;
	}

	@Override
	public AreaModel insert(AreaModel bean) {
		// Variables
		Integer id;
		Connection cn = null;
		PreparedStatement pstm;
		ResultSet rs;
		String sql;
		// Proceso
		try {
			// Inicio de la TX
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// Insertar registro
			sql = "INSERT INTO area(name, name_head_area, lastname_head_area)VALUES(?,?)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getName());
			pstm.setString(2, bean.getName_head_area());
			pstm.setString(3, bean.getLastname_head_area());
			pstm.executeUpdate();
			// obteniendo el id
			sql = "SELECT @@IDENTITY id";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			rs.next();
			id = rs.getInt("id");
			bean.setId(id);
			// Fin de la TX
			cn.commit();
		} catch (SQLException e) {
			try {
				cn.rollback();
			} catch (Exception e2) {
			}
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("Error en el proceso");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		// Reporte
		return bean;
	}

	@Override
	public AreaModel update(AreaModel bean) {
		// Variables
		Connection cn = null;
		PreparedStatement pstm;
		String sql;
		// Proceso
		try {
			// Inicio de la TX
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// Actualizar registro
			sql = "UPDATE area SET name = ?,name_head_area = ?, lastname_head_area = ? WHERE id = ?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getName());
			pstm.setString(2, bean.getName_head_area());
			pstm.setString(3, bean.getLastname_head_area());
			pstm.setInt(4, bean.getId());
			int filas = pstm.executeUpdate();
			pstm.close();
			if (filas == 0) {
				throw new SQLException("ID no existe");
			}
			// Fin de la TX
			cn.commit();
		} catch (SQLException e) {
			try {
				cn.rollback();
			} catch (Exception e2) {
			}
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("Error en el proceso");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		// Reporte: Devolver el objeto actualizado
		return bean;
	}

	@Override
	public void delete(Integer id) {
		// Variables
		Connection cn = null;
		PreparedStatement pstm;
		String sql;
		int filas;
		// Proceso
		try {
			// Inicio de la TX
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// Insertar registro
			sql = "UPDATE area SET active = 'I' WHERE id =?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, id);
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas == 0) {
				throw new SQLException("ID no existe");
			}
			// Fin de la TX
			cn.commit();
		} catch (SQLException e) {
			try {
				cn.rollback();
			} catch (Exception e2) {
			}
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("Error en el proceso");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
	}

//activar cliente
	@Override
	public void activar(Integer id) {
		// Variables
		Connection cn = null;
		PreparedStatement pstm;
		String sql;
		int filas;
		// Proceso
		try {
			// Inicio de la TX
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// Insertar registro
			sql = "UPDATE area SET active = 'A' WHERE id =?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, id);
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas == 0) {
				throw new SQLException("ID no existe");
			}
			// Fin de la TX
			cn.commit();
		} catch (SQLException e) {
			try {
				cn.rollback();
			} catch (Exception e2) {
			}
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("Error en el proceso");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public List<AreaModel> getInactive(AreaModel bean) {
		List<AreaModel> lista = new ArrayList<>();
		Connection cn = null;
		AreaModel rec = null;
		try {
			cn = AccesoDB.getConnection();
			String sql = "SELECT id, name, getName_head_area, lastname_head_area, active " + "FROM category_product WHERE active='I' ";

			// Agregar condiciones solo si id no es nulo
			if (bean.getId() != null) {
				sql += " AND id LIKE ? ";
			}
			if (bean.getName() != null) {
				sql += " AND name LIKE ? ";
			}

			PreparedStatement pstm = cn.prepareStatement(sql);

			// Establecer parámetros solo si id no es nulo
			int parameterIndex = 1;
			if (bean.getId() != null) {
				pstm.setString(parameterIndex++, "%" + bean.getId() + "%");
			}
			if (bean.getName() != null) {
				pstm.setString(parameterIndex++, "%" + bean.getName() + "%");
			}

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				rec = new AreaModel();
				rec.setId(rs.getInt("id"));
				rec.setName(rs.getString("name"));
				rec.setName_head_area(rs.getString("name_head_area"));
				rec.setLastname_head_area(rs.getString("lastname_head_area"));
				//rec.setActive(rs.getString("active"));
				lista.add(rec);
			}
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("Error en el proceso");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return lista;
	}


}
