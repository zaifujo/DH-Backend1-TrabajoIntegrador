document.getElementById("add_new_turno").onsubmit=function(e) {
    e.preventDefault();
};

// POST
window.addEventListener('load', function () {

    const formulario = document.querySelector('#add_new_turno');
    formulario.addEventListener('submit', function (event) {

        const formData = {
            paciente: {
                id: document.querySelector('#paciente').value
            },
            odontologo: {
                id: document.querySelector('#odontologo').value
            },
            fecha: document.querySelector('#fecha').value
        };

        const url = 'http://localhost:8080/turnos';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            //.then(response => response.json())
            .then(response => {
                if (!response.ok) {
                    return response.text().then(errorMessage => {
                        throw new Error(`Status: ${response.status} ${response.statusText}, Message: ${errorMessage}`);
                    });
                    //throw new Error(`Status: ${response.status} ${response.statusText}`);
                }

                const contentType = response.headers.get('content-type');
                if (contentType && contentType.includes('application/json')) {
                    return response.json();
                } else {
                    return response.text();
                }
            })
            .then(data => {
                if (typeof data === 'string') {
                    console.log('Response text:', data);
                } else {
                    console.log('Response json:', data);

                    let successAlert = '<div id="success-alert" class="alert alert-success alert-dismissible">' +
                        '<button type="button" class="close" data-dismiss="alert" ' +
                        'onclick="document.getElementById(\'success-alert\').classList.add(\'d-none\')">&times;</button>' +
                        '<strong> Turno registrado</strong> </div>'

                    document.querySelector('#response').innerHTML = successAlert;

                    document.querySelector('#response').style.display = "block";
                    resetUploadForm();

                    console.log('Turno registrado con éxito');
                }
            })
            .catch(error => {
                let errorAlert = '<div id="error-alert" class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert" ' +
                    'onclick="document.getElementById(\'error-alert\').classList.add(\'d-none\')">&times;</button>' +
                    '<strong> Error al registrar turno, intente nuevamente</strong> </div>'

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();

                //alert(`Error post turno: ${error.message}`);
                console.log('Error post turno:', error);
            })
    });

    function resetUploadForm(){
        document.querySelector('#paciente').value = "";
        document.querySelector('#odontologo').value = "";
        document.querySelector('#fecha').value = "";
    }

    /*(function(){
        let pathname = window.location.pathname;
        if (pathname === "/") {
            //document.querySelector(".nav .nav-item a:first").addClass("active");
            document.querySelector(".nav .nav-item a:first-child").classList.add("active");
        } else if (pathname == "/turnoList.html") {
            //document.querySelector(".nav .nav-item a:last").addClass("active");
            document.querySelector(".nav .nav-item a:last-child").classList.add("active");
        }
    })();*/
});

document.addEventListener('DOMContentLoaded', function() {
    fetch('http://localhost:8080/pacientes')
        .then(response => response.json())
        .then(data => {
            const selectPaciente = document.getElementById('paciente');
            data.forEach(paciente => {
                const option = document.createElement('option');
                option.value = paciente.id;
                option.textContent = `${paciente.nombre} ${paciente.apellido} (DNI: ${paciente.dni})`;
                selectPaciente.appendChild(option);
            });
        })
        .catch(error => console.error('Error fetching pacientes:', error));

    fetch('http://localhost:8080/odontologos')
        .then(response => response.json())
        .then(data => {
            const selectOdontologo = document.getElementById('odontologo');
            data.forEach(odontologo => {
                const option = document.createElement('option');
                option.value = odontologo.id;
                option.textContent = `${odontologo.nombre} ${odontologo.apellido} (MATRÍCULA: ${odontologo.matricula})`;
                selectOdontologo.appendChild(option);
            });
        })
        .catch(error => console.error('Error fetching odontólogos:', error));
});