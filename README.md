# Capsule Clash

[![Build Status](https://travis-ci.org/cpe305/fall2016-project-morteriser.svg?branch=master)](https://travis-ci.org/cpe305/fall2016-project-morteriser)

Capsule Clash is a 2 player strategy game where players select units to fight each other with on a turn based board.

Github url: https://github.com/cpe305/fall2016-project-morteriser
Github pages: https://cpe305.github.io/fall2016-project-morteriser/

## Description

Each player begins with their selected units.

Player 1 begins their turn and is able to:
   - Select a unit they own:
      - Select to Move:
         - Select a tile to move to. Able to Attack afterwards or end the turn.
      - Select to Attack:
         - Select a tile to attack if possible. Will end the turn afterwards.
      - Select to end turn.

Player 1's turn then ends, and player 2's turn begins, giving them the same choices to follow.

After an attack is dealt the units will lose health based on the damage it will take. When a unit no longer has any health it will be removed from play. A player wins once all the opposing units are gone or they have the highest overall health of all their units should 50 turns have based for both players.

## Tools Used

   - Libgdx
   - Gradle

## Contact

E-mail: hstoytch@calpoly.edu
Alternative e-mail: christoytchev@gmail.com
