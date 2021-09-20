package com.example.fgm.repository;

import com.example.fgm.model.InfoResponse;
import com.example.fgm.model.Item;
import com.example.fgm.model.Micro_Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface MicroSavingsRepository extends JpaRepository<Micro_Savings, Long> {

    @Query("SELECT new com.example.fgm.model.InfoResponse (i.goal, SUM(m.micros), m.item) FROM Item i JOIN i.micro_savings2 m WHERE i.id = :id GROUP BY i.goal")
    //  @Query(value = "SELECT SUM(m.micros) FROM Micro_Savings m WHERE m.item_id IN (SELECT i.id FROM Item i WHERE i.id = i.id)")
    public List<InfoResponse> getJoinInfo(@Param("id")long id);

    @Query(value = "SELECT target_amt FROM Item WHERE id = :id")
    public Long getTargetVal(@Param("id") long id);

    //Delete the id, amount of that row which inserted with more Remaining target value.
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Micro_Savings WHERE id2 = :id AND micros = :amt")
    public void deleteHigherValue(long id, long amt);


    @Query(value = "SELECT new com.example.fgm.model.InfoResponse (i.id, i.goal, i.target_amt) FROM Item i JOIN i.micro_savings2 m WHERE i.target_amt <> SUM(m.micros) GROUP BY i.id")
    public List<InfoResponse> findSome();
}