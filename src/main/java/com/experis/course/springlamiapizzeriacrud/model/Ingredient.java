package com.experis.course.springlamiapizzeriacrud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ingredients")
public class Ingredient {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank
  @Size(max = 50)
  @Column(nullable = false, unique = true)
  private String name;

  @JsonIgnore
  @ManyToMany(mappedBy = "ingredients")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private List<Pizza> pizzas = new ArrayList<>();

  public Integer getId() {
    return id;
  }


  public List<Pizza> getPizzas() {
    return pizzas;
  }

  public void setPizzas(List<Pizza> pizzas) {
    this.pizzas = pizzas;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
