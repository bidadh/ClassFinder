package com.example.searchnames.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Arthur Kazemi<bidadh@gmail.com>
 * @date 13/7/17 11:11 AM
 */
public class PortionTest {
    @Test
    public final void givenPortion_WhenMatchSameName_ShouldReturnTrue() {
        final String name = "Foo";
        final Portion portion1 = new Portion(name);
        assertThat(portion1.matches(name)).isTrue();
    }

    @Test
    public final void givenPortion_WhenMatchDifferentName_ShouldReturnFalse() {
        final String name = "Foo";
        final String pattern = "Bar";
        final Portion portion1 = new Portion(name);
        assertThat(portion1.matches(pattern)).isFalse();
    }

    @Test
    public final void givenPortion_WhenMatchFirstCharacterInUppercase_ShouldReturnTrue() {
        final String name = "Foo";
        final String pattern = "F";
        final Portion portion1 = new Portion(name);
        assertThat(portion1.matches(pattern)).isTrue();
    }

    @Test
    public final void givenPortion_WhenMatchPartOfName_ShouldReturnTrue() {
        final String name = "Foobar";
        final Portion portion1 = new Portion(name);
        assertThat(portion1.matches("Fo")).isTrue();
        assertThat(portion1.matches("Foo")).isTrue();
        assertThat(portion1.matches("Foob")).isTrue();
        assertThat(portion1.matches("Fooba")).isTrue();
    }

    @Test
    public final void testEquals() {
        final String name = "Foobar";

        final Portion portion1 = new Portion(name);
        final Portion portion2 = new Portion(name);
        final Portion portion3 = new Portion("");

        assertThat(portion1.equals(portion1)).isTrue();
        assertThat(portion1.equals(portion2)).isTrue();
        assertThat(portion1.equals(portion3)).isFalse();
    }
}