package com.atl.curso.dao;

import com.atl.curso.models.Usuario;

import java.util.List;

public interface UsuarioDao {
    List<Usuario> getUsuarios();

    Usuario getUsuario();

    void eliminarUsuario(Long id);

    void regisrar(Usuario usuario);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}
