//subclass of Card.java
public class businessman extends Card{
	
	private static int pp_consume = 3;//belong to businessman
	
	//without saveing, default constructor
    public businessman(){
        super (50, 50, 50, 1, 20);
    }
	
	//withsaveing
	public businessman(int LIFE, int ATK, int DEFENSE){
		super (50, LIFE, ATK, 1, DEFENSE);
		
	}

	//return class name "assassin"
	public String toString(){
		return "businessman";
	}

	//activate businessman's skill
    public void skill(Card ally[], Card enermy[], Player commander, Player opponent, int unit_position){
		if(enermy[unit_position - 1] != null){
			System.out.println("\nP" + commander.get_id() + "(" + commander.get_name() +")'s " + toString() + " in Area " + unit_position + " activate its skill");
			System.out.println("P" + commander.get_id() + "(" + commander.get_name() +")'s " + toString()+ " in Area " + unit_position + " is excluded");
			System.out.println("P" + opponent.get_id() + "(" + opponent.get_name() +")'s " + enermy[unit_position - 1].toString()+ " in Area " + unit_position + " is transfered to P" + commander.get_id() + "'s Area " + unit_position);
			ally[unit_position - 1] = enermy[unit_position - 1];
			enermy[unit_position - 1] = null;
			
		}
    }

	//return pp_comsume
    public static int get_pp_consume(){
        return pp_consume;
    }

	
	
}

