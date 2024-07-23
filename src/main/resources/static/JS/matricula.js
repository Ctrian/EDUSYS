var header = document.getElementById('header');

window.addEventListener('scroll', () => {

    var scroll = window.scrollY

    if (scroll > 10) {
        header.style.backgroundColor = ''
    } else {
        header.style.backgroundColor = ''
    }

})

function soloLetras(event) {
    var letra = event.keyCode;
    // Acepta letras mayúsculas, minúsculas, tecla de retroceso, espacio y acentos
    if ((letra >= 65 && letra <= 90) || (letra >= 97 && letra <= 122) || letra === 8 || letra === 32 ||
        (letra >= 192 && letra <= 255) || (letra >= 128 && letra <= 165)) {
        return true;
    } else {
        return false;
    }
}

function soloNumeros(event) {
    var letra = event.keyCode;
    if (letra >= 48 && letra <= 57) {
        return true;
    } else {
        return false;
    }
}

function validarCedulaEcuatoriana(cedula) {
    if (cedula.length !== 10) {
        return false;
    }

    const coeficientes = [2, 1, 2, 1, 2, 1, 2, 1, 2];
    let suma = 0;

    for (let i = 0; i < coeficientes.length; i++) {
        let valor = parseInt(cedula.charAt(i)) * coeficientes[i];
        suma += (valor >= 10) ? valor - 9 : valor;
    }

    const digitoVerificador = parseInt(cedula.charAt(9));
    const resultado = (suma % 10 === 0) ? 0 : 10 - (suma % 10);

    return resultado === digitoVerificador;
}

function validarFechaNacimiento() {
    const birthdateInput = document.getElementById('child_birthdate');
    const birthdate = new Date(birthdateInput.value);
    const minDate = new Date('2000-01-01');
    const maxDate = new Date('2018-12-31');
    const mensajeFechaNacimiento = document.getElementById('mensajeFechaNacimiento');

    if (birthdate < minDate || birthdate > maxDate) {
        mensajeFechaNacimiento.textContent = 'La fecha de nacimiento debe estar entre el 31/12/2018 y 01/01/2000';
        mensajeFechaNacimiento.classList.add('mensaje-error');
        return false;
    } else {
        mensajeFechaNacimiento.textContent = '';
        mensajeFechaNacimiento.classList.remove('mensaje-error');
        return true;
    }
}

function validarCedula(event) {
    const cedula = event.target.value;
    const mensajeElement = document.getElementById('mensajeCedulaEstu');
    const regexCedula = /^\d{10}$/;

    if (!regexCedula.test(cedula)) {
        mensajeElement.textContent = 'Ingrese el formato de cédula correcto para Ecuador (10 dígitos)';
        mensajeElement.classList.remove('mensaje-error');
        mensajeElement.classList.add('mensaje-advertencia');
        return false;
    }

    if (!validarCedulaEcuatoriana(cedula)) {
        mensajeElement.textContent = 'Ingrese una cédula ecuatoriana válida';
        mensajeElement.classList.remove('mensaje-advertencia');
        mensajeElement.classList.add('mensaje-error');
        return false;
    }

    mensajeElement.textContent = '';
    mensajeElement.classList.remove('mensaje-error', 'mensaje-advertencia');
    return true;
}

function validarTelefono(event) {
    const telefono = event.target.value;
    console.log('Teléfono ingresado:', telefono); // Agregado para depuración
    const mensajeElement = document.getElementById('mensajeTelefono');
    const regexTelefono = /^\d{10}$/; // Exactamente 10 dígitos

    if (!regexTelefono.test(telefono)) {
        mensajeElement.textContent = 'Ingrese un número de teléfono válido (exactamente 10 dígitos)';
        mensajeElement.classList.add('mensaje-error');
        return false;
    } else {
        mensajeElement.textContent = '';
        mensajeElement.classList.remove('mensaje-error');
        return true;
    }
}


function validarFormulario() {
    const telefonoInput = document.getElementById('telefono_estudiante').value;
    const esValidoTelefono = validarTelefono({ target: { value: telefonoInput } });

    const cedulaInput = document.getElementById('cedula_estudiante');
    const cedulaEstudiante = cedulaInput.value;
    const esValidoCedula = validarCedula({ target: { value: cedulaEstudiante } });
    const esValidoFecha = validarFechaNacimiento({ target: document.getElementById('child_birthdate') });

    const cedulaRepresentante = document.getElementById('cedula_representante').value;

    if (cedulaEstudiante === cedulaRepresentante) {
        document.getElementById('mensajeCedulaEstu').innerText = 'La cédula del estudiante no puede ser igual a la del representante.';
        return false;
    }

    if (!esValidoCedula || !esValidoFecha || !esValidoTelefono) {
        alert("Por favor, ingrese los datos correctamente al formulario.");
        return false;
    }

    return true;
}

document.addEventListener('DOMContentLoaded', function () {
    const birthdateInput = document.getElementById('child_birthdate');
    const cedulaInput = document.getElementById('cedula_estudiante');
    const mensajeError = document.getElementById('mensajeCedulaEstu');
    const telefonoInput = document.getElementById('telefono_estudiante');
    const botonRegistrar = document.getElementById('registrarBtn');

    birthdateInput.addEventListener('input', validarFechaNacimiento);
    telefonoInput.addEventListener('input', validarTelefono);
    cedulaInput.addEventListener('input', function () {
        const cedula = cedulaInput.value.trim();

        // Validación local
        const esValidaLocal = validarCedula({ target: { value: cedula } });
        if (!esValidaLocal) {
            return;
        }

        // Validación de existencia en base de datos
        if (cedula.length === 10) { // Asumiendo que la cédula tiene 10 dígitos
            fetch(`/representantes/validarCedula?cedula=${cedula}`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.json();
                })
                .then(data => {
                    if (data) {
                        mensajeError.textContent = 'La cédula del estudiante ya está en uso. Por favor, elige otra.';
                        botonRegistrar.disabled = true;
                    } else {
                        mensajeError.textContent = '';
                        botonRegistrar.disabled = false;
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                });
        } else {
            mensajeError.textContent = '';
            botonRegistrar.disabled = false;
        }
    });
});

document.getElementById('estudiante-select').addEventListener('change', function () {
    document.getElementById('estudiante-select').value = this.value;
});

document.querySelectorAll('input[name="offer"]').forEach(function (input) {
    input.addEventListener('change', function () {
        document.getElementById('offer-select').value = this.id;
    });
});

