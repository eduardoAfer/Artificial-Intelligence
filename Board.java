import java.util.LinkedList;
import java.util.Comparator;

public class Board{
    
    private int[][] board;
    static private int size;
    private int blankx;
    private int blanky;
    private String path;
    public static int countNodes = 0;
    public static Board end; //Estado final
    

    Board(int start[], String path) {
        size = 4;
        board = new int[size][size];
        countNodes++;

        int x=0;
        this.path = path;
        

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                board[i][j] = start[x];
                x++;

                if(board[i][j] == 0){
                    blankx=j;
                    blanky=i;
                }
            }
        }
    }

    int[] toVector(){

        int vec[] = new int[size*size];
        int x=0;

        for(int i = 0; i < size; i++){
            for (int j = 0; j < size; j++) {
                vec[x] = board[i][j];
                x++;
            }
        }
        return vec;
    }

    int countInversions(){
        
        int invs = 0;
        int vec[]=toVector();
        
        for (int i = 0; i < vec.length; i++) {
            if(vec[i]==0) i++;
            for (int j = i+1; j < vec.length; j++) {
                if(vec[j]==0) j++;
                if(j>=vec.length) break; //para se vec[n-1]==0, senao tentar ler vec[n] (out of bounds)
                if(vec[i] > vec[j]) invs++;
            }
        }
        return invs;
    }

    boolean isGoal(){
        for(int i = 0; i < size; i++){
            for (int j = 0; j < size; j++) {
                if(board[i][j] != end.board[i][j]) return false;
            }
        }
        return true;
    }

    void printBoard(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("|" + board[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println();
    }

    //retorn true se quadrado branco esta em linha par contando a partir da ultima linha
    boolean blankOnEvenRow(){
        boolean ret = false;
        if((size-blanky)%2==0) return true;
        return ret;
    }
    
    int cost(){
        return path.split("-").length -1; // ver se da certo pois temos um primeiro "-", que nao conta" 
    }
    

    Boolean checkSolvable(){
        int invs = countInversions();

        //(Inv%2 == 0) == (blankRow%2 == 1)
        if((invs%2==0) == (blanky%2==1))
            return true;
        
        return false;
    }

    Board up(){
        Board newBoard = new Board(toVector(), this.path + "-up");
        int aux = newBoard.board[blanky][blankx];
        newBoard.board[blanky][blankx] = newBoard.board[blanky-1][blankx];
        newBoard.board[blanky-1][blankx] = aux;
        newBoard.blanky--;
        return newBoard;
    }

    Board down(){
        Board newBoard = new Board(toVector(), this.path + "-down");
        int aux = newBoard.board[blanky][blankx];
        newBoard.board[blanky][blankx] = newBoard.board[blanky+1][blankx];
        newBoard.board[blanky+1][blankx] = aux;
        newBoard.blanky++;
        return newBoard;
    }

    Board left(){
        Board newBoard = new Board(toVector(), this.path + "-left");
        int aux = newBoard.board[blanky][blankx];
        newBoard.board[blanky][blankx] = newBoard.board[blanky][blankx-1];
        newBoard.board[blanky][blankx-1] = aux;
        newBoard.blankx--;
        return newBoard;
    }

    Board right(){
        Board newBoard = new Board(toVector(), this.path + "-right");
        int aux = newBoard.board[blanky][blankx];
        newBoard.board[blanky][blankx] = newBoard.board[blanky][blankx+1];
        newBoard.board[blanky][blankx+1] = aux;
        newBoard.blankx++;
        return newBoard;
    }
    
     LinkedList<Board> generateChildren(){
        LinkedList<Board> children = new LinkedList<Board>();
        if(blanky>0      && !getLastMove().equals("down"))    children.add(up());
        if(blanky<size-1 && !getLastMove().equals("up"))  children.add(down());
        if(blankx>0      && !getLastMove().equals("right") ) children.add(left());
        if(blankx<size-1 && !getLastMove().equals("left")) children.add(right());
        return children;
    }


    String getPath(){
        return path;
    }

    //retorna ultimo movimento feito
    String getLastMove(){
        //String[] moves = path.split("-");
        String lastMove = path.substring(path.lastIndexOf('-') + 1);
        return lastMove;
    }

    int heuristica1(){
        int h = 0;
        for(int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if(board[i][j] != end.board[i][j]) h++;
            }
        }
        return h;
    }

    int[] find(int t, int[][] b){
        //encontra o valor t na matriz e retorna a posicao num vetor
        int r[] = new int[2];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {                    
                    if(b[i][j]==t){
                        r[0] = i; //posx
                        r[1] = j; //posy
                    }
                }
            }

        return r;
    }

    int manhattan(){
        int r = 0;

        int pos[]; 
        int endpos[];

        for (int i = 0; i < 15; i++) {
            pos = find(i+1, board);
            endpos = find(i+1,end.board);

            int x = Math.abs(endpos[0]-pos[0]);
            int y = Math.abs(endpos[1]-pos[1]);

            r += x+y;
        }
        
        return r;
    }
  
    public static Comparator<Board> heuristic1Compare = new Comparator<Board>() {
	    public int compare(Board b1, Board b2) {

            if(b1.heuristica1() > b2.heuristica1())
                return 1;
            if(b1.heuristica1() < b2.heuristica1())
                return -1;
            else
                return 0;
	    }

	}; 

    public static Comparator<Board> manhattanCompare = new Comparator<Board>() {
	    public int compare(Board b1, Board b2) {

            if(b1.manhattan() > b2.manhattan())
                return 1;
            if(b1.manhattan() < b2.manhattan())
                return -1;
            else
                return 0;
            
	    };
	};
    
}
