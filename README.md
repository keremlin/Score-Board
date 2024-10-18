# ScoreBoard
A Scoreboard implementation
For this implementation, there are assumptions :
- the number of summary requests is more than updates and adds, so I use treeSet because it is always sorted.
- there are Sync problems, therefore I used synchronizedSortedSet to solve the problem.
- some commits show the TDD progress.

