package card_class;
import card_game.*;

public class assassin extends Card {

    protected static int pp_consume = 3;//belong to assassin

    //without saveing, default constructor
    public assassin(){
        Max_life = 150;
        life = 150;
        atk = 70;
        Action_speed = 4;
    }

    //withsaveing
    public assassin(int LIFE, int ATK){
        Max_life = 150;
        life = LIFE;
        atk = ATK;
        Action_speed = 4;
    }

    //return class name "assassin"
    public String toString(){
        return "assassin";
    }

    //activate assassin's skill
    public void skill(Card ally[], Card enemy[], Player commander, Player opponent, int unit_position){
        System.out.println("\nP" + commander.get_id() + "(" + commander.get_name() +")'s " + toString() + " activate its skill");
        direct_attack(50, opponent);
    }

    //return pp_comsume
    public static int get_pp_consume(){
        return pp_consume;
    }


}
