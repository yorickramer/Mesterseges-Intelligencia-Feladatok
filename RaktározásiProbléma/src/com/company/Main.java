package com.company;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner myObj = new Scanner(System.in);
        String storagedim = myObj.nextLine();
        int[][] storage = new int[Integer.parseInt(storagedim.split("\t")[0]) + 1][Integer.parseInt(storagedim.split("\t")[1])];

        int numofCol = Integer.parseInt(myObj.nextLine());
        int numofObjects = Integer.parseInt(myObj.nextLine());

        int[][] colums = new int[numofCol][2];
        int[][] packages = new int[numofObjects][2];
        int[][] packages2 = new int[numofObjects][2];

        for(int i = 0; i < numofCol; i++){
            String colum = myObj.nextLine();
            String[] arrOfStr = colum.split("\t");
            colums[i][0] = Integer.parseInt(arrOfStr[0]);
            colums[i][1] = Integer.parseInt(arrOfStr[1]);
        }

        for(int i = 0; i < numofObjects; i++){
            String object = myObj.nextLine();
            String[] arrOfStr = object.split("\t");
            packages[i][0] = Integer.parseInt(arrOfStr[0]);
            packages2[i][0] = Integer.parseInt(arrOfStr[0]);
            packages[i][1] = Integer.parseInt(arrOfStr[1]);
            packages2[i][1] = Integer.parseInt(arrOfStr[1]);
        }

        FIFO fifo = new FIFO();

        for(int i = 0; i < packages.length; i++){
            int idx = GetMaxIdx(packages2);
            fifo.Packages.add(packages[idx]);
            fifo.idx.add(idx+1);
        }

        int root = 0;
        while(root <= packages.length - 1){

            if(Placing(storage, fifo.idx.get(root), fifo.Packages.get(root), colums))
                root++;
            else
                root--;
            //PrintStorage(storage);
        }

        PrintStorage(storage);
    }

    public static void PrintStorage(int[][] s){
        for(int i = 0; i < s.length - 1;i++){
            for(int j = 0; j < s[0].length;j++){
                if(j == s[0].length - 1)
                    System.out.print(String.valueOf(s[i][j]));
                else
                    System.out.print(String.valueOf(s[i][j]) + '\t');
            }
            System.out.println();
        }
    }

    public static int GetMaxIdx(int[][] packages){
        int max = 0;
        for(int i = 0; i < packages.length;i++){
            if(packages[i][0] * packages[i][1] >= max)
                max = packages[i][0] * packages[i][1];
        }

        for(int i = 0; i < packages.length; i++){
            if(packages[i][0] * packages[i][1] == max){
                packages[i][0] = 0;
                packages[i][1] = 0;
                return i;
            }
        }
        return 0;
    }

    public static int[] rotate(int[] pos){
        int tmp = pos[0];
        pos[0] = pos[1];
        pos[1] = tmp;
        return pos;
    }

    public static boolean Placing(int[][] storage, int id,  int[] object, int[][] columns){
        boolean found = false;
        int x = 0;
        int y = 0;
        for(int i = 0; i < storage.length;i++) {
            for (int j = 0; j < storage[0].length; j++) {
                if (storage[i][j] == id) {
                    if(found == false) {
                        x = i + 1;
                        y = j;
                        if(x >=  storage.length - object[0]){
                            x = 0;
                            y = j + 1;
                        }

                    }
                    found = true;
                    storage[i][j] = 0;
                }
            }
        }

        if(PlaceHelp(storage, id, object, columns, x, y))
            return true;

        object = rotate(object);
        x = 0;
        y = 0;

        if(PlaceHelp(storage, id, object, columns, x, y))
            return true;

        return false;
    }

    public static boolean PlaceHelp(int[][] storage, int id,  int[] object, int[][] columns, int x, int y){

        while(storage[0].length >= y + object[1]){


            if(IsCrossing(columns,x,y,object)){
                boolean utkozik = false;
                for(int n = x; n < x + object[0]; n++){
                    for(int m = y; m < y + object[1]; m++){
                        if(storage[n][m]!=0) {
                            utkozik = true;
                        }
                    }
                }
                if(utkozik != true){

                    for(int n = x; n < x + object[0]; n++){
                        for(int m = y; m < y + object[1]; m++){
                            storage[n][m] = id;
                        }
                    }
                    return true;
                }
            }

            x++;
            if(x >=  storage.length - object[0]){
                x = 0;
                y++;
            }
        }
        return false;
    }

    public static boolean IsCrossing(int[][] coloms, int Xpos, int Ypos, int[] size){
        for(int i = 0; i < coloms.length; i++){
            if(Xpos < coloms[i][0] && Xpos+size[0] > coloms[i][0] && Ypos < coloms[i][1] && Ypos+size[1] > coloms[i][1])
                return false;
        }
        return true;
    }
}
