

import java.util.ArrayList;
import java.util.Arrays;

public class Player {
    private int ID;//P1 --> 1, P2 --> 2
    private int life;
    private String player_name;
    private int pp;//energy
    private int hand_area[] = new int[6];//[0]-->priest, [1]-->tank, [2]-->shooter, [3]-->assassin, [4]-->businessman, [5]-->bomber
    private ArrayList<Equipment> hand_area_equipments = new ArrayList<Equipment>();//Player's equipments in hand (objects)
    private ArrayList<Potion> hand_area_potions = new ArrayList<Potion>();//Player's potions in hand (objects)
    

    //to note player's id
    public Player(int id){
        ID = id;
        
    }

    //return basic info
    public String toString(){
        String player_status;
        int temp[] = new int[6];
        temp = Arrays.copyOf(hand_area, hand_area.length);
        ArrayList<String> class_name = new ArrayList<String>();

        for(int i = 0; i < 6; i++){
            if(i == 0 && temp[i] > 0){
                class_name.add("priest");
                temp[i]--;
                i--;
            }
            else if(i == 1 && temp[i] > 0){
                class_name.add("tank");
                temp[i]--;
                i--;
            }
            else if(i == 2 && temp[i] > 0){
                class_name.add("shooter");
                temp[i]--;
                i--;
            }
            else if(i == 3 && temp[i] > 0){
                class_name.add("assassin");
                temp[i]--;
                i--;
            }
            else if(i == 4 && temp[i] > 0){
                class_name.add("businessman");
                temp[i]--;
                i--;
            }
            else if(i == 5 && temp[i] > 0){
                class_name.add("bomber");
                temp[i]--;
                i--;
            }
        }
        player_status = "Player" + ID + " : " + player_name + "\nlife : " + life + "\nenergy : " + pp + "\nhand area of Card : \n";
        for(int i = 0; i < class_name.size(); i++){
            if(i < class_name.size() - 1){
                player_status = player_status + class_name.get(i) + ", ";
            }
            else{
                player_status = player_status + class_name.get(i) + "\n";
            }
        }
        if(class_name.size() == 0){
            player_status = player_status + "none\n";
        }

        player_status += "hand area of Equipment : \n"; 
        for(int i = 0; i < hand_area_equipments.size(); i++){
            if(i < hand_area_equipments.size() -1 ){
                player_status = player_status + hand_area_equipments.get(i) + ", ";
            }    
            else{
                player_status = player_status + hand_area_equipments.get(i) + "\n";
            }
        }

        player_status += "hand area of Potion : \n"; 
        for(int i = 0; i < hand_area_potions.size(); i++){
            if(i < hand_area_potions.size() -1 ){
                player_status = player_status + hand_area_potions.get(i).tostring() + ", ";
            }    
            else{
                player_status = player_status + hand_area_potions.get(i).tostring() + "\n";
            }
        }
        
        return player_status;
    }

    //assign player's life
    public void assign_life(int new_life){
        life = new_life;
    }

    //return player's life
    public void assign_pp(int current_pp){
        pp = current_pp;
    }

    //return player's name
    public void assign_player_name(String name){
        player_name = name;
    }

    //return player's name
    public String get_name(){
        return player_name;
    }

    //return player's life
    public int get_life(){
        return life;
    }

    // return player's hand area
    public int[] get_hand_area(){
        return hand_area;
    }

    // return player's hand area of equipment
    public ArrayList<Equipment> get_hand_area_equipments(){
        return hand_area_equipments;
    }

    // return player's hand area of potions
    public ArrayList<Potion> get_hand_area_potions(){
        return hand_area_potions;
    }

    //return player's id
    public int get_id(){
        return ID;
    }

    //return player's energy
    public int get_pp(){
        return pp;
    }

    //return boolean to determine match's result
    public boolean is_alive(){
        if(life > 0){
            return true;
        } 
        else{
            return false;
        }
    }

}
