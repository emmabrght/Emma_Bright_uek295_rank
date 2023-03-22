package com.example.test.domain.authority;

import com.example.test.domain.role.Role;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "authorities")
@Data
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
}
