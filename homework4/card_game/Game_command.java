package card_game;
import card_class.Card;

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
        else{
            System.out.println("command failed\n");
        }
        //retrun 0 --> wrong type
        //return 1 --> execute command 1
        //return 2 --> execute command 2
        //return 3 --> execute command 3

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
        if(space_count != 2){
            return 0;
        }

        //second check
        String token[] = new String[3];
        int position;
        token = command.split(" ");
        if(token[0].equals("put")){
            if(token[1].equals("priest") || token[1].equals("tank") || token[1].equals("shooter") || token[1].equals("assassin")){
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
        //hand_area[0]-->priest, [1]-->tank, [2]-->shooter, [3]-->assassin
        Card.summon(card_class, position, player, Arena);
    }
    
    //show game status function
    public static void show_game_status(Player P1, Player P2, Fightground arena){
        System.out.println("\ngaming information:\n");
        System.out.println("Turn : " + arena.get_Phase() + "\n");
        System.out.println(P1);
        System.out.print(arena.ground_status(P1.ID));
        System.out.println("\n\n");
        System.out.println(P2);
        System.out.print(arena.ground_status(P2.ID));
        System.out.println();
    }

    //end layout stage function
    public static void end_layout_stage(int turn[]){
        turn[0]++;
        System.out.println("end layout stage command successed\n\n");
    }


}
