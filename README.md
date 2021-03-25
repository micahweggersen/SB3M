# SB3M [![Codacy Badge](https://app.codacy.com/project/badge/Grade/18f327b2374540bdb5586b06a289b864)](https://www.codacy.com/gh/micahweggersen/SB3M/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=micahweggersen/SB3M&amp;utm_campaign=Badge_Grade) [![Build Status](https://travis-ci.com/micahweggersen/SB3M.svg?branch=main)](https://travis-ci.com/micahweggersen/SB3M)
## How to Run
 
### Windows: 
*   Clone project from git in IntelliJ or another IDE.
*   If you are using Windows, you can simply press run main in editor, 
    which you do by right clicking on the main-class in the project, and pressing "Run dMain.main()"


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


## Playing the game 
After running main, a menu bar will show up and you will have the options of playing a new game (starting a server),
or joining a game (provided that a server game already has been started). Once you have started a game, you can use 
the arrow keys to move around, but to get 9 cards to choose from, press D. You see the cards listed in the terminal. 
Press number keys 1-9 to choose, when the fifth card is chosen, the player moves by itself. 
    
## Known bugs
None
