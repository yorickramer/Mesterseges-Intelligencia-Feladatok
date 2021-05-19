package com.company;

import java.util.ArrayList;

public class Tree {

    ArrayList<Node> Nodes = new ArrayList<Node>();
    ArrayList<Node> ImportantNode = new ArrayList<Node>();
    ArrayList<Double> Result = new ArrayList<Double>();
    int goalID;

    public void ConnectNodes(int[] parentIDs, Node children){
        for(int i = 0; i < parentIDs.length; i++){
            for(int j = 0; j < Nodes.size();j++){
                if(parentIDs[i] == Nodes.get(j).ID){
                    Nodes.get(j).AddChildren(children);
                    children.AddParent(Nodes.get(j));
                }
            }
        }
    }

    public void Normalize(){
        double div = 0;
        for(int i = 0; i < Result.size();i++){
            div = div + Result.get(i);
        }
        //div = 1 / div;
        for(int i = 0; i < Result.size();i++){
            Result.set(i,Result.get(i) / div);
        }
    }

    public void AddNode(Node n){
        Nodes.add(n);
    }

    public void SetupNode(String line, int i){
        int from = 0;
        Node n = new Node();
        AddNode(n);
        n.ID = i;
        String[] splittedLine = line.split("\t");
        n.numOfValue = Integer.parseInt(splittedLine[0]);
        //System.out.println("Number of Values:" + n.numOfValue);
        int numOfParents = Integer.parseInt(splittedLine[1]);
        int[] parentIDs = new int[numOfParents];
        for(int j = 0; j < numOfParents; j++){
            parentIDs[j] = Integer.parseInt(splittedLine[j + 2]);
            from = j + 2;
        }
        from++;
        for(int j = 0; j < numOfParents; j++){ //megvan
            //System.out.println(parentIDs[j]);
        }

        ConnectNodes(parentIDs,n); //megvan
        SetProbTable(n, line, from);
    }

    public void SetProbTable(Node n, String line, int from){
        int numOfParents = n.Parents.size();
        if(numOfParents == 0){
            n.ProbTable = new double[1][n.numOfValue];
            for(int i = 0; i < n.numOfValue; i++){
                n.ProbTable[0][i] = Double.parseDouble(line.split("\t")[2+numOfParents].split(",")[i]);
            }
            return;
        }
        int height = 1;
        for(int i = 0; i < numOfParents; i++){
            height = height * n.Parents.get(i).numOfValue;
        }

        n.ProbTable = new double[height][n.numOfValue + numOfParents]; //azert adtam hozza a num of parentset meert igy konnyebb lesz kezelni a atablazatot
        for(int i = from; i < from + height ;i++){
            String row = line.split("\t")[i];
            String val = row.split(":")[0];
            String probs = row.split(":")[1];
            int j = 0;
            for(int k = 0; k < val.split(",").length; k++){
                n.ProbTable[i-from][k] = Double.parseDouble(val.split(",")[k]);
                j = k;
            }
            j++;
            for(int k = 0; k < probs.split(",").length; k++){
                n.ProbTable[i-from][j] = Double.parseDouble(probs.split(",")[k]);
                j++;
            }
        }
    }

    public void AddEvidence(int ID, int value){
        for(int i = 0; i < Nodes.size();i++){
            if(Nodes.get(i).ID == ID)
                Nodes.get(i).Value = value;
        }
    }

    public void PrintTreeStat(){
        for(int i = 0; i<Nodes.size();i++){
            System.out.println("Node:" + Nodes.get(i).ID);
            PrintProbTable(Nodes.get(i).ProbTable);
        }
    }

    public void PrintProbTable(double[][] table){
        for(int i = 0; i < table.length;i++){
            for(int j = 0; j < table[0].length;j++){
                if(j == table[0].length - 1)
                    System.out.print(String.valueOf(table[i][j]));
                else
                    System.out.print(String.valueOf(table[i][j]) + '\t');
            }
            System.out.println();
        }
    }

    public void PrintEliminatedTree(){
        System.out.println("Old num of Nodes:" + Nodes.size());
        System.out.println("New num of Nodes:" + ImportantNode.size());
        for(int i = 0; i<ImportantNode.size();i++){
            System.out.println("Node:" + ImportantNode.get(i).ID);
            PrintProbTable(ImportantNode.get(i).ProbTable);
        }
    }

    public void EliminateNodes(){
        for(int i = 0; i < Nodes.size(); i++){
            if(BFS(Nodes.get(i)))
                ImportantNode.add(Nodes.get(i));
        }
    }

    public boolean BFS(Node n){
        ArrayList<Node> Edge = new ArrayList<Node>();

        if(n.Value>-1 || n.ID == goalID)
            return true;

        for(int i = 0; i < n.Children.size();i++){
            Edge.add(n.Children.get(i));
        }

        while(Edge.size()>0){
            for(int i = 0; i < Edge.size();i++){
                if(Edge.get(i).Value>-1 || Edge.get(i).ID == goalID)
                    return true;
            }
            for(int i = 0; i < Edge.get(0).Children.size();i++){
                Edge.add(Edge.get(0).Children.get(i));
            }
            Edge.remove(0);
        }
        return false;
    }



    public void setGoal(int g){
        goalID = g;
    }



    public ArrayList<String> MakePermutation(){ //tesztelve

        additive PermAdditive = new additive();

        for(int i = 0; i < ImportantNode.size(); i++){
            if(ImportantNode.get(i).Value==-1){ //5 valtozo kell
                PermAdditive.IDs.add(ImportantNode.get(i).ID);
                PermAdditive.NumofValues.add(ImportantNode.get(i).numOfValue);
            }
        }

        return PermAdditive.MakePermutation();
    }



    public ArrayList<String> CreateOneLineArray(){ //tesztelve
        //letrehoz egy string tombot, amely tartalmazza a paramétereket
        //formatum: "{szam1}|{szulei felvett ertekei sorban : "," vel elvallasztva, ha valtozom akkor ID:szam}"
        //ha van fix erteke, akkor a fix erteket veszi fel
        //szam1:a csomopontIDje, szam... : a szulocsomopontok altal felvett ertek

        ArrayList<String> Parametric = new ArrayList<String>();
        for(int i = 0; i < ImportantNode.size();i++){ //vegig megy a fontos pontokon
            String prob  = String.valueOf(ImportantNode.get(i).ID) + ";";
            for(int j = 0; j < ImportantNode.get(i).Parents.size();j++){ //vegig megy a szulokon
                if(ImportantNode.get(i).Parents.get(j).Value>-1){
                    if(j != ImportantNode.get(i).Parents.size() - 1)
                        prob = prob + String.valueOf(ImportantNode.get(i).Parents.get(j).Value) + ","; //ha szulo fix erteket vesz fel, akkor az ertek
                    else
                        prob = prob + String.valueOf(ImportantNode.get(i).Parents.get(j).Value);
                }
                else{
                    if(j != ImportantNode.get(i).Parents.size() - 1)
                        prob = prob + "ID:" + String.valueOf(ImportantNode.get(i).Parents.get(j).ID) + ",";
                    else
                        prob = prob + "ID:" + String.valueOf(ImportantNode.get(i).Parents.get(j).ID);
                }
            }
            Parametric.add(prob);
        }

        return Parametric;
    }

    public void PrintParametric(ArrayList<String> param){
        System.out.println("Parametric:\n");
        for(int i = 0; i<param.size();i++){
            System.out.println(param.get(i));
        }
    }





    public void CalCulate(){ //ez jo lesz
        for(int i = 0; i < Nodes.get(goalID).numOfValue;i++){
            Nodes.get(goalID).Value = i;
            Result.add(FinalCalc());
        }
    }

    public void PrintResult(){ //jo
        for(int i = 0; i < Result.size();i++){
            System.out.println(Result.get(i));
        }
    }



    public double FinalCalc(){ //ez jo lesz
        ArrayList<String> Parametric = CreateOneLineArray(); //ezt egy szinttel lejjebb lehet szervezni, nem kell mindig legeneralni
        ArrayList<String> Permutation = MakePermutation();

        double res = 0;
        for(int i = 0; i < Permutation.size(); i++){
            res = res + ExecuteOneLineArray(Parametric, Permutation.get(i));
        }
        return res;
    }


    public double ExecuteOneLineArray(ArrayList<String> Parametric, String permutacio){ //ez egy permutacioban szamolja ki a dolgokat
        double res = 1;
        for(int i = 0; i < Parametric.size();i++){
            String[] ProbSplitted = Parametric.get(i).split(";");
            if(ProbSplitted.length!=1){ //tehat ha vannak szuloi
                String[] Values = ProbSplitted[1].split(","); //le kell kezelni, ha nincs szulo --- kulonozo szulok
                ArrayList<Integer> parentValues = new ArrayList<Integer>();

                int selfvalue;
                if(Nodes.get(Integer.parseInt(ProbSplitted[0])).Value == -1){ //ha cel valtozo nem evidencia
                    selfvalue = GetPermNum(permutacio, Integer.parseInt(ProbSplitted[0])); //sajat felvett erteke
                }
                else{
                    selfvalue = Nodes.get(Integer.parseInt(ProbSplitted[0])).Value; //sajat felvett erteke
                }

                for(int j = 0; j < Values.length;j++){
                    String[] valueOrId = Values[j].split(":");
                    if(valueOrId.length == 1)
                        parentValues.add(Integer.parseInt(valueOrId[0]));
                    else{
                        parentValues.add(GetPermNum(permutacio, Integer.parseInt(valueOrId[1])));
                    }
                }

                double value = getProbability(Nodes.get(Integer.parseInt(ProbSplitted[0])).ID, selfvalue, parentValues);
                res = res * value;
            }
            else{ //ha nincsneek szuloi
                double value;
                int id = Integer.parseInt(ProbSplitted[0]);
                int selfvalue  = 0;
                if(Nodes.get(Integer.parseInt(ProbSplitted[0])).Value == -1){ //ha cel valtozo nem evidencia
                    selfvalue = GetPermNum(permutacio, Integer.parseInt(ProbSplitted[0])); //sajat felvett erteke

                }
                else{
                    selfvalue = Nodes.get(Integer.parseInt(ProbSplitted[0])).Value; //sajat felvett erteke

                }

                value = Nodes.get(id).ProbTable[0][selfvalue];
                res = res * value;
            }
        }
        return res;
    }

    //tesztelve
    public double getProbability(int ID, int value, ArrayList<Integer> parentValues){ //mit csinál
        double[][] ProbTable = Nodes.get(ID).ProbTable;
        boolean match = true;
        for(int i = 0; i < ProbTable.length;i++){
            for(int j = 0; j < parentValues.size();j++){

                if((ProbTable[i][j]) != parentValues.get(j)){
                    match = false;
                    break;
                }

            }
            if(match){
                return ProbTable[i][parentValues.size() + value];
            }
            match = true;
        }
        return -1;
    }



    //mukodik
    public int GetPermNum(String permutacio, int id){ //megadja egy adott ID-ju elem milyen erteket vesz fel az adott permutacioban
        String[] ProbSplitted = permutacio.split(",");
        for(int i = 0; i < ProbSplitted.length;i++){
            if(Integer.parseInt(ProbSplitted[i].split(":")[0]) == id)
                return Integer.parseInt(ProbSplitted[i].split(":")[1]);
        }
        return -1;
    }
}
