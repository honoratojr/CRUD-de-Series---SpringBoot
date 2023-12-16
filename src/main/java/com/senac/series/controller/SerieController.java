package com.senac.series.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.senac.series.entities.Serie;
import com.senac.series.model.SerieRepository;
import com.senac.series.service.SerieService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    public SerieRepository repository;

    @Autowired
    public SerieService service;

    @GetMapping
    public List<Serie> listar() {
        List<Serie> lista = new ArrayList<>();
        lista.add(new Serie(null, "Breaking Bad", "Drama", 2008));
        lista.add(new Serie(null, "Game of Thrones", "Aventura", 2011));
        lista.add(new Serie(null, "Round 6", "Ação", 2022));
        return lista;
    }

/*Lista as séries disponíveis na tela */
    @GetMapping("/lista")
    public ModelAndView listarSeries() {
        List<Serie> series = this.repository.findAll();
        ModelAndView mv = new ModelAndView("/listar-serie");
        mv.addObject("series", series);
        return mv;
    }

/*Este end-point busca o formulário de cadastro de séries */
    @GetMapping("/cadastrar")
    public ModelAndView novaSerie() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("series", new Serie());
        mv.setViewName("/cadastrar-serie");
        return mv;
    }

/*Realiza as validações necessárias de cada campo, e se estiver tudo correto, salva os dados da série no banco de dados */
    @PostMapping("/salvar")
    public ModelAndView salvarProduto(@ModelAttribute("series") 
    @Valid Serie serie, BindingResult result, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors()) {
            mv.setViewName("/cadastrar-serie");
            return mv;
        }
        repository.save(serie);
        attributes.addFlashAttribute("mensagem", "Série adicionada com sucesso!");
        mv.setViewName("redirect:/series/lista");
        return mv;
    }

/*End-point para recuperar as informações da série em um formulário para possibilitar sua edição */
    @GetMapping("/editar/{id}")
	public ModelAndView editarSerie(@PathVariable("id") long id) {
		Serie series = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Série não encontrada: " + id));
        ModelAndView mv = new ModelAndView();
        mv.addObject("series", series);
        mv.setViewName("editar-serie");
        return mv;
    }

/*Método para validar e atualizar as informações no Banco de Dados */
    @PostMapping("/editar/{id}")
    public ModelAndView alterarSerie(@PathVariable("id") long id, @ModelAttribute("series") 
    @Valid Serie serie, BindingResult result, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors()) {
            serie.setId(id);
            mv.setViewName("/editar-serie");
            mv.addObject("series", serie);
            return mv;
        }
        repository.save(serie);
        attributes.addFlashAttribute("mensagem", "Alterações salvas com sucesso!");
        mv.setViewName("redirect:/series/lista");
        return mv;
    }

/*Método para deletar a série */
     @GetMapping("/apagar/{id}")
    public ModelAndView apagarSerie(@PathVariable("id") long id) {
        ModelAndView mv = new ModelAndView();
        Serie serie = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id inválido: " + id));
        repository.delete(serie);
        mv.setViewName("redirect:/series/lista");
        return mv;
    }
}
