/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.giovanni.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Contacto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idContacto;
    
    @NotEmpty(message = "Este campo es obligatorio")
    private String nombre;
    
    @PastOrPresent(message = "Este valor no puede ser futuro")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaNacimiento;
    
    @NotEmpty(message = "Este campo debe tener 15 numeros como maximo")
    @Size(max = 15)
    private String telefono;
    
    @Email(message = "Este campo debe tener formato de correo")
    private String email;
    
    private LocalDateTime fechaRegistro;
}
