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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
      return "redirect:/ingredients";
    } catch (IngredientNameUniqueException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "An ingredient with name " + e.getMessage() + " arleady exists");
    }
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
    // recupero l'ingrediente con quell'id
    try {
      Ingredient ingredientToDelete = ingredientService.getIngredientById(id);
      ingredientService.deleteIngredient(id);
      redirectAttributes.addFlashAttribute("message", "Ingredient " + ingredientToDelete.getName() + " deleted!");
      return "redirect:/ingredients";
    } catch (IngredientNameUniqueException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }
}
