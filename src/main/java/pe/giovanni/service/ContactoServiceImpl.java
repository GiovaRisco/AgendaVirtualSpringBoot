/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.giovanni.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pe.giovanni.model.Contacto;
import pe.giovanni.repository.ContactoRepository;

@Service
public class ContactoServiceImpl implements IContactoService {

    @Autowired
    private ContactoRepository contactoRepository;

    @Override
    public Page<Contacto> listarContactos(Pageable pageable) {
        Page<Contacto> contactosPage = contactoRepository.findAll(pageable);
        return  contactosPage;
    }

    @Override
    public Contacto buscarContacto(int idContacto) {
        Contacto contacto = contactoRepository.findById(idContacto).orElse(null);
        return contacto;
    }

    @Override
    public void guardarContacto(Contacto contacto) {
        contactoRepository.save(contacto);
    }

    @Override
    public void eliminarContacto(Contacto contacto) {
        contactoRepository.delete(contacto);
    }

    @Override
    public Page<Contacto> buscarContactoPorNombre(String nombre, Pageable pageable) {
        Page<Contacto> contactosPage = contactoRepository.findByNombreContaining(nombre, pageable);
        return  contactosPage;
    }

}
