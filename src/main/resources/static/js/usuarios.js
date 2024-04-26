// Call the dataTables jQuery plugin
$(document).ready(function() {
  cargarUsuarios();
  $('#usuarios').DataTable();

  actualizarEmailUsuario();
});

function actualizarEmailUsuario(){
  document.getElementById("txt-email-usuario").outerHTML = localStorage.email;
}

async function cargarUsuarios(){
  const request = await fetch('api/usuarios', {
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization': localStorage.token
    },
    //body: JSON.stringify({a: 1, b: 'Textual content'})
  });
  const listaUsuarios = await request.json();

  let listaUsuariosHtml="";
  for (let usr of listaUsuarios){
    let usuarioHtml = `<tr><td>${usr.id}</td><td>${usr.nombre} ${usr.apellido}</td>
                        <td>${usr.email}</td><td>${usr.telefono}</td><td><a href="#" onclick="eliminarUsuario(${usr.id})" class="btn btn-danger btn-circle"><i class="fas fa-trash"></i></a>
                        </td>
                   </tr>`;
    listaUsuariosHtml+=usuarioHtml;
  }
  console.log(listaUsuarios);
  document.querySelector('#usuarios tbody').outerHTML = listaUsuariosHtml;

}

async function eliminarUsuario(id){
  if(confirm("¿Desea eliminar el usuario?")){
    const request = await fetch('api/usuarios/'+id, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': localStorage.token
      },
      //body: JSON.stringify({a: 1, b: 'Textual content'})
    });
    //const usuario = await request.json();
    //console.log(usuario);
    location.reload();
  } else{
    return;
  }

}