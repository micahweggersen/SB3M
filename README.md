# SB3M [![Codacy Badge](https://app.codacy.com/project/badge/Grade/18f327b2374540bdb5586b06a289b864)](https://www.codacy.com/gh/micahweggersen/SB3M/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=micahweggersen/SB3M&amp;utm_campaign=Badge_Grade) [![Build Status](https://travis-ci.com/micahweggersen/SB3M.svg?branch=main)](https://travis-ci.com/micahweggersen/SB3M)
## How to Run
 
### Windows: 
*   Clone project from git in IntelliJ or another IDE.
*   If you are using Windows, you can simply press run main in editor, 
    which you do by right clicking on the main-class in the project, and pressing "Run dMain.main()"
*   After running main, a menu bar will show up and you will have the options of playing a new game (starting a server),
    or joining a game (provided that a server game already has been started). 
*   To test the "join game"-function on just one computer you have to have two run configurations:
    *   Open the "Edit Run/Debug Configurations" (to the right of the hammer on the top of the screen)
    *   Press "Edit Configurations..."
    *   Click on main in Application, and then click on the icon above that is called "copy configuration", shaped 
        like two paper sheets.
*   Start a new game, maybe move your player around a bit if you want
*   Change your main configuration to your second one, run the application, and click join game.
*   You will now have to screens with the game, both showing what happens in the game.


### macOS:
*   If you are using OS X, you may have to add a VM option to run the program:
    *   Right-click on the main-class, choose 
        1. "More Run/Debug"
        2. "Modify Run Configurations..."
        3. "Modify Options"
        4. "Add VM Options"
        5. then in "VM Options" paste -XstartOnFirstThread.
*   After this you will be able to 
    run the program every time by right-clicking the main-class and pressing "Run Main.main()"
*   To test the "join game"-function on just one computer you have to have two run configurations:
    *   Open the "Edit Run/Debug Configurations" (to the right of the hammer on the top of the screen)
    *   Press "Edit Configurations..."
    *   Click on main in Application, and then click on the icon above that is called "copy configuration", shaped
        like two paper sheets.
*   Start a new game, maybe move your player around a bit if you want
*   Change your main configuration to your second one, run the application, and click join game.
*   You will now have to screens with the game, both showing what happens in the game.



## Playing the game 
Once you have started a game, you can press "S" to get dealt nine cards. These will be shown in the terminal, as well as on the screen. Press number keys 1-9 to choose your cards. When the fift card is chosen, your chosen cards will appear in reversed order (bug/mistake) further down on the screen. Press "S" to get the player to move according to each card. If you want to move around with arrow keys, press "F". This will
also make you able to test the different card functions, as they correspond with the number keys when you are not in "choose card mode".

## Known bugs
* Det er ikke lagt til innstillinger for spillet ennå, så når man trykker “preferences” på menyen, skjer det ingenting. 
* Serveren kan dessverre fort krasje. Vi tror det er fordi programmet får for mye å gjøre. Vi har erfart at om man trykker på de forskjellige tastene langsomt så skal det gå fint.
* De valgte kortene tegnes opp i reversert rekkefølge. Første valgte kort vil tegnes opp helt til høyre.

