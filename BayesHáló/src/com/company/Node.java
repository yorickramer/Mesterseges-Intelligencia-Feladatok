package com.company;

import java.util.ArrayList;

public class Node {

    //lokális feltételes valsz táblák
    //evidenciavááltozók rögzítése

    public int ID;
    public int Value = -1;
    public int numOfValue;
    public double[][] ProbTable;

    ArrayList<Node> Parents = new ArrayList<Node>();
    ArrayList<Node> Children = new ArrayList<Node>();

    public void AddParent(Node n){
        this.Parents.add(n);
    }

    public void AddChildren(Node n){
        this.Children.add(n);
    }
}
