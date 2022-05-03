package com.fanzin.servicios;

import com.fanzin.repositorios.EventoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventoServicio {

    @Autowired
    private EventoRepositorio eventoRepositorio;
}
