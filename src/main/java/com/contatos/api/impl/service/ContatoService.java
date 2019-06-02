package com.contatos.api.impl.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.contatos.api.impl.exception.ContatoInvalidoException;
import com.contatos.api.impl.exception.ContatoNotFoundException;
import com.contatos.api.impl.exception.ListaDeContatosVaziaException;
import com.contatos.api.impl.model.Contato;
import com.contatos.api.impl.model.Numero;
import com.contatos.api.impl.repository.ContatoRepository;
import com.contatos.api.impl.repository.NumeroRepository;

@Service
public class ContatoService {

	@Autowired
	private ContatoRepository contatoRepository;

	@Autowired
	private NumeroRepository numeroRepository;

	@Transactional
	public Contato save(Contato contato) {
		if (contato != null) {
			return this.contatoRepository.save(contato);
		} else {
			throw new ContatoInvalidoException("Contato informado é inválido");
		}
	}

	@Transactional
	public List<Contato> findAll() {
		List<Contato> contatos = this.contatoRepository.findAll();
		if (contatos.isEmpty())
			throw new ListaDeContatosVaziaException("A lista de contatos está vazia!");
		return contatos;
	}

	public Contato findById(Long id) {
		Optional<Contato> contatoFinded = this.contatoRepository.findById(id);
		if (contatoFinded.isPresent()) {
			return contatoFinded.get();
		}
		throw new ContatoNotFoundException("Contato não encontrado!");
	}

	@Transactional
	public Contato update(Contato contato) {

		Optional<Contato> contatoFinded = this.contatoRepository.findByNome(contato.getNome());

		if (contatoFinded.isPresent()) {
			Contato contatoEdited = contatoFinded.get();

			Numero numeroEdited = contatoEdited.getNumero();
			Optional<Numero> numeroFinded = this.numeroRepository.findById(numeroEdited.getId());

			if (numeroFinded.isPresent()) {
				Numero numeroSaved = numeroFinded.get();
				numeroSaved.setDdi(contato.getNumero().getDdi());
				numeroSaved.setDdd(contato.getNumero().getDdd());
				numeroSaved.setDescricao(contato.getNumero().getDescricao());
				contatoEdited.setNumero(numeroSaved);
			}

			this.contatoRepository.saveAndFlush(contatoEdited);
			return contatoEdited;
		}
		throw new ContatoNotFoundException("Contato não encontrado!");
	}

	public Boolean delete(Long id) {
		Optional<Contato> contatoFinded = this.contatoRepository.findById(id);
		if (contatoFinded.isPresent()) {
			Contato contatoRemoved = contatoFinded.get();
			this.contatoRepository.delete(contatoRemoved);
			return true;
		}
		throw new ContatoNotFoundException("Contato não encontrado!");
	}
}
