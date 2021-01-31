package com.meli.utils;

import io.micronaut.core.annotation.Introspected;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

@Introspected
public class MessageDecoder {

    public static String getMessage(String[]... enconded){
        int toRemove = enconded.length-1;
        ArrayList<String[]> arrayList = Arrays.stream(enconded).sorted(new MaxComparator()).collect(Collectors.toCollection(ArrayList::new));
        String[] finalBaseArray = arrayList.get(toRemove);
        arrayList.remove(toRemove);
        arrayList.forEach(i ->{
            for (int j = 0; j < i.length; j++) {
                if (!i[j].isBlank()) {
                    if (!finalBaseArray[j].equals(i[j])) {
                        if ((j - 1) == -1) {
                            if (!checkRight(finalBaseArray, i, j)) {
                                finalBaseArray[j] = i[j];
                            }
                        } else if ((j + 1) == finalBaseArray.length) {
                            if (!checkLeft(finalBaseArray, i, j)) {
                                finalBaseArray[j] = i[j];
                            }
                        } else if (j + 1 != finalBaseArray.length) {
                            checkAllSides(finalBaseArray, i, j);
                        }
                    }
                }
            }
        });
        return (String.join(" ", finalBaseArray)).trim();
    }

    private static boolean checkRight(String[] finalBaseArray, String[] i, Integer j){
        if ((j + 1) < finalBaseArray.length && finalBaseArray[j + 1].equals(i[j])) {
            return true;
        } else if ((j + 1) < finalBaseArray.length && finalBaseArray[j + 1].isBlank()) {
            finalBaseArray[j + 1] = i[j];
            return true;
        }
        return false;
    }

    private static boolean checkLeft(String[] finalBaseArray, String[] i, Integer j){
        if ((j - 1) != -1 && finalBaseArray[j - 1].equals(i[j])) {
            return true;
        } else if ((j - 1) !=-1 && finalBaseArray[j - 1].isBlank()) {
            finalBaseArray[j - 1] = i[j];
            return true;
        }
        return false;
    }

    private static void checkAllSides(String[] finalBaseArray, String[] i, Integer j){
        if ((j + 1) < finalBaseArray.length && finalBaseArray[j + 1].equals(i[j])) {
            return;
        }else if(finalBaseArray[j].isBlank()){
            finalBaseArray[j] = i[j];
        } else if ((j + 1) < finalBaseArray.length && finalBaseArray[j + 1].isBlank()) {
            finalBaseArray[j + 1] = i[j];
        }else if ((j - 1) != -1 && finalBaseArray[j - 1].equals(i[j])) {
            return;
        } else if ((j - 1) !=-1 && finalBaseArray[j - 1].isBlank()) {
            finalBaseArray[j - 1] = i[j];
        }
    }
}

class MaxComparator implements Comparator<String[]> {
    @Override
    public int compare(String[] p1, String[] p2) {
        return  String.valueOf(p1.length).compareTo(p2.length+"");
    }

}
