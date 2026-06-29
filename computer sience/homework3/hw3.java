
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.*;

public class hw3 { 
    public static void main(String args[]){

        Scanner sca = new Scanner(System.in);
        Data list = new Data();
        String[] token = new String[6];
        int[] score = new int[5];
        String keyword;
        boolean loop = true;
        String input;
        int indexOfspace;
        int spacecount = 0;
        int commacount = 0;
        Updator updator = new Updator();
        String student_name;
        String homework_number;
        String project_number;


        //--------------------------------------------------------------------------------------------------------------------------------------


        Text.welcome_and_explain(); //print out the command list


        try{
            Scanner origin_data = new Scanner(new FileInputStream(args[0]));
            System.out.printf("%s upload success\n", args[0]);
            updator.boot(origin_data, list);
        } catch(FileNotFoundException e){
            System.out.println("Score.txt didn't be found. please make sure to establish Score.txt before executing this program\n");
            loop = false;
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Score.txt didn't be found. please check the typing or the existance of Score.txt\n");
            loop = false;
        }
        //checking the file is existing or not. If not, close the program


        //--------------------------------------------------------------------------------------------------------------------------------------


        while (loop) {


            spacecount = 0;
            commacount = 0;
            System.out.print("Please enter your command: ");
            input = sca.nextLine().trim();
            indexOfspace = input.indexOf(' ');
              
            if(indexOfspace != -1){//.indexOf() returns -1 when space didn't be found
                keyword = input.substring(0, indexOfspace).trim();
            }
            
            else{
                keyword = input;
            }

            for(int i = 0; i < input.length(); i++){

                if(input.charAt(i) == ' '){
                    spacecount++;
                }
                
                if(input.charAt(i) == ','){
                    commacount++;
                }

            }//using the number of space and comma to determine the correction of the command


            //--------------------------------------------------------------------------------------------------------------------------------------

            
            if(keyword.equals("add")){//when first word is add

                if(spacecount == 2 && commacount == 4){//check the correction

                    token = new String[6];
                    input = input.substring(indexOfspace + 1).trim();//drop the "add" part and begin from [name]
                    indexOfspace = input.indexOf(' ');
                    token[0] = input.substring(0 ,indexOfspace);//store [name] to token[0]
                    input = input.substring(indexOfspace + 1).trim();//drop the [name] part and begin from first score

                    Scanner purse = new Scanner(input);
                    purse.useDelimiter(",");//using method .useDelimiter to split the score and store them in token String array

                    try{
                        for(int i = 1; i < 6; i++){
                            token[i] = purse.next();
                        }
                        
                        for(int i = 1; i < 6; i++){    
                            score[i-1] = Integer.parseInt(token[i]);//transform String in token to int and put them in score int array 
                        }

                        if(Student.score_boundary(score) && Student.Name_isuppercase(token[0])){//check the correction of every score
                            
                            list.addstudent(token[0], new Student(token[0], score[0], score[1], score[2], score[3], score[4]));//push the data to listarray in object list
                            
                            try{
                                PrintWriter link = new PrintWriter(new FileOutputStream("Score.txt", false));
                                updator.update(link, list);
                            } catch(FileNotFoundException e){
                                System.out.println("\nunexpected error, file is missing\n");
                            }//update Score.txt
                            
                        }
                        if(!Student.score_boundary(score)){
                            System.out.println("\nadding Failed, please notice boundary of score is from 0 to 100\n");
                        }
                        if(!Student.Name_isuppercase(token[0])){
                            System.out.println("\nadding Failed, please notice first character of the student's name should be uppercase\n");
                        }


                    }
                    catch(NoSuchElementException e){
                        System.out.println("\nadding Failed, please insert five sorts of score at once\n");
                    }//for situation that the number of data is wrong
                    catch(NumberFormatException e){
                        System.out.println("\nadding Failed, please do not key in char in score parts\n");
                    }//for situation that using enter string in score part
                    
                    purse.close();

                }

                else{
                    System.out.println("\nadding Failed, please be careful with the command of space and comma\n");
                }
            }
            
            else if(keyword.equals("delete")){//when first word is delete  

                if(spacecount == 1){
                    token = input.split(" ");//split the string with " " and store them in token
                    list.delete(token[1]);

                    try{
                        PrintWriter link = new PrintWriter(new FileOutputStream("Score.txt", false));
                        updator.update(link, list);
                    } catch(FileNotFoundException e){
                        System.out.println("IO exception");
                    }//update Score.txt


                }

                else{
                    System.out.println("\ndelete Failed, please be careful with the space and avoid unecessary commands");
                }
                
            }

            else if(keyword.equals("show")){//when first word is show
                
                try{

                    token = input.split("\\s+");//split the string with " " and store every part of them in token String array. For("\s+"), \s means whitespace character, + means one or more times
                    String command = input.substring(0, input.lastIndexOf(" ")).trim();//the part before variables

                    if(spacecount == 3 && command.equals("show individual score")){//command ==> show individual score 
                        student_name = token[3];
                        System.out.println("\nshow individual score command success\n");
                        list.show_individual_score(student_name);
                        
                    }

                    else if(spacecount == 3 && command.equals("show individual average")){//command ==> show individual average
                        student_name = token[3];
                        System.out.println("\nshow individual average command success\n");
                        list.show_individual_average(student_name);
                    }
                    
                    else if(spacecount == 2 && token[1].substring(0, 8).equals("Homework") && token[2].equals("average")){//command ==> show specific Homework average
                        try{    
                            homework_number = token[1].substring(8);//catch the number part
                            System.out.println("\nshow Homework average command success\n");
                            list.show_homework_x_average(Integer.parseInt(homework_number));//transfer the number into int and call Data's method
                        } //
                        catch(NoSuchElementException e){
                            System.out.println("\nshow Homework average Failed, please check whether you type in the number of Homework\n");
                        } //for situation that the number of data is wrong
                        catch(NumberFormatException e){
                            System.out.println("\nshow Homework average Failed, please do not enter char after Homework directly\n");
                        }//for situation that using enter string in score part
                        
                    }
                                        
                    else if(spacecount == 3 && token[1].equals("Final") && token[2].substring(0, 7).equals("Project") && token[3].equals("average")){//command ==> show specific Final project average
                        try{
                            project_number = token[2].substring(7);//catch the number part, and since Homework is 8 characters long, so index 8 would begin in number part. If not, exception occurs
                            System.out.println("\nshow Final Project average command success\n");
                            list.show_final_project_x_average(Integer.parseInt(project_number));//transfer the number into int and call Data's method
                        } 
                        catch(NoSuchElementException e){
                            System.out.println("\nshow Final Project average Failed, please check whether you type in the number of Final Project\n");
                        } //for situation that the number of data is wrong
                        catch(NumberFormatException e){
                            System.out.println("\nshow Final Project Failed, please do not enter char after Project directly\n");
                        }//for situation that using enter string in score part
                    }
                    
                    else if(input.equals("show total")){//command ==> show total
                        System.out.println("\nshow total command success\n");
                        list.show_total();
                    }
                    
                    else{//when other words aren't recognized as one of the command
                        System.err.println("\ncommand Failed, please check whether there are any wrong spelling, case, boundary of number, or unecessary command issues\n");
                    }
                
                } 
                catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("show Failed, please check the formet of the command again");
                }
                catch(StringIndexOutOfBoundsException e){
                    System.out.println("show Failed, please check the formet of the command again, and whether some chars are missing");
                }

            }
            
            else if(keyword.equals("exit")){//when first word is exit
                loop = false;//leave the loop ==> while(loop)
                System.out.println("\nexit ommand success\n");
                System.out.println("Thank you for using, have a nice day\n");
            }

            else if(keyword.equals("help")){//when first word is help
                System.out.println("\nhelp command success\n");
                Text.help();
            }

            else if(keyword.equals("clear")){
                System.out.println("\nclear command success\n");
                list.all_clear();
                try{
                    PrintWriter link = new PrintWriter(new FileOutputStream("Score.txt", false));
                    updator.update(link, list);
                } catch(FileNotFoundException e){
                    System.out.println("IO exception");
                }//update Score.txt

            }

            else if(keyword.equals("sorting")){
                System.out.println("\nsorting command success\n");
                list.sort_by_average();
                try{
                    PrintWriter link = new PrintWriter(new FileOutputStream("Score.txt", false));
                    updator.update(link, list);
                } catch(FileNotFoundException e){
                    System.out.println("IO exception");
                }//update Score.txt
            }

            else{//when first word isn't recognized as one of the command
                System.err.println("\ncommand Failed, please check whether there are any wrong spelling, case or unecessary command\n");
            }
        
        
        }//while(loop)

        sca.close();

    }//main method
    

}




