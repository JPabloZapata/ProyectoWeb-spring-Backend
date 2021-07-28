package com.bolsadeideas.springboot.backend.apirest.models.service.iface;

import java.util.List;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Cliente;

public interface IClienteService {
	
	public List<Cliente> findAll();
	
	public Cliente findById(long id);
	
	public Cliente save(Cliente cliente);
	
	public void delete(long id);

}
