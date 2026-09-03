package br.edu.ifto.pwebII.controller;

import br.edu.ifto.pwebII.model.entity.Consulta;
import br.edu.ifto.pwebII.model.repository.ConsultaRepository;
import br.edu.ifto.pwebII.model.repository.MedicoRepository;
import br.edu.ifto.pwebII.model.repository.PacienteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("consulta")
public class ConsultaController {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    // Injetamos os três repositórios para conseguirmos listar pacientes e médicos na hora do cadastro
    public ConsultaController(ConsultaRepository consultaRepository,
                              PacienteRepository pacienteRepository,
                              MedicoRepository medicoRepository) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    @GetMapping("/form")
    public ModelAndView form(Consulta consulta, ModelMap model) {
        // Mandamos a lista de pacientes e médicos para a View montar as caixas de seleção (select)
        model.addAttribute("pacientes", pacienteRepository.listar());
        model.addAttribute("medicos", medicoRepository.listar());
        return new ModelAndView("consulta/form", model);
    }

    @GetMapping("/list")
    public ModelAndView list(ModelMap model) {
        model.addAttribute("consultas", consultaRepository.listar());
        return new ModelAndView("consulta/list", model);
    }

    @PostMapping("/save")
    public ModelAndView save(Consulta consulta) {
        consultaRepository.save(consulta);
        return new ModelAndView("redirect:/consulta/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("consulta", consultaRepository.buscar(id));
        // Na hora de editar, também precisamos carregar as listas para o dropdown
        model.addAttribute("pacientes", pacienteRepository.listar());
        model.addAttribute("medicos", medicoRepository.listar());
        return new ModelAndView("consulta/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(Consulta consulta) {
        consultaRepository.update(consulta);
        return new ModelAndView("redirect:/consulta/list");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        consultaRepository.remove(id);
        return new ModelAndView("redirect:/consulta/list");
    }
}