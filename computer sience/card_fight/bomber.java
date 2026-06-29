public class bomber extends Card {

    private static int pp_consume = 1;//belong to businessman
	
	//without saveing, default constructor
    public bomber(){
        super (999, 999, 1, 6, 20);
    }
	
	//withsaveing
	public bomber(int LIFE, int ATK, int DEFENSE){
		super (999, LIFE, ATK, 6, DEFENSE);
		
	}

    //return class name "assassin"
	public String toString(){
		return "bomber";
	}

    //activate bomber's skill
    public void skill(Card ally[], Card enermy[], Player commander, Player opponent, int unit_position){
        String nameOfCommander;
        String nameOfOpponent;
        if(commander.get_id() == 1){
            nameOfOpponent = "P2(" + opponent.get_name() + ")'s ";
            nameOfCommander = "P1(" + commander.get_name() + ")'s ";
        }
        else{//id = 2
            nameOfCommander = "P2(" + commander.get_name() + ")'s ";
            nameOfOpponent = "P1(" + opponent.get_name() + ")'s ";
        }
		if(enermy[unit_position - 1] != null){
			System.out.println("\nP" + commander.get_id() + "(" + commander.get_name() +")'s " + toString() + " in Area " + unit_position  + " activate its skill");
			enermy[unit_position - 1].assign_life(0);
            System.out.println("area "+ unit_position + " : " + nameOfOpponent + enermy[unit_position - 1] + " has "+ enermy[unit_position - 1].get_life() +" life points left");
            ally[unit_position - 1].assign_life(0);
            System.out.println("area "+ unit_position + " : " + nameOfCommander + ally[unit_position - 1] + " has "+ ally[unit_position - 1].get_life() +" life points left");
		}
    }

    //return pp_comsume
    public static int get_pp_consume(){
        return pp_consume;
    }

}
