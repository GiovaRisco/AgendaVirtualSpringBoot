/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.giovanni.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.giovanni.model.Contacto;


public interface ContactoRepository extends JpaRepository<Contacto, Integer>{
    Page<Contacto> findByNombreContaining(String nombre,Pageable pageable );
}
