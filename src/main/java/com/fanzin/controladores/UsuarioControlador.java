package com.fanzin.controladores;

import com.fanzin.entidades.Usuario;
import com.fanzin.enumeraciones.ActividadesEvento;
import com.fanzin.servicios.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/form")
    public String form() {
        return "artist-form-Artista.html";
    }

    @GetMapping("/login")
    public String login(ModelMap modelo, @RequestParam(required = false) String error, @RequestParam(required = false) String logout) {

        if (error != null) {
            modelo.put("error", "Usuario o clave incorrectos");
        }
        if (logout != null) {
            modelo.put("logout", "Has cerrado sesion exitosamente");
        }

        return "login.html";
    }

    @PostMapping("/form")
    public String crear(ModelMap modelo, @RequestParam String nombre, @RequestParam String mail, @RequestParam String contrasenia, MultipartFile archivo, @RequestParam String contrasenia1,
            @RequestParam String descripcion, @RequestParam ActividadesEvento actividad, String facebook, String twitter, String youtube, String instagram) {
        try {
            usuarioServicio.crear(nombre, mail, contrasenia, contrasenia1, archivo, actividad, descripcion, facebook, twitter, youtube, instagram);
            modelo.put("exito", "Se cargó exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
            modelo.put("error", e.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("mail", mail);
            modelo.put("archivo", archivo);
            modelo.put("actividad", actividad);
            modelo.put("descripcion", descripcion);
            modelo.put("facebook", facebook);
            modelo.put("twitter", twitter);
            modelo.put("instagram", instagram);
            modelo.put("youtube", youtube);

            return "artist-form-Artista.html";
        }

        return "artist-form-Artista.html";

    }

    @GetMapping("/listar")
    public String listarUsuario(ModelMap modelo) {
        List<Usuario> usuarios = usuarioServicio.listar();

        modelo.put("usuarios", usuarios);

        return "artist-list.html";
    }

}
