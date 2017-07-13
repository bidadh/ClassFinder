package com.example.searchnames.model;

/**
 * @author Arthur Kazemi<bidadh@gmail.com>
 * @date 13/7/17 11:09 AM
 */
class Portion {
    private final String name;

    Portion(String name) {
        this.name = name;
    }

    boolean matches(String pattern) {
        return name.startsWith(pattern);
    }
}
