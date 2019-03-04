package com.lattisi.euclide;

import java.util.Set;
import java.util.TreeSet;

public class AliasesUtility {

    public static Set<String> computeAliases(String name) {
        return permute(name);
    }

    private static Set<String> permute(String chars) {
        Set<String> set = new TreeSet<>();
        if (chars.length()==1) {
            set.add(chars);
        } else {
            for (int i=0; i<chars.length(); i++) {
                char charAtIndex = chars.charAt(i);
                String remaining = removeCharAtIndex(chars, i);
                for (String permutation: permute(remaining)) {
                    set.add(charAtIndex+permutation);
                }
            }
        }
        return set;
    }

    private static String removeCharAtIndex(String chars, int index) {
        String pre = chars.substring(0, index);
        String post = chars.substring(index +1);
        return pre+post;
    }

}
