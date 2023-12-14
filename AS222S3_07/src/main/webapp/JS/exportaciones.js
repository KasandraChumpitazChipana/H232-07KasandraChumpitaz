// Función para exportar la tabla a formato XLS
function exportToXLS() {
	// Obtener la tabla HTML
	let table = document.getElementById("detalleTabla");

	// Crear un libro de trabajo (workbook)
	let wb = XLSX.utils.table_to_book(table);

	// Guardar el libro de trabajo como archivo XLS
	XLSX.writeFile(wb, 'exported_data.xls');
}

// Función para exportar la tabla a formato CSV
function exportToCSV() {
	// Obtener la tabla HTML
	let table = document.getElementById("detalleTabla");

	// Crear un libro de trabajo (workbook)
	let wb = XLSX.utils.table_to_book(table);

	// Obtener la primera hoja del libro de trabajo
	let ws = wb.Sheets[Object.keys(wb.Sheets)[0]];

	// Convertir la hoja de trabajo a formato CSV
	let csv = XLSX.utils.sheet_to_csv(ws);

	// Guardar el contenido CSV como archivo
	let blob = new Blob([csv], { type: 'text/csv;charset=utf-8;' });
	let link = document.createElement("a");

	// Crear un enlace y descargar el archivo CSV
	link.href = URL.createObjectURL(blob);
	link.download = 'exported_data.csv';
	link.click();
}

// Asociar las funciones a los botones correspondientes
document.getElementById("btnExportCSV").addEventListener("click", exportToCSV);
document.getElementById("btnExportXLS").addEventListener("click", exportToXLS);
//------------------------------------------------------------------------//

// Programar el evento click del botón para generar PDF
document.getElementById("btnGenerarPDF").addEventListener("click", function() {
	// Obtener el estado actual (activos o inactivos)
	let estadoActual = document.getElementById("listaClientesTitulo").textContent;

	// Determinar la URL adecuada según el estado
	let pdfUrl = (estadoActual === "Lista de Clientes Activos") ? "/cliente/PDFclient" : "/cliente/PDFclientInac";

	// Llama a la función generarPDF con la URL correspondiente
	generarPDF(pdfUrl);
});

function generarPDF(url) {
	// Crear un objeto FormData con los datos del cliente
	let formData = new FormData();
	formData.append("type_document", document.getElementById("frmType_document").value);
	formData.append("number_document", document.getElementById("frmNumber_document").value);
	formData.append("names", document.getElementById("frmNames").value);
	formData.append("last_name", document.getElementById("frmLast_name").value);
	formData.append("cell_phone", document.getElementById("frmCell_phone").value);
	formData.append("email", document.getElementById("frmEmail").value);
	formData.append("birthdate", document.getElementById("frmBirthdate").value);

	// Obtener el estado actual (activos o inactivos)
	let estadoActual = document.getElementById("listaClientesTitulo").textContent;

	// Definir el nombre del archivo en función del estado
	let nombreArchivo = (estadoActual === "Lista de Clientes Activos") ? "clientes_activos.pdf" : "clientes_inactivos.pdf";

	// Realizar una solicitud AJAX para generar el PDF
	let xhr = new XMLHttpRequest();
	xhr.open("POST", url, true);

	xhr.setRequestHeader("enctype", "multipart/form-data");
	xhr.responseType = "blob";

	xhr.onload = function() {
		if (xhr.status === 200) {
			// Crear un objeto URL con la respuesta del servidor
			let url = URL.createObjectURL(xhr.response);

			// Crear un enlace y simular un clic para descargar el PDF
			let a = document.createElement("a");
			a.href = url;
			a.download = nombreArchivo; // Usar el nombre del archivo según el estado
			document.body.appendChild(a);
			a.click();

			// Verificar el estado antes de cerrar el enlace
			if (document.body.contains(a)) {
				document.body.removeChild(a);
			}
		} else {
			// Manejar errores de la solicitud AJAX
			console.error("Error al generar el PDF. Estado:", xhr.status);
			alert("Error al generar el PDF. Por favor, inténtalo de nuevo.");
		}
	};

	xhr.onerror = function() {
		// Manejar errores de red
		console.error("Error de red al intentar generar el PDF.");
		alert("Error de red al intentar generar el PDF. Por favor, verifica tu conexión.");
	};

	// Manejar posibles errores durante la solicitud AJAX
	xhr.onabort = function() {
		console.error("La solicitud AJAX fue abortada.");
	};

	xhr.ontimeout = function() {
		console.error("La solicitud AJAX ha caducado.");
		alert("La solicitud AJAX ha caducado. Por favor, inténtalo de nuevo.");
	};

	xhr.send(formData);
}


