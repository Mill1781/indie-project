

public class Fightground {
    private int Phase;
    private Card P1_fight_area[] = new Card[3];
    private Card P2_fight_area[] = new Card[3];

    //return fightground basic info
    public String ground_status(int Player_ID){
        String equipment;
        Card temp[] = new Card[3];
        if(Player_ID == 1){
            temp = P1_fight_area;
        }
        else if(Player_ID == 2){
            temp = P2_fight_area;
        }
        String ground_status = "card placement area : \n";
        for(int i = 0; i < 3; i++){
            if(temp[i] == null){
                ground_status += "Area " + String.valueOf(i+1) + " " + "none\n";
            }
            else{
                if(temp[i].get_equipment() == null){
                    equipment = "none";
                }
                else{
                    equipment = temp[i].get_equipment().toString();
                }
                ground_status += "Area " + String.valueOf(i+1) + " " + temp[i] + " equipment: " + equipment + ", life: " + temp[i].get_life() + ", atk : " + temp[i].get_atk() + ", defense : " + temp[i].get_defense() + "\n";
            }
        }
        return ground_status;
    }

    //return P1's fightground info
    public Card[] get_P1_fight_area(){
        return P1_fight_area;
    }

    //return P2's fightground info
    public Card[] get_P2_fight_area(){
        return P2_fight_area;
    }

    //clear up unit which is dead
    public void stage_update(){
        for(int i = 0; i < 3; i++){
            if(P1_fight_area[i] != null){
                if(P1_fight_area[i].get_life() <= 0){
                    System.out.println(P1_fight_area[i] + " is dead");
                    P1_fight_area[i] = null;
                }
            }
        }
        for(int i = 0; i < 3; i++){
            if(P2_fight_area[i] != null){
                if(P2_fight_area[i].get_life() <= 0){
                    System.out.println(P2_fight_area[i] + " is dead");
                    P2_fight_area[i] = null;
                }
            }
        }
    }

    //assign new Phase
    public void assign_Phase(int phase){
        Phase = phase;
    }

    //return current Phase
    public int get_Phase(){
        return Phase;
    }

}
