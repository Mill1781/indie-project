

public class Student {
    
    private String Name;
    private int Homework1;
    private int Homework2;
    private int Homework3;
    private int Finalproject1;
    private int Finalproject2;
    private double average;


    //public method

    //Constructor ==> create a student
    public Student(String name, int Homework1, int Homework2, int Homework3, int Finalproject1, int Finalproject2){
        Name = name;
        this.Homework1 = Homework1;
        this.Homework2 = Homework2;
        this.Homework3 = Homework3;
        this.Finalproject1 = Finalproject1;
        this.Finalproject2 = Finalproject2;
        average = (Homework1 + Homework2 + Homework3 + Finalproject1 + Finalproject2) / 5.0;
    }

    //Testing the boundary of score is whether between 0 to 100
    public static boolean score_boundary(int[] score){
        for(int i = 0; i < 5; i++){
            if(score[i] < 0 || score[i] >100){
                return false;
            }       
        }
        return true;
    }    

    //Testing the first char of the name is whether uppercase or not
    public static boolean Name_isuppercase(String name){
        char first_char = name.charAt(0);
        if(first_char >= 65 && first_char <= 90){
            return true;
        }
        return false;
    }

    //return the name of the student when object's name is called
    public String toString(){
        return Name;
    }
    
    //return the name of the student
    public String get_Name(){
        return Name;
    }

    //return the Score of Homewrok1
    public int get_Homework1(){
        return Homework1;
    }

    //return the Score of Homewrok2
    public int get_Homework2(){
        return Homework2;
    }

    //return the Score of Homewrok3
    public int get_Homework3(){
        return Homework3;
    }

    //return the Score of Final Project1
    public int get_final_1(){
        return Finalproject1;
    }
    
    //return the Score of Final Project2
    public int get_final_2(){
        return Finalproject2;
    }

    //return the average of the Score
    public double get_average(){
        return average;
    }

    //public method

}
