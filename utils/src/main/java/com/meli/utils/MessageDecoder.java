package com.meli.utils;

import java.util.ArrayList;
import java.util.Arrays;

public class MessageDecoder {

    public static String getMessage(String[]... enconded){
        int largerSize = 0;
        String [] baseArray = null;
        int position = 0;
        ArrayList<String[]> listsToCompare = new ArrayList<>(Arrays.asList(enconded));
        for (int i = 0; i < listsToCompare.size(); i++) {
            int size = listsToCompare.get(i).length;
            if(size > largerSize){
                largerSize = size;
                baseArray = listsToCompare.get(i);
                position = i;
            }
        }
        listsToCompare.remove(position);
        String[] finalBaseArray = baseArray;
        listsToCompare.forEach(i ->{
            for (int j = 0; j < i.length; j++) {
                if(i[j].isBlank()){
                    continue;
                }else if(finalBaseArray[j].equals(i[j])){
                    continue;
                }else if((j-1)==-1) {
                    if (!checkRight(finalBaseArray,i,j)){
                        finalBaseArray[j] = i[j];
                    }
                }else if((j+1) == finalBaseArray.length){
                    if (!checkLeft(finalBaseArray,i,j)){
                        finalBaseArray[j] = i[j];
                    }
                }else if(j + 1 != finalBaseArray.length){
                    checkAllSides(finalBaseArray,i,j);
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
        if ((j - 1) != -1 && finalBaseArray[j - 1].equals(i[j])) {
            return;
        } else if ((j - 1) !=-1 && finalBaseArray[j - 1].isBlank()) {
            finalBaseArray[j - 1] = i[j];
        }
        if ((j + 1) < finalBaseArray.length && finalBaseArray[j + 1].equals(i[j])) {
            return;
        }else if(finalBaseArray[j].isBlank()){
            finalBaseArray[j] = i[j];
        } else if ((j + 1) < finalBaseArray.length && finalBaseArray[j + 1].isBlank()) {
            finalBaseArray[j + 1] = i[j];
        }
    }
}
