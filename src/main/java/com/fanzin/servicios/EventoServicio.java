package com.fanzin.servicios;

import com.fanzin.entidades.Evento;
import com.fanzin.entidades.Imagen;
import com.fanzin.entidades.Usuario;
import com.fanzin.enumeraciones.ActividadesEvento;
import com.fanzin.repositorios.EventoRepositorio;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EventoServicio {

    @Autowired
    private EventoRepositorio eventoRepositorio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ImagenServicio imagenServicio;

    @Transactional(rollbackFor = Exception.class)
    public void crear(String idOrganizador, String contenido, String direccion, String valor, MultipartFile archivo, Date fecha, String titulo, ActividadesEvento actividad) throws Exception {

        validar(titulo, contenido, direccion);

        Evento evento = new Evento();
        Usuario usuario = usuarioServicio.buscarPorId(idOrganizador);
        evento.setOrganizador(usuario);
        evento.setTitulo(titulo);
        evento.setContenido(contenido);
        evento.setDireccion(direccion);
        evento.setValor(valor);
        evento.setActividad(actividad);
        Imagen imagen = imagenServicio.guardar(archivo);
        evento.setImagen(null);
        evento.setImagen(imagen);
        evento.setFecha(fecha);

//        if(actividad.toString().isEmpty()){
//            evento.setActividad(ActividadesEvento.OTRO);
//        }else{
//           evento.setActividad(actividad); 
//        }
        eventoRepositorio.save(evento);

    }

    @Transactional(rollbackFor = Exception.class)
    public void eliminar(String id) throws Exception {
        Evento evento = buscarPorId(id);
        eventoRepositorio.delete(evento);
    }

    @Transactional(readOnly = true)
    public Evento buscarPorId(String id) throws Exception {
        Optional<Evento> response = eventoRepositorio.findById(id);

        if (response.isPresent()) {
            return response.get();
        } else {
            throw new Exception("No se encontro el evento indicado");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void modificarEvento(String id, String contenido, String direccion, String valor, MultipartFile archivo, Date fecha) throws Exception {

        Evento evento = buscarPorId(id);
        evento.setContenido(contenido);
        evento.setDireccion(direccion);
        evento.setValor(valor);
        String idImagen = null;
        if (evento.getImagen() != null) {
            idImagen = evento.getImagen().getId();
        }
        Imagen imagen = imagenServicio.modificar(idImagen, archivo);
        evento.setImagen(imagen);
        evento.setFecha(fecha);

        eventoRepositorio.save(evento);

    }

    @Transactional(readOnly = true)
    public List<Evento> listar() {
        return eventoRepositorio.findAll();

    }

    private void validar(String titulo, String contenido, String direccion) throws Exception {

        if (titulo == null || titulo.isEmpty()) {
            throw new Exception("Titulo vacío");
        }
        if (contenido == null || contenido.isEmpty()) {
            throw new Exception("Contenido vacío");
        }

        // falta validar fecha   [!]
        if (direccion == null || direccion.isEmpty()) {
            throw new Exception("Direccion vacía");
        }
    }

}
