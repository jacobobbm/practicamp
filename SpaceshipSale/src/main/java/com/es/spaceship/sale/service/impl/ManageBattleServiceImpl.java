package com.es.spaceship.sale.service.impl;

import com.es.spaceship.sale.dto.RankingDTO;
import com.es.spaceship.sale.models.entity.*;
import com.es.spaceship.sale.util.Constants;
import com.es.spaceship.sale.dto.ProbeInformationDTO;
import com.es.spaceship.sale.exception.BusinessException;
import com.es.spaceship.sale.models.dao.BattleDao;
import com.es.spaceship.sale.models.dao.NotificationDao;
import com.es.spaceship.sale.models.dao.ShipDao;
import com.es.spaceship.sale.models.dao.UserDao;
import com.es.spaceship.sale.service.IManageBattleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManageBattleServiceImpl implements IManageBattleService {

    private final ShipDao shipDao;

    private final UserDao userDao;

    private final BattleDao battleDao;

    private final NotificationDao notificationDao;

    private void hasBattlePending(String userId) {
        List<Battle> battlePending = battleDao.hasBattlePending(userId);
        StringBuilder battleLst = new StringBuilder();
        battlePending.forEach(item -> battleLst.append(Constants.BattlePending.BATTLE_NUMBER)
                .append(item.getId()).append(Constants.BattlePending.CHALLENGING)
                .append(item.getAttacker())
                .append(Constants.BattlePending.SEPARATOR));

        if (!battlePending.isEmpty()) {
            throw new BusinessException(
                    BusinessException.errorBattlePending + battleLst.toString());
        }
    }

    private void validateChallengeStatus(Battle battle) {
        if (battle.getChallengeStatus() == null || Constants.Battle.REJECTED.equals(battle.getChallengeStatus())) {
            throw new BusinessException(BusinessException.errorBattleChallengeStatus);
        } else if (Constants.Battle.FINALIZED.equals(battle.getChallengeStatus())) {
            throw new BusinessException(BusinessException.errorChallengeFinished);
        }
    }

    private User validateNick(String nick) {
        User user = this.userDao.findByNick(nick);

        if (user == null)
            throw new BusinessException(BusinessException.errorNickNotExist);

        return user;
    }

    private void validateDefenseFromNick(String nick) {
        User user = this.userDao.findByNick(nick);

        if (user.getShipSelfDefense().isEmpty())
            throw new BusinessException(BusinessException.forbidenForShipDefenseEmpty);
    }

    @Override
    public List<Ships> getShipsByUser(String ownerId) {
        this.hasBattlePending(ownerId);
        return this.shipDao.findByOwnerId(ownerId);
    }

    @Override
    public void setShipsToSelfDefense(String ownerId, List<Long> lstIdShips) {
        User user = userDao.findById(ownerId).get();

        user.getShipSelfDefense().addAll(lstIdShips.stream().map(item -> {
            Ships ship = new Ships();
            ship.setId(item);
            return ship;
        }).collect(Collectors.toList()));

        this.userDao.save(user);
    }

    @Override
    public List<Ships> getShipsByCompetitor(String nick) {
        this.hasBattlePending(this.validateNick(nick).getId());
        return battleDao.findShipsByCompetitor(nick);
    }

    @Override
    public List<User> getUserAvailable(String ownerId) {
        this.hasBattlePending(ownerId);
        return userDao.findAllByIdNot(ownerId);
    }

    @Override
    public void challengeUser(String attackerId, String defenderNick, List<Long> shipsForBattle) {
        this.hasBattlePending(attackerId);
        this.validateNick(defenderNick);
        this.validateDefenseFromNick(defenderNick);

        User user = this.userDao.findById(attackerId).get();

        if (user.getShipSelfDefense().isEmpty())
            throw new BusinessException(BusinessException.errorShipDefenseEmpty);

        List<Ships> shipsBattle = this.shipDao.findShipsByUserForBattle(attackerId, shipsForBattle,
                user.getShipSelfDefense().stream().map(Ships::getId).collect(Collectors.toList()));

        if (shipsBattle.isEmpty())
            throw new BusinessException(BusinessException.errorShipBattleEmpty);

        Battle battle = new Battle();
        battle.setAttacker(user.getNick());
        battle.setDefender(defenderNick);
        battle.setShipsBattle(shipsBattle);
        battle.setAttackerDefensivePower(this.calculateDefensivePower(shipsBattle));
        battle.setAttackerOffensivePower(this.calculateOffensivePower(shipsBattle));

        battle = this.battleDao.save(battle);

        StringBuilder message = new StringBuilder();
        message.append(Constants.Notification.NEW_CHALLENGE)
                .append(user.getNick())
                .append(Constants.BattlePending.BATTLE_NUMBER)
                .append(battle.getId());
        this.createNotification(this.userDao.findByNick(defenderNick).getId(), message.toString());
    }

    @Override
    public void confirmBattle(String userId, Long battleId, Boolean acceptChallenge) {
        User user = this.userDao.findById(userId).get();
        Battle battle = this.battleDao.findById(battleId).get();

        if (acceptChallenge) {
            battle.setDefenderDefensivePower(this.calculateDefensivePower(user.getShipSelfDefense()));
            battle.setDefenderOffensivePower(this.calculateOffensivePower(user.getShipSelfDefense()));
            battle.setChallengeStatus(Constants.Battle.ACCEPTED);
            user.getShipSelfDefense().forEach(ships -> battle.getShipsDefense().add(ships));
        } else {
            User userAttacker = this.userDao.findByNick(battle.getAttacker());

            battle.setBattleDate(new Date());
            battle.setChallengeStatus(Constants.Battle.REJECTED);
            battle.setWinner(battle.getAttacker());

            List<Ships> tributes = this.shipsTribute(user.getShipSelfDefense());
            tributes.forEach(ships -> battle.getShipsToTransfers().add(ships));
            shipDao.changeOwnerByShipsId(userAttacker.getName(), tributes.stream().map(Ships::getId).collect(Collectors.toList()));

            user.getShipSelfDefense().removeAll(tributes);
            userDao.save(user);
        }

        battleDao.save(battle);
    }

    @Override
    public Battle startBattle(Long battleId) {
        Battle battle = battleDao.findById(battleId).get();
        this.validateChallengeStatus(battle);

        Long attackAttacker = battle.getAttackerOffensivePower();
        Long attackDefender = battle.getDefenderOffensivePower();

        List<Long> attackerDefense = battle.getShipsBattle().stream().map(Ships::getId).map(shipDao::sumDefensivePowerByShip).collect(Collectors.toList());
        List<Long> defenderDefense = battle.getShipsDefense().stream().map(Ships::getId).map(shipDao::sumDefensivePowerByShip).collect(Collectors.toList());

        Long AttackResult = this.attackInBattle(attackAttacker, defenderDefense);
        Long defenderResult = this.attackInBattle(attackDefender, attackerDefense);

        switch (AttackResult.compareTo(defenderResult)) {
            case 1:
                this.updateWinner(battle.getAttacker(), battle, battle.getShipsDefense());
                User user = userDao.findByNick(battle.getDefender());
                user.getShipSelfDefense().clear();
                userDao.save(user);
                break;
            case -1:
                this.updateWinner(battle.getDefender(), battle, battle.getShipsBattle());
                break;
            default:
                this.updateWinner(Constants.Battle.NO_WINNER, battle, null);
        }

        return battle;
    }

    @Override
    public ProbeInformationDTO launchProbe(String userId, String owner) {
        this.validateNick(owner);
        User targetUser = this.userDao.findByNick(owner);
        User launcherUser = this.userDao.findById(userId).get();

        ProbeInformationDTO probeInformationDTO = new ProbeInformationDTO();

        if (probability50()) {
            probeInformationDTO.setNumberShips(targetUser.getShipSelfDefense().size());

            probeInformationDTO.setTypesShips(targetUser.getShipSelfDefense().stream()
                    .map(Ships::getShipType)
                    .flatMap(List::stream)
                    .map(ShipType::getNameShipType)
                    .collect(Collectors.groupingBy(e -> e, Collectors.counting())));
        }
        if (probability25()) {
            probeInformationDTO.setTotalAttackPower(calculateOffensivePower(targetUser.getShipSelfDefense()));
            probeInformationDTO.setFleetDefense(calculateDefensivePower(targetUser.getShipSelfDefense()));
        }
        if (probability50()) {
            this.createNotification(targetUser.getId(), Constants.Notification.LAUNCH_PROBE + launcherUser.getNick());
        }

        return probeInformationDTO;
    }

    @Override
    public List<Notification> notificationList(String userId) {
        return this.notificationDao.findByUserId(userId);
    }

    @Override
    public List<RankingDTO> getRanking() {
        return battleDao.findRanking();
    }

    private boolean probability25() {
        Random random = new Random();
        return random.nextInt(4) == 0;
    }

    private boolean probability50() {
        Random random = new Random();
        return random.nextInt(2) == 0;
    }

    private Long attackInBattle(Long attack, List<Long> defense) {
        Long attackValue = attack;
        List<Long> defenseLst = defense.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        for (Long item : defenseLst) {
            attackValue = attackValue - item;
        }

        return attackValue;
    }

    private void updateWinner(String winner, Battle battle, List<Ships> shipsTransfer) {
        battle.setWinner(winner);
        battle.setBattleDate(new Date());
        battle.setChallengeStatus(Constants.Battle.FINALIZED);

        if (shipsTransfer != null && !shipsTransfer.isEmpty()) {
            User userWinner = this.userDao.findByNick(winner);

            shipsTransfer.forEach(ships -> battle.getShipsToTransfers().add(ships));
            shipDao.changeOwnerByShipsId(userWinner.getName(), shipsTransfer.stream().map(Ships::getId).collect(Collectors.toList()));

        }

        battleDao.save(battle);
    }

    private Long calculateDefensivePower(Collection<Ships> ships) {
        return ships.stream().map(Ships::getId).mapToLong(id -> {
            Long sum = shipDao.sumDefensivePowerByShip(id);
            return sum == null ? 0L : sum;
        }).sum();
    }

    private Long calculateOffensivePower(Collection<Ships> ships) {
        return ships.stream().map(Ships::getId).mapToLong(id -> {
            Long sum = shipDao.sumOffensivePowerByShip(id);
            return sum == null ? 0L : sum;
        }).sum();
    }

    private List<Ships> shipsTribute(Collection<Ships> ships) {
        return shipDao.findShipWithMaxDefensivePowerByIdIn(ships.stream().map(Ships::getId).collect(Collectors.toList()));
    }

    private void createNotification(String userId, String message) {
        Notification notification = new Notification();

        notification.setMessage(message);
        notification.setRead(false);
        notification.setUserId(userId);
        notification.setNotificationDate(new Date());

        this.notificationDao.save(notification);
    }
}
