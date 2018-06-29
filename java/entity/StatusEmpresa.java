package entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StatusEmpresa implements Serializable {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String status; // Ativa ou Desativa, usar enum.
	private Long data; // para registrar o log da alteracao
	
	public StatusEmpresa(String status) {
		this.setStatus(status);
		// TODO this.setData(Date.getTime());
	}
	
	public StatusEmpresa() {
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
