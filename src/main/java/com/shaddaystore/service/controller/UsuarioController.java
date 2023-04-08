package com.shaddaystore.service.controller;

import com.shaddaystore.service.entity.Usuario;
import com.shaddaystore.service.service.UsuarioService;
import com.shaddaystore.service.utlis.GenericResponse;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }
    @PostMapping("/login")
    public GenericResponse<Usuario> login(HttpServletRequest request){
        String email = request.getParameter("email");
        String contrasenia = request.getParameter("pass");
        return this.service.login(email, contrasenia);
    }

    @PostMapping
    /* método guardar usuario*/
    public GenericResponse save(@RequestBody Usuario u){
        return this.service.guardarUsuario(u);
    }
    @PutMapping("/{id}")
    /*método actualizar usuario*/
    public GenericResponse update(@PathVariable int id, @RequestBody Usuario u){
        return this.service.guardarUsuario(u);
    }

    @PostMapping("/recovery")
    public GenericResponse<Usuario> recovery(HttpServletRequest request){
        String email = request.getParameter("email");
        return this.service.recovery(email);
    }
}
