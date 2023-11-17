package com.experis.course.springlamiapizzeriacrud.controller;


import com.experis.course.springlamiapizzeriacrud.exceptions.IngredientNameUniqueException;
import com.experis.course.springlamiapizzeriacrud.model.Ingredient;
import com.experis.course.springlamiapizzeriacrud.service.IngredientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

  @Autowired
  IngredientService ingredientService;

  @GetMapping
  public String index(Model model) {
    // passa al model ingredientList con la lista di ingredienti
    model.addAttribute("ingredientList", ingredientService.getAll());
    // passa al model un ingrediente vuoto come attrivuto ingredientObj del form
    model.addAttribute("ingredientObj", new Ingredient());
    return "ingredients/index";
  }

  @PostMapping
  public String doSave(@Valid @ModelAttribute("ingredientObj") Ingredient formIngredient,
                       BindingResult bindingResult,
                       Model model) {
    //valido l'ingrediente
    if (bindingResult.hasErrors()) {
      model.addAttribute("ingredientList", ingredientService.getAll());
      return "ingredients/index";
    }
    try {
      // salvo il nuovo ingrediente su database
      ingredientService.save(formIngredient);
      return "ingredients/index";
    } catch (IngredientNameUniqueException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "An ingredient with name " + e.getMessage() + " arleady exists");
    }
  }
}
