package com.croco.demo.domains;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "TEST")
@XmlRootElement
@Data
public class Test implements Serializable {

    private static final long  serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;

    @Column(name = "TEST")
    private String test;
}
