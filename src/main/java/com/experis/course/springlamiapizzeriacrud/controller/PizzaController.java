package com.experis.course.springlamiapizzeriacrud.controller;

import com.experis.course.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import com.experis.course.springlamiapizzeriacrud.model.Pizza;
import com.experis.course.springlamiapizzeriacrud.service.IngredientService;
import com.experis.course.springlamiapizzeriacrud.service.PizzaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;


@Controller
@RequestMapping("/pizzas")
public class PizzaController {
  // ATTRIBUTI


  //attributi
  @Autowired
  private PizzaService pizzaService;

  @Autowired
  private IngredientService ingredientService;

  // metodo che mostra la lista di tutte le pizze

  @GetMapping
  public String index(@RequestParam Optional<String> search,
                      Model model) {
    // passo al template la lista di tutti i libri
    model.addAttribute("pizzaList", pizzaService.getPizzaList(search));
    return "pizzas/list";
  }

  // metodo che mostra i dettagli di una pizza presa per id
  @GetMapping("/show/{id}")
  public String show(@PathVariable Integer id, Model model) {
    try {
      Pizza pizza = pizzaService.getPizzaById(id);
      model.addAttribute("pizza", pizza);
      return "pizzas/show";
    } catch (PizzaNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  // metodo che mostra il form di creazione delle pizze
  @GetMapping("/create")
  public String create(Model model) {
    model.addAttribute("pizza", new Pizza());
    model.addAttribute("ingredientList", ingredientService.getAll());
    return "pizzas/form";
  }

  @PostMapping("/create")
  public String doCreate(@Valid @ModelAttribute("pizza") Pizza formPizza,
                         BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("ingredientList", ingredientService.getAll());
      return "pizzas/form";
    }
    // salvo la pizza sul database
    Pizza savedPizza = pizzaService.createPizza(formPizza);
    return "redirect:/pizzas/show/" + savedPizza.getId();
  }

  // metodo che mostra la pagina di modifica di una pizza
  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Integer id, Model model) {
    try {
      // aggiungo la pizza come attributo del Model
      model.addAttribute("pizza", pizzaService.getPizzaById(id));
      model.addAttribute("ingredientList", ingredientService.getAll());
      // proseguo a restituire la pagina di modifica
      return "/pizzas/form";
    } catch (PizzaNotFoundException e) {
      // sollevo un'eccesione con HttpStatus 404
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  // metodo che riceve il submit del form di edit e salva la pizza
  @PostMapping("/edit/{id}")
  public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("pizza") Pizza formPizza,
                       BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      // se ci sono errori ricarico la pagina col form
      model.addAttribute("ingredientList", ingredientService.getAll());
      return "/pizzas/form";
    }
    try {
      Pizza savedPizza = pizzaService.editPizza(formPizza);
      return "redirect:/pizzas/show/" + savedPizza.getId();
    } catch (PizzaNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  // metodo per eliminare una pizza dal database
  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
    //recupero la pizza con quell'id
    try {
      Pizza pizzaToDelete = pizzaService.getPizzaById(id);
      pizzaService.deletePizza(id);
      redirectAttributes.addFlashAttribute("message",
          "Pizza" + pizzaToDelete.getName() + " deleted!");
      return "redirect:/pizzas";
    } catch (PizzaNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }
}
