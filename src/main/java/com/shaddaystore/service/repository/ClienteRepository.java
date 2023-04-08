package com.shaddaystore.service.repository;

import com.shaddaystore.service.entity.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
    @Query(value = "(SELECT EXISTS(SELECT id FROM cliente WHERE num_doc=:dni))", nativeQuery = true)
    /*En estas consultas debe ponerse el nombre de la columna tal cual está en phpMyAdmin
      ya que phpMyAdmin no permite combinar mayus y minus ---> numDoc quedaría entonces num_doc */
    int existByDoc(String dni);
    @Query(value = "SELECT EXISTS(SELECT C.* FROM cliente C WHERE C.num_doc=:dni AND NOT (C.id=:id))", nativeQuery = true)
    int existByDocForUpdate(String dni, int id);
}
