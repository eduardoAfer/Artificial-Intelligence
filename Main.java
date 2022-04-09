import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int[] startVec = new int[16];
        int[] endVec = new int[16];

        for (int i = 0; i < 16; i++) {
            startVec[i] = in.nextInt();
        }

        for (int i = 0; i < 16; i++) {
            endVec[i] = in.nextInt();
        }

        int search = Integer.parseInt(args[0]);

        /* inicializa o primeiro no, colocando uma copia do board final na var estatica endState  */
        Board startState = new Board(startVec, "");
        Board endState = new Board(endVec, "");
        Board.end = endState;

        switch (search) {
            case 0:
                System.out.println("#########BFS#######");
                System.out.println(Search.BFS(startState));
                break;
            case 1:
                System.out.println("#########IDFS#######");
                System.out.println(Search.iterativeDFSearch(startState));
                break;
            case 2:
                System.out.println("#########A* - # de blocos#######");
                System.out.println(Search.aStarSearch(startState));
                break;
            case 3:
                System.out.println("#########Greedy - Manhattan#######");
                System.out.println(Search.greedySearch(startState));
                break;
            case 4:
                System.out.println("#########DFS#######");
                System.out.println(Search.DFS(startState));
                break;
            default:
                System.out.println("Nao selecionou metodo de busca. Consultar readme.txt");
                break;
        }

        in.close();
        
    }
    
     
    
}
