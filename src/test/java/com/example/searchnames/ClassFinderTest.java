package com.example.searchnames;


import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Arthur Kazemi<bidadh@gmail.com>
 * @date 14/7/17 4:48 PM
 */
public class ClassFinderTest {
    private ClassFinder sut;

    @Before
    public void setup() throws IOException {
        String initialString = "Foo\r\nFooBar\r\nFooBarBaz\r\n\r\nFoo\r\nZooBar";
        InputStream namesInputStream = new ByteArrayInputStream(initialString.getBytes());
        sut = new ClassFinder(namesInputStream);
    }

    @Test
    public final void givenClassFinder_whenSearchForMatchingPattern_shouldFindMatches() {
        Collection<String> matches = sut.findMatching("FB");
        assertThat(matches).isNotNull();
        assertThat(matches.size()).isEqualTo(2);
    }

    @Test
    public final void givenClassFinder_whenSearchForMatchingPattern_shouldListMatchesSortedAlphabetically() {
        Object[] matches = sut.findMatching("B").toArray();

        assertThat(matches.length).isEqualTo(3);
        assertThat(matches[0]).isEqualTo("FooBar");
        assertThat(matches[1]).isEqualTo("FooBarBaz");
        assertThat(matches[2]).isEqualTo("ZooBar");
    }
}