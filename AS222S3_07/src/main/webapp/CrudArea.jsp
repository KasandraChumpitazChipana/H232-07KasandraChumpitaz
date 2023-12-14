<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="CSS/style.css">
<title>Crud Categoria de Productos</title>
<!-- Incluir SheetJS -->
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.16.9/xlsx.full.min.js"></script>
	<!-- Imagen de web -->
<link rel="icon" type="image/png" href="IMG/logo.jpg">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
	<!-- Agrega los estilos de Bootstrap Icons -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
	<!-- cierre Icons -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
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
<nav class="navbar navbar-light" style="background-color: #515456;">
		<div class="container-fluid d-flex justify-content-end">
			<a class="navbar-brand text-white" href="#"
				style="font-size: 1.9rem; font-weight: bold;"> Viña del Prado <img
				src="IMG/logo.jpg" alt="Logo" width="50" height="40"
				style="margin-top: 1px;border-radius: 45%;" class="d-inline-block align-text-top">
			</a>
		</div>
	</nav>
	<jsp:include page="menu.jsp"></jsp:include>
	<div class="ajustar">
		<h4 class="h4-cliente">Gestion de Areas de Bienes</h4>
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
										style="font-weight: bold;">ID</span> <input type="text"
										class="form-control border-black" id="ctrlId" />
								</div>
								<div class="col-md-3 col-sm-3 col-xs-3">
									<span class="help-block text-muted small-font"
										style="font-weight: bold;">Nombre</span> <input type="text"
										class="form-control border-black" id="ctrlNombre" />
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
		<!-- LISTAR LOS CLIENTES -->
		<div class="card" id="divResultado">
			<div class="card-header" style="font-weight: bold;">
				<span id="listaCategoriaTitulo">Lista de Bienes Activas</span>
			</div>
			<div class="card-body">

				<table class="table table-hover">
					<thead class="border-black">
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Nombre de Area</th>
							<th scope="col">Nombre</th>
							<th scope="col">Apellidos</th>
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
		<div class="card" style="width: 50vw; height: 50vh; margin: auto;">
			<div class="card-header" id="tituloRegistro">{accion}</div>
			<div class="card-body">
				<form class="needs-validation" novalidate>
					<input type="hidden" id="accion" name="accion">
					<div class="row mb-3">
						<label id="frmId_label" for="frmId"
							class="col-sm-2 col-form-label"
							style="width: auto; margin-left: 240px;">ID</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" id="frmId"
								disabled="disabled" value="0" style="text-align: center;">
						</div>
					</div>
					<div class="row mb-4">
						<label for="frmName" class="col-sm-2 col-form-label">Nombre</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="frmName"
								autocomplete="off" required>
							<div id="names" class="invalid-feedback">Por favor, rellene el campo</div>
							<div class="valid-feedback">Correcto</div>
						</div>
					</div>
					<div class="row mb-4">
						<label for="frmDescription" class="col-sm-2 col-form-label">Descripcion</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" autocomplete="off"
								id="frmDescription" required>
							<div class="invalid-feedback">Por favor, rellene el campo</div>
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
	<script type="text/javascript" src="JS/crudArea.js"></script>
	<script type="text/javascript" src="JS/ValidacionesArea.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</body>
</html>
