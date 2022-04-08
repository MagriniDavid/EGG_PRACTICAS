/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.repositorios;

import com.libreria.entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author David
 */
public interface AutorRepositorio extends JpaRepository<Autor, String>{
    
}
