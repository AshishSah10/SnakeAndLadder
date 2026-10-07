Requirements:

    - Size of the board should be scalable.(>=100)
    - Any no. of players can participate in the game (>=2).
    - By default, 2 entities are supported {Snake, Ladder}, in future more board entities can be added.
    - Snake and Ladder can be setup as per following and can be further extndable:
        - Random setup 
            - Board size will be 100.
            - Board will randomly setup the snakes and ladders for each game
            - Random setup has difficulty levels:
                Easy -> no. of ladders > no. of snakes
                Medium -> no. of ladders == no. of snakes
                Hard -> no. of ladders < no. of snakes
        - Standard setup -> Board will have its default board size and standard position for snake and ladder .
        - Custom setup -> Players can define Board size and positions for snakes and ladders.
    - There are standard game rule and can be further extendable.
    - In app notification of turn, moves, going up(via ladder), coming down(via snake), win e.t.c.
    - Dice face-values are {1,2,3,4,5,6}.