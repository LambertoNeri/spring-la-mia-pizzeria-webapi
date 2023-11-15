package com.experis.course.springlamiapizzeriacrud.service;

import com.experis.course.springlamiapizzeriacrud.exceptions.DealNotFoundException;
import com.experis.course.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import com.experis.course.springlamiapizzeriacrud.model.Deal;
import com.experis.course.springlamiapizzeriacrud.model.Pizza;
import com.experis.course.springlamiapizzeriacrud.repository.DealRepository;
import com.experis.course.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DealService {

  @Autowired
  PizzaRepository pizzaRepository;
  @Autowired
  DealRepository dealRepository;

  public Deal createNewDeal(Integer pizzaId) throws PizzaNotFoundException {
    Pizza pizza = pizzaRepository.findById(pizzaId)
        .orElseThrow(() -> new PizzaNotFoundException("Pizza with id " + pizzaId + " not found"));
    Deal deal = new Deal();
    deal.setStartDate(LocalDate.now());
    deal.setExpireDate(LocalDate.now().plusMonths(1));
    deal.setPizza(pizza);
    return deal;
  }

  public Deal saveDeal(Deal deal) {
    return dealRepository.save(deal);
  }

  public Deal getDeal(Integer id) throws DealNotFoundException {
    return dealRepository.findById(id)
        .orElseThrow(() -> new DealNotFoundException("Deal with id " + id + " not found"));
  }
}
