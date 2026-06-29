//subclass of Card.java
public class tank extends Card {//inheritance
    
    private static int pp_consume = 2;//belong to tank

    //without saveing, default constructor
    public tank(){
        super(300, 300, 60, 3, 20);
    }

    //withsaveing
    public tank(int LIFE, int ATK, int DEFENSE){
        super(300, LIFE, ATK, 3, DEFENSE);
    }

    //return class name "tank"
    public String toString(){
        return "tank";
    }

    //activate tank's skill
    public void skill(Card ally[], Card enermy[], Player commander, Player opponent, int unit_position){
        String id_and_name;
        if(commander.get_id() == 1){
            id_and_name = "P1(" + commander.get_name() + ")'s ";
        }
        else{//id = 2
            id_and_name = "P2(" + commander.get_name() + ")'s ";
        }
        System.out.println("\nP" + commander.get_id() + "(" + commander.get_name() +")'s " + toString() + " in Area " + unit_position  + " activate its skill");
        ally[unit_position-1].assign_life(ally[unit_position-1].get_life() + 20);
        if(ally[unit_position-1].get_life() >= ally[unit_position-1].get_Max_life()){
            ally[unit_position-1].assign_life(ally[unit_position-1].get_Max_life());
        }
        System.out.println("area "+ unit_position + " : " + id_and_name + "tank's life  restored to " + ally[unit_position-1].get_life());
    }

    //return pp_comsume
    public static int get_pp_consume(){
        return pp_consume;
    }

}
