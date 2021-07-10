package com.es.spaceship.sale.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.es.spaceship.sale.models.entity.Ships;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ShipDao extends JpaRepository<Ships, Long> {

    List<Ships> findByOwnerId(String ownerId);

    @Query("select s from Ships s where s.ownerId = :attackerId and s.id not in (:shipsDefense) and s.id in (:shipsBattle)")
    List<Ships> findShipsByUserForBattle(@Param("attackerId") String attackerId, @Param("shipsBattle") List<Long> lstships, @Param("shipsDefense") List<Long> lstshipsDefense);

    @Query(nativeQuery = true,
            value = "SELECT sum(w.power) " +
                    "FROM Ships s " +
                    "left join ships_ship_type st on st.ships_id = s.id " +
                    "left join Ship_type t on t.id = st.ship_type_id " +
                    "left join ship_type_set_weapons tsw on tsw.ship_type_id = t.id " +
                    "left join weapon w on w.id = tsw.set_weapons_id " +
                    "where s.id = :shipId")
    Long sumOffensivePowerByShip(Long shipId);

    @Query(nativeQuery = true,
            value = "SELECT sum(h.absorbed_Damage + a.absorbed_Damage) " +
                    "FROM Ships s " +
                    "left join ships_ship_type st on st.ships_id = s.id " +
                    "left join Ship_type t on t.id = st.ship_type_id " +
                    "left join ship_type_system_defence tsd on tsd.ship_type_id = t.id " +
                    "left join defense_system ds on ds.id = tsd.system_defence_id " +
                    "left join defense_system_armor dsa on dsa.defense_system_id = ds.id " +
                    "left join armor a on a.id = dsa.armor_id " +
                    "left join defense_system_shield dsh on dsh.defense_system_id = ds.id " +
                    "left join shield h on h.id = dsh.shield_id " +
                    "where s.id = :shipId")
    Long sumDefensivePowerByShip(Long shipId);


    @Query(nativeQuery = true,
            value = "SELECT s.* " +
                    "FROM Ships s " +
                    "left join ships_ship_type st on st.ships_id = s.id " +
                    "left join Ship_type t on t.id = st.ship_type_id " +
                    "left join ship_type_system_defence tsd on tsd.ship_type_id = t.id " +
                    "left join defense_system ds on ds.id = tsd.system_defence_id " +
                    "left join defense_system_armor dsa on dsa.defense_system_id = ds.id " +
                    "left join armor a on a.id = dsa.armor_id " +
                    "left join defense_system_shield dsh on dsh.defense_system_id = ds.id " +
                    "left join shield h on h.id = dsh.shield_id " +
                    "where s.id in (:shipIds) " +
                    "group by s.id " +
                    "order by sum(h.absorbed_Damage + a.absorbed_Damage) desc " +
                    "limit 1")
    List<Ships> findShipWithMaxDefensivePowerByIdIn(List<Long> shipIds);


    @Modifying
    @Query("UPDATE Ships s SET s.owner = :newOwner WHERE s.id in (:ships)")
    void changeOwnerByShipsId(String newOwner, List<Long> ships);

}
