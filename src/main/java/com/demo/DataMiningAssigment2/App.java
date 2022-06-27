package com.demo.DataMiningAssigment2;
import java.util.Scanner;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Kmeans solve =new Kmeans();
        solve.read_from_excel_file("Course Evaluation .xlsx");
        Scanner sc= new Scanner(System.in);
        int k = sc.nextInt();
        solve.Groupintoclusters(k);
    }
}
