package com.experis.course.springlamiapizzeriacrud.controller;

import com.experis.course.springlamiapizzeriacrud.exceptions.DealNotFoundException;
import com.experis.course.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import com.experis.course.springlamiapizzeriacrud.model.Deal;
import com.experis.course.springlamiapizzeriacrud.service.DealService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/deals")
public class DealController {

  @Autowired
  DealService dealService;

  @GetMapping("/create")
  public String create(@RequestParam Integer pizzaId, Model model) {
    try {
      model.addAttribute("deal", dealService.createNewDeal(pizzaId));
      return "deals/form";
    } catch (PizzaNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  @PostMapping("/create")
  public String doCreate(@Valid @ModelAttribute("deals") Deal formDeal,
                         BindingResult bindingResult) {
    // valido i dati
    if (bindingResult.hasErrors()) {
      return "deals/form";
    }
    // salvo su database
    Deal savedDeal = dealService.saveDeal(formDeal);
    // redirect al dettaglio della pizza
    return "redirect:/pizzas/show/" + formDeal.getPizza().getId();
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Integer id, Model model) {
    try {
      Deal deal = dealService.getDeal(id);
      model.addAttribute("deal", deal);
      return "deals/form";
    } catch (DealNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  @PostMapping("/edit/{id}")
  public String doEdit(@PathVariable Integer id,
                       @Valid @ModelAttribute("deal") Deal formDeal, BindingResult bindingResult) {
    //valido
    if (bindingResult.hasErrors()) {
      return "deals/form";
    }
    // salvo su db
    Deal savedDeal = dealService.saveDeal(formDeal);
    // redirect
    return "redirect:/pizzas/show/" + formDeal.getPizza().getId();
  }

}
