// Call the dataTables jQuery plugin
$(document).ready(function() {
    //ON READY
});

async function registrarUsuario(){
    let datos ={};

    datos.nombre = document.getElementById('txtNombre').value;
    datos.apellido = document.getElementById('txtApellido').value;
    datos.telefono = document.getElementById('txtTelefono').value;
    datos.email = document.getElementById('txtEmail').value;
    datos.password = document.getElementById('txtPassword').value;

    let repetirPassword = document.getElementById('txtRepeatPassword').value;

    if(datos.password != repetirPassword){
        alert("Las contraseñas son diferentes");
        return;
    }
    const request = await fetch('api/usuarios', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)

    });
    alert("Registro exitoso");
    window.location.href="login.html"
}