public class User <Player>{
    private Player type ;
    private boolean isalive = true;
    private int index = 0;
    public Player getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }

    public boolean isIsalive() {
        return isalive;
    }

    public void setIsalive(boolean isalive) {
        this.isalive = isalive;
    }

    public void setType(Player type) {
        this.type = type;
    }
}
