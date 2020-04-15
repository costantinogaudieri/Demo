package com.croco.demo.repositories.configurationParameters;

import com.croco.demo.domains.configurationParameters.StartupParameter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StartupParameterRepository extends JpaRepository<StartupParameter,Long> {

    List<StartupParameter> findByCod(String cod);

}
