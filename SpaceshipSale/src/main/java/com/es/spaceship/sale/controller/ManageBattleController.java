package com.es.spaceship.sale.controller;

import com.es.spaceship.sale.dto.ProbeInformationDTO;
import com.es.spaceship.sale.dto.RankingDTO;
import com.es.spaceship.sale.models.entity.Battle;
import com.es.spaceship.sale.models.entity.Notification;
import com.es.spaceship.sale.models.entity.Ships;
import com.es.spaceship.sale.models.entity.User;
import com.es.spaceship.sale.service.IManageBattleService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/battle/probes/management")
@RequiredArgsConstructor
public class ManageBattleController {

    private final IManageBattleService manageBattleService;

    /**
     * Listado de naves por usuario.
     *
     * @param ownerId id del usuario.
     * @return List<Ships> listado de naves.
     */
    @GetMapping("/ships/ownerId/{ownerId}")
    public List<Ships> getShipsByUser(@PathVariable String ownerId) {
        return manageBattleService.getShipsByUser(ownerId);
    }

    /**
     * Agrega naves a la defensa.
     *
     * @param ownerId id del usuario.
     * @param lstIdShips listado de naves.
     */
    @PutMapping("/user/{ownerId}/ships-self-defense")
    public void setShipsToSelfDefense(@PathVariable String ownerId, @RequestBody List<Long> lstIdShips) {
        manageBattleService.setShipsToSelfDefense(ownerId, lstIdShips);
    }

    /**
     * Listado de naves perdidas y ganadas en las batallas.
     *
     * @param nick id del usuario.
     * @return List<Ships> listado de naves.
     */
    @GetMapping("/user/{nick}/ships-battle")
    public List<Ships> getShipsByCompetitor(@PathVariable String nick) {
        return manageBattleService.getShipsByCompetitor(nick);
    }

    /**
     * Listado de usuarios disponibles para desafiar.
     *
     * @param ownerId id del desafiante.
     * @return List<User> listado de usuaior disponibles para desafiar.
     */
    @GetMapping("/user/{ownerId}/battle")
    public List<User> getUserAvailable(@PathVariable String ownerId) {
        return manageBattleService.getUserAvailable(ownerId);
    }

    /**
     * Creacion de desafio.
     *
     * @param attackerId id del desafiante.
     * @param defenderNick nick del desafiado.
     * @param shipsForBattle listado de naves para la batalla.
     */
    @PutMapping("/user/{attackerId}/challenge")
    public void challengeUser(@PathVariable String attackerId, @RequestParam String defenderNick, @RequestParam List<Long> shipsForBattle) {
        manageBattleService.challengeUser(attackerId, defenderNick, shipsForBattle);
    }

    /**
     * El usuario desafiado, confirma o rechaza la  batalla.
     *
     * @param userId id del usuario desafiado.
     * @param battleId id de la batalla.
     * @param acceptChallenge valor de respuesta al desafio, donde true es aceptar y false rechazar.
     */
    @PutMapping("/user/{userId}/confirmBattle")
    public void confirmBattle(@PathVariable String userId, @RequestParam Long battleId,  @RequestParam Boolean acceptChallenge){
        manageBattleService.confirmBattle(userId, battleId, acceptChallenge);
    }

    /**
     * Ejecucion de la batalla.
     *
     * @param battleId id de la batalla.
     * @return Battle donde se encuentra la informacion del ganador de la batalla.
     */
    @PostMapping("/battle/{battleId}/startBattle")
    public Battle startBattle(@PathVariable Long battleId){
       return manageBattleService.startBattle(battleId);
    }

    /**
     * Lanzar sonda.
     *
     * @param userId id de usuario lanzador de sonda.
     * @param owner nick del usuario al que le lanzaran la sonda.
     * @return ProbeInformationDTO datos de la sonda.
     */
    @GetMapping("/user/{userId}/launchProbe")
    public ProbeInformationDTO launchProbe(@PathVariable String userId, @RequestParam String owner){
        return manageBattleService.launchProbe(userId, owner);
    }

    /**
     * Obtiene el listado de notificaciones por usuario.
     *
     * @param userId id del usuario.
     * @return List<Notification> listado de notificaciones.
     */
    @GetMapping("/user/{userId}/notificationList")
    public List<Notification> notificationList(@PathVariable String userId){
        return manageBattleService.notificationList(userId);
    }

    /**
     * Ranking de batallas.
     *
     * @return List<RankingDTO> listado de ranking general de las batallas realizadas.
     */
    @GetMapping("/battle/ranking")
    public List<RankingDTO> getRanking() {
        return manageBattleService.getRanking();
    }
}
