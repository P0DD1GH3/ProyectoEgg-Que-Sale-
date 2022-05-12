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
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;
  
     @GetMapping("/usuario")
    public String Usuario(ModelMap modelo) {
        List<Usuario> usuarios = usuarioServicio.listar();

        modelo.put("usuarios", usuarios);

        return ".html";
    }

    @GetMapping("/listar")
    public String listarUsuario(ModelMap modelo) {
        List<Usuario> usuarios = usuarioServicio.listar();


        modelo.put("usuarios", usuarios);
        
        return ".html";
    }
    
    
    
    @PostMapping("/")
    public String registrarUsuario(ModelMap modelo, @RequestParam String nombre, @RequestParam String mail,@RequestParam String contraseña, MultipartFile archivo){
        
         try {
            usuarioServicio.crear(nombre, mail, contraseña, archivo);
            modelo.put("exito", "Se guardó correctamente");
        } catch (Exception e) {
            modelo.put("error",e.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("mail", mail);
            modelo.put("contraseña", contraseña);
            //CUIDADO CON LA IMAGEN VER VIDEO
            modelo.put("archivo", archivo);
            return "html";
        }
        return "html";
        
        
        
    }
    
    
    
  

}
