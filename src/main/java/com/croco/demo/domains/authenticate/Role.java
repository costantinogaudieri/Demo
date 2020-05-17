package com.croco.demo.domains.authenticate;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "AUTH_ROLE")
@XmlRootElement
@Data
public class Role implements Serializable {

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "auth_role_id")
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_desc")
    private String roleDesc;
}
