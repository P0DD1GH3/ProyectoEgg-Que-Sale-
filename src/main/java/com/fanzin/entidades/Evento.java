package com.fanzin.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class Evento {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne
    private Usuario usuario;

    @NonNull
    private String contenido;

    @NonNull
    private String direccion;

    @NonNull
    private String valor;

    @NonNull
    @OneToOne
    private Imagen imagen;

    @NonNull
    private String fecha;

}
