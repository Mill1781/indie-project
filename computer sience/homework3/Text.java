public class Text {

    //just some trival text
    //public method

    //command list and welcoming
    public static void welcome_and_explain(){
        System.out.println("\n\nWelcome, here is System for arranging students' score");
        System.out.println("Caution 1: this system would automatically delete repeative data and score in the original Score list");
        System.out.println("Caution 2: make sure the name of list \"Score.txt\" is correct, wrong name would result in severe error");
        System.out.println("Caution 3 !!!: user better not to manually change the data in Score.txt, changing them by this program is extremely recommanded");
        System.out.println("Please read the following commands to operate the system.");
        System.out.println("--------------------------------------------------------\n");
        System.out.println("add [Student name] [Homework1, Homework2, Homework3, Final Project1, Final Project2]\nExplain: you can apply this command to add student's score\nEx. add Tom 87,86,90,85,95\n\n");
        System.out.println("delete [Student name]\nExplain: delete all information for one student\nEx. delete Tom\n\n");
        System.out.println("show individual score [Student name]\nExplain: show score of all projects and homeworks for one specific student\nEx. show individual score Tom\n\n");
        System.out.println("show individual average [Student name]\nExplain: calculate and show the average of score for one specific stuent\nEx. show individual average Tom\n\n");
        System.out.println("show Homework[number] average\nExplain: show the average of one specific homework\nEx. show Homework3 average\n\n");
        System.out.println("show Final Project[number] average\nExplain: show the average of one specific project\nEx. show Final Project2 average\n\n");
        System.out.println("help\nExplain: show the commands again\nEx. help\n\n");
        System.out.println("clear\nExplain: clear all student's information in Score.txt\nEx. clear\n\n");
        System.out.println("sorting\nExplain: sort the student's data by their average from highest to lowest and print out the result\nEx. sorting\n\n");
        System.out.println("exit\nExplain: end the service\nEx. exit\n\n");
        System.out.println("--------------------------------------------------------\n");
        System.out.println("if you do have any problems about those commands again, please type help\n\n");
    }
    
    //command list
    public static void help(){
        System.out.println("--------------------------------------------------------\n");
        System.out.println("add [Student name] [Homework1, Homework2, Homework3, Final Project1, Final Project2]\nExplain: you can apply this command to add student's score\nEx. add Tom 87,86,90,85,95\n\n");
        System.out.println("delete [Student name]\nExplain: delete all information for one student\nEx. delete Tom\n\n");
        System.out.println("show individual score [Student name]\nExplain: show score of all projects and homeworks for one specific student\nEx. show individual score Tom\n\n");
        System.out.println("show individual average [Student name]\nExplain: calculate and show the average of score for one specific stuent\nEx. show individual average Tom\n\n");
        System.out.println("show Homework[number] average\nExplain: show the average of one specific homework\nEx. show Homework3 average\n\n");
        System.out.println("show Final Project[number] average\nExplain: show the average of one specific project\nEx. show Final Project2 average\n\n");
        System.out.println("help\nExplain: show the commands again\nEx. help\n\n");
        System.out.println("clear\nExplain: clear all student's information in Score.txt\nEx. clear\n\n");
        System.out.println("sorting\nExplain: sort the student's data by their average from highest to lowest and print out the result\nEx. sorting\n\n");
        System.out.println("exit\nExplain: end the service\nEx. exit\n\n");
        System.out.println("--------------------------------------------------------\n");
        System.out.println("if you do have any problems about those commands again, please type help\n\n");

    }

    //public method

}
