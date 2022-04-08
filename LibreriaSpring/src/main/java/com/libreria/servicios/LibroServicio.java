package com.libreria.servicios;
import com.libreria.entidades.Autor;
import com.libreria.entidades.Editorial;
import com.libreria.entidades.Libro;
import com.libreria.repositorios.LibroRepositorio;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class LibroServicio {
    @Autowired /*crea una instancia en espacio en memoria o usa una instancia que este guardada*/
    private LibroRepositorio librorepositorio;/*Esto es INYECCION DE DEPENDENCIA de librorepositorio
      ---------------------------------------- con esto podemos usar las querys que estan por defaul o armar una*/
    @Autowired
    private AutorServicio autorServicio;
    
    @Autowired
    private EditorialServicio editorialServicio;
    
    @Transactional(rollbackFor = Exception.class)/*es para un error de base de datos. transactional guarda un punto en la historia y si algo sale mal vuelve al punto donde se guardo*/
    public Libro crear(String titulo, Long isbn, String idAutor, String idEditorial) throws Exception{
        Libro libro = new Libro();
        
        libro.setTitulo(titulo);
        libro.setIsbn(isbn);
        libro.setAlta(new Date());
        
        Autor autor = autorServicio.buscarPorId(idAutor);
        libro.setAutor(autor);
        
        Editorial editorial = editorialServicio.buscarPorId(idEditorial);
        libro.setEditorial(editorial);
        return librorepositorio.save(libro);/* el save hace que si viene con id lo actualiza y sino lo crea */
    }
    
    @Transactional(readOnly = true)/*este transactional es: este metodo no deberia dar error por que solo va a leer pero si sale mal volver atras*/
    public Libro buscarPorId(String id) throws Exception{
        
        Optional<Libro> respuesta = librorepositorio.findById(id);/*eL OPTIONAL es un chequeo de informacion si es null o no*/
        if (respuesta.isPresent())/*el is present reemplaza el = a null nos retorna true o false*/ {
            Libro libro = respuesta.get(); /*creamos un objeto libro para agarrar la respuesta */
            return libro;/* tambien se puede poner return respuesta.get() .. es lo mismo*/
        }else{
            throw new Exception("No existe ese libro.");/*si no exste un libro lanza esta excepcion*/
        }
    }
    
    @Transactional(rollbackFor = Exception.class)
   public Libro modificar(String id, String titulo, Long isbn, String idAutor, String idEditorial) throws Exception{
      
       Optional<Libro> respuesta = librorepositorio.findById(id);
        if (respuesta.isPresent()) {
       Libro libro = librorepositorio.findById(id).get();
       libro.setTitulo(titulo);
       libro.setIsbn(isbn);
       Autor autor = autorServicio.buscarPorId(idAutor);
       libro.setAutor(autor);
       Editorial editorial = editorialServicio.buscarPorId(idEditorial);
       libro.setEditorial(editorial);
       return librorepositorio.save(libro);
        }else{
            throw new Exception("No se encontro ese libro");
        }
   }
   
//   public Libro eliminar (String id)throws Exception{
//       Optional<Libro> respuesta = librorepositorio.findById(id);
//       if (respuesta.isPresent()) {
//           Libro libro = librorepositorio.findById(id).get();
//           return librorepositorio.save(libro);
//       }else{
//            throw new Exception("No se encontro ese libro");
//        }
//   }
    
    
    
    
    /*crear-modificar-eliminar-alta-baja-validar*/
    
    
}
