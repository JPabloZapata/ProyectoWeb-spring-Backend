package com.bolsadeideas.springboot.backend.apirest.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.backend.apirest.models.dao.IProductosDao;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Productos;
import com.bolsadeideas.springboot.backend.apirest.models.service.iface.IProductosServices;

@Service
public class ProductosServicesImpl implements IProductosServices {
	
	@Autowired
	private IProductosDao productosDao;

	@Override
	public List<Productos> listar() {
		return (List<Productos>) productosDao.findAll();
	}

	@Override
	public Productos findById(int id) {
		return productosDao.findById(id).orElse(null);
	}

	@Override
	public Productos guardar(Productos producto) {
		return productosDao.save(producto);
	}

	@Override
	public void delete(int id) {
		productosDao.deleteById(id);;
	}

}
