# Memory Game

This program implements a basic 5x5 memory game for users to play. It supports the following game functions:
- Start
- Restart
- View total number of guesses
- Quit

A specific design decision made for this assignment included the "happy face" card. There is one stand-alone "happy face" wildcard that does not have another match. If the user uncovers the happy face on their first attempt, it has no impact on the number of guesses made and resets to allow another first attempt. If the user uncovers the happy face on their second attempt in trying to match an already uncovered first card, total number of guesses is incremeted as this match is not valid.

The card icons are randomly assigned between games so that they do not fall on the same location on the grid for every game.

