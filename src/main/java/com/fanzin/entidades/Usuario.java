package com.fanzin.entidades;

import com.fanzin.enumeraciones.Rol;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
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
    private String contraseña;

    @OneToOne
    private Imagen imagen;

    private Rol rol;
}
