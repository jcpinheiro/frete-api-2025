package edu.ifma.dcomp.lpweb.frete.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.ifma.dcomp.lpweb.frete.domain.exception.NegocioException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Entrega {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@ManyToOne
	private Cliente cliente;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Destinatario destinatario;

	@NotNull @Positive
	private BigDecimal taxa;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	private StatusEntrega status;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private LocalDateTime dataPedido;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private LocalDateTime dataFinalizacao;


	public String getSenha() {
		return "senha_secreta";
	}

	public void finaliza() {
		if (naoPodeSerFinalizada()) {
			throw new NegocioException("Entrega não pode ser finalizada");
		}
		
		setStatus(StatusEntrega.FINALIZADA);
		setDataFinalizacao(LocalDateTime.now());
	}
	
	public boolean podeSerFinalizada() {
		return StatusEntrega.PENDENTE.equals(getStatus());
	}
	
	public boolean naoPodeSerFinalizada() {
		return !podeSerFinalizada();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Destinatario getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Destinatario destinatario) {
		this.destinatario = destinatario;
	}

	public BigDecimal getTaxa() {
		return taxa;
	}

	public void setTaxa(BigDecimal taxa) {
		this.taxa = taxa;
	}

	public StatusEntrega getStatus() {
		return status;
	}

	public void setStatus(StatusEntrega status) {
		this.status = status;
	}

	public LocalDateTime getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}

	public LocalDateTime getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(LocalDateTime dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}
}
