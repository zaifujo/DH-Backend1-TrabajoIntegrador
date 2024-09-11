document.getElementById("add_new_turno").onsubmit=function(e) {
    e.preventDefault();
};

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
            .then(response => response.json())
            .then(data => {

                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong></strong> Turno agregado </div>'

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();

            })
            .catch(error => {
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> Error intente nuevamente</strong> </div>'

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();
            })
    });

    function resetUploadForm(){
        document.querySelector('#paciente').value = "";
        document.querySelector('#odontologo').value = "";
        document.querySelector('#fecha').value = "";
    }

    (function(){
        let pathname = window.location.pathname;
        if (pathname === "/") {
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/turnoList.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
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