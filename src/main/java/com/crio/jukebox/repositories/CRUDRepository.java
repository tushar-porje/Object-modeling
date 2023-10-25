package com.crio.jukebox.repositories;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository<T,M> {
    public T save(T entity);
    public List<T> findAll();
    public Optional<T> findById(M id);
    boolean existsById(M id);
    public void delete(T entity);
    public void deleteById(M id);
    public long count();
}
