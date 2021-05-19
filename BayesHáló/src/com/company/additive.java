package com.company;

import java.util.ArrayList;
import java.util.IdentityHashMap;

public class additive {
    ArrayList<Integer> NumofValues = new ArrayList<Integer>();
    ArrayList<Integer> value = new ArrayList<Integer>();
    ArrayList<Integer> IDs = new ArrayList<Integer>();


    public void PrintAdditiveStat(){
        for(int i = 0; i < value.size(); i++){
            System.out.println(String.valueOf("ID: " + IDs.get(i)) + "  NumofValues:  " + String.valueOf(NumofValues.get(i)) + "  Values:  " + String.valueOf(value.get(i)));
        }
    }

    public boolean AddOne(){
        for(int i = 0; i < value.size(); i++){
            if(!(value.get(i) + 1 == NumofValues.get(i))){
                value.set(i, value.get(i) + 1);
                return IsNotZero();
            }
            else{
                value.set(i, 0);
            }
        }
        return false;
    }

    public boolean IsNotZero(){ //ha igazzal ter vissza akkor meg van mit hozzaadni
        for(int i = 0; i < value.size(); i++){
            if(value.get(i) != 0)
                return true;
        }
        return false;
    }

    public void FillData(){
        value.add(1);
        for(int i = 1; i < NumofValues.size(); i++){
            value.add(0);
        }
    }

    public ArrayList<String> MakePermutation(){
        FillData();
        ArrayList<String> Permutation = new ArrayList<String>();

        //PrintAdditiveStat();

        String tmp = "";
        if(NumofValues.size() == 1)
            tmp = tmp + String.valueOf(IDs.get(0)) + ":" + String.valueOf(0);
        else
            tmp = tmp + String.valueOf(IDs.get(0)) + ":" + String.valueOf(0) + ",";

        for(int i = 1; i < value.size(); i++){
            if(i != value.size()-1)
                tmp = tmp + String.valueOf(IDs.get(i)) + ":" + String.valueOf(value.get(i)) + ",";
            else
                tmp = tmp + String.valueOf(IDs.get(i)) + ":" + String.valueOf(value.get(i));
        }

        Permutation.add(tmp);
        boolean bool = true;
        while (bool){
            String str = "";
            for(int i = 0; i < value.size(); i++){
                if(i != value.size()-1)
                    str = str + String.valueOf(IDs.get(i)) + ":" + String.valueOf(value.get(i)) + ",";
                else
                    str = str + String.valueOf(IDs.get(i)) + ":" + String.valueOf(value.get(i));
            }
            Permutation.add(str);
            bool = AddOne();
        }
        return Permutation;
    }


    //normalizációs konstans
}


