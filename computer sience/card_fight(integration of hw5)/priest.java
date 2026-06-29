//subclass of Card.java
public class priest extends Card {
    
    private static int pp_consume = 2;//belong to priest

    //without saveing, default constructor
    public priest(){
        super(200, 200, 50, 2, 20);//fastest
    }

    //withsaveing
    public priest(int LIFE, int ATK, int DEFENSE){
        super(200, LIFE, ATK, 2, DEFENSE);
    }

    //return class name "priest"
    public String toString(){
        return "priest";
    }

    //activate priest's skill
    public void skill(Card ally[], Card enermy[], Player commander, Player opponent, int unit_position){
        String id_and_name;
        if(commander.get_id() == 1){
            id_and_name = "P1(" + commander.get_name() + ")'s ";
        }
        else{//id = 2
            id_and_name = "P2(" + commander.get_name() + ")'s ";
        }
        System.out.println("\nP" + commander.get_id() + "(" + commander.get_name() +")'s " + toString() + " in Area " + unit_position  + " activate its skill");
        for(int i = 0; i < 3; i++){
            if(ally[i] != null){
                ally[i].assign_life(ally[i].get_life() + 10);
                if(ally[i].get_life() >= ally[i].get_life()){
                    ally[i].assign_life(ally[i].get_Max_life());
            }
            System.out.println("area "+ (i + 1) + " : " + id_and_name + ally[i] + "'s life restored to " + ally[i].get_life());
            }
        }
    }
    
    //return pp_comsume
    public static int get_pp_consume(){
        return pp_consume;
    }
    
}

