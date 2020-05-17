package com.croco.demo.domains.authenticate;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table( name = "AUTH_USER_ROLE")
@XmlRootElement
@Data
public class UserRole {
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "auth_user_id")
    private Long id;
}
