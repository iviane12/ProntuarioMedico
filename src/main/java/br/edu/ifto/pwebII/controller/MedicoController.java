package br.edu.ifto.pwebII.controller;

import br.edu.ifto.pwebII.model.entity.Medico;
import br.edu.ifto.pwebII.model.repository.MedicoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("medico")
public class MedicoController {

    private final MedicoRepository repository;

    public MedicoController(MedicoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/form")
    public ModelAndView form(Medico medico) {
        return new ModelAndView("medico/form");
    }

    @GetMapping("/list")
    public ModelAndView list(ModelMap model) {
        model.addAttribute("medicos", repository.listar());
        return new ModelAndView("medico/list", model);
    }

    @PostMapping("/save")
    public ModelAndView save(Medico medico) {
        repository.save(medico);
        return new ModelAndView("redirect:/medico/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("medico", repository.buscar(id));
        return new ModelAndView("medico/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(Medico medico) {
        repository.update(medico);
        return new ModelAndView("redirect:/medico/list");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        repository.remove(id);
        return new ModelAndView("redirect:/medico/list");
    }
}
