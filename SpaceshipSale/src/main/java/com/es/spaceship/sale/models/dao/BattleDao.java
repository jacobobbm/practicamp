package com.es.spaceship.sale.models.dao;

import com.es.spaceship.sale.dto.RankingDTO;
import com.es.spaceship.sale.models.entity.Battle;
import com.es.spaceship.sale.models.entity.Ships;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BattleDao extends JpaRepository<Battle, Long> {

    @Query("select b.shipsToTransfers from Battle b where b.battleDate is not null and (b.attacker = :competitor or b.defender = :competitor)")
    List<Ships> findShipsByCompetitor(@Param("competitor") String competitorNick);

    @Query("select b from Battle b where b.battleDate is null and b.defender = (select c.nick from User c where c.id =  :userId)")
    List<Battle> hasBattlePending(@Param("userId") String userId);

    @Query("select new com.es.spaceship.sale.dto.RankingDTO(u.nick, count(b)) from User u " +
            "left join Battle b on b.challengeStatus in ('REJECTED','FINALIZED') and b.winner = u.nick " +
            "group by u.nick order by count(b) desc "
    )
    List<RankingDTO> findRanking();
}
