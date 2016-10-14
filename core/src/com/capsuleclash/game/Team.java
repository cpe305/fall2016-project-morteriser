package com.capsuleclash.game;
import java.util.ArrayList;
import java.util.Collections;

public class Team {
   private ArrayList<Capsule> team;
   
   public Team() {
      team = new ArrayList<Capsule>();
   }
   
   public void add(Capsule x) {
      team.add(x);
   }
   
   public void remove(Capsule x) {
      team.remove(x);
   }
   
   public void sort() {
      Collections.sort(team);
   }
   
   public void print() {
      for (Capsule c: team) {
         System.out.println(c);
      }
   }
}
