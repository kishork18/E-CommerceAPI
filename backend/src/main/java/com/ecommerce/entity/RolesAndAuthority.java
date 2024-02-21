package com.ecommerce.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RolesAndAuthority {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  @JsonIgnore
  @ManyToMany(mappedBy = "authSet")
  private Set<User> users;
}
