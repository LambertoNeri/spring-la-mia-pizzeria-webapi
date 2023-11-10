package com.experis.course.springlamiapizzeriacrud.controller;

import com.experis.course.springlamiapizzeriacrud.model.Pizza;
import com.experis.course.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/pizzas")
public class PizzaController {
  // ATTRIBUTI

  @Autowired
  private PizzaRepository pizzaRepository;

  // METODO CHE MOSTRA LA LISTA DI TUTTE LE PIZZE

  @GetMapping
  public String index(@RequestParam Optional<String> search,
                      Model model) {
    List<Pizza> pizzaList;

    if (search.isPresent()) {
      pizzaList = pizzaRepository.findByNameContainingIgnoreCase(search.get());
    } else {
      // pizzaRepository recupera da database la lista di tutte le pizze
      pizzaList = pizzaRepository.findAll();
      // passo al template la lista di tutte le pizze
    }
    model.addAttribute("pizzaList", pizzaList);
    return "pizzas/list";
  }

  @GetMapping("/show/{id}")
  public String show(@PathVariable Integer id, Model model) {
    Optional<Pizza> result = pizzaRepository.findById(id);
    // verifico se il risultato è presente
    if (result.isPresent()) {
      // passo al template l'oggetto Pizza
      model.addAttribute("pizza", result.get());
      return "pizzas/show";
    } else {
      // se non ho trovato il libro sollevo un'eccezione
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "pizza with id " + id + " not found");
    }
  }
}
