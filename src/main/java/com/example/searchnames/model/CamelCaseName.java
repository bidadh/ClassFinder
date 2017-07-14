package com.example.searchnames.model;

import com.example.searchnames.util.StringUtils;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Arthur Kazemi<bidadh@gmail.com>
 * @date 13/7/17 12:36 PM
 */
class CamelCaseName {
    private final List<Portion> portions;

    CamelCaseName(String name) {
        //TODO: throw exception if pass an empty string or string without class name!
        this.portions = StringUtils.splitCamelCase(name)
                .stream()
                .map(Portion::new)
                .collect(Collectors.toList());
    }

    List<Portion> getPortions() {
        return portions;
    }

    boolean matches(String pattern) {
        Boolean exactMatchLastWord = pattern.endsWith(" ");
        List<String> patternPortions = StringUtils.splitCamelCase(pattern);
        Iterator<String> patternsIterator = patternPortions.iterator();
        Iterator<Portion> portionsIterator = portions.iterator();

        //TODO: replace this with throw exceptions for empty names / patterns
        if((patternPortions.size() == 0) || (portions.size() == 0)) {
            return false;
        }

        while (patternsIterator.hasNext()) {
            String p = patternsIterator.next();
            Boolean m = false;
            while (portionsIterator.hasNext() && !m) {
                Portion namePortion = portionsIterator.next();
                m = namePortion.matches(p);
            }

            if(!m) {
                return false;
            }
        }

        if(exactMatchLastWord) {
            Portion lastPatternPortion = new Portion(patternPortions.get(patternPortions.size() - 1));
            return portions.lastIndexOf(lastPatternPortion) == portions.size() - 1;
        }

        return true;
    }
}
