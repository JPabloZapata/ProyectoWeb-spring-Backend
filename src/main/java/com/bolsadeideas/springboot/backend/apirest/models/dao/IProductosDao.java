package com.bolsadeideas.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Productos;

public interface IProductosDao extends CrudRepository<Productos, Integer>{

}
