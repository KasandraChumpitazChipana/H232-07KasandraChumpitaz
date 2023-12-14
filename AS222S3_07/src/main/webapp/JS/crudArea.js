// Constantes del CRUD
const ACCION_NUEVO = "NUEVO";
const ACCION_EDITAR = "EDITAR";
const ACCION_ELIMINAR = "ELIMINAR";
const ACCION_ACTIVAR = "ACTIVAR";

// Arreglo de registros
let arreglo = [];
let arregloInactivos = [];
// Acceder a los controles
let btnBuscar = document.getElementById("btnBuscar");
let btnBuscarInactivos = document.getElementById("btnBuscarInactivos");
let btnInactivos = document.getElementById("btnInactivos");
let btnNuevo = document.getElementById("btnNuevo");
let btnProcesar = document.getElementById("btnProcesar");

// Programar los controles
btnBuscar.addEventListener("click", fnBtnBuscar);
btnBuscarInactivos.addEventListener("click", fnBtnBuscarInactivos);
btnInactivos.addEventListener("click", fnBtnInactivos);
btnNuevo.addEventListener("click", fnBtnNuevo);
btnProcesar.addEventListener("click", fnBtnProcesar);

// Campos del formulario
let accion = document.getElementById('accion');
let frmId = document.getElementById('frmId');
let frmName = document.getElementById('frmName');
let frmName_head_area = document.getElementById('frmName_head_area');
let frmLastname_head_area = document.getElementById('frmLastname_head_area');
//-------------------------------------------------------------------------------------------------------------//
//--------- BUSCAR --------//

document.addEventListener("DOMContentLoaded", function() {
	// Mostrar la lista de clientes al cargar la página
	fnBtnBuscar();
});

// Función fnBtnBuscar
function fnBtnBuscar() {
	// Datos
	let ctrlId = document.getElementById("ctrlId").value;
	let ctrlNombre = document.getElementById("ctrlNombre").value;

	let url = "AreaBuscar?id=" + ctrlId;
	url += "&nombre=" + ctrlNombre;
	// La llama AJAX
	let xhttp = new XMLHttpRequest();
	xhttp.open("GET", url, true);
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			let respuesta = xhttp.responseText;
			arreglo = JSON.parse(respuesta);
			let detalleTabla = "";
			arreglo.forEach(function(item) {
				detalleTabla += "<tr>";
				detalleTabla += "<th scope='row'>" + item.id + "</th>";
				detalleTabla += "<td>" + item.name + "</td>";
				detalleTabla += "<td>" + item.description + "</td>";
				detalleTabla += "<td>";
				detalleTabla += "<button onclick='fnEditar(" + item.id + ");' type='button' class='btn btn-primary' style='width: auto;'><img src='IMG/editar.png' alt=''style='display: block; margin: auto;'></button> ";
				detalleTabla += "<button onclick='fnEliminar(" + item.id + ");' type='button' class='btn btn-danger' style='width: auto;'><img src='IMG/eliminar.png' alt=''style='display: block; margin: auto;'></button>";
				detalleTabla += "</td>";
				detalleTabla += "</tr>";
			});
			document.getElementById("detalleTabla").innerHTML = detalleTabla;
		}
	};
	xhttp.send();
}

// Función fnBtnInactivos
function fnBtnInactivos() {
	// Datos
	let ctrlId = document.getElementById("ctrlId").value;
	let ctrlNombre = document.getElementById("ctrlNombre").value;

	let url = "AreaInactivos?id=" + ctrlId;
	url += "&nombre=" + ctrlNombre;
	// La llama AJAX
	let xhttp = new XMLHttpRequest();
	xhttp.open("GET", url, true);
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			let respuesta = xhttp.responseText;
			arregloInactivos = JSON.parse(respuesta);
			let detalleTabla = "";
			arregloInactivos.forEach(function(item) {
				detalleTabla += "<tr>";
				detalleTabla += "<th scope='row'>" + item.id + "</th>";
				detalleTabla += "<td>" + item.name + "</td>";
				detalleTabla += "<td>" + item.description + "</td>";
				detalleTabla += "<td>";
				detalleTabla += "<button onclick='fnActivar(" + item.id + ");' class='btn btn-success' style='width: auto;'><img src='IMG/activar.png' alt=''style='display: block; margin: auto;'></button> ";
				detalleTabla += "</td>";
				detalleTabla += "</tr>";
			});
			document.getElementById("detalleTabla").innerHTML = detalleTabla;
		}
	};
	xhttp.send();
}

// Función fnBtnInactivos
function fnBtnBuscarInactivos() {
	// Datos
	let ctrlId = document.getElementById("ctrlId").value;
	let ctrlNombre = document.getElementById("ctrlNombre").value;

	let url = "AreaInactivos?id=" + ctrlId;
	url += "&nombre=" + ctrlNombre;
	// La llama AJAX
	let xhttp = new XMLHttpRequest();
	xhttp.open("GET", url, true);
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			let respuesta = xhttp.responseText;
			arregloInactivos = JSON.parse(respuesta);
			let detalleTabla = "";
			arregloInactivos.forEach(function(item) {
				detalleTabla += "<tr>";
				detalleTabla += "<th scope='row'>" + item.id + "</th>";
				detalleTabla += "<td>" + item.name + "</td>";
				detalleTabla += "<td>" + item.description + "</td>";
				detalleTabla += "<td>";
				detalleTabla += "<button onclick='fnActivar(" + item.id + ");' class='btn btn-success' style='width: auto;'><img src='IMG/activar.png' alt=''style='display: block; margin: auto;'></button> ";
				detalleTabla += "</td>";
				detalleTabla += "</tr>";
			});
			document.getElementById("detalleTabla").innerHTML = detalleTabla;
		}
	};
	xhttp.send();
}



let ocultarInactivos = document.getElementById("ocultar_inactivos");
let Buscar = document.getElementById("Buscar");
let BuscarInactivos = document.getElementById("BuscarInactivos");
let mostrarActivos = document.getElementById("mostrar_activos");

ocultarInactivos.addEventListener("click", function() {
	// Ocultar el botón "Inactivos" y mostrar el botón "Activos"
	ocultarInactivos.style.display = "none";
	mostrarActivos.style.display = "block";
	Buscar.style.display = "none";
	BuscarInactivos.style.display = "block";
	document.getElementById("listaAreaTitulo").textContent = "Lista de Area Inactivos";

	// Llama a la función para cargar la lista de clientes inactivos
	fnBtnInactivos();
});

mostrarActivos.addEventListener("click", function() {
	// Ocultar el botón "Activos" y mostrar el botón "Inactivos"
	ocultarInactivos.style.display = "block";
	mostrarActivos.style.display = "none";
	Buscar.style.display = "block";
	BuscarInactivos.style.display = "none";
	document.getElementById("listaAreaTitulo").textContent = "Lista de Area Activas";

	// Llama a la función para cargar la lista de clientes activos
	fnBtnBuscar();
});

//---------------------------------------------------------------------------------------------------------------//
//------------------EDITAR--------------------//
function fnEditar(id) {
	// Preparando el formulario
	document.getElementById("tituloRegistro").innerHTML = ACCION_EDITAR + " REGISTRO";
	document.getElementById("accion").value = ACCION_EDITAR;
	// Cargar los datos del registro que estás editando
	fnCargarForm(id);
	fnEstadoFormulario(ACCION_EDITAR);
	document.getElementById("divRegistro").addEventListener("submit", function(event) {
		event.preventDefault();
	});

	// Mostrar formulario encima de todo
	const divRegistro = document.getElementById("divRegistro");
	divRegistro.style.opacity = "1";
	divRegistro.style.display = "grid";
	divRegistro.style.position = "fixed";
	divRegistro.style.top = "0";
	divRegistro.style.left = "0";
	divRegistro.style.width = "100%";
	divRegistro.style.height = "100%";
	divRegistro.style.zIndex = "1000"; // Asegura que esté en la parte superior
	document.getElementById("divRegistro").style.placeItems = "center";
}

//---------------------------------------------------------------------------------------------------
//-----------------------ELIMINAR--------------------------//

// Función fnEliminar
function fnEliminar(id) {
	Swal.fire({
		text: "Estas seguro de eliminar esta categoria?",
		icon: "warning",
		showCancelButton: true,
		confirmButtonColor: "#3085d6",
		cancelButtonColor: "#d33",
		confirmButtonText: "Aceptar!"
	}).then((result) => {
		if (result.isConfirmed) {
			// Aquí podrías llamar a tu función de eliminación con el ID
			// fnEliminar(id);
			Swal.fire({
				title: "Eliminado!",
				text: "Categoria Eliminado Correctamente",
				icon: "success"
			});
			// Si el usuario elige "Aceptar", procedemos con la eliminación
			document.getElementById("accion").value = ACCION_ELIMINAR;
			fnCargarForm(id);
			fnBtnProcesar();
			fnBtnBuscar();
		}
	});
}

//---------------------------------------------------------------------------------------------------
//-----------------------REACTIVAR--------------------------//

// Funcion fnEliminar
function fnActivar(id) {
	Swal.fire({
		text: "Estas seguro de reactivar a esta categoria?",
		icon: "warning",
		showCancelButton: true,
		confirmButtonColor: "#3085d6",
		cancelButtonColor: "#d33",
		confirmButtonText: "Aceptar"
	}).then((result) => {
		if (result.isConfirmed) {
			Swal.fire({
				title: "Reactivado",
				text: "Categoria reactivado correctamente",
				icon: "success"
			});
			document.getElementById("accion").value = ACCION_ACTIVAR;
			fnCargarFormInactivos(id);
			fnBtnProcesar();
			fnBtnBuscarInactivos();
		}
	});
}
//----------------------------------------------------------------------------------------------------------
//-------------------------------------PROCESO----------------------------------------------
function fnBtnProcesar() {
	// Validar
	if (!fnValidar()) {
		return; // Aquí simplemente detenemos la ejecución si la validación falla
	}

	// Preparar los datos
	let datos = "accion=" + document.getElementById("accion").value;
	datos += "&id=" + document.getElementById("frmId").value;
	datos += "&name=" + document.getElementById("frmName").value;
	datos += "&name_head_area=" + document.getElementById("frmName_head_area").value;
	datos += "&Lastname_head_area=" + document.getElementById("frmLastname_head_area").value;
	// Obtener el estado actual (activos o inactivos)
	let estadoActual = document.getElementById("listaCategoriaTitulo").textContent;

	// El envío con AJAX
	let xhr = new XMLHttpRequest();
	xhr.open("POST", "CategoryProductProcesar", true);
	xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200) {
				// La solicitud se completó correctamente
				console.log(xhr.responseText);
				// Actualizar la lista de clientes automáticamente según el estado actual
				if (estadoActual === "Lista de Categorias Activas") {
					fnBtnBuscar(); // Actualizar la lista de activos
				} else if (estadoActual === "Lista de Categorias Inactivos") {
					fnBtnBuscarInactivos(); // Actualizar la lista de inactivos
				}
			} else {
				// Manejar el error aquí si es necesario
				console.error("Error en la solicitud AJAX");
			}
			// Limpia los campos después de guardar
			limpiarCampos();
			reiniciarValidacion();

		}
	};
	xhr.send(datos);
}
//--------------------------------------------------------------------------------------------------------------------
//------------------------LIMPIAR CAMPOS--------------------------------------
// Función para limpiar los campos y reiniciar la validación
function limpiarCampos() {
	// Limpiar todos los campos del formulario
	frmName.value = "";
	frmDescription.value = "";
	// Eliminar clases de validación de Bootstrap
	frmName.classList.remove('is-valid', 'is-invalid');
	frmDescription.classList.remove('is-valid', 'is-invalid');

	// También puedes ocultar el formulario si lo deseas
	document.getElementById("divRegistro").style.display = "none";
}

//--------------------------------------------------------------------------------------------------------------------
//--------------------NUEVO-------------------------------//
// Funcion fnBtnNuevo
function fnBtnNuevo() {
	// Preparando el formulario
	document.getElementById("tituloRegistro").innerHTML = ACCION_NUEVO + " REGISTRO";
	document.getElementById("accion").value = ACCION_NUEVO;
	fnEstadoFormulario(ACCION_NUEVO);
	document.getElementById("divRegistro").addEventListener("submit", function(event) {
		event.preventDefault();
	});

	// Ocultar el campo frmId
	document.getElementById("frmId").style.display = "none";
	document.getElementById("frmId_label").style.display = "none";
	// Limpiar todos los campos del formulario
	frmName.value = "";
	frmDescription.value = "";

	// Mostrar formulario encima de todo
	const divRegistro = document.getElementById("divRegistro");
	divRegistro.style.opacity = "1";
	divRegistro.style.display = "grid";
	divRegistro.style.position = "fixed";
	divRegistro.style.top = "0";
	divRegistro.style.left = "0";
	divRegistro.style.width = "100%";
	divRegistro.style.height = "100%";
	divRegistro.style.zIndex = "1000"; // Asegura que esté en la parte superior
	document.getElementById("divRegistro").style.placeItems = "center";
}


document.getElementById("btnCancelar").addEventListener("click", function() {
	// Oculta el formulario
	document.getElementById("divRegistro").style.display = "none";
	// Ocultar el campo frmId
	document.getElementById("frmId").style.display = "block";
	document.getElementById("frmId_label").style.display = "block";
	// Limpia los campos después de guardar
	limpiarCampos();
	reiniciarValidacion();
});

//---------------------------------------------------------------------------------------------------------------

function fnCargarForm(id) {
	arreglo.forEach(function(item) {
		if (item.id == id) {
			frmId.value = item.id;
			frmName.value = item.name;
			frmDescription.value = item.description;
			return true;
		}
	});
}

function fnCargarFormInactivos(id) {
	arregloInactivos.forEach(function(item) {
		if (item.id == id) {
			frmId.value = item.id;
			frmName.value = item.name;
			frmDescription.value = item.description;
			return true;
		}
	});
}



function fnEstadoFormulario(estado) {
	frmName.disabled = (estado == ACCION_ELIMINAR)
	frmDescription.disabled = (estado == ACCION_ELIMINAR)
	if (estado == ACCION_NUEVO) {
		frmId.value = "0";
		frmName.value = "";
		frmDescription.value = "";
	}
}

function fnValidar() {

	return true;
}
