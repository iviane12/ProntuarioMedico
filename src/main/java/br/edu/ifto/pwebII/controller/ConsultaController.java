package br.edu.ifto.pwebII.controller;

import br.edu.ifto.pwebII.model.entity.Consulta;
import br.edu.ifto.pwebII.model.repository.ConsultaRepository;
import br.edu.ifto.pwebII.model.repository.MedicoRepository;
import br.edu.ifto.pwebII.model.repository.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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

    public ConsultaController(ConsultaRepository consultaRepository,
                              PacienteRepository pacienteRepository,
                              MedicoRepository medicoRepository) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    @GetMapping("/form")
    public ModelAndView form(Consulta consulta, ModelMap model) {
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
    public ModelAndView save(@Valid Consulta consulta, BindingResult result, ModelMap model) {
        // Mudado para 'paciente.id' e 'medico.id' para o Thymeleaf reconhecer na view
        if (consulta.getPaciente() == null || consulta.getPaciente().getId() == null) {
            result.rejectValue("paciente.id", "error.paciente", "Selecione um paciente.");
        }
        if (consulta.getMedico() == null || consulta.getMedico().getId() == null) {
            result.rejectValue("medico.id", "error.medico", "Selecione um médico.");
        }

        if (result.hasErrors()) {
            model.addAttribute("pacientes", pacienteRepository.listar());
            model.addAttribute("medicos", medicoRepository.listar());
            return new ModelAndView("consulta/form", model);
        }

        consulta.setPaciente(pacienteRepository.buscar(consulta.getPaciente().getId()));
        consulta.setMedico(medicoRepository.buscar(consulta.getMedico().getId()));

        consultaRepository.save(consulta);
        return new ModelAndView("redirect:/consulta/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("consulta", consultaRepository.buscar(id));
        model.addAttribute("pacientes", pacienteRepository.listar());
        model.addAttribute("medicos", medicoRepository.listar());
        return new ModelAndView("consulta/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid Consulta consulta, BindingResult result, ModelMap model) {
        if (consulta.getPaciente() == null || consulta.getPaciente().getId() == null) {
            result.rejectValue("paciente.id", "error.paciente", "Selecione um paciente.");
        }
        if (consulta.getMedico() == null || consulta.getMedico().getId() == null) {
            result.rejectValue("medico.id", "error.medico", "Selecione um médico.");
        }

        if (result.hasErrors()) {
            model.addAttribute("pacientes", pacienteRepository.listar());
            model.addAttribute("medicos", medicoRepository.listar());
            return new ModelAndView("consulta/form", model);
        }

        consulta.setPaciente(pacienteRepository.buscar(consulta.getPaciente().getId()));
        consulta.setMedico(medicoRepository.buscar(consulta.getMedico().getId()));

        consultaRepository.update(consulta);
        return new ModelAndView("redirect:/consulta/list");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        consultaRepository.remove(id);
        return new ModelAndView("redirect:/consulta/list");
    }
}