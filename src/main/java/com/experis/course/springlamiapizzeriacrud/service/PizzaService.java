package com.experis.course.springlamiapizzeriacrud.service;

import com.experis.course.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import com.experis.course.springlamiapizzeriacrud.model.Pizza;
import com.experis.course.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {
  @Autowired
  private PizzaRepository pizzaRepository;

  // metodo che restituisce la lista di tutte le pizze eventualmente filtrate
  public List<Pizza> getPizzaList(Optional<String> search) {
    if (search.isPresent()) {
      // se il parametro di ricerca è presente, filtro la lista di pizze
      return pizzaRepository.findByNameContainingIgnoreCase(
          search.get());
    } else {
      // altrimenti prendo tutti le pizze non filtrate
      // pizzaRepository recupera da database la lista di tutti i libri
      return pizzaRepository.findAll();
    }
  }

  public List<Pizza> getPizzaList() {
    return pizzaRepository.findAll();
  }

  // metodo che restituisce una pizza presa per id, se non la trova solleva un'eccezione
  public Pizza getPizzaById(Integer id) throws PizzaNotFoundException {
    Optional<Pizza> result = pizzaRepository.findById(id);
    if (result.isPresent()) {
      return result.get();
    } else {
      throw new PizzaNotFoundException("Pizza with id " + id + " not found");
    }
  }

  // metodo per creare una nuova pizza
  public Pizza createPizza(Pizza pizza) {
    return pizzaRepository.save(pizza);
  }

  // metodo per modificare una pizza con un id

  public Pizza editPizza(Pizza pizza) throws PizzaNotFoundException {
    Pizza pizzaToEdit = getPizzaById(pizza.getId());
    //sostituisco i valori dei campi previsti
    pizzaToEdit.setName(pizza.getName());
    pizzaToEdit.setDescription(pizza.getDescription());
    pizzaToEdit.setPic(pizza.getPic());
    pizzaToEdit.setPrice(pizza.getPrice());
    pizzaToEdit.setIngredients(pizza.getIngredients());

    return pizzaRepository.save(pizzaToEdit);
  }

  // metodo che elimina pizza dal database

  public void deletePizza(Integer id) {
    pizzaRepository.deleteById(id);
  }

  // metodo che prende in ingresso un Pageable e restituisce la Page di pizze
  public Page<Pizza> getPage(Pageable pageable) {
    return pizzaRepository.findAll(pageable);
  }

}
