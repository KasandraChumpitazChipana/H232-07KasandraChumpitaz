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
	const names = document.getElementById('names');
	names.textContent = '';
	frmName.addEventListener('input', function() {
		const name = frmName.value.trim();
		if (isNamesExists(name)) {
			frmName.setCustomValidity('Nombre ya existe en la lista.');
			names.textContent = 'Nombre ya existe en la lista.';
		} else {
			frmName.setCustomValidity('');
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
	frmName.classList.remove('is-valid', 'is-invalid');
	frmDescription.classList.remove('is-valid', 'is-invalid');
}

