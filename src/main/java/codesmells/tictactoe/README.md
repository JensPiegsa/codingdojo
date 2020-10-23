# Code Smells Kata

**Smelly Tic Tac Toe**

We created a very smelly implementation of TicTacToe. There are quite a few code smells in the implementation: 

 * [x] Primitive obsession
    * Board.java
 * [x] Feature envy
    * Board.java
 * [x] Data class
    * Tile.java
 * [x] Message chain
    * Game.java
 * [x] Long method
    * Game.java
 * [x] Comments
    * Game.java
 * [x] Long parameter list
    * Game.java (Play(...))
 * [x] Shotgun surgery
    * Game.java (f.e. extending x and o with a new symbol)
 * [x] Duplicated code
    * Game.java (winner-method)
 * [x] Large class
    * Game.java
 * [x] Divergent change
    * Game.java (Play and Winner)
 * [x] Data clump
    * Board.java & Game.java for Symbol
 * [x] Lazy class
    * Tile.java
 * [ ] Dead code
 * [x] Magic value
    * Game.java

Start by identifying the smells and then slowly refactor the code. Remember to keep the tests passing at all times during the refactor. It's ok to revert back to a previous working state at any moment.
