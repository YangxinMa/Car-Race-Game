package com.team20.Controller;

import com.team20.GameLogic.gameMechanics.Game;
import com.team20.TempSql.Record;
import com.team20.Wrapper.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class MainController {
    private static List<GameWrapper> games = new ArrayList<>();
    private static int gameID = 1; // Todo: may use AtomicInteger will be better
    private static List<Record> users = new ArrayList<>(); // Todo: Need to switch to SQL storage
    private List<Game> backGames = new ArrayList<>();

//    @PostMapping("/login")
//    @ResponseStatus(HttpStatus.OK)
//    public String login(@RequestBody String username) {
//        for (RecordWrapper user : users) {
//            if (user.user.equals(username)) {
//                return username;
//            }
//        }
//        RecordWrapper newUser = new RecordWrapper(username);
//        users.add(newUser);
//        return username;
//    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody String username) {
        for (Record user : users) {
            if (user.user.equals(username)) {
                return username;
            }
        }
        Record newUser = new Record(username, users.size()+1);
        users.add(newUser);
        return username;
    }

    @GetMapping("/rank")
    public Object[] getRank(){
        List<Map> userList = new ArrayList<>();
        users.sort(new Comparator<Record>() {
            @Override
            public int compare(Record r1, Record r2) {
                return r1.rank - r2.rank;
            }
        });
        for (Record user: users) {
            Map dict = new HashMap();
            dict.put("Rank", user.rank);
            dict.put("User", user.user);
            dict.put("Score", user.score);
            userList.add(dict);
        }
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
        GameWrapper newGame = GameWrapper.getGame(game, gameID);
        gameID++;
        games.add(newGame);
        return newGame;
    }

    @GetMapping("/game/{id}")
    public GameWrapper getGameById(@PathVariable("id") int id) throws Exception {
        for (GameWrapper game : games) {
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
        move = move.substring(0, move.length()-1); // Do not remove it.
        System.out.println(move);
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
            backGames.get(id).moveOpposites();
            backGames.get(id).restartOpposites();
        }
        else
            backGames.get(id).playGame(movement);
        GameWrapper temp = GameWrapper.getGame(backGames.get(id), id + 1);
        games.set(id, temp);

    }

    @PostMapping("/game/{id}/stop")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void stopGameAndUploadRecord(@PathVariable("id") int id,
                                        @RequestBody String username) {
        for (GameWrapper game : games) {
            if (game.gameId == id) {
                game.stopByUser = false;
                for (Record user : users) {
                    if (user.user.equals(username)) {
                        user.score = game.currentScore;
                        // Todo: bug: rank needs to be update too.
                    }
                }
            }
        }
    }




}

