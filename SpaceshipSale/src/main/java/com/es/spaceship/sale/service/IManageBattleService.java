package com.es.spaceship.sale.service;

import com.es.spaceship.sale.dto.ProbeInformationDTO;
import com.es.spaceship.sale.dto.RankingDTO;
import com.es.spaceship.sale.models.entity.Battle;
import com.es.spaceship.sale.models.entity.Notification;
import com.es.spaceship.sale.models.entity.Ships;
import com.es.spaceship.sale.models.entity.User;

import java.util.List;

public interface IManageBattleService {

    public List<Ships> getShipsByUser(String ownerId);

    public void setShipsToSelfDefense(String ownerId, List<Long> lstIdShips) ;

    public List<Ships> getShipsByCompetitor(String ownerId);

    public List<User> getUserAvailable(String ownerId);

    public void challengeUser(String attackerId, String defenderNick, List<Long> shipsForBattle);

    public void confirmBattle(String userId, Long battleId, Boolean acceptChallenge);

    public Battle startBattle(Long battleId);

    public ProbeInformationDTO launchProbe(String userId, String owner);

    public List<Notification> notificationList(String userId);

    public List<RankingDTO> getRanking();
}
