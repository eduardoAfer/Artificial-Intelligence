import java.util.LinkedList;

public class Search {
    
    static String success(int nivel, int nos, String path){
        System.out.println("Solução com " + nivel + " movimentos");
        System.out.println(nos + " nos expandidos");
        return path;
    }

    static String BFS(Board startState){
        //resets node count to run multiple searches
        startState.countNodes = 0;
        
        //if there is no solution
        if(!startState.checkSolvable()){
            return "Não existe solução";
        }
        LinkedList<Board> queue = new LinkedList<Board>();
        queue.add(startState);
    
        while(!queue.isEmpty()){
            Board current = queue.remove();
            if(current.isGoal()) {
                return success(
                    current.getPath().substring(1).split("-").length,
                    Board.countNodes,
                    current.getPath().substring(1)
                );
            }
            
            LinkedList <Board> children = current.generateChildren();

            while(!children.isEmpty())   
                queue.add(children.remove());
               
        }
        return "Não existe solução";
    } 

    static String DFS(Board startState){
        //resets node count to run multiple searches
        startState.countNodes = 0;
        
        //if there is no solution
        if(!startState.checkSolvable()){
            return "Não existe solução";
        }
        LinkedList<Board> queue = new LinkedList<Board>();
        queue.add(startState);
    
        while(!queue.isEmpty()){
            Board current = queue.remove();
            if(current.isGoal()) {
                return success(
                    current.getPath().substring(1).split("-").length,
                    Board.countNodes,
                    current.getPath().substring(1)
                );
            }
            
            LinkedList <Board> children = current.generateChildren();

            while(!children.isEmpty())   
                queue.addFirst(children.remove());
               
        }
        return "Não existe solução";
    } 

    static String iterativeDFSearch(Board startState){
        //resets node count to run multiple searches
        startState.countNodes = 0;
        if (!startState.checkSolvable()) {
           return "nao existe solucao";
       }
       LinkedList<Board> queue = new LinkedList<Board>();


       for(int nivel = 0; ; nivel++){
           queue.add(startState);

           while (!queue.isEmpty()) {
               Board current = queue.remove();
               if (current.isGoal()) {
                    return success(
                        current.getPath().substring(1).split("-").length,
                        Board.countNodes,
                        current.getPath().substring(1)
                    );
               }

               LinkedList<Board> children = current.generateChildren();

               while (!children.isEmpty()) {
                   if(children.getFirst().cost() <= nivel) queue.addFirst(children.remove());
                   else children.remove();
               }
           }
           nivel++;
        }
   }

    static String aStarSearch(Board startState){

        startState.countNodes = 0;
        if(!startState.checkSolvable()){
            return "Não existe solução";
        }
        LinkedList<Board> queue = new LinkedList<Board>();
        queue.add(startState);
    
        while(!queue.isEmpty()){
            Board current = queue.remove();
            if(current.isGoal()) {
                return success(
                    current.getPath().substring(1).split("-").length,
                    Board.countNodes,
                    current.getPath().substring(1)
                );
            }
            
            LinkedList <Board> children = current.generateChildren();
            children.sort(Board.heuristic1Compare);//heuristica 1

            while(!children.isEmpty()){
                
                queue.add(children.remove());
            }
            
        }
        return "Não existe solução";
    } 

    static String greedySearch(Board startState){

        startState.countNodes = 0;
        if(!startState.checkSolvable()){
            return "Não existe solução";
        }
        LinkedList<Board> queue = new LinkedList<Board>();
        queue.add(startState);
    
        while(!queue.isEmpty()){
            Board current = queue.remove();
            if(current.isGoal()) {
                return success(
                    current.getPath().substring(1).split("-").length,
                    Board.countNodes,
                    current.getPath().substring(1)
                );
            }
            
            LinkedList <Board> children = current.generateChildren();
            children.sort(Board.manhattanCompare);//Manhattan

            while(!children.isEmpty()){
                
                queue.add(children.remove());
            }
            
        }
        return "Não existe solução";
    } 
}
