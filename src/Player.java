public abstract class Player {
    private int playerindex ;
    private double currentHP ;
    private boolean isalive ;

    abstract public String getType() ;
//        return type;
//    }

     public void setType(String type) {
        this.type = type;
    }

    private String type;
    abstract public int getPlayerindex();
//    return playerindex;
//    }

    public void setPlayerindex(int playerindex) {
        this.playerindex = playerindex;
    }


    abstract public double getCurrentHP() ;
//    return currentHP;
//    }

    abstract public void setCurrentHP(double currentHP);
//        this.currentHP = currentHP;
//    }

    abstract public boolean isIsalive() ;
//    return isalive;
//    }

    abstract public void setIsalive(boolean isalive) ;
//        this.isalive = isalive;
//    }
}
