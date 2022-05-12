package com.fanzin.controladores;

import com.fanzin.entidades.Usuario;
import com.fanzin.servicios.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/form")
    public String form() {
        return "artist-form.html";
    }

    @PostMapping("/form")
    public String crear(ModelMap modelo, @RequestParam String nombre, @RequestParam String mail, @RequestParam String contrasenia, @RequestParam String contrasenia1) {
        try {
            usuarioServicio.crear(nombre, mail, contrasenia, contrasenia1, null);
            modelo.put("exito", "Se cargó exitosamente");
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("mail", mail);

            return "artist-form.html";
        }

        return "artist-form.html";
    }

    @GetMapping("/listar")
    public String listarUsuario(ModelMap modelo) {
        List<Usuario> usuarios = usuarioServicio.listar();

        modelo.put("usuarios", usuarios);

        return ".html";
    }

}
