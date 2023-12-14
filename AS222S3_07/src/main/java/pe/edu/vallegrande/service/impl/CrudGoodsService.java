package pe.edu.vallegrande.service.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.vallegrande.db.AccesoDB;
import pe.edu.vallegrande.model.GoodsModel;
import pe.edu.vallegrande.service.spec.CrudServiceGoodsSpec;

public class CrudGoodsService implements CrudServiceGoodsSpec<GoodsModel> {
	// LISTAR LOS CLIENTES ACTIVOS
	@Override
	public List<GoodsModel> getAll() {
		List<GoodsModel> lista = new ArrayList<>();
		Connection cn = null;
		GoodsModel rec = null;
		try {
			cn = AccesoDB.getConnection();
			String sql = "SELECT g.code, g.amount, g.details_goods, a.name ,d.descriptions_good , g.book_value, g.date_entry, g.date_depreciation\r\n"
					+ "FROM goods g\r\n" + "JOIN  \r\n" + "area a ON g.area_id = a.id  \r\n" + "join \r\n"
					+ "depreciation d on g.depreciation_id = d.id\r\n" + "WHERE g.state='ALTA'ORDER BY details_goods";
			PreparedStatement pstm = cn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				rec = new GoodsModel();
				rec.setCode(rs.getString("code"));
				rec.setAmount(rs.getInt("amount"));
				rec.setDetails_goods(rs.getString("details_goods"));
				rec.setName(rs.getString("name"));
				rec.setDescriptions_good(rs.getString("descriptions_good"));
				rec.setBook_value(rs.getDouble("book_value"));
				rec.setDate_entry(rs.getString("date_entry"));
				rec.setDate_depreciation(rs.getString("date_depreciation"));
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
	public GoodsModel getByCode(String goodsCode) {
		// Preparando los datos
		Connection cn = null;
		GoodsModel bean = null;

		// Proceso
		try {
			cn = AccesoDB.getConnection();
			String sql = "SELECT g.code, g.amount, g.details_goods, a.name ,d.descriptions_good , g.book_value, g.date_entry, g.date_depreciation, g.state\r\n"
					+ "FROM goods g\r\n" + "JOIN  \r\n" + "area a ON g.area_id = a.id  \r\n" + "join \r\n"
					+ "depreciation d on g.depreciation_id = d.id\r\n" + "WHERE g.state='ALTA' AND g.code = ?";

			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, goodsCode);
			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {
				bean = new GoodsModel();
				bean.setCode(rs.getString("code"));
				bean.setAmount(rs.getInt("amount"));
				bean.setDetails_goods(rs.getString("details_goods"));
				bean.setName(rs.getString("name"));
				bean.setDescriptions_good(rs.getString("descriptions_good"));
				bean.setBook_value(rs.getDouble("book_value"));
				bean.setDate_entry(rs.getString("date_entry"));
				bean.setDate_depreciation(rs.getString("date_depreciation"));
				bean.setState(rs.getString("state"));
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
				// Manejar la excepción, si es necesario
			}
		}
		return bean;
	}

	@Override
	public List<GoodsModel> get(GoodsModel bean) {
		// Preparando los datos
		String code = bean.getCode() != null ? "%" + bean.getCode().trim() + "%" : "%%";
		String name = bean.getName() != null ? "%" + bean.getName().trim() + "%" : "%%";
		List<GoodsModel> lista = new ArrayList<>();
		Connection cn = null;
		GoodsModel rec = null;

		// Proceso
		try {
			cn = AccesoDB.getConnection();
			String sql = "SELECT g.code, g.amount, g.details_goods, a.name ,d.descriptions_good, g.book_value, g.date_entry, g.date_depreciation\r\n"
					+ "FROM goods g\r\n" + "JOIN  \r\n" + "area a ON g.area_id = a.id  \r\n" + "join \r\n"
					+ "depreciation d on g.depreciation_id = d.id\r\n"
					+ "WHERE g.state='ALTA' AND g.code LIKE ? AND a.name LIKE ?";

			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, code);
			pstm.setString(2, name);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				rec = new GoodsModel();
				rec.setCode(rs.getString("code"));
				rec.setAmount(rs.getInt("amount"));
				rec.setDetails_goods(rs.getString("details_goods"));
				rec.setName(rs.getString("name"));
				rec.setDescriptions_good(rs.getString("descriptions_good"));
				rec.setBook_value(rs.getDouble("book_value"));
				rec.setDate_entry(rs.getString("date_entry"));
				rec.setDate_depreciation(rs.getString("date_depreciation"));
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
				// Manejar la excepción, si es necesario
			}
		}
		return lista;
	}

	@Override
	public GoodsModel insert(GoodsModel bean) {
		// Variables
		String code;
		Connection cn = null;
		PreparedStatement pstm;
		ResultSet rs;
		String sql;
		// Proceso
		try {
			// Inicio de la TX
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);

			// Obtener el ID de la categoría a partir del nombre
			int areaId = getAreaIdByName(cn, bean.getName());
			int depreciacionId = getdepreciacionIdByName(cn, bean.getDescriptions_good());

			sql = "INSERT INTO goods(code, amount, details_goods, book_value, depreciation_id, area_id, state)\r\n"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getCode()); // Asumiendo que el código es una cadena
			pstm.setInt(2, bean.getAmount());
			pstm.setString(3, bean.getDetails_goods());
			pstm.setDouble(4, bean.getBook_value());
			pstm.setInt(5, depreciacionId);
			pstm.setInt(6, areaId);
			pstm.setString(7, bean.getState());

			// Usamos el ID de la categoría en lugar del nombre
			pstm.executeUpdate();

			// obteniendo el id
			sql = "SELECT @@IDENTITY code";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			rs.next();
			code = String.valueOf(rs.getInt("code"));
			bean.setCode(code);
			// Fin de la TX
			cn.commit();
		} catch (SQLException e) {
			try {
				cn.rollback();
			} catch (Exception e2) {
				// Manejar la excepción, si es necesario
			}
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("Error en el proceso");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
				// Manejar la excepción, si es necesario
			}
		}
		// Reporte
		return bean;
	}

	// Método para obtener el ID de la categoría a partir del nombre
	private int getAreaIdByName(Connection cn, String AreaName) throws SQLException {
		int categoryId = 0; // Suponiendo que el ID no puede ser 0; ajusta según tu lógica

		String getAreaIDSQL = "SELECT id FROM area WHERE name = ?";
		try (PreparedStatement getAreaIDStatement = cn.prepareStatement(getAreaIDSQL)) {
			getAreaIDStatement.setString(1, AreaName);

			try (ResultSet categoryIDResultSet = getAreaIDStatement.executeQuery()) {
				if (categoryIDResultSet.next()) {
					categoryId = categoryIDResultSet.getInt("id");
				}
			}
		}

		return categoryId;
	}

	// Método para obtener el ID de la categoría a partir del nombre
	private int getdepreciacionIdByName(Connection cn, String AreaName) throws SQLException {
		int categoryId = 0; // Suponiendo que el ID no puede ser 0; ajusta según tu lógica

		String getAreaIDSQL = "SELECT id FROM depreciation WHERE descriptions_good = ?";
		try (PreparedStatement getAreaIDStatement = cn.prepareStatement(getAreaIDSQL)) {
			getAreaIDStatement.setString(1, AreaName);

			try (ResultSet categoryIDResultSet = getAreaIDStatement.executeQuery()) {
				if (categoryIDResultSet.next()) {
					categoryId = categoryIDResultSet.getInt("id");
				}
			}
		}

		return categoryId;
	}

	@Override
	public GoodsModel update(GoodsModel bean) {
		// Variables
		Connection cn = null;
		PreparedStatement pstm;
		String sql;

		// Proceso
		try {
			// Inicio de la TX
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);

			// Obtener el ID de la categoría a partir del nombre
			int areaId = getAreaIdByName(cn, bean.getName());
			int depreciacionId = getdepreciacionIdByName(cn, bean.getDescriptions_good());

			// Actualizar registro
			sql = "UPDATE goods SET  amount = ?, details_goods = ?, book_value = ?, depreciation_id = ?, area_id = ?, state = ? WHERE code = ?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, bean.getAmount());
			pstm.setString(2, bean.getDetails_goods());
			pstm.setDouble(3, bean.getBook_value());
			pstm.setInt(4, depreciacionId);
			pstm.setInt(5, areaId);
			pstm.setString(6, bean.getState());
			pstm.setString(7, bean.getCode());

			int filas = pstm.executeUpdate();
			pstm.close();

			if (filas == 0) {
				throw new SQLException("CODE no existe");
			}

			// Fin de la TX
			cn.commit();
		} catch (SQLException e) {
			try {
				cn.rollback();
			} catch (Exception e2) {
				// Manejar la excepción, si es necesario
			}
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("Error en el proceso");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
				// Manejar la excepción, si es necesario
			}
		}

		// Reporte: Devolver el objeto actualizado
		return bean;
	}

	@Override
	public void delete(String code) {
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
			sql = "UPDATE goods SET state = 'BAJA' WHERE code =?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, code);
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas == 0) {
				throw new SQLException("CODE no existe");
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
	public List<GoodsModel> getAllInac() {
		List<GoodsModel> lista = new ArrayList<>();
		Connection cn = null;
		GoodsModel rec = null;
		try {
			cn = AccesoDB.getConnection();
			String sql = "SELECT g.code, g.amount, g.details_goods, a.name ,d.descriptions_good , g.book_value, g.date_entry, g.date_depreciation\r\n"
					+ "FROM goods g\r\n" + "JOIN  \r\n" + "area a ON g.area_id = a.id  \r\n" + "join \r\n"
					+ "depreciation d on g.depreciation_id = d.id\r\n" + "WHERE g.state='BAJA'ORDER BY details_goods";
			PreparedStatement pstm = cn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				rec = new GoodsModel();
				rec.setCode(rs.getString("code"));
				rec.setAmount(rs.getInt("amount"));
				rec.setDescriptions_good(rs.getString("descriptions_good"));
				rec.setName(rs.getString("name"));
				rec.setDescriptions_good(rs.getString("descriptions_good"));
				rec.setBook_value(rs.getDouble("book_value"));
				rec.setDate_entry(rs.getString("date_entry"));
				rec.setDate_depreciation(rs.getString("date_depreciation"));
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
	public List<GoodsModel> getInactive(GoodsModel bean) {
		// Preparando los datos
		String code = bean.getCode() != null ? "%" + bean.getCode().trim() + "%" : "%%";
		String name = bean.getName() != null ? "%" + bean.getName().trim() + "%" : "%%";
		List<GoodsModel> lista = new ArrayList<>();
		Connection cn = null;
		GoodsModel rec = null;

		// Proceso
		try {
			cn = AccesoDB.getConnection();
			String sql = "SELECT g.code, g.amount, g.details_goods, a.name ,d.descriptions_good, g.book_value, g.date_entry, g.date_depreciation\r\n"
					+ "FROM goods g\r\n" + "JOIN  \r\n" + "area a ON g.area_id = a.id  \r\n" + "join \r\n"
					+ "depreciation d on g.depreciation_id = d.id\r\n"
					+ "WHERE g.state='BAJA' AND g.code LIKE ? AND a.name LIKE ?";

			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, code);
			pstm.setString(2, name);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				rec = new GoodsModel();
				rec.setCode(rs.getString("code"));
				rec.setAmount(rs.getInt("amount"));
				rec.setDetails_goods(rs.getString("details_goods"));
				rec.setName(rs.getString("name"));
				rec.setDescriptions_good(rs.getString("descriptions_good"));
				rec.setBook_value(rs.getDouble("book_value"));
				rec.setDate_entry(rs.getString("date_entry"));
				rec.setDate_depreciation(rs.getString("date_depreciation"));
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
				// Manejar la excepción, si es necesario
			}
		}
		return lista;
	}

	@Override
	public void activar(String code) {
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
			sql = "UPDATE goods SET state = 'ALTA' WHERE code =?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, code);
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas == 0) {
				throw new SQLException("CODE no existe");
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

	public GoodsModel getByCode(int goodsCode) {
		// TODO Auto-generated method stub
		return null;
	}

}