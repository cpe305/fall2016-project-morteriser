package com.capsuleclash.game;

public class Figure implements Comparable<Figure>{
   private int id;
   private int rarity;
   private int size;
   private int health;
   private int attack;
   private int defense;
   private int move;
   private String name;
   private Action action;
   
   public Figure(int id, int rarity, int size, int health, int attack, int defense,
		   int move, String name, Action action) {
      this.id = id;
      this.rarity = rarity;
      this.size = size;
      this.health = health;
      this.attack = attack;
      this.defense = defense;
      this.move = move;
      this.name = name;
   }
   
   @Override
   public int compareTo(Figure other) {
      int comparing = other.getIdentity();
      return ((Integer)id).compareTo(comparing);
   }
   
   @Override
   public String toString() {
      return "[" + name + "]: " + id;
   }
   
   @Override
   public boolean equals(Object other) {
	   boolean result = false;

	   if (other != null && other instanceof Figure) {
		   Figure fig = (Figure)other;
		   result = this.id == fig.getIdentity();
	   }
	   return result;
   }
   
   public int getIdentity() {
	   return id;
   }

}
