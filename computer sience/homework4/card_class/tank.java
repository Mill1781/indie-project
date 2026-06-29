package card_class;
import card_game.*;
public class tank extends Card {//inheritance
    
    protected static int pp_consume = 2;//belong to tank

    //without saveing, default constructor
    public tank(){
        Max_life = 300;
        life = 300;
        atk = 60;
        Action_speed = 2;
    }

    //withsaveing
    public tank(int LIFE, int ATK){
        Max_life = 300;
        life = LIFE;
        atk = ATK;
        Action_speed = 2;
    }

    //return class name "tank"
    public String toString(){
        return "tank";
    }

    //activate tank's skill
    public void skill(Card ally[], Card enemy[], Player commander, Player opponent, int unit_position){
        String id_and_name;
        if(commander.get_id() == 1){
            id_and_name = "P1(" + commander.get_name() + ")'s ";
        }
        else{//id = 2
            id_and_name = "P2(" + commander.get_name() + ")'s ";
        }
        System.out.println("\nP" + commander.get_id() + "(" + commander.get_name() +")'s " + toString() + " activate its skill");
        life += 20;
        if(life >= Max_life){
            life = Max_life;
        }
        System.out.println("area "+ unit_position + " : " + id_and_name + "tank's life  restored to " + life);
    }

    //return pp_comsume
    public static int get_pp_consume(){
        return pp_consume;
    }

}
