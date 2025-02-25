package model;

public enum Rating{
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);
    
    private final int val;

    Rating(int val){
        this.val = val;
    }

    public int getValue(){
        return val;
    }
    
    @Override
    public String toString(){
        return String.valueOf(val);
    }
}