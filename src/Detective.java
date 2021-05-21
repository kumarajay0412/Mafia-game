public class Detective extends Player{
    private int playerindex ;
    private double currentHP ;
    private boolean isalive ;

    public int getPlayerindex() {
        return playerindex;
    }

    public double getCurrentHP() {
        return currentHP;
    }

    public void setIsalive(boolean isalive) {
        this.isalive = isalive;
    }

    public void setCurrentHP(double currentHP) {
        this.currentHP = currentHP;
    }

    public boolean isIsalive() {
        return isalive;
    }

    public String getType() {
        return "Detective";
    }

    public Detective(int playerindex,int currentHP,boolean isalive){
        this.playerindex = playerindex;
        this.currentHP = currentHP;
        this.isalive = isalive;
    }
}
