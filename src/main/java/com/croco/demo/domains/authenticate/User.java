package com.croco.demo.domains.authenticate;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table( name = "AUTH_USER")
@XmlRootElement
@Data
public class User implements Serializable {

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "auth_user_id")
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "STATUS")
    private boolean status;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "AUTH_USER_ROLE", joinColumns=@JoinColumn(name = "auth_user_id", referencedColumnName = "auth_user_id"))
    private Set<Role> role;

}
