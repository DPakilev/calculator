public enum RomanNumerals {
    I(1), IV(4), V(5), IX(9), X(10), L(50), C(100);

    private int value;

    RomanNumerals(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
