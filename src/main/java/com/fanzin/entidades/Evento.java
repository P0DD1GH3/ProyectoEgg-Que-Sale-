package com.fanzin.entidades;

import com.fanzin.enumeraciones.ActividadesEvento;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NonNull
    private String titulo;

    @ManyToOne
    private Usuario organizador;

    @NonNull
    private String contenido;

    @NonNull
    private String direccion;

    @NonNull
    private String valor;

    @OneToOne
    private Imagen imagen;

    @Enumerated(EnumType.STRING)
    private ActividadesEvento actividad;

    @NonNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

}
