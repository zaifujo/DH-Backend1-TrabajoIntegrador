window.addEventListener('load', function () {
    const formulario = document.querySelector('#update_paciente_form');

    formulario.addEventListener('submit', function (event) {
        let pacienteId = document.querySelector('#paciente_id').value;

        const formData = {
            id: document.querySelector('#paciente_id').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            domicilio: {
                calle: document.querySelector('#domicilio_calle').value,
                numero: document.querySelector('#domicilio_numero').value,
                localidad: document.querySelector('#domicilio_localidad').value,
                provincia: document.querySelector('#domicilio_provincia').value,
            },
            dni: document.querySelector('#dni').value,
            fechaAlta: document.querySelector('#fecha_alta').value,
        }
        const url = 'http://localhost:8080/pacientes/' + pacienteId;
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
        fetch(url,settings)
            .then(response => response.json())
    })
})

function findBy(id) {

    const url = 'http://localhost:8080/paciente'+"/"+id;
    const settings = {
        method: 'GET'
    }
    fetch(url,settings)
        .then(response => response.json())
        .then(data => {
            let paciente = data;
            document.querySelector('#id').value = paciente.id;
            document.querySelector('#nombre').value = paciente.nombre;
            document.querySelector('#apellido').value = paciente.apellido;
            document.querySelector('#domicilio').value = paciente.domicilio;
            document.querySelector('#dni').value = paciente.dni;
            document.querySelector('#fecha_alta').value = paciente.fechaAlta;


            document.querySelector('#div_paciente_updating').style.display = "block";
        }).catch(error => {
        alert("Error: " + error);
    })
}