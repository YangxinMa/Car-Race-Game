## CMPT470 Team20 Final Project
```
    This project is a single-player car parkour rank web game, and players control a racing car to avoid oncoming obstacles on the track.
    Players do not need to control the accelerations but the moving directions of the car. 
    During the game, players get scores by touching gold coins on the track. With more scores, the player will rank higher on the leaderboard.
```

### How to play
```
    1. Players log in with their user IDs（maximum length 16 characters） and click "New Game" to start.
    2. Use the up, down, left, and right buttons on the keyboard to control the vehicle and avoid oncoming roadblocks.
    3. Players can change the difficulty (speed) of the game through the three buttons at the top of the game window.
    4. The game ends when the player hits the wall or a roadblock.
    5. Collect golds on the track and get more scores.
```

### About the Leaderboard
```
    Players are allowed to upload their records.
    The leaderboard only shows the highest record of each player.
    Use Previous and Next buttons, or enter keywords in the search bar to find player records.
    Use the scroll-down menu to change the number of entries displayed on each page (10, 15, 25...).
    Click the arrows on the table header to change the order.
```

## Steps should be taken to get the project working
### Web part
```
    1. vagrant up
    2. vagrant ssh
    3. cd project
    4. sudo mysql -u root -p
       (You dont need to enter password, just press enter)
    5. ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'Abc123456.';
    6. \q
    7. mvn spring-boot:run
```
### Game logic part
```
    Need to use IntelliJ IDEA to run src/main/java/com/team20/Model/App.java
```

## URL
```
    http://localhost:8080
```

## Reference:
```
   Bootstrap: https://getbootstrap.com/docs/4.0/examples/
   Springboot Tutorial: https://www.bilibili.com/video/BV1PE411i7CV?from=search&seid=11513155705726910520&spm_id_from=333.337.0.0
   MySQL Tutorial: https://www.bilibili.com/video/BV1NJ411J79W?p=1
```
