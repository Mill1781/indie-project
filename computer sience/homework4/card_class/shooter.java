package card_class;
import card_game.*;

public class shooter extends Card {

    protected static int pp_consume = 3;//belong to shooter
    
    //without saveing, default constructor
    public shooter(){
        Max_life = 150;
        life = 150;
        atk = 100;
        Action_speed = 3;
    }

    //withsaveing
    public shooter(int LIFE, int ATK){
        Max_life = 150;
        life = LIFE;
        atk = ATK;
        Action_speed = 3;
    }

    //return class name "shooter"
    public String toString(){
        return "shooter";
    }

    //activate shooter's skill
    public void skill(Card ally[], Card enemy[], Player commander, Player opponent, int unit_position){
        String id_and_name;
        if(commander.get_id() == 1){
            id_and_name = "P2(" + opponent.get_name() + ")'s ";
        }
        else{//id = 2
            id_and_name = "P1(" + opponent.get_name() + ")'s ";
        }
        System.out.println("\nP" + commander.get_id() + "(" + commander.get_name() +")'s " + toString() + " activate its skill");

        for(int i = 0; i < 3; i++){
            if(enemy[i] == null){
                continue;
            }
            enemy[i].life -= 10;
            if(enemy[i].life <= 0){
            enemy[i].life = 0;
            }
            System.out.println("area "+ (i + 1) + " : " + id_and_name + enemy[i] + " has "+ enemy[i].life +" life points left");
        }
    }

    //return pp_comsume
    public static int get_pp_consume(){
        return pp_consume;
    }
    
}
