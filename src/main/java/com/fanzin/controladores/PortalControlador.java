package com.fanzin.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @GetMapping("/")
    public String index() {
        return "index.html";
    }
    
    @GetMapping("/login")
    public String login(){
        return "Login.html";
        }

    
    @GetMapping("/register")
    public String register(){
        return "artist-form.html";
        }
    
    
    @PostMapping("/registrar")
    public String registrar(@RequestParam String nombre, @RequestParam String mail, @RequestParam String contraseña, @RequestParam String contraseña1, MultipartFile archivo){
        System.out.print("Nombre" + nombre);
        System.out.print("Mail" + mail);
        System.out.print("Contraseña" + contraseña);
        System.out.print("Contraseña1" + contraseña1);
   

        return "artist-form.html";
    }
    
}
