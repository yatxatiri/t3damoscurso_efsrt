package com.t3damoscurso.controllers;

import com.t3damoscurso.interfaces.IUsuarioRepository;
import com.t3damoscurso.interfaces.IPlanRepository;
import com.t3damoscurso.models.Plan;
import com.t3damoscurso.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin-user")
public class AdminUserController {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IPlanRepository planRepository;

    @GetMapping("/list")
    public String listarUsuarios(Model model) {
        model.addAttribute("listUsuarios", usuarioRepository.findAll());
        return "admin-user-list"; // Archivo HTML correspondiente
    }

    @GetMapping("/create")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("user", new Usuario());
        model.addAttribute("planes", planRepository.findAll()); // Cargar planes desde el repositorio
        return "admin-user-create"; // Archivo HTML correspondiente
    }

    @PostMapping("/create")
    public String crearUsuario(@ModelAttribute("user") Usuario usuario, Model model) {
        try {
            // Verificar que el usuario haya seleccionado un plan válido
            Plan plan = planRepository.findById(usuario.getObjPlan().getId_plan()).orElse(null);
            if (plan != null) {
                usuario.setObjPlan(plan);  // Asignar el plan al usuario
            } else {
                model.addAttribute("errorMessage", "Por favor, selecciona un plan válido.");
                return "admin-user-create";
            }

            // Guardar el usuario con el plan asignado
            usuarioRepository.save(usuario);
            return "redirect:/admin-user/list";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error al crear el usuario.");
            return "admin-user-create";
        }
    }


    @GetMapping("/edit/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) {
            return "redirect:/admin-user/list";
        }
        model.addAttribute("usuario", usuario);
        model.addAttribute("planes", planRepository.findAll()); // Cargar planes desde el repositorio
        return "admin-user-edit"; // Archivo HTML correspondiente
    }

    @PostMapping("/edit/{id}")
    public String actualizarUsuario(@PathVariable("id") Integer id, @ModelAttribute("usuario") Usuario usuario, Model model) {
        try {
            // Buscar el usuario existente en la base de datos
            Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);
            if (usuarioExistente == null) {
                model.addAttribute("errorMessage", "Usuario no encontrado.");
                return "admin-user-edit";
            }

            // Actualizar los campos del usuario existente con los datos del formulario
            usuarioExistente.setNombre(usuario.getNombre());
            usuarioExistente.setCorreo(usuario.getCorreo());  // Asegúrate de que no se repita el correo
            usuarioExistente.setRol(usuario.getRol());

            // Mantener la contraseña anterior si no se ha cambiado
            if (usuario.getContrasena() == null || usuario.getContrasena().isEmpty()) {
                usuarioExistente.setContrasena(usuarioExistente.getContrasena());
            } else {
                usuarioExistente.setContrasena(usuario.getContrasena());
            }

            // Asignar el plan seleccionado
            Plan plan = planRepository.findById(usuario.getObjPlan().getId_plan()).orElse(null);
            usuarioExistente.setObjPlan(plan);

            // Guardar el usuario actualizado
            usuarioRepository.save(usuarioExistente);

            return "redirect:/admin-user/list";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error al actualizar el usuario.");
            return "admin-user-edit";
        }
    }


    @GetMapping("/delete/{id}")
    public String eliminarUsuario(@PathVariable("id") Integer id) {
        usuarioRepository.deleteById(id);
        return "redirect:/admin-user/list";
    }
}
