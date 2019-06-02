package com.contatos.api.impl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contatos.api.impl.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

	public Optional<Contato> findByNome(String nome);
}
