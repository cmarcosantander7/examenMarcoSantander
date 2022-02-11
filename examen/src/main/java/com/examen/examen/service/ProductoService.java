package com.examen.examen.service;

import com.examen.examen.entity.Producto;

import java.util.Optional;

public interface ProductoService {

    public Iterable<Producto> findAll();
    public Optional<Producto> findById(Long id);

    public Producto save(Producto user);

    public void deleteById(Long id);
}
