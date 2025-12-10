//This file mainly include information about user and order history
public class user {
    //attribute ==> data for the user
    public int size = 999;
    public String movie_id[] = new String[size];//movie's notes
    public String user_id[] = new String[size];//name of the user
    public int ticket_number[] = new int[size];//how many ticket(s) ordered
    public boolean delete[] = new boolean[size];//record which order is canceled
    private int order_number = 0; 
    private boolean found;


    //Method ==> welcoming
    public static void welcome(){
        System.out.println("\nWelcome to NCKU movie theater, you can order tickets and inquire movie information with this system.");
        System.out.println("Please read the following command to operate our service.");
        System.out.println("Caution: This system can only upload 999 pieces of data, please use the clear function to reset the order.");
        System.out.println("--------------------------------------------------------\n");
        System.out.println("add [movie's number] [number of tickets] [user id]\nExplain: you can apply this command to order tickets\n\n");
        System.out.println("show movie\nExplain: to show the available movies\n\n");
        System.out.println("show movie [movie type]\nExplain: search movies with your desired type\n\n");
        System.out.println("show movie [length of movie]\nExplain: search movies with the time shorter than [length of movie]\n\n");
        System.out.println("show order [user id]\nExplain: after ordering, you can cheak history order by this command\n\n");
        System.out.println("cancel order [user id][order id]\nExplain: you can cancel your previous order by this command\n\n");
        System.out.println("help\nExplain: show the commands again\n\n");
        System.out.println("clear\nExplain: clear all order.\nCaution: this command won't conserve any previous order\n\n");
        System.out.println("exit\nExplain: end your using of this service\n\n");
        System.out.println("--------------------------------------------------------\n");
    }

    //Method ==> call out method
    public static void help(){
        System.out.println("--------------------------------------------------------\n");
        System.out.println("add [movie's number] [number of tickets] [user id]\nExplain: you can apply this command to order tickets\n\n");
        System.out.println("show movie\nExplain: to show the available movies\n\n");
        System.out.println("show movie [movie type]\nExplain: search movies with your desired type\n\n");
        System.out.println("show movie [length of movie]\nExplain: search movies with the time shorter than [length of movie]\n\n");
        System.out.println("show order [user id]\nExplain: after ordering, you can cheak history order by this command\n\n");
        System.out.println("cancel order [user id][order id]\nExplain: you can cancel your previous order by this command\n\n");
        System.out.println("clear\nExplain: clear all order.\nCaution: this command won't conserve any previous order\n\n");
        System.out.println("exit\nExplain: end your using of this service\n\n");
        System.out.println("--------------------------------------------------------\n");
    }

    //Method ==> add function
    public void add(String Movie_id, int Ticket_number, String id){
        if(Movie_id.charAt(0) < 65 || Movie_id.charAt(0) > 78){
            System.out.println("fail.\nmovie doesn't exist, please insert correct movie number");
            return;
        }
        movie_id[order_number] = Movie_id;
        ticket_number[order_number] = Ticket_number;
        user_id[order_number] = id;
        System.out.printf("order completed, your order id is %d\n", order_number+1);


        order_number++;
    }
    
    //Method ==> show order history
    public void show_order_for_user(String id){
        found = false;

        
        for(int i = 0; i < order_number; i++){
            if(id.equals(user_id[i]) && !delete[i]){
                if(!found){
                    found = true;
                }
                System.out.printf("%d, %s, %d, %s\n", i+1, movie_id[i], ticket_number[i], user_id[i]);
            }
        }

        if(!found){
            System.out.println("this user dosen't make any order.");
        }


    }

    //Method ==> cancel previous order
    public void cancel_order(String id, int order_id){
        order_id--;//order_number
        if(delete[order_id]){
            System.out.println("fail.\norder has been canceled.");
            return;
        }
        if(order_id > (order_number-1)){
            System.out.println("fail.\nthis order doesn't exist, please try again.");
            return;
        }
        if(!user_id[order_id].equals(id)){
            System.out.println("fail.\nthis user doesn't match, please try again.");
            return;
        }

        delete[order_id] = true;
        System.out.println("cancel complete");
    }

    //Method ==> clear all order
    public void clear(){
        movie_id = new String[size];
        user_id = new String[size];
        ticket_number = new int[size];
        delete = new boolean[size];
        order_number = 0;
        System.out.println("\nclear complete\n");
    }
}
