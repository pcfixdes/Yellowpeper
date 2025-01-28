package com.kata.kata.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long roleId;

    @NotBlank(message = "Role name is mandatory")
    @Size(max = 50, message = "Role name must be less than or equal to 50 characters")
    @Column(nullable = false, unique = true, length = 50)
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    @ToString.Exclude // Prevent circular reference
    private Set<User> users = new HashSet<>();
}
