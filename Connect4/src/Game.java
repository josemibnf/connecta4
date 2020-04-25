public class Game {

    private final Board board;
    private final int toWin;

    private Status status;

    public Game(int rows, int columns, int toWin) {
        this.board = new Board(rows, columns);
        this.toWin = toWin;
        this.status = Status.PLAYER1_PLAYS; //empieza jugando player1
    }

    public Status getStatus() {
        return status;
    }

    public boolean canPlayColumn(int column) {
        return this.board.canPlayColumn(column);
    }

    public Move play(int column) {
        // !isFinished
        if(this.status == Status.PLAYER1_PLAYS){  
            if(this.board.maxConnected(this.board.play(column, Player.player1()))>=this.toWin){ 
                this.status=Status.PLAYER1_WINS; 
            }else if(!this.hasValidMoves()){ 
                this.status = Status.DRAW;
            }else{                           
                this.status = Status.PLAYER2_PLAYS;
            }
            return new Move(Player.player1(), this.board.play(column, Player.player1()));
        
        }else{                                   
            if(this.board.maxConnected(this.board.play(column, Player.player2()))>=this.toWin){
                this.status=Status.PLAYER2_WINS;
            }else if(!this.hasValidMoves()){
                this.status = Status.DRAW;
            }else{
                this.status = Status.PLAYER1_PLAYS;
            }
            return new Move(Player.player2(), this.board.play(column, Player.player2()));
        }
    }

    public boolean isFinished() {
        return (this.status == Status.PLAYER1_WINS || this.status == Status.PLAYER2_WINS || this.status == Status.DRAW);
    }
    
    private boolean hasValidMoves(){  //retorna True si hay espacios para seguir jugando. False si no.
        return this.board.hasValidMoves();
    }
    
    // Only for testing

    public void loadGame(String str) {
        // Does not check if it corresponds to a "real" game
        int played = board.loadBoard(str);
        status = played % 2 == 0
                ? Status.PLAYER1_PLAYS
                : Status.PLAYER2_PLAYS;
    }
    
    public String toString() {
        return this.board.toString();
    }
}
