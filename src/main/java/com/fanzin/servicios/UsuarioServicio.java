package com.fanzin.servicios;

import com.fanzin.entidades.Imagen;
import com.fanzin.entidades.Usuario;
import com.fanzin.enumeraciones.ActividadesEvento;
import com.fanzin.enumeraciones.Rol;
import com.fanzin.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private ImagenServicio imagenServicio;

    @Transactional(rollbackFor = Exception.class)
    public Usuario crear(String nombre, String mail, String contrasenia, String contrasenia1, MultipartFile archivo, 
            ActividadesEvento actividad, String descripcion,String facebook,String twitter,String youtube,String instagram) throws Exception {

        validar(nombre, mail, contrasenia, contrasenia1,descripcion);
        
        
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setMail(mail); String contraseniaEncriptada = new BCryptPasswordEncoder().encode(contrasenia);
        usuario.setContrasenia(contraseniaEncriptada);
       
        usuario.setRol(Rol.USUARIO);
        usuario.setActividad(actividad);
        usuario.setDescripcion(descripcion);
        usuario.setFacebook(facebook);
        usuario.setTwitter(twitter);
        usuario.setInstagram(instagram);
        usuario.setYoutube(youtube);
        Imagen imagen = imagenServicio.guardar(archivo);
        usuario.setImagen(imagen);
//        if (actividad.toString().isEmpty()) {
//            usuario.setActividad(actividad.Otro.toString());
//        } else {
//            //dudoso toString (if)
//            usuario.setActividad(actividad.toString());
//        }

        return usuarioRepositorio.save(usuario);

    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(String id) throws Exception {

        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            return usuario;
        } else {
            throw new Exception("[!] - No se encontro el Usuario indicado");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Usuario modificar(String id, String nombre, String mail, String contrasenia, String contrasenia1, MultipartFile archivo,String descripcion, String facebook,String twitter,String youtube,String instagram) throws Exception {

        validar(nombre, mail, contrasenia, contrasenia,descripcion);

        Usuario usuario = buscarPorId(id);
        usuario.setNombre(nombre);
        usuario.setMail(mail);
        usuario.setContrasenia(contrasenia);
        String idImagen = null;
        if (usuario.getImagen() != null) {
            idImagen = usuario.getImagen().getId();
        }
        Imagen imagen = imagenServicio.modificar(idImagen, archivo);
        usuario.setImagen(imagen);
        usuario.setFacebook(facebook);
        usuario.setTwitter(twitter);
        usuario.setYoutube(youtube);
        usuario.setInstagram(instagram);

        return usuarioRepositorio.save(usuario);
    }
       public Usuario modificarCommon(String id,ActividadesEvento actividad,String descripcion, String facebook,String twitter,String youtube,String instagram) throws Exception {


        Usuario usuario = buscarPorId(id);
        usuario.setDescripcion(descripcion);
        usuario.setActividad(actividad);
        usuario.setFacebook(facebook);
        usuario.setTwitter(twitter);
        usuario.setYoutube(youtube);
        usuario.setInstagram(instagram);

        return usuarioRepositorio.save(usuario);
    }


    @Transactional(rollbackFor = Exception.class)
    public void Eliminar(String id) throws Exception {

        Usuario usuario = buscarPorId(id);

        usuarioRepositorio.delete(usuario);
    }

    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        return usuarioRepositorio.findAll();
    }

    private void validar(String nombre, String mail, String contrasenia, String contrasenia1,String descripcion)throws Exception { 

        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("Nombre vacío");
        }

        if (mail == null || mail.isEmpty()) {
            throw new Exception("E-mail vacío");
        }

        if (contrasenia == null || contrasenia.isEmpty()) {
            throw new Exception("Contraseña vacío");
        }

        if (contrasenia1 == null || contrasenia1.isEmpty()) {
            throw new Exception("Contraseña vacío");
        }

        if (!contrasenia.equals(contrasenia1)) {
            throw new Exception("No coinciden las contraseñas");
        }
        if(descripcion==null || descripcion.isEmpty()){
            throw new Exception("Descripcion vacia");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Usuario u = usuarioRepositorio.buscarPorEmail(mail);

        if (u == null) {
            return null;
        }

        List<GrantedAuthority> permisos = new ArrayList<>();

        GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_" + u.getRol().toString());
        permisos.add(p1);

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        HttpSession session = attr.getRequest().getSession(true);
        session.setAttribute("usuariosession", u);

        return new User(u.getMail(), u.getContrasenia(), permisos);

    }

}
