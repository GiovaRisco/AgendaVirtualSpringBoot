/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.giovanni.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.giovanni.model.Contacto;
import pe.giovanni.service.ContactoServiceImpl;

@Controller
public class ContactoController {
    
    @Autowired
    private ContactoServiceImpl contactoServicio;
    
    @GetMapping("/")
    public String getContactos(@PageableDefault(size = 4 , sort = "fechaRegistro",direction = Sort.Direction.DESC)Pageable pageable,ModelMap model,
            @RequestParam(required = false) String busqueda){
        
        Page<Contacto> contactosPage;
        if(busqueda != null && busqueda.trim().length() > 0){
            contactosPage = contactoServicio.buscarContactoPorNombre(busqueda, pageable);
        }else{
            contactosPage = contactoServicio.listarContactos(pageable);
        }
        
        model.put("contactos", contactosPage);
        return "index";
    }
    
    @GetMapping("/nuevo")
    public String getNuevoForm(ModelMap model){
        Contacto contacto = new Contacto();
        model.put("contacto", contacto);
        return "nuevo";
    }
    
    @PostMapping("/nuevo")
    public String crearContacto(ModelMap model,@Validated Contacto contacto,BindingResult bindingResult,
            RedirectAttributes ra){
        if(bindingResult.hasErrors()){
            model.put("contacto", contacto);
        return "nuevo";
        }
        contacto.setFechaRegistro(LocalDateTime.now());
        contactoServicio.guardarContacto(contacto);
        ra.addFlashAttribute("msgExito", "El contacto " + contacto.getNombre() + " se ha creado correctamente");
        return "redirect:/";
    }
    
     @GetMapping("/editar/{id}")
    public String getEditarForm(@PathVariable("id")int id ,ModelMap model ){
        Contacto contacto = contactoServicio.buscarContacto(id);
        model.put("contacto", contacto);
        return "editar";
    }
    
    @PostMapping("/editar/{id}")
    public String editarContacto(@PathVariable("id")int id,ModelMap model,
            @Validated Contacto contacto,BindingResult bindingResult,RedirectAttributes ra){
        Contacto contactoFromDB = contactoServicio.buscarContacto(id);
        
        if(bindingResult.hasErrors()){
            model.put("contacto", contacto);
            return "/editar";
        }
        
        contactoFromDB.setNombre(contacto.getNombre());
        contactoFromDB.setFechaNacimiento(contacto.getFechaNacimiento());
        contactoFromDB.setTelefono(contacto.getTelefono());
        contactoFromDB.setEmail(contacto.getEmail());
         contactoFromDB.setFechaRegistro(LocalDateTime.now());
        
        contactoServicio.guardarContacto(contactoFromDB);
        ra.addFlashAttribute("msgExito", "El contacto " + contactoFromDB.getNombre() + " se ha actualizado correctamente");
        return "redirect:/";
    }
    
    
    @PostMapping("/eliminar/{id}")
    public String eliminarContacto(@PathVariable("id")int id,RedirectAttributes ra){
        
      
        Contacto contactoFromDB = contactoServicio.buscarContacto(id);
       
        contactoServicio.eliminarContacto(contactoFromDB);
        ra.addFlashAttribute("msgExito", "El contacto " + contactoFromDB.getNombre() + " se ha eliminado correctamente");
        return "redirect:/";
    }
    
    
}
