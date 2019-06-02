package com.contatos.api.impl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contatos.api.impl.model.Numero;

@Repository
public interface NumeroRepository extends JpaRepository<Numero, Long> {

}
