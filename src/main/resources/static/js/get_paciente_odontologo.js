document.addEventListener('DOMContentLoaded', function() {
    fetch('http://localhost:8080/pacientes')  // Cambia esta URL a la ruta de tu API
        .then(response => response.json())
        .then(data => {
            const selectPaciente = document.getElementById('paciente');
            data.forEach(paciente => {
                const option = document.createElement('option');
                option.value = paciente.id;
                option.textContent = ${paciente.nombre} ${paciente.apellido};
                selectPaciente.appendChild(option);
            });
        })
        .catch(error => console.error('Error fetching pacientes:', error));

    fetch('http://localhost:8080/odontologos')  // Cambia esta URL a la ruta de tu API
        .then(response => response.json())
        .then(data => {
            const selectOdontologo = document.getElementById('odontologo');
            data.forEach(odontologo => {
                const option = document.createElement('option');
                option.value = odontologo.id;
                option.textContent = ${odontologo.nombre} ${odontologo.apellido} (${odontologo.matricula});
                selectOdontologo.appendChild(option);
            });
        })
        .catch(error => console.error('Error fetching odontologos:', error));
});