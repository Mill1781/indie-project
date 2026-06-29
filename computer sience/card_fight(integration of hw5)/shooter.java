//subclass of Card.java
public class shooter extends Card {

    private static int pp_consume = 3;//belong to shooter

    //without saveing, default constructor
    public shooter(){
        super(150, 150, 100, 4, 20);
    }

    //withsaveing
    public shooter(int LIFE, int ATK, int DEFENSE){
        super(150, LIFE, ATK, 4, DEFENSE);
    }

    //return class name "shooter"
    public String toString(){
        return "shooter";
    }

    //activate shooter's skill
    public void skill(Card ally[], Card enermy[], Player commander, Player opponent, int unit_position){
        String id_and_name;
        if(commander.get_id() == 1){
            id_and_name = "P2(" + opponent.get_name() + ")'s ";
        }
        else{//id = 2
            id_and_name = "P1(" + opponent.get_name() + ")'s ";
        }
        System.out.println("\nP" + commander.get_id() + "(" + commander.get_name() +")'s " + toString() + " in Area " + unit_position  + " activate its skill");

        for(int i = 0; i < 3; i++){
            if(enermy[i] == null){
                continue;
            }
            enermy[i].assign_life(enermy[i].get_life() - 10);
            if(enermy[i].get_life() <= 0){
                enermy[i].assign_life(0);
            }
            System.out.println("area "+ (i + 1) + " : " + id_and_name + enermy[i] + " has "+ enermy[i].get_life() +" life points left");
        }
    }

    //return pp_comsume
    public static int get_pp_consume(){
        return pp_consume;
    }
    
}
