package com.bolsadeideas.springboot.backend.apirest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Productos;
import com.bolsadeideas.springboot.backend.apirest.models.service.iface.IClienteService;
import com.bolsadeideas.springboot.backend.apirest.models.service.iface.IProductosServices;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {
	
	@Autowired
	private IClienteService clienteService;
	@Autowired
	private IProductosServices productoService;
	
	
	@GetMapping("/clientes")
	public List<Cliente> index(){
		return clienteService.findAll();
	}

	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> show(@PathVariable long id) {
		
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		try {
			cliente = clienteService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		if(cliente == null) {
			response.put("mensaje", "el cliente id: " + id + " no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	
	@PostMapping("/clientes")
	//@ResponseStatus(HttpStatus.CREATED)//esto es para que retorne un 201 en vez de ok(200)
	public ResponseEntity<?> create(@RequestBody Cliente cliente) {
		//cliente.setCreateAt(new Date());----segun asi se setea la fecha
		
		Cliente clienteNuevo = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			clienteNuevo = clienteService.save(cliente);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		response.put("mensaje", "el cliente ha sido creado con exito");
		response.put("cliente", clienteNuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@RequestBody Cliente cliente, @PathVariable long id) {
		
		Cliente clienteActual = clienteService.findById(id);
		Cliente clienteUpdate = null;
		Map<String, Object> response = new HashMap<>();
		
		if(clienteActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el cliente id: " + id + " no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setEmail(cliente.getEmail());
			clienteActual.setCreateAt(cliente.getCreateAt());
			
			clienteUpdate = clienteService.save(clienteActual);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar el cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		response.put("mensaje", "el cliente ha sido creado con exito");
		response.put("cliente", clienteUpdate);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		Map<String, Object> response = new HashMap<>();
			
		try {
			clienteService.delete(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		response.put("mensaje", "el cliente ha sido eliminado con exito");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	/////-----------------------------PRODUCTOS-----------------------------/////
	
	@GetMapping("/productos")
	public List<Productos> mostrar(){
		return productoService.listar();
	}
	
	@GetMapping("/productos/{id}")
	public Productos buscarPorId(@PathVariable int id) {
		Productos producto = null;
		try {
			producto = productoService.findById(id);			
		}catch(DataAccessException e) {
			System.out.println("error al realizar la consulta en la base de datos");
			System.out.println("error " + e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		}
		
		return producto;
	}
	
	@PutMapping("/productos")
	public Productos crear(@RequestBody Productos producto) {
		Productos product = null;
		try {
			product = productoService.guardar(producto);
		}catch(DataAccessException e) {
			System.out.println("error al crear el producto en la base de datos");
			System.out.println("error " + e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		}
		System.out.println("Cliente creado con exito");
		return product;
	}
	
	@DeleteMapping("/productos/{id}")
	public String eliminar(@PathVariable int id) {
		try {
			productoService.delete(id);
			System.out.println("Producto eliminado correctamente");
			return "eliminado con exito";
		}catch(DataAccessException e){
			System.out.println("error al eliminar el producto en la base de datos");
			System.out.println("error " + e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return "No se pudo eliminar el producto";
		}
	}
	
	@PutMapping("/productos/{id}")
	public Productos actualizar(@RequestBody Productos productos, @PathVariable int id) {
		Productos productoRecibido = productoService.findById(id);
		Productos productoActualizado = null;
		
		if(productoRecibido == null) {
			System.out.println("este producto no existe en la base de datos");
			return null;
		}else {
			try {
				productoRecibido.setNombre(productos.getNombre());
				productoRecibido.setDescripcion(productos.getDescripcion());
				productoRecibido.setFechaCreada(productos.getFechaCreada());
				productoRecibido.setPrecio(productos.getPrecio());	
				productoRecibido.setImage(productos.getImage());
				
				productoActualizado = productoService.guardar(productoRecibido);
			}catch(DataAccessException e) {
				System.out.println("No se pudo actualizar el producto");
			}
		}
		
		System.out.println("Cliente actualizado correctamente");	
		return productoActualizado;
	}
	
	@GetMapping("/productos/imagen/{id}")//este metodo no lo utilizo
	public String cargarImagen(@PathVariable int id) {
		Productos productoRecibido = productoService.findById(id);
		String foto = "";
		foto = productoRecibido.getImage();
		return foto;
	}
	
	
}
