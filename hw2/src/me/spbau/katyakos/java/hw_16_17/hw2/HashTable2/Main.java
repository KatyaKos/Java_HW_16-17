package me.spbau.katyakos.java.hw_16_17.hw2.HashTable2;

/**
 * Created by KatyaKos on 19.09.2016.
 * @author KatyaKos
 */
public class Main {
    public static void main(String[] args){
        HashTable myTable = new HashTable();
        System.out.println(myTable.put("hey", "you"));
        System.out.println(myTable.put("hey", "me"));
        System.out.println(myTable.put("hoy", "you"));
        System.out.println(myTable.put("hay", "you"));
        System.out.println(myTable.get("hey"));
        System.out.println(myTable.size());
        System.out.println(myTable.remove("hay"));
        System.out.println(myTable.size());
        System.out.println(myTable.get("hey"));
        System.out.println(myTable.get("hoy"));
        System.out.println(myTable.get("hay"));
        myTable.clear();
        System.out.println(myTable.size());
        System.out.println(myTable.get("hey"));
    }
}

