<style>
.sidebar {
	position: fixed;
	top: 0;
	bottom: 0;
	left: 0;
	z-index: 100;
	padding: 48px 10px; /* Ajusta el padding según tus preferencias */
	background-color: #515456;;
	width: 250px; /* Ajusta el ancho del sidebar según tus preferencias */
	text-align: center;
	font-family: 'Poppins', sans-serif;
	/* Cambia la fuente de letra a Poppins */
}

.sidebar .title {
	font-size: 1.5rem;
	font-weight: bold;
	margin-bottom: 20px;
	color: #fff;
}

.nav-link {
	font-weight: bold;
	font-size: 1.2rem;
	color: #fff;
}

.content {
	margin-left: 200px;
	padding: 20px;
}

@media ( max-width : 767.98px) {
	.sidebar {
		position: static;
		padding: 20px;
		margin-bottom: 20px;
	}
	.content {
		margin-left: 0;
		padding: 20px;
	}
}
</style>



<div class="container-fluid">
	<div class="row">
		<div class="col-md-3 sidebar">
			<ul class="nav flex-column">
				<li class="insignia"><img src="IMG/logo.jpg"
					class="list__img" width="123px" align="middle"
					style="margin-top: 50px;border-radius: 50%;"></li>
				<div class="title">Administrador</div>
				<li class="nav-item"><a class="nav-link" href="index.jsp"><i class="bi bi-house-door-fill"></i> Inicio</a></li>
				<li class="nav-item"><a class="nav-link" href="CrudArea.jsp"><i class="bi bi-people-fill"></i> Cliente</a></li>
				<li class="nav-item"><a class="nav-link" href="CrudGoods.jsp"><i class="bi bi-people-fill"></i> Cliente</a></li>
			</ul>
		</div>
	</div>
</div>

