<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="CSS/style.css">
<title>Crud Productos</title>
<!-- Incluir SheetJS -->
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.16.9/xlsx.full.min.js"></script>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Imprima&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="CSS/style.css">
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<div class="ajustar">
		<h4 class="h4-cliente">Gestion de Productos</h4>
		<!-- BUSQUEDA DEL CLIENTE -->
		<div class="card">
			<div class="card-header" style="font-weight: bold;">Búsqueda</div>
			<div class="card-body">
				<form method="post" action="#" class="credit-card-div">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="row">
								<div class="col-md-3 col-sm-3 col-xs-3">
									<span class="help-block text-muted small-font"
										style="font-weight: bold;">Nombre</span> <input type="text"
										class="form-control border-black" id="ctrlNombre" />
								</div>
								<div class="col-md-3 col-sm-3 col-xs-3">
									<span class="help-block text-muted small-font"
										style="font-weight: bold;">Area</span> <input
										type="text" class="form-control border-black"
										id="ctrlArea_Nombre" />
								</div>
								<div class="col-md-2 col-sm-2 col-xs-2" id="Buscar">
									<br>
									<button type="button" class="btn btn-light border-black"
										id="btnBuscar" style="width: auto;" name="btnBuscar">
										<img src="IMG/buscar.png" alt=""
											style="display: block; margin: auto;">
									</button>
								</div>
								<div class="col-md-2 col-sm-2 col-xs-2" id="BuscarInactivos"
									style="display: none;">
									<br>
									<button type="button" class="btn btn-light border-black"
										id="btnBuscarInactivos" style="width: auto;"
										name="btnBuscarInactivos">
										<img src="IMG/buscar.png" alt=""
											style="display: block; margin: auto;">
									</button>
								</div>
								<div class="col-md-2 col-sm-3">
									<br />
									<button type="button" class="btn btn-success" id="btnNuevo"
										name="btnNuevo">
										<img src="IMG/nuevo.png" alt="">Nuevo
									</button>
								</div>
								<div class="col-md-2 col-sm-3" id="ocultar_inactivos">
									<br />
									<button type="button" class="btn btn-danger" id="btnInactivos"
										name="btnInactivos">
										<img src="IMG/eliminados.png" alt="">Inactivos
									</button>
								</div>
								<div class="col-md-2 col-sm-3" id="mostrar_activos"
									style="display: none;">
									<br />
									<button type="button" class="btn btn-primary" id="btnActivos"
										name="btnActivos">
										<img src="IMG/activos.png" alt="">Activos
									</button>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		<br>
		<div class="card">
			<div class="card-header" style="font-weight: bold;">Exportar
				Datos</div>
			<div class="card-body d-flex">
				<button type="button" id="btnExportXLS"
					class="btn btn-outline-primary">XLS</button>
				<div class="mx-2"></div>
				<button type="button" id="btnExportCSV"
					class="btn btn-outline-success">CSV</button>
				<div class="mx-2"></div>
				<button type="button" id="PDF" class="btn btn-outline-dark">PDF</button>
			</div>
		</div>


		<br>
		<!-- LISTAR LOS CLIENTES -->
		<div class="card" id="divResultado">
			<div class="card-header" style="font-weight: bold;">
				<span id="listaGoodsTitulo">Lista de Bienes</span>
			</div>
			<div class="card-body">

				<table class="table table-hover">
					<thead class="border-black">
						<tr>
							<th scope="col">codigo</th>
							<th scope="col">Area</th>
							<th scope="col">Prec. Compra</th>
							<th scope="col">Prec. Venta</th>
							<th scope="col">Fech. Caducidad</th>
							<th scope="col">Stock</th>
							<th scope="col">Acciones</th>
						</tr>

					</thead>
					<tbody id="detalleTabla">

					</tbody>
				</table>

			</div>
		</div>
	</div>
	<br>
	<!-- Formulario de edición de registro -->
	<div class="fondo bg-dark bg-opacity-75" id="divRegistro"
		style="width: auto; height: 100vh; display: none">
		<div class="card" style="width: 50vw; height: 90vh; margin: auto;">
			<div class="card-header" id="tituloRegistro">{accion}</div>
			<div class="card-body">
				<form class="needs-validation" novalidate>
					<input type="hidden" id="accion" name="accion">
					<div class="row mb-3">
						<label id="frmCode_label" for="frmCode"
							class="col-sm-3 col-form-label"
							style="width: auto;">Codigo</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" id="frmCode"
								disabled="disabled" value="0" style="text-align: center;">
						</div>
					</div>
					<div class="row mb-4">
						<label for="frmName" class="col-sm-3 col-form-label">Nombre</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="frmName"
								autocomplete="off" required>
							<div id="Name" class="invalid-feedback">Por favor, rellene
								el campo</div>
							<div class="valid-feedback">Correcto</div>
						</div>
					</div>
					<div class="row mb-4">
						<label for="frmCategory_name" class="col-sm-3 col-form-label">Categoria</label>
						<div class="col-sm-8" style="width: auto;">
							<select class="form-select" id="frmCategory_name">
							</select>
						</div>
					</div>
					<div class="row mb-4">
						<label for="frmPrice_purchase" class="col-sm-3 col-form-label">Prec.
							de compra</label>
						<div class="col-sm-8">
							<input type="number" step="0.01" class="form-control"
								autocomplete="off" id="frmPrice_purchase" required>
							<div id="precioC" class="invalid-feedback">Por favor, rellene el campo</div>
							<div class="valid-feedback">Correcto</div>
						</div>
					</div>
					<div class="row mb-4">
						<label for="frmPrice_sale" class="col-sm-3 col-form-label">Prec.
							de Venta</label>
						<div class="col-sm-8">
							<input type="number" step="0.01" class="form-control"
								id="frmPrice_sale" autocomplete="off" required>
							<div id="precioV" class="invalid-feedback">Por favor, rellene el campo</div>
							<div class="valid-feedback">Correcto</div>
						</div>
					</div>
					<div class="row mb-4">
						<label for="frmDate_expiry" class="col-sm-3 col-form-label">Fecha
							de Caducidad</label>
						<div class="col-sm-8">
							<input type="date" class="form-control" id="frmDate_expiry"
								autocomplete="off" required>
							<div id="fech_C" class="invalid-feedback">Por favor,
								rellene el campo</div>
							<div class="valid-feedback">Correcto</div>
						</div>
					</div>
					<div class="row mb-4">
						<label for="frmStock" class="col-sm-3 col-form-label">Stock</label>
						<div class="col-sm-8">
							<input type="number" class="form-control" id="frmStock" required>
							<div id="Stock" class="invalid-feedback">Por favor, rellene el campo</div>
							<div class="valid-feedback">Correcto</div>
						</div>
					</div>
					<div class="d-flex justify-content-end">
						<button onclick="alerta()" type="submit" class="btn btn-success"
							id="btnProcesar" style="width: auto;" disabled>
							<img src="IMG/guardar.png" alt=""> Guardar
						</button>
						<div class="mx-2"></div>
						<!-- Espacio horizontal -->
						<button type="button" class="btn btn-danger" id="btnCancelar"
							style="width: auto;">
							<img src="IMG/prohibido.png" alt=""> Cancelar
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="JS/notificacion.js"></script>
	<script type="text/javascript" src="JS/crudProductos.js"></script>
	<script type="text/javascript" src="JS/ValidacionesProductos.js"></script>
	<script type="text/javascript" src="JS/exportaciones.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</body>
</html>