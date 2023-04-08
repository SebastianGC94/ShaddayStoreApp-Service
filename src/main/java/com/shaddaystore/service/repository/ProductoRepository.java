package com.shaddaystore.service.repository;

import com.shaddaystore.service.entity.Producto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepository extends CrudRepository<Producto, Integer> {
    @Query("SELECT P FROM Producto P WHERE P.vigencia IS 1 ")
    Iterable<Producto> listarProductosRecomendados();

    /*@Query("SELECT Prod FROM Producto Prod WHERE Prod.vigencia IS 1")
    Iterable<Producto> listarProductos();*/
    @Query("SELECT P FROM Producto P WHERE P.categoria.id=:idC")
    Iterable<Producto> listarProductosPorCategoria(int idC);

    @Modifying
    @Query("UPDATE Producto P SET P.stock=stock-:cant WHERE P.id=:id")
    void descontarStock(int cant, int id);

    @Modifying
    @Query("UPDATE Producto P SET P.stock=stock+:cant WHERE P.id=:id")
    void aumentarStock(int cant, int id);
}
