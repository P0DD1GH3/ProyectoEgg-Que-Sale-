package com.fanzin.controladores;

import com.fanzin.entidades.Evento;
import com.fanzin.entidades.Usuario;
import com.fanzin.servicios.EventoServicio;
import com.fanzin.servicios.ImagenServicio;
import com.fanzin.servicios.UsuarioServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/imagen")
public class ImagenControlador {

    @Autowired
    private ImagenServicio imagenServicio;
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @Autowired
    private EventoServicio eventoServicio;
    
    @GetMapping("/usuario")
    public ResponseEntity<byte[]> imagenUsuario(@RequestParam String id){
        try {
            Usuario usuario = usuarioServicio.buscarPorId(id);
            byte[] imagen = usuario.getImagen().getContenido();
            
            if(usuario.getImagen()== null){
                throw new Exception();
            }
            
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.IMAGE_JPEG);
            
            return new ResponseEntity<>(imagen,header,HttpStatus.OK);
        } catch (Exception ex) {
            Logger.getLogger(ImagenControlador.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/evento")
    public ResponseEntity<byte[]> imagenEvento(@RequestParam String id){
        try {
            Evento evento = eventoServicio.buscarPorId(id);
            byte[] imagen = evento.getImagen().getContenido();
            
            if(evento.getImagen()== null){
                throw new Exception();
            }
            
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.IMAGE_JPEG);
            
            return new ResponseEntity<>(imagen,header,HttpStatus.OK);
        } catch (Exception ex) {
            Logger.getLogger(ImagenControlador.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
   
    
    
    
    
}
