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
    public final void givenSimpleName_WhenMatchPartOfName_ShouldReturnTrue() {
        final String string = "Foobar";
        final CamelCaseName name = new CamelCaseName(string);
        assertThat(name.matches("F")).isTrue();
        assertThat(name.matches("Fo")).isTrue();
        assertThat(name.matches("Foo")).isTrue();
        assertThat(name.matches("Foob")).isTrue();
        assertThat(name.matches("Fooba")).isTrue();
    }

    @Test
    public final void givenName_WhenMatchPatternWithCorrectOrder_ShouldReturnTrue() {
        final String string = "FooBarBazBar";
        final CamelCaseName name = new CamelCaseName(string);
        assertThat(name.matches("FooBar")).isTrue();
        assertThat(name.matches("FoBarBB")).isTrue();
        assertThat(name.matches("FoBarBBar")).isTrue();
        assertThat(name.matches("FooBarBazBar")).isTrue();
        assertThat(name.matches("FoBaBaBa")).isTrue();
        assertThat(name.matches("FoBar")).isTrue();
        assertThat(name.matches("FoBaz")).isTrue();
        assertThat(name.matches("FooBazBar")).isTrue();
        assertThat(name.matches("FBBB")).isTrue();
    }

    @Test
    public final void givenName_WhenMatchInvalidPattern_ShouldReturnFalse() {
        final String string = "FooBarBazBar";
        final CamelCaseName name = new CamelCaseName(string);

        assertThat(name.matches("FoBz")).isFalse();
        assertThat(name.matches("BFBB")).isFalse();
        assertThat(name.matches("FoBarBBarz")).isFalse();
    }

    @Test
    public final void givenName_WhenMatchPatternInInvalidOrder_ShouldReturnFalse() {
        final String string = "FooBarBazCat";
        final CamelCaseName name = new CamelCaseName(string);

        assertThat(name.matches("FooCatBarBaz")).isFalse();
        assertThat(name.matches("FCBB")).isFalse();
        assertThat(name.matches("FBCB")).isFalse();
        assertThat(name.matches("FoCaBaBz")).isFalse();
    }

    @Test(expected = IllegalArgumentException.class)
    public final void givenName_WhenMatchLowercasePattern_ShouldThrowException() {
        final String string = "FooBarBazCat";
        final CamelCaseName name = new CamelCaseName(string);

        name.matches("foobar");
    }

    @Test
    public final void givenName_whenPatternEndsWithASpace_ShouldMatchTheLastWord() {
        final String string = "FooBarBazCat";
        final CamelCaseName name = new CamelCaseName(string);

        assertThat(name.matches("FooBarBazCa ")).isFalse();
        assertThat(name.matches("FooBarBaz ")).isFalse();
        assertThat(name.matches("FooBarCat ")).isTrue();
    }
}