package com.fanzin.servicios;

import com.fanzin.entidades.Evento;
import com.fanzin.entidades.Imagen;
import com.fanzin.entidades.Usuario;
import com.fanzin.repositorios.EventoRepositorio;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventoServicio {

    @Autowired
    private EventoRepositorio eventoRepositorio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ImagenServicio imagenServicio;

    @Transactional(rollbackFor = Exception.class)
    public void guardar(String idOrganizador, String contenido, String direccion, String valor, String idImagen, Date fecha) {

        Evento evento = new Evento();
        Usuario usuario = usuarioServicio.buscarPorId(idOrganizador);
        evento.setOrganizador(usuario);
        evento.setContenido(contenido);
        evento.setDireccion(direccion);
        evento.setValor(valor);
        Imagen imagen = imagenServicio.buscarPorId(idImagen);
        evento.setImagen(imagen);
        evento.setFecha(fecha);

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
    public void modificarEvento(String id, String contenido, String direccion, String valor, Imagen imagen, Date fecha) throws Exception {

        Optional<Evento> response = eventoRepositorio.findById(id);
        if (response.isPresent()) {
            Evento evento = response.get();
            evento.setContenido(contenido);
            evento.setDireccion(direccion);
            evento.setValor(valor);
            evento.setImagen(imagen);
            evento.setFecha(fecha);

            eventoRepositorio.save(evento);

        } else {
            throw new Exception("No se encontro el evento que desea modificar");
        }
    }

}
