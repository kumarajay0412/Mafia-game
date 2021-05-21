import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

public class working {
    public static void main(String[] args) throws IOException {
        Random rand  = new Random();
        Reader.init(System.in);
        System.out.println("Welcome To Mafia");
        System.out.println("Enter Number of Players = ");
        int n = Reader.nextInt();

        System.out.println("Choose a Character\n" +
                "1) Mafia\n" +
                "2) Detective\n" +
                "3) Healer\n" +
                "4) Commoner\n" +
                "5) Assign Randomly");

        Game game = new Game(n);
        int choose = Reader.nextInt();
        if(choose == 5)
            game.assign(rand.nextInt(4)+1,0);
        else
            game.assign(choose,0);
        game.assignall();
        System.out.println("You're Player 1");

        if(game.getAllplayers().get(0) instanceof Mafia) System.out.println("You're Mafia");
        else if(game.getAllplayers().get(0) instanceof Detective) System.out.println("You're Detective");
        else if(game.getAllplayers().get(0) instanceof Healer) System.out.println("You're Healer");
        else System.out.println("You're Commoner");


        // Play rounds
        for(int i = 0;i<Integer.MAX_VALUE;i++){
//            for(int j = 0;j<game.getAllplayers().size();j++){
//                if(game.getAllplayers().get(j).isIsalive())
//                    System.out.print((game.getAllplayers().get(j).getPlayerindex()+1) + " " + game.getAllplayers().get(j).getType() + "  ");
//            }
            System.out.println();
            System.out.println("ROUND "+(i+1));
            game.play();
            int mafcnt = 0;
            int alive = 0;
            for(int j=0;j<game.getAllplayers().size();j++){
                if(game.getAllplayers().get(j).getType().equals("Mafia") && game.getAllplayers().get(j).isIsalive())
                    mafcnt++;
                if(game.getAllplayers().get(j).isIsalive())
                    alive++;
            }
            System.out.println("Round Ends  ----------- \n");
            if(mafcnt == 0){
                System.out.println(" \n === GAME OVER ===");
                System.out.println("Mafia lose");
                break;
            }
            if((2*mafcnt)>=alive){
                System.out.println(" === GAME OVER ===");
                System.out.println("Mafia wins");
                break;
            }
        }

        //game ends
        System.out.println("Characters - ");
        ArrayList<Player> temp = game.getAllplayers();
        for(int i=0;i<temp.size();i++){
            if(temp.get(i) instanceof Mafia)
                System.out.print("Player" + i + " ");
        }
        System.out.println(" - are Mafias");
        for(int i=0;i<temp.size();i++){
            if(temp.get(i) instanceof Detective)
                System.out.print("Player"+i+" ");
        }
        System.out.println(" - are Detectives");
        for(int i=0;i<temp.size();i++){
            if(temp.get(i) instanceof Healer)
                System.out.print("Player"+i+" ");
        }
        System.out.println(" - are Healers");
        for(int i=0;i<temp.size();i++){
            if(temp.get(i) instanceof Commoner)
                System.out.print("Player"+i+" ");
        }
        System.out.println(" -- are Commoner");

    }

    static class Reader {
        static BufferedReader reader;
        static StringTokenizer tokenizer;

        static void init(InputStream in) {
            reader = new BufferedReader(
                    new InputStreamReader(System.in) );
            tokenizer = new StringTokenizer("");
        }

        static String next() throws IOException {
            while ( ! tokenizer.hasMoreTokens() ) {
                tokenizer = new StringTokenizer(
                        reader.readLine() );
            }
            return tokenizer.nextToken();
        }

        static int nextInt() throws IOException {
            return Integer.parseInt( next() );
        }

        static double nextDouble() throws IOException {
            return Double.parseDouble( next() );
        }
    }

}
