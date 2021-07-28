package com.bolsadeideas.springboot.backend.apirest.models.service.iface;

import java.util.List;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Productos;

public interface IProductosServices {
	
	public List<Productos> listar();
	
	public Productos findById(int id);
	
	public Productos guardar(Productos producto);
	
	public void delete(int id);

}
