package com.example.searchnames.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Arthur Kazemi<bidadh@gmail.com>
 * @date 13/7/17 12:36 PM
 */
public class CamelCaseNameTest {
    @Test
    public final void givenName_whenCreateCamelCaseName_shouldInitializePortions() {
        final String string = "Foo";
        final CamelCaseName name = new CamelCaseName(string);

        assertThat(name.getPortions()).isNotNull();
    }

    @Test
    public final void givenSimpleName_whenCreateClassName_shouldContainPortionInPortions() {
        final String string = "Foo";
        final Portion portion = new Portion(string);
        final CamelCaseName name = new CamelCaseName(string);

        assertThat(name.getPortions().contains(portion)).isTrue();
    }

    @Test
    public final void givenName_whenCreateClassName_shouldContainAllPortions() {
        final String string = "FooBarBazBar";
        final CamelCaseName name = new CamelCaseName(string);

        assertThat(name.getPortions().get(0)).isEqualTo(new Portion("Foo"));
        assertThat(name.getPortions().get(1)).isEqualTo(new Portion("Bar"));
        assertThat(name.getPortions().get(2)).isEqualTo(new Portion("Baz"));
        assertThat(name.getPortions().get(3)).isEqualTo(new Portion("Bar"));
        assertThat(name.getPortions().size()).isEqualTo(4);
    }

    @Test
    public final void givenSimpleName_WhenMatchSameName_ShouldReturnTrue() {
        final String string = "Foo";
        final String pattern = "Foo";
        final CamelCaseName name = new CamelCaseName(string);

        assertThat(name.matches(pattern)).isTrue();
    }

    @Test
    public final void givenSimpleName_WhenMatchDifferentName_ShouldReturnFalse() {
        final String string = "Foo";
        final String pattern = "Bar";
        final CamelCaseName name = new CamelCaseName(string);
        assertThat(name.matches(pattern)).isFalse();
    }

    @Test
    public final void givenSimpleName_WhenMatchFirstCharacterInUppercase_ShouldReturnTrue() {
        final String string = "Foo";
        final String pattern = "F";
        final CamelCaseName name = new CamelCaseName(string);
        assertThat(name.matches(pattern)).isTrue();
    }

    @Test
    public final void givenPortion_WhenMatchPartOfName_ShouldReturnTrue() {
        final String string = "Foobar";
        final CamelCaseName name = new CamelCaseName(string);
        assertThat(name.matches("F")).isTrue();
        assertThat(name.matches("Fo")).isTrue();
        assertThat(name.matches("Foo")).isTrue();
        assertThat(name.matches("Foob")).isTrue();
        assertThat(name.matches("Fooba")).isTrue();
    }
}