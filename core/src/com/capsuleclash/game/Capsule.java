package com.capsuleclash.game;

public class Capsule implements Comparable<Capsule>{
   private int level;
   private String name;
   
   public Capsule(int level, String name) {
      this.setLevel(level);
      this.setName(name);
   }
   
   @Override
   public int compareTo(Capsule other) {
      int comparing = other.getLevel();
      return this.level - comparing;
   }
   
   @Override
   public String toString() {
      return "[" + name + "]: " + level;
   }

   public int getLevel() {
      return level;
   }

   public void setLevel(int level) {
      this.level = level;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
