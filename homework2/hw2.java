import java.util.Scanner;
public class hw2 {
    public static void main(String args[]){
                
        //declare needed data, and execute welcome function
        String movie_signal;//movie's notes
        String user_id;//user's name
        String input;
        int count;//number(s) for space
        int order_history;
        int ticket_number;
        boolean condition = true;//whether the program end
        user guest = new user();
        Scanner sca = new Scanner(System.in);
        user.welcome();


        
        while (condition) {

            //data collect
            System.out.print("\nPlease enter your command:");
            input = sca.nextLine().trim();
            System.out.println("\ncommand recieved\n");
            count = 0;
            for(int i = 0 ; i < input.length(); i++){
                if(input.charAt(i) == ' ')
                    count++;
            }
            String[] token = new String[99];
            for(int i = 0; i < count+1; i++){
                if (i < count){
                    token[i] = input.substring(0,input.indexOf(' '));
                    token[i] = token[i].trim();
                    input = input.substring(input.indexOf(' ')+1);
                    input.trim();
                }
                else{
                    token[i] = input;
                }
            }


            //exemine input corrction
            boolean out = false;
            if(count > 4 || count < 0){
                System.out.println("Wrong format, please don't write redundant word(s), thank you.\n");
                out = true;
            }
            if(out){
                continue;
            }


            //command execution and correction about input
            if(count == 0 && token[0].equals("exit")){//scan count first, that can somehow prevent crash since the program scan the condition in order from left to right
                System.out.println("Thanks for using our system.\nNCKU theater is always welcoming, have a nice day:)\n");
                break;
            }

            else if(count == 0 && token[0].equals("help")){
                user.help();
            }

            else if(count == 0 && token[0].equals("clear")){
                guest.clear();
            }

            else if(count == 3 && token[0].equals("add") && token[2].matches("\\d+")){  //p.s. token[0] == "add" refers to memory address
                movie_signal = token[1];
                ticket_number = Integer.parseInt(token[2]);
                //my original way, let number = 0 at the beginning
                /*for(int i = 0; i < token[2].length(); i++){
                    number += (token[2].charAt(token[2].length()-i-1)-48)*Math.pow(10, i);
                }*/
                user_id = token[3];
                guest.add(movie_signal, ticket_number, user_id); 
            }

            else if(token[0].equals("show")){
                if(count == 1 && token[1].equals("movie")){
                    movie.show_movie();
                }//show the movie
                else if(count == 2 && token[1].equals("movie") && token[2].matches("\\d+")){
                    double time = Double.parseDouble(token[2]);
                    movie.movie_time_search(time);

                }//for showing by type
                else if(count == 2 && token[1].equals("movie") && token[2].matches("\\D+")){
                    movie.movie_type_search(token[2]);
                
                }//for showing by time
                else if(count == 2 && token[1].equals("order")){
                    guest.show_order_for_user(token[2]);
                }

            }

            else if(count == 3 && token[0].equals("cancel") && (token[1].equals("order")) && (token[3].matches("\\d+"))){
                    order_history = Integer.parseInt(token[3]);
                    guest.cancel_order(token[2], order_history);
            }//cancel order

            else{
                System.out.println("Wrong format, please check the order of words or the typing correction.\n");
                continue;
            }//No command exist, typing error

        }//the loop won't end util user key in exit
        sca.close();
    }
}

//for writer heself to read:
//some convinient method to keep down:
//1. String.match(regex), refering the API to see regular expression. return boolean
//2. Double.parseDouble(String). return double
//3. Integer.parseInt(String). return int
//4. String.equal(String). return boolean
