package com.fanzin.servicios;

import com.fanzin.entidades.Imagen;
import com.fanzin.repositorios.ImagenRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImagenServicio {

    @Autowired
    private ImagenRepositorio imagenRepositorio;

    
    public Imagen guardar(MultipartFile archivo) throws Exception {
        if (archivo != null && archivo.getBytes().length != 0) {
            try {
                Imagen imagen = new Imagen();
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());

                return imagenRepositorio.save(imagen);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public Imagen modificar(String id, MultipartFile archivo) throws Exception {
        if (archivo != null && archivo.getBytes().length != 0) {
            try {
                Imagen imagen = new Imagen();

                if (id != null) {
                    imagen = buscarPorId(id);
                }

                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());

                return imagenRepositorio.save(imagen);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public Imagen buscarPorId(String id) throws Exception {
        Optional<Imagen> response = imagenRepositorio.findById(id);

        if (response.isPresent()) {
            return response.get();
        } else {
            throw new Exception("No se encontro la imagen indicada");
        }
    }

}
