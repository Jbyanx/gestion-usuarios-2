package com.atl.curso.controllers;

import com.atl.curso.dao.UsuarioDao;
import com.atl.curso.models.Usuario;
import com.atl.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {
    @Autowired
    UsuarioDao usuarioDao;
    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping("api/usuarios")
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token){
        if(!validarToken(token)){
            return null;
        }

        return usuarioDao.getUsuarios();
    }

    private boolean validarToken(String token){
        String idUsuario = jwtUtil.getKey(token);

        return idUsuario!=null;
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Long id){
        return usuarioDao.getUsuario();
    }

    @RequestMapping(value = "/api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);
        usuarioDao.regisrar(usuario);
    }

    @RequestMapping("usuario")
    public Usuario modificarUsuario(){
        Usuario usuario = new Usuario();
        usuario.setNombre("jabes");
        usuario.setApellido("borre");
        usuario.setEmail("jabes@email.com");
        usuario.setTelefono("3004415689");
        return usuario;
    }

    @RequestMapping(value = "api/usuario/{id}", method = RequestMethod.DELETE)
    public void eliminarUsuario( @RequestHeader(value = "Authorization") String token, @PathVariable Long id){
        if (!validarToken(token)){
            return;
        }
        usuarioDao.eliminarUsuario(id);
    }
}
