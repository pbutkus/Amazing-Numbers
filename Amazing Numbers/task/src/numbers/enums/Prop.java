package numbers.enums;

public enum Prop {

    EVEN        (1),
    ODD         (1),
    BUZZ        (2),
    DUCK        (3),
    SPY         (3),
    PALINDROMIC (4),
    GAPFUL      (5),
    SUNNY       (6),
    SQUARE      (6),
    JUMPING     (7),
    SAD         (8),
    HAPPY       (8);

    private int code;

    Prop(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public void negateCode() {
        this.code *= -1;
    }

}
