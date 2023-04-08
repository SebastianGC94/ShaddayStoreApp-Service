package com.shaddaystore.service.service;

import com.shaddaystore.service.entity.Usuario;
import com.shaddaystore.service.repository.UsuarioRepository;
import com.shaddaystore.service.utlis.GenericResponse;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.shaddaystore.service.utlis.Global.*;

@Service
@Transactional
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }
    //Método para iniciar sesión
    public GenericResponse<Usuario> login(String email, String contrasenia){
        Optional<Usuario> optU = this.repository.login(email, contrasenia);
        if(optU.isPresent()){
            return new GenericResponse<Usuario>(TIPO_AUTH, RPTA_OK, "¡Inicio de sesión exitoso!", optU.get());
        }else{
            return new GenericResponse<Usuario>(TIPO_AUTH, RPTA_WARNING, "Lo sentimos, el usuario no existe.", new Usuario());
        }
    }
    public GenericResponse<Usuario> recovery(String email){
        Optional<Usuario> optU = this.repository.recovery(email);
        if(optU.isPresent()){
            return new GenericResponse<Usuario>(TIPO_AUTH, RPTA_OK, "¡Validación de correo exitosa!", optU.get());
        }else{
            return new GenericResponse<Usuario>(TIPO_AUTH, RPTA_WARNING, "Lo sentimos, el correo no existe.", new Usuario());
        }
    }
    //Método para guardar credenciales del usuario
    public GenericResponse guardarUsuario(Usuario u){
        Optional<Usuario> optU = this.repository.findById(u.getId());
        int idf = optU.isPresent() ? optU.get().getId() : 0;
        if(idf == 0){
            return new GenericResponse(TIPO_DATA, RPTA_OK, "Usuario registrado correctamente.", this.repository.save(u));
        }else{
            return new GenericResponse(TIPO_DATA, RPTA_OK, "Datos del usuario actualizados.", this.repository.save(u));
        }
    }
}
