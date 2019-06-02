package com.contatos.api.contract.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.contatos.api.contract.mapper.ContatoMapper;
import com.contatos.api.contract.model.ContatoResponse;
import com.contatos.api.contract.model.NovoContatoRequest;
import com.contatos.api.impl.model.Contato;
import com.contatos.api.impl.service.ContatoService;
import com.google.gson.Gson;
@CrossOrigin("http:localhost:4200")
@RequestMapping("/contato")
@RestController
public class ContatoController {

	@Autowired
	private ContatoService contatoService;

	@Autowired
	private Gson g;

	@PostMapping
	public ResponseEntity<ContatoResponse> save(@RequestBody @Valid NovoContatoRequest novoContatoRequest) {
		if (novoContatoRequest != null) {
			Contato contato = this.contatoService.save(ContatoMapper.contractToImpl(novoContatoRequest));
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(contato.getId()).toUri();

			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/contatos")
	public ResponseEntity<List<ContatoResponse>> findAll() {
		List<Contato> contatos = this.contatoService.findAll();
		return ResponseEntity.ok(ContatoMapper.contatoResponses(contatos));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ContatoResponse> findById(@PathVariable Long id) {
		Contato contato = this.contatoService.findById(id);
		if (contato != null) {
			return ResponseEntity.ok(ContatoMapper.implToContract(contato));
		}
		return ResponseEntity.notFound().build();

	}

	@PutMapping
	public ResponseEntity<String> update(@RequestBody @Valid NovoContatoRequest novoContatoRequest) {
		Contato contatoSaved = this.contatoService.update(ContatoMapper.contractToImpl(novoContatoRequest));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(contatoSaved.getId()).toUri();
		return ResponseEntity.ok(this.g.toJson(location));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Boolean contatoRemoved = this.contatoService.delete(id);
		if(contatoRemoved) {
			return ResponseEntity.ok(contatoRemoved);
		}
		return ResponseEntity.notFound().build();
	}
}
