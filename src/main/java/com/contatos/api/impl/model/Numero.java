package com.contatos.api.impl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "numero")
@Builder
public class Numero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "DDI é obrigatório")
	@Size(min = 2, max = 2)
	@Column(name = "DDI", length = 2, nullable = false)
	private String ddi;
	@NotEmpty(message = "DDD é obrigatório")
	@Size(min = 2, max = 2)
	@Column(name = "DDD", length = 2, nullable = false)
	private String ddd;
	@NotEmpty(message = "número é obrigatório")
	@Column(name = "numero", length = 9, nullable = false)
	private String descricao;
}
