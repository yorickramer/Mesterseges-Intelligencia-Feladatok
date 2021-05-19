package com.company;

import java.util.ArrayList;
import java.util.PrimitiveIterator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner myObj = new Scanner(System.in);
        int numberofNodes = Integer.parseInt(myObj.nextLine());
        Tree tree = new Tree();

        for(int i = 0; i < numberofNodes; i++){
            String line = myObj.nextLine();
            //System.out.println(line);
            tree.SetupNode(line, i);
        }

        int numberofEvidence = Integer.parseInt(myObj.nextLine());

        for(int i = 0; i<numberofEvidence;i++){
            String line = myObj.nextLine();
            tree.AddEvidence(Integer.parseInt(line.split("\t")[0]),Integer.parseInt(line.split("\t")[1]));
        }
        int goalID = Integer.parseInt(myObj.nextLine());
        tree.setGoal(goalID);
        //tree.PrintTreeStat();
        tree.EliminateNodes();
        //System.out.println("Eliminated Nodes:");
        //tree.PrintEliminatedTree(); //ugy tunik mukodik


        //mukodik a peremutacio is

        /*
        ArrayList<String> PermutationString = tree.MakePermutation();


        for(int i = 0; i< PermutationString.size();i++)
            System.out.println(PermutationString.get(i));

        System.out.println(tree.GetPermNum(PermutationString.get(PermutationString.size()-1),0));
            */




        /* mukodik a parametric is
        tree.PrintParametric(tree.CreateOneLineArray());
        ArrayList<String> Paramteric = tree.CreateOneLineArray();
        String[] split = Paramteric.get(0).split(";");
        System.out.println(split.length);
        */



        /*getprobability tesztelés*/
        /*
        ArrayList<Integer> parentVal = new ArrayList<Integer>();
        parentVal.add(0);
        parentVal.add(1);
        parentVal.add(1);
        parentVal.add(1);
        System.out.println(tree.getProbability(13,2,parentVal));
        //node :13
         */












        //vege
        tree.CalCulate();
        tree.Normalize();
        tree.PrintResult();

    }
}
