package com.capsuleclash.game;
import java.util.ArrayList;
import java.util.Collections;

public class Team {
   private ArrayList<Figure> team;
   
   public Team() {
      team = new ArrayList<Figure>();
   }
   
   public void add(Figure x) {
      team.add(x);
   }
   
   public void remove(Figure x) {
      team.remove(x);
   }
   
   public void sort() {
      Collections.sort(team);
   }
   
   public void print() {
      for (Figure c: team) {
         System.out.println(c);
      }
   }
}
