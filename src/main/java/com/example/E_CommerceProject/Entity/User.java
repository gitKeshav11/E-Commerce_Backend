package com.example.E_CommerceProject.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @JsonIgnore //iska use isliye kiya kyuki jab hum user ki details nikale to orders ki detail na aae
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Orders> ordersList;
}
