public class OffByN implements CharacterComparator {
    private int difference = 1;
    public OffByN(int n){
        difference = n;
    }
    @Override
    public boolean equalChars(char x, char y){
        if (java.lang.Math.abs(x - y) == difference){
            return true;
        }
        return false;
    }
}
