# Monty Hall Kata #

This eponymous gameshow host tempts contestants with a
big prize, hidden behind one of three doors. The contestant
begins by choosing a door, but not opening it. Then Monty
steps forward and opens one of the other doors. He reveals a
goat (!). Then the contestant has the choice of either sticking
with the door they have already chosen, or switching to the
other unopened door. Whichever door the contestant decides
on will be opened, and if they find the prize, they get to keep
it. (I’m not sure what happens if they get the second goat!) So
what’s the best strategy?

## The Monty Hall Dilemma ##

That’s the classic “Monty Hall Dilemma”. Should you keep to
your original choice of door? You could try playing the game
yourself using this [online version](https://www.mathwarehouse.com/monty-hall-simulation-online/)?
People are biased towards sticking with what they’ve chosen,
and the vast majority of people keep the door they originally
picked. Intuitively there should be an equal chance of the prize
being behind any of the three doors, so it shouldn’t matter if
you stick or switch. However, in this case, your intuition is
wrong. You are twice as likely to win the prize if you switch
to the other unopened door.

I was not the only one to disbelieve this result, apparently
even famous mathematicians have refused to accept it. What
finally convinced them, was a computer simulation. So, your
task is to write that computer simulation. Prove that switching
doors is best, by simulating 1000 games using each strategy
and comparing the winning percentage.