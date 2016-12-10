package com.capsuleclash.game;

/**
 * A unit that contains many statistics to be used in the game.
 * @author Hristo
 */
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
   
   /**
    * A copy constructor to ensure that each Figure is unique.
    * @param other the Figure to copy.
    */
   public Figure(Figure other) {
	   this.id = other.getIdentity();
	   this.rarity = 1;
	   this.size = 1;
	   this.health = other.getHealth();
	   this.attack = other.getAttack();
	   this.defense = other.getDefense();
	   this.move = other.getMove();
	   this.name = other.getName();
	   this.action = other.getAction();
   }
   
   /**
    * A long constructor to set up all variables, handled by FigureCollection.
    * @param id the id of the figure.
    * @param health the figure's health.
    * @param attack the figure's attack.
    * @param defense the figure's defense.
    * @param move the figure's amount of tiles to move.
    * @param name the figure's name.
    * @param action the action that this figure uses.
    */
   public Figure(int id, int health, int attack, int defense,
		   int move, String name, Action action) {
      this.id = id;
      this.rarity = 1;
      this.size = 1;
      this.health = health;
      this.attack = attack;
      this.defense = defense;
      this.move = move;
      this.name = name;
      this.action = action;
   }
   
   @Override
   public int compareTo(Figure other) {
      int comparing = other.getIdentity();
      return ((Integer)id).compareTo(comparing);
   }
   
   @Override
   public String toString() {
      return "[" + name + "]: " + id + " " + rarity + " " + size + " " + health + " " + attack +
    		  " " + defense + " " + move + " " + action;

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
   
   @Override
   public int hashCode() {
	   return 0;
   }
   
   public int getAttack() {
	   return attack;
   }
   
   public int getDefense() {
	   return defense;
   }
   /**
    * Have this figure lose health based on how much damage it would take.
    * @param damage the amount of health to lose.
    */
   public void takeDamage(int damage) {
	   health -= damage;
	   if (health < 0) {
		   health = 0;
	   }
   }
   
   public int getHealth() {
	   return health;
   }
   
   public int getMove() {
	   return move;
   }
   
   public Action getAction() {
	   return action;
   }
   
   public String getName() {
	   return name;
   }
   
   public int getIdentity() {
	   return id;
   }

}
