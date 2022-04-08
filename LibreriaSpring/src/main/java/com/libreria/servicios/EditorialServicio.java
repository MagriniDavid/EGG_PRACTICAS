/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.servicios;

import com.libreria.entidades.Editorial;
import com.libreria.repositorios.EditorialRepositorio;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditorialServicio {

    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional(readOnly = true)/*este transactional es: este metodo no deberia dar error por que solo va a leer pero si sale mal volver atras*/
    public Editorial buscarPorId(String id) throws Exception {

        Optional<Editorial> respuesta = editorialRepositorio.findById(id);/*eL OPTIONAL es un chequeo de informacion si es null o no*/
        if (respuesta.isPresent())/*el is present reemplaza el = a null nos retorna true o false*/ {
            Editorial editorial = respuesta.get();
            /*creamos un objeto libro para agarrar la respuesta */
            return editorial;/* tambien se puede poner return respuesta.get() .. es lo mismo*/
        } else {
            throw new Exception("No existe ese libro.");/*si no exste un libro lanza esta excepcion*/
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public Editorial crear(String nombre) {
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorial.setAlta(new Date());
        return editorialRepositorio.save(editorial);
    }

}
