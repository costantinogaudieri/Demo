package com.croco.demo.domains.configurationParameters;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("START_UP")
@Data
public class StartupParameter extends ConfigurationParameters{
    @Basic(optional = false)
    @Column(name = "COD")
    private String cod;
}
