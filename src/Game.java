import java.io.IOException;
import java.util.*;

public class Game {
    private ArrayList<Player> allplayers = new ArrayList<>();
    private User<Player> userr = new User<>();
    private Random rand = new Random();
    int n;

    public Game (int n){
        this.n = n;
    }

    public ArrayList<Player> getAllplayers() {
        return allplayers;
    }

    //assign single player its Character
    public void assign(int choose,int ind){
        switch (choose)
        {
            case 1:
                this.allplayers.add(new Mafia(ind,2500,true));
                if(ind == 0)
                    userr.setType(allplayers.get(ind));
                break;
            case 2:
                this.allplayers.add(new Detective(ind,800,true));
                if(ind == 0)
                    userr.setType(allplayers.get(ind));
                break;
            case 3:
                this.allplayers.add(new Healer(ind,800,true));
                if(ind == 0)
                    userr.setType(allplayers.get(ind));
                break;
            case 4:
                this.allplayers.add(new Commoner(ind,1000,true));
                if(ind == 0)
                    userr.setType(allplayers.get(ind));
                break;
        }
    }


    // randomly assign remaining players their character
    public void assignall(){
        ArrayList<Integer> temp = new ArrayList<>();
        for(int i = 0;i< (allplayers.get(0) instanceof Mafia? n/5-1:n/5);i++){
            temp.add(1);
        }
        for(int i=0;i<(allplayers.get(0) instanceof Detective? n/5-1:n/5);i++){
            temp.add(2);
        }
        for(int i=0;i<Math.max((allplayers.get(0) instanceof Healer? n/10-1:n/10),1);i++){
            temp.add(3);
        }
        for(int i = temp.size();i<n-1;i++){
            temp.add(4);
        }
        Collections.shuffle(temp);
        for(int i=1;i<n;i++){
            assign(temp.get(i-1),i);
        }
    }

    //display details at start of round
    public void startdisplay(){
        System.out.println("You're Player 1");
        if(userr.getType().getType().equals("Mafia")) {
            System.out.print("You are Mafia and Others Mafias are - ");
            for (int i = 1; i < allplayers.size(); i++) {
                if (allplayers.get(i).getType().equals("Mafia"))
                    System.out.print("Player" + (allplayers.get(i).getPlayerindex()+1) + " ");
            }
        }
        else if(userr.getType().getType().equals("Healer")){
            System.out.print("You are Healer and Others Healers are - ");
            for (int i = 1; i < allplayers.size(); i++) {
                if (allplayers.get(i).getType().equals("Healer"))
                    System.out.print("Player" + (allplayers.get(i).getPlayerindex()+1) + " ");
            }
        }
        else if(userr.getType().getType().equals("Detective")){
            System.out.print("You are Detective and Others Detective are - ");
            for (int i = 1; i < allplayers.size(); i++) {
                if (allplayers.get(i).getType().equals("Detective"))
                    System.out.print("Player" + (allplayers.get(i).getPlayerindex()+1) + " ");
            }
        }
        else{
            System.out.print("You are Commoner ");
        }
        System.out.println();
    }

    // Play a Round
    public void play() throws IOException {
        int tokill;
        int toheal;
        int tochck;
        int tovote = -1;
        int count = 0;

        for(int i=0;i<allplayers.size();i++){
            if(allplayers.get(i).isIsalive())
                count++;
        }

        System.out.print(count + " Players are alive - ");
        for (int i = 0; i < allplayers.size(); i++) {
            if (allplayers.get(i).isIsalive())
                System.out.print("Player" + (allplayers.get(i).getPlayerindex()+1) + " ");
        }
        System.out.println();

        //Mafia
        if (userr.getType().getType().equals("Mafia") && userr.isIsalive()) {
            System.out.println("choose Plaayer to kill between Player1 to Player" + n +" - ");
            tokill = working.Reader.nextInt()-1;
            while (allplayers.get(tokill).getType().equals("Mafia") || !allplayers.get(tokill).isIsalive()) {
                System.out.println("You cannot choose a mafia to kill, choose again between Player1 to Player" + n +" - ");
                tokill = working.Reader.nextInt()-1;
            }
        } else {
            tokill = rand.nextInt(n);
            while (allplayers.get(tokill).getType().equals("Mafia") || !allplayers.get(tokill).isIsalive())
                tokill = rand.nextInt(n);
            System.out.println("Mafias have chosen their target");
        }

        //detective
        if (userr.getType().getType().equals("Detective") && userr.isIsalive()) {
            System.out.println("Choose a player a test between Player1 to Player" + n +" - ");
            tochck = working.Reader.nextInt()-1;
            while (allplayers.get(tochck).getType().equals("Detective") || !allplayers.get(tochck).isIsalive()) {
                System.out.println("You cannot choose a Detective, Choose again between Player1 to Player" + n +" - ");
                tochck = working.Reader.nextInt()-1;
            }
        } else {
            tochck = rand.nextInt(n);
            while (allplayers.get(tochck).getType().equals("Detective") || !allplayers.get(tochck).isIsalive())
                tochck = rand.nextInt(n);
            System.out.println("Detective have choosen the Player to check");
        }

        //Healers
        if (userr.getType().getType().equals("Healer") && userr.isIsalive()) {
            System.out.println("Choose a Player to heal between Player1 to Player" + n +" - ");
            toheal = working.Reader.nextInt()-1;
            while(!allplayers.get(toheal).isIsalive()){
                System.out.println("You cannot heal dead players, choose again between Player1 to Player" + n +" - ");
                toheal = working.Reader.nextInt()-1;
            }
        } else {
            toheal = rand.nextInt(n);
            while(!allplayers.get(toheal).isIsalive())
                toheal = rand.nextInt(n);
            System.out.println("Healer has chosen someone to heal");
        }

        System.out.print("Player " + (allplayers.get(tochck).getPlayerindex()+1));
        System.out.println(allplayers.get(tochck).getType().equals("Mafia") ? " is mafia" : " is not mafia");

        allplayers.get(toheal).setCurrentHP(allplayers.get(toheal).getCurrentHP() + 500);

        int totalmafiapower = 0;
        for (int i = 0; i < allplayers.size(); i++) {
            if (allplayers.get(i).getType().equals("Mafia"))
                totalmafiapower += allplayers.get(i).getCurrentHP();
        }

        // kill is allowed , kill and distribute damage among Mafias
        if (totalmafiapower >= allplayers.get(tokill).getCurrentHP()) {
            double todec = allplayers.get(tokill).getCurrentHP();
            double rem = 0;
            int cnt = 0;
            for (int i = 0; i < allplayers.size(); i++) {
                if (allplayers.get(i).getType().equals("Mafia")) {
                    if (allplayers.get(i).getCurrentHP() >= todec)
                        allplayers.get(i).setCurrentHP(allplayers.get(i).getCurrentHP() - todec);
                    else {
                        rem += todec - allplayers.get(i).getCurrentHP();
                        allplayers.get(i).setCurrentHP(0);
                        cnt++;
                    }
                }
            }

            while (cnt != 0) {
                todec = rem / cnt;
                rem = 0;
                cnt = 0;
                for (int i = 0; i < allplayers.size(); i++) {
                    if (allplayers.get(i).getType().equals("Mafia") && allplayers.get(i).getCurrentHP() != 0) {
                        if (allplayers.get(i).getCurrentHP() >= todec) {
                            allplayers.get(i).setCurrentHP(allplayers.get(i).getCurrentHP() - todec);
                        } else {
                            rem += rem - allplayers.get(i).getCurrentHP();
                            allplayers.get(i).setCurrentHP(0);
                            cnt++;
                        }
                    }
                }
            }

            System.out.println("Player" + (allplayers.get(tokill).getPlayerindex()+1)+ " has died");
            allplayers.get(tokill).setIsalive(false);
            if (allplayers.get(tokill).equals(userr.getType()))
                userr.setIsalive(false);
        } else {  // cannot kill the target
            System.out.println("No one get killed");
        }

        //checking if games end here or not
        int mafiacnt = 0;
        int alivecnt = 0;
        for(int i=0;i<n;i++){
            if(allplayers.get(i).isIsalive()){
                alivecnt++;
                if(allplayers.get(i) instanceof Mafia){
                    mafiacnt++;
                }
            }
        }
        if(mafiacnt*2 >= alivecnt || mafiacnt == 0) {
            return;
        }

        // detective caught mafia
        if (allplayers.get(tochck).getType().equals("Mafia")) {
            System.out.println("Player " + (allplayers.get(tochck).getPlayerindex()+1) + " has been voted out");
            allplayers.get(tochck).setIsalive(false);
        } else { //voting time bro

            /* tovote = rand.nextInt(n);
            while (!allplayers.get(tovote).isIsalive())
                tovote = rand.nextInt(n);
            allplayers.get(tovote).setIsalive(false);
            if(tovote == 0)
                userr.setIsalive(false);
            System.out.println("Player " + (allplayers.get(tovote).getPlayerindex()+1) + " is voted out"); */

            boolean flag = true;
            while(flag) {
                if (userr.isIsalive()) {
                    System.out.println("Choose the player to vote between Player1 to Player" + n +" - ");
                    tovote = working.Reader.nextInt()-1;
                    while (!allplayers.get(tovote).isIsalive()) {
                        System.out.println("You cannot vote dead player, Choose again between Player1 to Player" + n +" - ");
                        tovote = working.Reader.nextInt()-1;
                    }
                }

                int[] choosee = new int[n];
                choosee[0] = tovote;
                for(int i=0;i<n;i++){
                    int curr = rand.nextInt(n);
                    while(!allplayers.get(curr).isIsalive())
                        curr = rand.nextInt(n);
                    choosee[i] = curr;
                }
                Pair[] arr = new Pair[n];
                for(int i=0;i<n;i++){
                    arr[i] = new Pair(0,i+1);
                }
                for(int i=0;i<n;i++){
                    arr[choosee[i]].l++;
                }
                //uncomment to chck how voting works
//                for(int i=0;i<n;i++){
//                    System.out.println(arr[i].l + " " + arr[i].r);
//                }
//                System.out.println("====");
                Compare.compare(arr,n);
//                for(int i=0;i<n;i++){
//                    System.out.println(arr[i].l + " " + arr[i].r);
//                }
                //uncomment to chck how voting works
                if(arr[n-1].l!=arr[n-2].l){
                    flag = false;
                    if(arr[n-1].r == 1)
                        userr.setIsalive(false);
                    System.out.println("Player " + (arr[n-1].r) + " is voted out");
                    allplayers.get((int) (arr[n-1].r-1)).setIsalive(false);
                }
                else
                    System.out.println("voting tied , Vote again");
            }
        }
    }
}


class Pair {
    long l;
    long r;
    // Constructor
    public Pair(long l,long r)
    {
        this.l = l;
        this.r = r;
    }
}

// class to define user defined conparator
class Compare {

    static void compare(Pair[] arr, int n)
    {
        // Comparator to sort the pair according to second element
        Arrays.sort(arr, new Comparator<Pair>() {
            @Override public int compare(Pair p1, Pair p2)
            {
                if(p1.l==p2.l){
                    return (int) (p1.r-p2.r);
                }
                else
                    return (int) (p1.l - p2.l);
            }
        });
    }
}
