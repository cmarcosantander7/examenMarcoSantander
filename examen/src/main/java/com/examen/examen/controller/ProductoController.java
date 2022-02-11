package com.examen.examen.controller;

import com.examen.examen.entity.Producto;
import com.examen.examen.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Producto producto){
        if(producto.getCantidad() >0 && producto.getPrecio() >0){
            return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(producto));
        }else{
            return ResponseEntity.badRequest().body("Cantidad y Precio deben ser mayores a 0 (cero)");
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read (@PathVariable Long id){
        Optional<Producto> oUser = productoService.findById(id);
        if(!oUser.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oUser);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update (@RequestBody Producto userDetails,@PathVariable Long id ){
        Optional<Producto> user= productoService.findById(id);
        if(!user.isPresent()){
            return ResponseEntity.notFound().build();
        }else{
            if(userDetails.getCantidad() >0 && userDetails.getPrecio() >0){
                user.get().setDescripcion(userDetails.getDescripcion());
                user.get().setPrecio(userDetails.getPrecio());
                user.get().setCantidad(userDetails.getCantidad());



                return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(user.get()));
            }else{
                return ResponseEntity.badRequest().body("Cantidad y Precio deben ser mayores a 0 (cero)");
            }
        }


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){
        if(!productoService.findById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        productoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Producto> readAll(){
        List<Producto> users= StreamSupport
                .stream(productoService.findAll().spliterator(),false)
                .collect(Collectors.toList());
        return users;
    }
}
