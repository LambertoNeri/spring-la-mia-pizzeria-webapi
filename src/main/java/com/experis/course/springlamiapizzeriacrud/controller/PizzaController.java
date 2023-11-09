package com.experis.course.springlamiapizzeriacrud.controller;

import com.experis.course.springlamiapizzeriacrud.model.Pizza;
import com.experis.course.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
  // ATTRIBUTI

  @Autowired
  private PizzaRepository pizzaRepository;

  // METODO CHE MOSTRA LA LISTA DI TUTTE LE PIZZE

  @GetMapping
  public String index(Model model) {
    // pizzaRepository recupera da database la lista di tutte le pizze
    List<Pizza> pizzaList = pizzaRepository.findAll();
    // passo al template la lista di tutte le pizze
    model.addAttribute("pizzaList", pizzaList);
    return "pizzas/list";
  }
}
