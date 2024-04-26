package com.atl.curso.controllers;

import com.atl.curso.dao.UsuarioDao;
import com.atl.curso.models.Usuario;
import com.atl.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public String iniciarSesion(@RequestBody Usuario usuario){

        Usuario usuarioLogueado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);

        if(usuarioLogueado != null){
            String tokenJwt = jwtUtil.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getEmail());
            return tokenJwt;
        }else
            return "Fail";
    }
}
