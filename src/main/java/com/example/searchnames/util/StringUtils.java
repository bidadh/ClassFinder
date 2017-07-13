package com.example.searchnames.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;

/**
 * @author Arthur Kazemi<bidadh@gmail.com>
 * @date 13/7/17 1:04 PM
 */
class StringUtils {
    static List<String> splitCamelCase(String name) {
        //TODO: refactor this to isEmpty method
        if(name.trim().length() == 0) {
            throw new IllegalArgumentException("Name cannot be empty!");
        }


        //TODO: refactor this to a new method
        if(Character.isLowerCase(name.charAt(0))) {
            throw new IllegalArgumentException("Name cannot be empty!");
        }


        //TODO: refactor to functional and add tests
        IntPredicate uppercasePredicate = Character::isUpperCase;
        StringBuilder res = new StringBuilder(name.substring(0,1));
        for(int i = 1; i < name.length(); i++) {
            Character ch = name.charAt(i);
            if(Character.isUpperCase(ch))
                res.append(" ").append(ch);
            else
                res.append(ch);
        }

        return Arrays.stream(res.toString().split(" "))
                .collect(Collectors.toList());
    }
}
