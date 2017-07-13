package com.example.searchnames.util;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Arthur Kazemi<bidadh@gmail.com>
 * @date 13/7/17 1:04 PM
 */
public class StringUtilsTest {
    @Test
    public final void givenSimpleName_whenSplitCamelCase_shouldReturnNamesList() {
        final String name = "Foo";
        final List<String> names = StringUtils.splitCamelCase(name);

        assertThat(names).isNotNull();
    }

    @Test
    public final void givenSimpleName_whenSplitCamelCase_shouldContainNameInResult() {
        final String name = "Foo";
        final List<String> names = StringUtils.splitCamelCase(name);

        assertThat(names.contains(name)).isTrue();
    }

    @Test
    public final void givenMultiPortionName_whenSplitCamelCase_shouldNotContainNameInResult() {
        final String name = "FooBar";
        final List<String> names = StringUtils.splitCamelCase(name);

        assertThat(names.contains(name)).isFalse();
    }

    @Test
    public final void givenMultiPortionName_whenSplitCamelCase_shouldContainAllPortionsInResult() {
        final String name = "FooBarBaz";
        final List<String> names = StringUtils.splitCamelCase(name);

        assertThat(names.size()).isEqualTo(3);
        assertThat(names.contains("Foo")).isTrue();
        assertThat(names.contains("Bar")).isTrue();
        assertThat(names.contains("Baz")).isTrue();
    }

    @Test
    public final void givenMultiOneCharacterPortionName_whenSplitCamelCase_shouldContainAllPortionsInResult() {
        final String name = "FBB";
        final List<String> names = StringUtils.splitCamelCase(name);

        assertThat(names.size()).isEqualTo(3);
        assertThat(names.contains("F")).isTrue();
        assertThat(names.contains("B")).isTrue();
        assertThat(names.contains("B")).isTrue();
    }

    @Test(expected = IllegalArgumentException.class)
    public final void givenEmptyName_whenSplitCamelCase_ShouldThrowException() {
        StringUtils.splitCamelCase(" ");
    }

    @Test(expected = IllegalArgumentException.class)
    public final void givenLowercaseName_whenSplitCamelCase_ShouldThrowException() {
        final String name = "foo";
        StringUtils.splitCamelCase(name);
    }
}