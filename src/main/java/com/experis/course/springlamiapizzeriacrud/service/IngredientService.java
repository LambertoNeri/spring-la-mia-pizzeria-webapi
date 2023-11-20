package com.experis.course.springlamiapizzeriacrud.service;

import com.experis.course.springlamiapizzeriacrud.exceptions.IngredientNameUniqueException;
import com.experis.course.springlamiapizzeriacrud.model.Ingredient;
import com.experis.course.springlamiapizzeriacrud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {
  @Autowired
  private IngredientRepository ingredientRepository;

  public List<Ingredient> getAll() {
    return ingredientRepository.findByOrderByName();
  }

  public Ingredient save(Ingredient ingredient) throws IngredientNameUniqueException {
    //verifico che questo nome esista già
    if (ingredientRepository.existsByName(ingredient.getName())) {
      throw new IngredientNameUniqueException(ingredient.getName());
    }
    // trasformo il nome in lowercase
    ingredient.setName(ingredient.getName().toLowerCase());
    // salvo su database
    return ingredientRepository.save(ingredient);
  }

  public Ingredient getIngredientById(Integer id) throws IngredientNameUniqueException {
    Optional<Ingredient> result = ingredientRepository.findById(id);
    if (result.isPresent()) {
      return result.get();
    } else {
      throw new IngredientNameUniqueException("Ingredient with id " + id + " not found");
    }
  }

  // metodo che elimina un ingrediente da database

  public void deleteIngredient(Integer id) {
    ingredientRepository.deleteById(id);
  }
}
