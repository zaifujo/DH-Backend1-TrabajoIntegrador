window.addEventListener('load', function () {
    const formulario = document.querySelector('#update_turno_form');

    formulario.addEventListener('submit', function (event) {


        const formData = {
            id: document.querySelector('#turno_id').value,
            paciente: {
                id: document.querySelector('#paciente').value
            },
            odontologo: {
                id: document.querySelector('#odontologo').value
            },
            fecha: document.querySelector('#fecha').value,
        }
        const url = 'http://localhost:8080/turnos';//
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

    const url = 'http://localhost:8080/turnos'+"/"+id;
    const settings = {
        method: 'GET'
    }
    fetch(url,settings)
        .then(response => response.json())
        .then(data => {
            let turno = data;
            document.querySelector('#turno_id').value = turno.id;
            document.querySelector('#paciente').value = turno.paciente.id;
            document.querySelector('#odontologo').value = turno.odontologo.id;
            document.querySelector('#fecha').value = turno.fecha;

            document.querySelector('#div_turno_updating').style.display = "block";
        }).catch(error => {
        alert("Error: " + error);
    })
}