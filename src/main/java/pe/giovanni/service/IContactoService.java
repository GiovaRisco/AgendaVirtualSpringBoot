/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.giovanni.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.giovanni.model.Contacto;

/**
 *
 * @author xtirp
 */
public interface IContactoService {
    
    public Page<Contacto> listarContactos(Pageable pageable);
    
    public Contacto buscarContacto(int idContacto);
    
    public Page<Contacto> buscarContactoPorNombre(String nombre,Pageable pageable);
    
    public void  guardarContacto(Contacto contacto);
    
    public void eliminarContacto(Contacto contacto);
}
