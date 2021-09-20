package com.example.fgm.repository;

import com.example.fgm.model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{

    @Query(value = "SELECT i FROM Item i WHERE i.id <> :id")
    List<Item> findAllNot(@Param("id") long id);
}
