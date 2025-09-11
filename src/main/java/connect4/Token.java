package connect4;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Token {
    private final String color;

    public Token(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return color.toUpperCase();
    }
}
