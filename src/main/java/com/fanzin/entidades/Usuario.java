package com.fanzin.entidades;

import com.fanzin.enumeraciones.Rol;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NonNull
    private String nombre;

    @NonNull
    private String mail;

    @NonNull
    private String contrasenia;

    @OneToOne
    private Imagen imagen;
    
    @Enumerated(EnumType.STRING)
    private Rol rol;
    
    //nuevos
    @Enumerated(EnumType.STRING)
    private String actividad;
    @NonNull
    private String descripcion;
    @NonNull
    private String facebook;
    @NonNull
    private String instagram;
    @NonNull
    private String twitter;
    @NonNull
    private String youtube;
}
