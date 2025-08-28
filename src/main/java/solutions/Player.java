package solutions;;

/**
 * Player
 */
public class Player {
    private String name;
    private Token token;

    public Player(String name, Token token) {
        this.name = name;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public Token getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", token=" + token.getColor() +
                '}';
    }
}
