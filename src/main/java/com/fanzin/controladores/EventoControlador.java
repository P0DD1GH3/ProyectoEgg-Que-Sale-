package com.fanzin.controladores;

import com.fanzin.servicios.EventoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/evento")
public class EventoControlador {

    @Autowired
    private EventoServicio eventoServicio;

}
