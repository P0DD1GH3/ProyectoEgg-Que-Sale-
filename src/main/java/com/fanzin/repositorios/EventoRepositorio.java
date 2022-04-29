package com.fanzin.repositorios;

import com.fanzin.entidades.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepositorio extends JpaRepository<Evento, String> {

}
