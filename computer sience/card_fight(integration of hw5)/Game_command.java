import java.util.*;

public class Game_command {

    //print out welcomeing messages and command explainations
    public static void welcome_and_explain(){
        System.out.println("\n\nWelcome to card fight");
        System.out.println("Caution 1: make sure the name of .txt file \"player_info.txt and playground.txt.txt\" is correct, wrong name would result in severe errors");
        System.out.println("Caution 2: user better not to manually change the data in saving data, and please be careful whether the save pattern of the saving data is correct");
        System.out.println("Caution 3: if you do have any rule problems please refer readme.txt");
        System.out.println("Please read the following commands and start your fighting.");
        System.out.println("--------------------------------------------------------\n");
        System.out.println("put [class name] [fightground_position]\nExplain: assign a unit to spesific position\nEx. put shooter 1\n\n");
        System.out.println("show game status\nExplain: show current information for both players and the fightground\nEx. show game status\n\n");
        System.out.println("end layout stage\nExplain: end your turn and move to next progress\nEx. end layout stage\n\n");
        System.out.println("--------------------------------------------------------\n");
    }

    //receive command from main and analyze which command it is by command_analysis, then execute command
    public static void command_execute(String command, Player P1, Player P2, Player commander, Fightground Arena, int turn[]){
        int command_type = command_analysis(command);
        int position;
        String card_class;
        
        if(command_type == 1){
            String token[] = new String[3];
            token = command.split(" ");
            card_class = token[1];
            position = Integer.parseInt(token[2]);

            put(card_class, position, commander, Arena);
        
        }
        else if(command_type == 2){
            show_game_status(P1, P2, Arena);
        }
        else if(command_type == 3){
            end_layout_stage(turn);
        }
        else if(command_type == 4){
            String token[] = new String[3];
            token = command.split(" ");
            position = Integer.parseInt(token[2]);
            Equipment equipment = find_equipment(token[1], commander);
            put_equipment(equipment, position, commander, Arena);

        }
        else if(command_type == 5){
            String token[] = new String[4];
            token = command.split(" ");
            position = Integer.parseInt(token[3]);
            Potion potion = find_potion(token[1], commander);
            put_potion(potion, position, commander, Arena);
        }
        else{
            System.out.println("command failed\n");
        }
        //retrun 0 --> wrong type
        //return 1 --> execute command 1, put card
        //return 2 --> execute command 2, show status
        //return 3 --> execute command 3, end layout stage
        //return 4 --> execute command 4, put equipment
        //return 5 --> execute command 5, put potion

    }

    //analysis the user's input into specific command
    public static int command_analysis(String command){
        //first check
        int space_count = 0;
        for(int i = 0; i < command.length(); i++){
            if(command.charAt(i) == ' '){
                space_count++;
            }
        }
        if(space_count < 2 || space_count > 3){
            return 0;
        }

        //second check
        String token[] = new String[4];
        int position;
        token = command.split(" ");
        if(token[0].equals("put")){
            if(is_card(token[1]) && token.length == 3){
                try{
                    position = Integer.parseInt(token[2]);
                    if(position < 4 && position > 0){
                        return 1;
                    }
                    else{
                        System.out.println("your position is beyond boundary\n");
                        return 0;
                    }
                } catch(Exception e){
                    System.out.println("please make sure your position is pure integer\n");
                    return 0;
                }
            }
            else if(is_equipment(token[1]) && token.length == 3){
                try{
                    position = Integer.parseInt(token[2]);
                    if(position < 4 && position > 0){
                        return 4;
                    }
                    else{
                        System.out.println("your position is beyond boundary\n");
                        return 0;
                    }
                }catch(Exception e){


                }

            }
            else if(is_potion(token[1])&& token.length == 4){
                try{
                    position = Integer.parseInt(token[3]);
                    if(position < 4 && position > 0){
                        return 5;
                    }
                    else{
                        System.out.println("your position is beyond boundary\n");
                        return 0;
                    }
                }catch(Exception e){

                    
                }
            }
        }
        else if(command.equals("show game status")){
            return 2;
        }
        else if(command.equals("end layout stage")){
            return 3;
        }
        else{
            return 0;
        }
        return 0;
    }

    //put function
    public static void put(String card_class, int position, Player player, Fightground Arena){
        Card.summon(card_class, position, player, Arena);
    }

    public static void put_equipment(Equipment equipment, int position, Player commander, Fightground Arena){
        Card[] fight_area;
        int id = commander.get_id();
        if(id == 1){
            fight_area = Arena.get_P1_fight_area();
        }
        else{
            fight_area = Arena.get_P2_fight_area();
        }

        if(fight_area[position - 1] == null){
            System.out.println("command invalids");
        }
        else if(fight_area[position - 1].get_equipment() != null){
            System.out.println("this unit has equipped");
        }
        else{
            fight_area[position - 1].put_on_equipment(equipment);
            commander.get_hand_area_equipments().remove(equipment);
            System.out.println("command success, equipment is put on");
        }        
    }

    public static void put_potion(Potion potion, int position, Player commander, Fightground Arena){
        Card[] fight_area;
        int id = commander.get_id();
        if(id == 1){
            fight_area = Arena.get_P1_fight_area();
        }
        else{
            fight_area = Arena.get_P2_fight_area();
        }

        if(fight_area[position - 1] == null){
            System.out.println("command invalids");
        }
        else if(fight_area[position - 1].get_potion() != null){
            System.out.println("this unit has consumed potion");
        }
        else{
            fight_area[position - 1].comsume_potion(potion);
            commander.get_hand_area_potions().remove(potion);
            System.out.println("command success, potion is consumed");
        }        
    }

    
    //show game status function
    public static void show_game_status(Player P1, Player P2, Fightground arena){
        System.out.println("\ngaming information:\n");
        System.out.println("Turn : " + arena.get_Phase() + "\n");
        System.out.println(P1);
        System.out.print(arena.ground_status(P1.get_id()));
        System.out.println("\n\n");
        System.out.println(P2);
        System.out.print(arena.ground_status(P2.get_id()));
        System.out.println();
    }

    //end layout stage function
    public static void end_layout_stage(int turn[]){
        turn[0]++;
        System.out.println("end layout stage command successed\n\n");
    }


    public static boolean is_card(String token){
        String[] name_pool = Card.get_pool();
        
        for(int i = 0; i < name_pool.length; i++){
            if(token.equals(name_pool[i])){
                return true;
            }
        }
        return false;
    }

    public static boolean is_equipment(String token){
        String[] name_pool = Equipment.get_pool();
        for(int i = 0; i < name_pool.length; i++){
            if(token.equals(name_pool[i])){
                return true;
            }
        }
        return false;
    }

    public static Equipment find_equipment(String token, Player commander){
        ArrayList<Equipment> equipment_pool = commander.get_hand_area_equipments();
        Equipment temp;
        for(int i = 0; i < equipment_pool.size(); i++){
            if(token.equals(equipment_pool.get(i).toString())){
                temp = equipment_pool.get(i);
                return temp;
            }
        }
        return null;
    } 

    public static boolean is_potion(String token){
        String[] name_pool = Potion.get_pool();
        for(int i = 0; i < name_pool.length; i++){
            if(token.equals(name_pool[i])){
                return true;
            }
        }
        return false;
    }

    public static Potion find_potion(String token, Player commander){
        ArrayList<Potion> potion_pool = commander.get_hand_area_potions();
        Potion temp;
        for(int i = 0; i < potion_pool.size(); i++){
            if(token.equals(potion_pool.get(i).toString())){
                temp = potion_pool.get(i);
                return temp;
            }
        }
        return null;
    } 

}
