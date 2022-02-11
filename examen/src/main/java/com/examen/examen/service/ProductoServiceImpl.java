package com.examen.examen.service;

import com.examen.examen.entity.Producto;
import com.examen.examen.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Iterable<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto save(Producto producto) {
        producto.setValor_compra(producto.getCantidad()*producto.getPrecio());

        if(producto.getValor_compra()>50){
            Double descuento= (producto.getValor_compra()*0.10);
            Double iva= (producto.getValor_compra()*0.12);
            Double total=(producto.getValor_compra()-descuento+iva);

            producto.setTotal_pagar(total);
        }else{
            producto.setTotal_pagar(producto.getValor_compra());
        }
        return productoRepository.save(producto);
    }

    @Override
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }
}
