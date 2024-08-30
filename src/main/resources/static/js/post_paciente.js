document.getElementById("add_new_paciente").onsubmit=function(e) {
    e.preventDefault();
};

window.addEventListener('load', function () {

    const formulario = document.querySelector('#add_new_paciente');


    formulario.addEventListener('submit', function (event) {

        const formData = {
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

        };

        const url = 'http://localhost:8080/pacientes';
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
                    '<strong></strong> Paciente agregado </div>'

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
                //se dejan todos los campos vacíos por si se quiere ingresar otra pelicula
                resetUploadForm();})
    });


    function resetUploadForm(){
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#domicilio_calle').value = "";
        document.querySelector('#domicilio_numero').value = "";
        document.querySelector('#domicilio_localidad').value = "";
        document.querySelector('#domicilio_provincia').value = "";
        document.querySelector('#dni').value = "";
        document.querySelector('#fecha_alta').value = "";

    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/pacienteList.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});