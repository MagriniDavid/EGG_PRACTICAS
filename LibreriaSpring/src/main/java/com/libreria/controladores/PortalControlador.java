package com.libreria.controladores;

import com.libreria.entidades.Autor;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PortalControlador {
    @GetMapping("/index2")
    public String LIBRO(){
        return "index2.html";
    }
    @PostMapping("index3")
    public String cargarLibro(ModelMap modelo){
try{
//            autorServicio.
        }catch(Exception e){
            }
        return "index3.html";
}
    @GetMapping("/index4")
    public String AUTOR(){
        return "index3.html";
    }
    @PostMapping("index4")
    public String cargarAutor(@ModelAttribute Autor autor){
        try{
//            autorServicio.
        }catch(Exception e){
            }
        return "index3.html";
}
    
    @GetMapping("/index4")
    public String EDITORIAL(){
        return "index4.html";
    }
}
