package com.example.searchnames.model;

import com.example.searchnames.util.StringUtils;

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
        this.portions = StringUtils.splitCamelCase(name)
                .stream()
                .map(Portion::new)
                .collect(Collectors.toList());
    }

    List<Portion> getPortions() {
        return portions;
    }

    boolean matches(String pattern) {
        Iterator<String> patternsIterator = StringUtils.splitCamelCase(pattern).iterator();
        Iterator<Portion> portionsIterator = portions.iterator();

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

        return true;
    }
}
