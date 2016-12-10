package com.capsuleclash.game;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * The player's Team of Figures that they will use on the board.
 * @author Hristo
 *
 */
public class Team {
   private List<Figure> myTeam;
   
   /**
    * Constructs a new list for the team.
    */
   public Team() {
      myTeam = new ArrayList<>();
   }
   
   /**
    * Adds the figure to the list.
    * @param x figure to be added.
    */
   public void add(Figure x) {
      myTeam.add(x);
   }
   
   /**
    * Removes the figure from the list.
    * @param x figure to be removed.
    */
   public void remove(Figure x) {
      myTeam.remove(x);
   }
   
   /**
    * Gets the total health of all figures in this team.
    * @return the total health of all figures.
    */
   public int totalHealth() {
	   int total = 0;
	   for (Figure fig : myTeam) {
		   total += fig.getHealth();
	   }
	   return total;
   }
   
   /**
    * Sorts the Team list by id order.
    */
   public void sort() {
      Collections.sort(myTeam);
   }
   
}
