package spw4.connectfour;

public enum Player {
    none('.'),
    yellow('y'),
    red('r');

    private final char value;

    Player(char r) {
        this.value = r;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
