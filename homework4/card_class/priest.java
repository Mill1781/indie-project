package card_class;
import card_game.*;
public class priest extends Card {
    
    protected static int pp_consume = 2;//belong to priest

    //without saveing, default constructor
    public priest(){
        Max_life = 200;
        life = 200;
        atk = 50;
        Action_speed = 1;//fastest
    }

    //withsaveing
    public priest(int LIFE, int ATK){
        Max_life = 200;
        life = LIFE;
        atk = ATK;
        Action_speed = 1;//fastest
    }

    //return class name "priest"
    public String toString(){
        return "priest";
    }

    //activate priest's skill
    public void skill(Card ally[], Card enemy[], Player commander, Player opponent, int unit_position){
        String id_and_name;
        if(commander.get_id() == 1){
            id_and_name = "P1(" + commander.get_name() + ")'s ";
        }
        else{//id = 2
            id_and_name = "P2(" + commander.get_name() + ")'s ";
        }
        System.out.println("\nP" + commander.get_id() + "(" + commander.get_name() +")'s " + toString() + " activate its skill");
        for(int i = 0; i < 3; i++){
            if(ally[i] != null){
                ally[i].life += 10;
                if(ally[i].life >= ally[i].Max_life){
                ally[i].life = ally[i].Max_life;
            }
            System.out.println("area "+ (i + 1) + " : " + id_and_name + ally[i] + "'s life restored to " + ally[i].life);
            }
        }
    }
    
    //return pp_comsume
    public static int get_pp_consume(){
        return pp_consume;
    }
    
}

