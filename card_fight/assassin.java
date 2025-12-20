//subclass of Card.java
public class assassin extends Card {

    private static int pp_consume = 3;//belong to assassin



    //without saveing, default constructor
    public assassin(){
        super(150, 150, 70, 5, 20);
    }

    //withsaveing
    public assassin(int LIFE, int ATK, int DEFENSE){
        super(150, LIFE, ATK, 5, DEFENSE);
    }

    //return class name "assassin"
    public String toString(){
        return "assassin";
    }

    //activate assassin's skill
    public void skill(Card ally[], Card enermy[], Player commander, Player opponent, int unit_position){
        System.out.println("\nP" + commander.get_id() + "(" + commander.get_name() +")'s " + toString() + " in Area " + unit_position  + " activate its skill");
        direct_attack(50, opponent);
    }

    //return pp_comsume
    public static int get_pp_consume(){
        return pp_consume;
    }


}
