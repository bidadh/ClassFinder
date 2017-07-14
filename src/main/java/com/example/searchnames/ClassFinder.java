package com.example.searchnames;

import com.example.searchnames.model.CamelCaseName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Arthur Kazemi<bidadh@gmail.com>
 * @date 14/7/17 4:47 PM
 */
class ClassFinder {
    private final Set<CamelCaseName> classNames;

    ClassFinder(InputStream inputStream) throws IOException {
        classNames = new BufferedReader(new InputStreamReader(inputStream))
                .lines()
                .filter(s -> !s.isEmpty())
                .distinct()
                .map(CamelCaseName::new)
                .collect(Collectors.toSet());
    }

    Collection<String> findMatching(String pattern) {
        return classNames.parallelStream()
                .filter(camelCaseName -> camelCaseName.matches(pattern))
                .map(Object::toString)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
    }
}
