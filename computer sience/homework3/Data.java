
import java.util.ArrayList;

public class Data {


    
    //attribute
    private ArrayList<Student> students = new ArrayList<Student>();//the main data to store student's information
    private double final_project_average = 0;//initial average
    private double homework_average = 0;//initial average
    private double individual_average = 0;//initial average
    private double sum = 0;//initial sum
    private int position;// the specific posion of one's student
    //attribute




    //public method

    //return the list of student(the main data)
    public ArrayList<Student> getStudents(){
        return students;
    }
    
    //adding student's information from Score.txt
    public void addstudent_from_list(String name, Student s){
        
        position = find_student(name);
        
        if(position == -1){    
            students.add(s);
        }
        else{
            System.out.printf("\nCaution!! : Student %s is already existed, this system would automatically delete other repeative data and score\n\n", name);
        }
    }

    //adding student's information from user's command
    public void addstudent(String name, Student s){

        position = find_student(name);
        
        if(position == -1){    
            students.add(s);
            System.out.println("\nadding success\n");
        }
        else{
            System.out.printf("Student %s is already existed\n\n", name);
        }
    }

    //delete specific student's information
    public void delete(String name){
        if(Studentlist_is_Empty()){
            System.out.println("\ndelete Failed, student list is empty, please add student's data\n");
            return;
        }
        position = find_student(name);
        if(position != -1){
            students.remove(position);
            System.out.println("\ndelete success\n");
        }
        else
            System.out.println("\nStudent " + name + " didn't found\n");
    }

    //show specific student's individual score
    public void show_individual_score(String name){
        if(Studentlist_is_Empty()){
            System.out.println("\nshow individual score Failed, student list is empty, please add student's data\n");
            return;
        }
        position = find_student(name);
        if(position != -1){
            print_colume();
            System.out.printf("          %-12s", students.get(position).toString());
            System.out.printf("||          %-12d", students.get(position).get_Homework1());
            System.out.printf("||          %-12d", students.get(position).get_Homework2());
            System.out.printf("||          %-12d", students.get(position).get_Homework3());
            System.out.printf("||          %-12d", students.get(position).get_final_1());
            System.out.printf("||          %-12d\n\n", students.get(position).get_final_2());
        }
        else{
            System.out.println("\nStudent " + name + " didn't found\n");
        }
    }
    
    //show specific student's average score
    public void show_individual_average(String name){
        if(Studentlist_is_Empty()){
            System.out.println("\nshow individual average Failed, student list is empty, please add student's data\n");
            return;
        }
        position = find_student(name);
        if(position != -1){
            sum = students.get(position).get_Homework1() + students.get(position).get_Homework2() + students.get(position).get_Homework3() + students.get(position).get_final_1() + students.get(position).get_final_2();
            individual_average = sum / 5.0;
            System.out.printf("%s's average score: ", name);
            print_rounded_score(individual_average);
            sum = 0;
        }
        else{
            System.out.print("\nshow individual average Failed, student " + name + " didn't found\n\n");
        }
    }

    //show one kind of the Homework's average score
    public void show_homework_x_average(int number){
        if(Studentlist_is_Empty()){
            System.out.printf("\nshow Homework%d average Failed, student list is empty, please add student's data\n\n", number);
            return;
        }
        switch (number) {
            case 1:
                homework_average = homework1_average();
                break;
            case 2:
                homework_average = homework2_average();
                break;
            case 3:
                homework_average = homework3_average();
                break;
            default:
                System.out.printf("\nshow Homework%d average Failed, number of Homework may be wrong\n\n", number);
                return;
        }
        System.out.printf("Homework%d's average: ", number);
        print_rounded_score(homework_average);
        sum = 0;
    }
    
    //show one kind of the Final Project's average score
    public void show_final_project_x_average(int number){
        if(Studentlist_is_Empty()){
            System.out.printf("\nshow Final Project%d average Failed, student list is empty, please add student's data\n\n", number);
            return;
        }
        switch (number) {
            case 1:
                final_project_average = final1_average();
                break;
            case 2:
                final_project_average = final2_average();
                break;
            default:
                System.out.printf("\nshow Final Project%d average Failed, number of Final Project may be wrong\n\n", number);
                return;
        }
        System.out.printf("Project%d's average: ", number);
        print_rounded_score(final_project_average);
        sum = 0;
    }

    //show total current information of the student(s)
    public void show_total(){
        if(Studentlist_is_Empty()){
            System.out.println("\nThe data is empty, please apply add command to begin\n");
        }
        else{
            print_colume();
            for(int i = 0; i < students.size(); i++){
                System.out.printf("          %-12s", students.get(i).toString());
                System.out.printf("||          %-12d", students.get(i).get_Homework1());
                System.out.printf("||          %-12d", students.get(i).get_Homework2());
                System.out.printf("||          %-12d", students.get(i).get_Homework3());
                System.out.printf("||          %-12d", students.get(i).get_final_1());
                System.out.printf("||          %-12d\n\n", students.get(i).get_final_2());
            }
            System.out.println("\n");
        }
    }

    //clear all student's information
    public void all_clear(){
        if(Studentlist_is_Empty()){
            System.out.println("student list is already clear\n");
        }
        else{
            students.clear();
            System.out.println("all Student's data have been deleted\n");
        }
    }

    //sort the data's order by average and print out
    public void sort_by_average(){
        if (Studentlist_is_Empty()){
            System.out.println("\nsorting Failed, student list is empty\n");
            return;
        }
        int begin_index = 1;
        double temp;
        Student temp_pointer;
        try{
            for(int i = 0; i < students.size(); i++){
                for(int j = students.size() - 1; j >= begin_index ; j--){
                    temp_pointer = students.get(j);
                    temp = students.get(j).get_average();
                    if(students.get(j).get_average() > students.get(j-1).get_average()){
                        students.set(j, students.get(j - 1));
                        students.set(j-1, temp_pointer);
                    }
                }
                begin_index++;
            }//bubble sort

            System.out.println("          Name        ||      Homework1       ||      Homework2       ||      Homework3       ||    Final_project1    ||   Fianl_project2     ||     Average\n\n");
            for(int i = 0; i < students.size(); i++){
                System.out.printf("          %-12s", students.get(i).toString());
                System.out.printf("||          %-12d", students.get(i).get_Homework1());
                System.out.printf("||          %-12d", students.get(i).get_Homework2());
                System.out.printf("||          %-12d", students.get(i).get_Homework3());
                System.out.printf("||          %-12d", students.get(i).get_final_1());
                System.out.printf("||          %-12d", students.get(i).get_final_2());
                System.out.printf("||       ");
                print_rounded_score(students.get(i).get_average());
            }
            System.out.print("\n");

        } catch(NullPointerException e){
            System.out.println("\nsorting Fail, unexpected error occured\n");
        }
    }
    
    //public method




    //private method

    //checking the list is whether empty or not
    private boolean Studentlist_is_Empty(){
        if(students.size() == 0){
            return true;
        }
        return false;
    }

    //return the index of the student from student's list
    private int find_student(String N){
        if(!Studentlist_is_Empty()){
            for(int i = 0; i < students.size(); i++){
                if(students.get(i).toString().equals(N)){
                    return i;
                }
            }
        }
        return -1;//-1 means student didn't be found or list is empty
    }
    
    //print out the default colume
    private void print_colume(){
        System.out.println("          Name        ||      Homework1       ||      Homework2       ||      Homework3       ||    Final_project1    ||   Fianl_project2   \n\n");
    }

    //return the average score of Homework1
    private double homework1_average(){
        for(int i = 0; i < students.size(); i++){
            sum += students.get(i).get_Homework1();
        }
        return (sum/students.size());
    }

    //return the average score of Homework2
    private double homework2_average(){
        for(int i = 0; i < students.size(); i++){
            sum += students.get(i).get_Homework2();
        }
        return (sum/students.size());
    }

    //return the average score of Homework3
    private double homework3_average(){
        for(int i = 0; i < students.size(); i++){
            sum += students.get(i).get_Homework3();
        }
        return (sum/students.size());
    }

    //return the average score of Final Project1
    private double final1_average(){
        for(int i = 0; i < students.size(); i++){
            sum += students.get(i).get_final_1();
        }
        return (sum/students.size());
    }

    //return the average score of Final Project2
    private double final2_average(){
        for(int i = 0; i < students.size(); i++){
            sum += students.get(i).get_final_2();
        }
        return (sum/students.size());
    }

    //print out the rounded score
    private void print_rounded_score(double score){
        if(score % 1 == 0){
            System.out.printf("%.0f\n\n", score);
        }
        else if(((score * 10) % 1 == 0)){
            System.out.printf("%.1f\n\n", score);
        }
        else{
            System.out.printf("%.2f\n\n", score);
        }
        
    }


    //private method

}
