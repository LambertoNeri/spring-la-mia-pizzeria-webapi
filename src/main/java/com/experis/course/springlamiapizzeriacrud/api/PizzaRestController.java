package com.experis.course.springlamiapizzeriacrud.api;

import com.experis.course.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import com.experis.course.springlamiapizzeriacrud.model.Pizza;
import com.experis.course.springlamiapizzeriacrud.service.PizzaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pizzas")
@CrossOrigin
public class PizzaRestController {
  @Autowired
  private PizzaService pizzaService;

  // endpoint per la lista di tutte le pizze
  @GetMapping
  public List<Pizza> index(@RequestParam Optional<String> search) {
    return pizzaService.getPizzaList(search);
  }

  // endpoint per i dettagli della pizza presa per id
  @GetMapping("/{id}")
  public Pizza details(@PathVariable Integer id) {
    try {
      return pizzaService.getPizzaById(id);
    } catch (PizzaNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  // endpoint per creare una nuova pizza
  @PostMapping
  public Pizza create(@Valid @RequestBody Pizza pizza) {
    return pizzaService.createPizza(pizza);
  }

  // endpoint per modificare una pizza
  @PutMapping("/{id}")
  public Pizza update(@PathVariable Integer id, @Valid @RequestBody Pizza pizza) {
    pizza.setId(id);
    try {
      return pizzaService.editPizza(pizza);
    } catch (PizzaNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  // endpoint per cancellare una pizza
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Integer id) {
    try {
      Pizza pizzaToDelete = pizzaService.getPizzaById(id);
      pizzaService.deletePizza(id);
    } catch (PizzaNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  // endpoint di test per lista paginata
  @GetMapping("/page")
  public Page<Pizza> pagedIndex(
      @RequestParam(name = "size", defaultValue = "2") Integer size,
      @RequestParam(name = "page", defaultValue = "0") Integer page) {
    return pizzaService.getPage(PageRequest.of(page, size));
  }

  @GetMapping("/page/v2")
  public Page<Pizza> pageIndexV2(@PageableDefault(page = 0, size = 2) Pageable pageable) {
    return pizzaService.getPage(pageable);
  }
}
