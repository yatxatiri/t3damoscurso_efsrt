package com.t3damoscurso.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.t3damoscurso.interfaces.IProfesorRepository;
import com.t3damoscurso.models.Profesor;

import java.util.List;

@Controller
@RequestMapping("/admin-professor")
public class AdminProfesorController {

    @Autowired
    private IProfesorRepository profe;

    @GetMapping("/list")
    public String listarProfesores(Model model) {
        List<Profesor> listaProfesores = profe.findAll();
        model.addAttribute("listaProfesor", listaProfesores);
        return "admin-professor-list"; 
    }

    @GetMapping("/create")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("profe", new Profesor());
        return "admin-professor-create"; 
    }

    @PostMapping("/create")
    public String registrarProfesor(@ModelAttribute Profesor profesor, RedirectAttributes redirectAttributes) {
        try {
            profe.save(profesor);
            redirectAttributes.addFlashAttribute("mensaje", "Profesor registrado exitosamente");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "El código del profesor ya está en uso.");
            return "redirect:/admin-professor/create";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al registrar el profesor: " + e.getMessage());
            return "redirect:/admin-professor/create"; 
        }
        return "redirect:/admin-professor/list";
    }

    @GetMapping("/edit/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
        Profesor profesor = profe.findById(id).orElse(null);
        if (profesor == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Profesor no encontrado.");
            return "redirect:/admin-professor/list";
        }
        model.addAttribute("profe", profesor);
        return "admin-professor-edit"; 
    }

    @PostMapping("/edit/{id}")
    public String editarProfesor(@PathVariable("id") int id, @ModelAttribute Profesor profesor, RedirectAttributes redirectAttributes) {
        try {
            profesor.setId_profesor(id); 
            profe.save(profesor);
            redirectAttributes.addFlashAttribute("mensaje", "Profesor actualizado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al actualizar el profesor: " + e.getMessage());
            return "redirect:/admin-professor/edit/" + id;
        }
        return "redirect:/admin-professor/list";
    }

    @GetMapping("/delete/{id}")
    public String eliminarProfesor(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            profe.deleteById(id);
            redirectAttributes.addFlashAttribute("mensaje", "Profesor eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar el profesor: " + e.getMessage());
        }
        return "redirect:/admin-professor/list"; 
    }
}
