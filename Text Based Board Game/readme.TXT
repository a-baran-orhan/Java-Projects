In this project I implemented an application which is the logic core simulator of a simple board game.

There are two sides which are called Zorde and Calliance. Main theme of the game is the war
between two sides. Each side has 3 different types of characters. The game is played on a
board where each character occupies a cell on the board (just like chess). During the progress
of the game, each side makes moves in turns. The aim of the game is to kill every opponent
character.

HP:
Each character has a hit point (HP) value which indicates how healthy they are. This value is
lowered equally to the damage taken when a character is being attacked by another character.
If a character's HP value is 0 or below, they are considered dead.

AP:
Attack power (AP) of a character indicates how hard it can attack its opponents. This value
determines how much damage will be taken by the defending character. By default, damage
done by an attacking character is equal to its AP so when it attacks another character, this
value will be subtracted from the defender's HP.
The table below shows cell types of each side and their default (initial) HP and AP val-
ues.

	ZORDE CHARACTERS				CALLIANCE CHARACTERS
	ORK	TROLL	GOBLIN				HUMAN	ELF		DWARF
HP	200	150		80			100		70		120
AP	30	20		10			30		15		20


Board and Initial Armies:

The game takes place on a board which is very similar to a chess board. Size of the board
and initial armies of both sides (including their initial positions) will be provided via an input
file, the initials file.
During the game, you will create an output showing the current status of the board.
There is one line for each row (horizontally) and two character spaces for each column
(vertically) (b).


Commands:

The program you will implement is a turn based strategy game, therefore, the players will
make their moves in turns as the game continues. The moves made by each player is supplied via an input le, the commands le. Each line of the commands le indicates a single move
made by a player.
As you can see in the figure, a move command starts with the ID of the character to be moved,
which is followed by a sequence of integers that are inside the interval [-1, 1] (i.e. the sequence
may only contain -1, 0 and 1) and these integer numbers are separated by semicolons. This
sequence consists of even number of integer as each couple indicates one step of the move.
For example, at the first line of the figure the integer sequence indicates a 4 step move as
illustrated in the figure. If we look at the position of E0, its current position is at the position
of \4 8", and if we make a move step with 0;-1, then its position will shift to the \4 7" on the
board.
After reading the initials file and locating the characters on the game board, your program
should start reading the commands file line by line and execute the moves of the players. The
program should exit when it reaches the end of the commands file.


COMBAT RULES
1. Characters cannot move onto the friendly characters (friendly character means charac-
ters that are at the same side). If a character tries to move onto a friendly character,
its move isfinalized at the current location and the rest of its move sequence will be
ignored. For example if a character tries to move onto a friendly character at the second
step of a 4-step move, only thefirst step of the move will be executed.
2. Characters can move onto the enemy characters. If such a situation occurs, two enemy
characters that are at the same cellfight to the death for the cell. Before afight to
the death, the attacking character will make a single attack to the defending character
with its attack power. Afight to the death can be won by the character that has the
highest current HP value but its HP value will decrease by an amount equal to the
losing characters current HP value. For example, if a human (current HP : 100) moves
onto a cell that is currently occupied by an ork (current HP : 60), the human will make
an initial attack to the ork (now, the ork's current HP is 30). Then, the ork will die
and human's HP value will decrease to 70.
(a) The attack that is made before thefight to the death is not a normal attack. Thus,
it will only effect the defending enemy character, not the other characters at the
neighboring 8 cells.
(b) After afight to the death, the attacker will stop moving. For example if a character
tries to move onto an enemy character at the second step of a 4-step move, even if
the attacker is the winner of thefight to the death, only thefirst and second steps
of the move will be executed. The rest of the move sequence will be ignored.
(c) If two characters have same HP just before afight to the death, they both die.
3. Before they start a move sequence, Orks will first heal any friendly character in one of
the neighboring cells.
• Friendly characters will be healed by 10 hit points.
• A character's hit point cannot exceed its default hit point via healing. If this
happens, you should decrease the current hit point to the default hit point of that
character.
• When healing the neighboring characters, Orks will heal themselves too.
4. After theyfinish a move sequence, Elfs will make a ranged attack (instead of thefinal
step's default attack) to all enemy characters that are in range of 2 cells with an attack
power of 15.
• If an Elf's move sequence is interrupted by moving through an enemy character,
then this power cannot be used. This power can only be used if the Elf completes
its whole move sequence without being interrupted.
5. Game ends when all characters of one side is dead. When the game ends, you should
add a line indicating the winner of the game as shown in thefigure.
