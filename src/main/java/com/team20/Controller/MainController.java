package com.team20.Controller;

import com.team20.GameLogic.gameMechanics.Game;
import com.team20.Dao.RankDao;
import com.team20.Wrapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class MainController {

    @Autowired
    RankDao rankDao;

    private static List<GameWrapper> games = new ArrayList<>();
    private List<Game> backGames = new ArrayList<>();

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody String username) {
        username = username.substring(0, username.length()-1);
        rankDao.addUser(username);
        return username;
    }

    @GetMapping("/rank")
    public Object[] getRank(){
        List<Map> userList = rankDao.getData();
        return userList.toArray();
    }

    @GetMapping("/game")
    public List<GameWrapper> getGames() {
        return games;
    }

    @PostMapping("/game")
    @ResponseStatus(HttpStatus.CREATED)
    public GameWrapper createGame() {
        Game game = new Game();
        backGames.add(game);
        int index = backGames.size();
        GameWrapper newGame = GameWrapper.getGame(game, index);
        games.add(newGame);
        return newGame;
    }

    @GetMapping("/game/{id}")
    public GameWrapper getGameById(@PathVariable("id") int id) throws Exception {
        for (GameWrapper game : games) {
            System.out.println(game.gameId);
            if (game.gameId == id) {
                return game;
            }
        }
        throw new Exception();
    }

    @GetMapping("/game/{id}/board")
    public BoardWrapper getBoardById(@PathVariable("id") int id) throws Exception {
        for (GameWrapper game : games) {
            if (game.gameId == id) {
                return game.board;
            }
        }
        throw new Exception();
    }

    // Todo: wait for implementation
    @PostMapping("/game/{id}/move")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void makeMoving(@PathVariable("id") int id,
                           @RequestBody String move) throws Exception {
        move = move.substring(0, move.length()-1);
        String movement = " ";
        boolean carsMove = false;
        switch (move) {
            case "MOVE_UP": {
                movement = "W";
                break;
            }
            case "MOVE_DOWN": {
                movement = "S";
                break;
            }
            case "MOVE_LEFT": {
                movement = "A";
                break;
            }
            case "MOVE_RIGHT": {
                movement = "D";
                break;
            }
            case "MOVE_CARS": {
                movement = "m";
                carsMove = true;
                break;
            }
            default:
                throw new Exception();
        }
        if(carsMove){
            backGames.get(id-1).moveOpposites();
            backGames.get(id-1).restartOpposites();
        }
        else {
            backGames.get(id-1).playGame(movement);
        }
        GameWrapper temp = GameWrapper.getGame(backGames.get(id-1), id);

        games.set(id-1, temp);

    }

    @PostMapping("/game/{id}/stop")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void stopGameAndUploadRecord(@PathVariable("id") int id,
                                        @RequestBody String username) {
        username = username.substring(0, username.length()-1);
        for (GameWrapper game : games) {
            if (game.gameId == id) {
                game.stopByUser = false;
                rankDao.save(username, (float) game.currentScore);

            }
        }
    }




}

