package br.com.imobiliaria.api.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.imobiliaria.api.entities.Bairro;
import br.com.imobiliaria.api.entities.Cidade;
import br.com.imobiliaria.api.response.Response;
import br.com.imobiliaria.api.services.impl.BairroServiceImpl;
import br.com.imobiliaria.api.services.impl.CidadeServiceImpl;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/bairros")
@CrossOrigin(origins = "*")
@Api(value = "Bairro", description = "API REST para bairros", tags = { "Bairro" })
public class BairroController {
	
	private static final Logger log = LoggerFactory.getLogger(BairroController.class);

	@Autowired
	private BairroServiceImpl bairroServiceImpl;

	@Autowired
	private CidadeServiceImpl cidadeServiceImpl;
	
	/**
	 * Retorna a listagem de bairros sem paginacao
	 * 
	 * @return ResponseEntity<Response<Bairro>>
	 */
	@GetMapping()
	public ResponseEntity<Response<List<Bairro>>> listarTodos() {
	
		log.info("Buscando bairros");
		Response<List<Bairro>> response = new Response<List<Bairro>>();

		// Buscar todos
		List<Bairro> bairros = this.bairroServiceImpl.buscarTodos();

		response.setData(bairros);
		return ResponseEntity.ok(response);
	}

	/**
	 * Cadastra um bairro no sistema.
	 * 
	 * @param bairro
	 * @param result
	 * @return ResponseEntity<Response<Bairro>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Bairro>> cadastrarBairro(@Valid @RequestBody Bairro bairro,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando bairro: {}", bairro.toString());
		Response<Bairro> response = new Response<Bairro>();

		validarDadosExistentes(bairro, result);
		
		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro do bairro: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		this.bairroServiceImpl.persistir(bairro);	
		
		response.setData(bairro);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Verifica se o bairro possui cidade valida
	 * 
	 * @param bairro
	 * @param result
	 */
	private void validarDadosExistentes(Bairro bairro, BindingResult result) {
		Optional<Cidade> cidade = this.cidadeServiceImpl.buscarPorCodigo(bairro.getCidade().getCodigo());

		if (!cidade.isPresent()) {
			result.addError(new ObjectError("cidade", "Cidade referenciada no bairro não existe! Código inválido"));
		}

	}
	
	/**
	 * Remove um bairro por ID.
	 * 
	 * @param id
	 * @return ResponseEntity<Response<String>>
	 */
	@DeleteMapping(value = "/{codigo}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> removerBairro(@PathVariable("codigo") Long codigo) {
		log.info("Removendo bairro: {}", codigo);
		Response<String> response = new Response<String>();
		Optional<Bairro> bairro = this.bairroServiceImpl.buscarPorCodigo(codigo);

		if (!bairro.isPresent()) {
			log.info("Erro ao remover bairro devido ID: {} ser inválido.", codigo);
			response.getErrors().add("Erro ao remover bairro. Registro não encontrado para o id " + codigo);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData("Bairro removido com Sucesso!");
		this.bairroServiceImpl.remover(codigo);
		return ResponseEntity.ok(response);
	}

	/**
	 * Atualiza os dados de uma cidade.
	 * 
	 * @param id
	 * @param codade
	 * @param result
	 * @return ResponseEntity<Response<Cidade>>
	 * @throws NoSuchAlgorithmException
	 */
	@PutMapping(value = "/{codigo}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Bairro>> atualizarBairro(@PathVariable("codigo") Long codigo,
			@Valid @RequestBody Bairro bairro, BindingResult result) throws NoSuchAlgorithmException {
		log.info("Atualizando bairro: {}", bairro.toString());
		Response<Bairro> response = new Response<Bairro>();

		Optional<Bairro> bai = this.bairroServiceImpl.buscarPorCodigo(codigo);
		if (!bai.isPresent()) {
			result.addError(new ObjectError("bairro", "bairro não encontrado."));
		}
		
		validarDadosExistentes(bairro, result);
		
		
		bairro.setCodigo(bai.get().getCodigo());
		bairro.setDataCriacao(bai.get().getDataCriacao());
		
		if (result.hasErrors()) {
			log.error("Erro validando bairro: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(bairro);
		this.bairroServiceImpl.persistir(bairro);

		return ResponseEntity.ok(response);
	}
	
}
