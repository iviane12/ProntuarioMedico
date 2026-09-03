package br.edu.ifto.pwebII.controller;

import br.edu.ifto.pwebII.model.entity.Paciente;
import br.edu.ifto.pwebII.model.repository.PacienteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("paciente")
public class PacienteController {

    private final PacienteRepository repository;

    // Injeção de Dependência: O Spring injeta o repositório aqui automaticamente
    public PacienteController(PacienteRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/form")
    public ModelAndView form(Paciente paciente) {
        return new ModelAndView("paciente/form");
    }

    @GetMapping("/list")
    public ModelAndView list(ModelMap model) {
        model.addAttribute("pacientes", repository.listar());
        return new ModelAndView("paciente/list", model);
    }

    @PostMapping("/save")
    public ModelAndView save(Paciente paciente) {
        repository.save(paciente);
        return new ModelAndView("redirect:/paciente/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("paciente", repository.buscar(id));
        return new ModelAndView("paciente/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(Paciente paciente) {
        repository.update(paciente);
        return new ModelAndView("redirect:/paciente/list");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        repository.remove(id);
        return new ModelAndView("redirect:/paciente/list");
    }
}