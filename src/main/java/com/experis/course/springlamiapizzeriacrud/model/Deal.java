package com.experis.course.springlamiapizzeriacrud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "deals")
public class Deal {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull
  private LocalDate startDate;
  @NotNull
  private LocalDate expireDate;

  @NotNull
  private String title;

  @NotNull
  @ManyToOne
  private Pizza pizza;

}
