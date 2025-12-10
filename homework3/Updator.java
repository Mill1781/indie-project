import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Updator {



    //attribute

    private ArrayList<String> strings = new ArrayList<String>();
    private String[] token = new String[6];
    private int[] score = new int[5]; 

    //attribute


    //public method

    //update Score.txt after add or delete command
    public void update(PrintWriter writer, Data list){
        Student student;
        for(int i = 0; i < list.getStudents().size(); i++){
            student = list.getStudents().get(i);
            writer.print(student.get_Name() + " " + student.get_Homework1() + " " + student.get_Homework2() + " " + student.get_Homework3() + " " + student.get_final_1() + " " + student.get_final_2() + "\n");
        }
        writer.flush();
        writer.close();
    }

    //upload the default information from the Score.txt
    public void boot(Scanner inputer, Data list){
        while(inputer.hasNextLine()){
            strings.add(inputer.nextLine());
        }
            for(int i = 0; i < strings.size(); i++){
                token = strings.get(i).split(" ");
                for(int j = 1; j < token.length; j++){
                    score[j-1] = Integer.parseInt(token[j]);
                }
                list.addstudent_from_list(token[0], new Student(token[0], score[0], score[1], score[2], score[3], score[4]));
            }
        
        System.out.println("\n\n");
    }
 
    //public method


}
