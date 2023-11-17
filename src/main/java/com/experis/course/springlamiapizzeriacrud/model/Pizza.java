package com.experis.course.springlamiapizzeriacrud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pizzas")
public class Pizza {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @NotNull
  @NotBlank(message = "Pizza's name must not be blank")
  private String name;

  @NotNull
  @NotBlank(message = "Pizza's description must not be blank")
  @Size(max = 255, message = "Length must be less than 255 characters")
  private String description;
  @NotNull
  @NotBlank(message = "Pizza's pic must not be blank")
  private String pic;
  @NotNull
  @Min(value = 1, message = "Pizza's price must atleast be of 1 euro")
  private double price;

  @OneToMany(mappedBy = "pizza", orphanRemoval = true, fetch = FetchType.LAZY)
  private List<Deal> deals = new ArrayList<>();

  @ManyToMany(fetch = FetchType.LAZY)
  private List<Ingredient> ingredients;

  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }
  
  public Integer getId() {
    return id;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPic() {
    return pic;
  }

  public void setPic(String pic) {
    this.pic = pic;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public List<Deal> getDeals() {
    return deals;
  }

  public void setDeals(List<Deal> deals) {
    this.deals = deals;
  }
}
