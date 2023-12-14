/*-------------------------------------------------------------------------------------------------------*/
//VALIDACIONES

// Example starter JavaScript for disabling form submissions if there are invalid fields
(function() {
	'use strict'

	// Fetch all the forms we want to apply custom Bootstrap validation styles to
	var forms = document.querySelectorAll('.needs-validation')

	// Loop over them and prevent submission
	Array.prototype.slice.call(forms)
		.forEach(function(form) {
			form.addEventListener('submit', function(event) {
				if (!form.checkValidity()) {
					event.preventDefault()
					event.stopPropagation()
				}

				form.classList.add('was-validated')
			}, false)
		})
})()


//VALIDACION DE NOMBRE
// Función para verificar si el nombre ya existe en el array (ya sea activo o inactivo)
function isNamesExists(name) {
	const activeExists = arreglo.some(item => item.name.trim() === name);
	const inactiveExists = arregloInactivos.some(item => item.name.trim() === name);
	return activeExists || inactiveExists;
}

document.addEventListener('DOMContentLoaded', function() {
	const frmName = document.getElementById('frmName');
	const Name = document.getElementById('Name');
	Name.textContent = '';
	frmName.addEventListener('input', function() {
		const name = frmName.value.trim();
		if (isNamesExists(name)) {
			frmName.setCustomValidity('Nombre ya existe en la lista.');
			Name.textContent = 'Nombre ya existe en la lista.';
		} else {
			frmName.setCustomValidity('');
		}
	});
});

document.addEventListener('DOMContentLoaded', function() {
	const frmPricePurchase = document.getElementById('frmPrice_purchase');
	const frmPriceSale = document.getElementById('frmPrice_sale');
	const precioC = document.getElementById('precioC');
	const precioV = document.getElementById('precioV');

	// Función para validar el formato del precio
	function validarPrecio(input, mensajeError) {
		const valor = input.value.trim();
		const esValido = /^\d{1,8}(\.\d{0,2})?$/.test(valor); // Hasta 8 dígitos antes del punto, opcionalmente seguido por hasta 2 dígitos después del punto

		if (!esValido) {
			input.setCustomValidity(mensajeError);
			input.classList.add('is-invalid');
			input.classList.remove('is-valid');
		} else {
			input.setCustomValidity('');
			input.classList.add('is-valid');
			input.classList.remove('is-invalid');
		}
	}

	frmPricePurchase.addEventListener('input', function() {
		validarPrecio(frmPricePurchase, 'Formato de precio no válido.');
		precioC.textContent = frmPricePurchase.validationMessage;
	});

	frmPriceSale.addEventListener('input', function() {
		validarPrecio(frmPriceSale, 'Formato de precio no válido.');
		precioV.textContent = frmPriceSale.validationMessage;
	});
});

document.addEventListener('DOMContentLoaded', function() {
	const frmDateExpiry = document.getElementById('frmDate_expiry');
	const fech_C = document.getElementById('fech_C');

	// Función para validar que la fecha sea mayor que la fecha actual
	function validarFecha(input) {
		const fechaIngresada = new Date(input.value);
		const fechaActual = new Date();

		if (fechaIngresada <= fechaActual) {
			input.setCustomValidity('La fecha debe ser mayor que la fecha actual.');
			input.classList.add('is-invalid');
			input.classList.remove('is-valid');
		} else {
			input.setCustomValidity('');
			input.classList.add('is-valid');
			input.classList.remove('is-invalid');
		}
	}

	frmDateExpiry.addEventListener('input', function() {
		validarFecha(frmDateExpiry);
		fech_C.textContent = frmDateExpiry.validationMessage;
	});
});

document.addEventListener('DOMContentLoaded', function() {
	const frmStock = document.getElementById('frmStock');
	const stock = document.getElementById('Stock');

	// Función para validar que el valor sea un número entero
	function validarStock(input) {
		const valor = input.value.trim();
		const esValido = /^\d+$/.test(valor); // Expresión regular que verifica si el valor es un número entero

		if (!esValido) {
			input.setCustomValidity('Debe ingresar un número entero.');
			input.classList.add('is-invalid');
			input.classList.remove('is-valid');
		} else {
			input.setCustomValidity('');
			input.classList.add('is-valid');
			input.classList.remove('is-invalid');
		}
	}

	frmStock.addEventListener('input', function() {
		validarStock(frmStock);
		stock.textContent = frmStock.validationMessage;
	});

	// Evitar que se ingresen puntos y otros caracteres no deseados
	frmStock.addEventListener('keypress', function(event) {
		const charCode = event.which || event.keyCode;
		if (charCode === 46 || (charCode < 48 || charCode > 57)) {
			event.preventDefault();
		}
	});
});



document.addEventListener('DOMContentLoaded', function() {
	const forms = document.querySelectorAll('.needs-validation');

	forms.forEach(function(form) {
		const inputs = form.querySelectorAll('input, select');

		inputs.forEach(function(input) {
			input.addEventListener('input', function() {
				if (input.checkValidity()) {
					input.classList.remove('is-invalid');
					input.classList.add('is-valid');
				} else {
					input.classList.remove('is-valid');
					input.classList.add('is-invalid');
				}
			});
		});
	});
});

document.addEventListener('DOMContentLoaded', function() {
	const forms = document.querySelectorAll('.needs-validation');
	const saveButton = document.getElementById('btnProcesar'); // Obtén el botón "Guardar"

	forms.forEach(function(form) {
		const inputs = form.querySelectorAll('input, select');

		inputs.forEach(function(input) {
			input.addEventListener('input', function() {
				if (form.checkValidity()) {
					// Habilitar el botón si el formulario es válido
					saveButton.removeAttribute('disabled');
				} else {
					// Deshabilitar el botón si el formulario no es válido
					saveButton.setAttribute('disabled', 'disabled');
				}
			});
		});
	});
});

// En el archivo de validaciones
function reiniciarValidacion() {
	// Reiniciar la validación del formulario
	const forms = document.querySelectorAll('.needs-validation');
	forms.forEach(function(form) {
		form.classList.remove('was-validated');
	});
	// Eliminar clases de validación de Bootstrap
	frmType_document.classList.remove('is-valid', 'is-invalid');
	frmNumber_document.classList.remove('is-valid', 'is-invalid');
	frmNames.classList.remove('is-valid', 'is-invalid');
	frmLast_name.classList.remove('is-valid', 'is-invalid');
	frmEmail.classList.remove('is-valid', 'is-invalid');
	frmCell_phone.classList.remove('is-valid', 'is-invalid');
	frmBirthdate.classList.remove('is-valid', 'is-invalid');
}

