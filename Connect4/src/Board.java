
import java.util.StringTokenizer;

public class Board {

    private final int numRows;
    private final int numColumns;
    private final Player[][] cells;

    public Board(int numRows, int numColumns) {
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.cells = new Player[numRows][numColumns];
    }

    public boolean canPlayColumn(int column) {
        boolean canPlay = false;   //si encuentra una casilla libre cambiara a true.
        int i=0; //contador
        while(column>=0 && column<this.numColumns && i<this.numRows && !canPlay){
            if(!Player.player1().isEqualTo(this.cells[i][column]) && !Player.player2().isEqualTo(this.cells[i][column])){
                canPlay=true;
            }
            i++;
        }
        return canPlay;
    }

    public boolean hasValidMoves() {
       boolean canPlay = false;     //si encuentra una columna en la que se puede jugar, cambiará a true.
       for(int i=0; i<this.numColumns; i++){
           if(this.canPlayColumn(i)){
               return true;
           }
       }
       return false;
    }

    public Position play(int column, Player player) {
        int row = this.lastEmptyRow(column);
        if(player.isEqualTo(Player.player1()) && row!=-1){      // el jugador es player1 y se puede jugar.
            this.cells[row][column]=Player.player1();
            return new Position(row,column);
        }else if(player.isEqualTo(Player.player2()) && row!=-1){  //el jugador es player2 y se puede jugar.
            this.cells[row][column]=Player.player2();
            return new Position(row,column);
        }  
        return null;   //la columna no es jugable.
    }

    public int lastEmptyRow(int column) {
        if(column >=0 && column < numColumns){
            for(int i=numRows-1; i>=0; i--){
                if(!Player.player1().isEqualTo(this.cells[i][column]) && !Player.player2().isEqualTo(this.cells[i][column])){  //casilla vacia.
                    return i;
                }
            }  
        }
        return -1;    //no hay casillas vacias.
    }

    public int maxConnected(Position position) {
        return Math.max(Math.max(cuenta_diagonal(position),cuenta_diagonal_invert(position)), Math.max(cuenta_horizontal(position), cuenta_vertical(position)));
    }
    
    private int cuenta_horizontal(Position position){ 
        int c = 0; //total de celdas conectadas. 
        //celdas a la izq y la celda position.
        int i=position.getColumn(); //variacion de las columnas.
        while(i<numColumns && this.cells[position.getRow()][position.getColumn()].isEqualTo(this.cells[position.getRow()][i])){
            c++;
            i++;
        }
        //celdas a la derecha.
        i = position.getColumn() -1;
        while(i>=0 && this.cells[position.getRow()][position.getColumn()].isEqualTo(this.cells[position.getRow()][i])){
            c++;
            i--; 
        } 
        return c;
    }
    
    
    private int cuenta_vertical(Position position){
        int c = 0; //total de celdas conectadas. 
        //celdas hacia abajo y la celda position.
        int j=position.getRow();   //variacion de las filas.
        while(j<numRows && this.cells[position.getRow()][position.getColumn()].isEqualTo(this.cells[j][position.getColumn()])){
            c++;
            j++;
        }
        //celdas hacia arriba.
        j = position.getRow() -1;
        while(j>=0 && this.cells[position.getRow()][position.getColumn()].isEqualTo(this.cells[j][position.getColumn()])){
            c++;
            j--;
        } 
        return c;
    }
    
    private int cuenta_diagonal(Position position){
        int c = 0;//total de celdas conectadas.
        //celdas hacia abajo/derecha y la celda position.
        int j=position.getRow();   //variacion de las filas.
        int i=position.getColumn(); //variacion de las columnas.
        while(i<numColumns && j<numRows && this.cells[position.getRow()][position.getColumn()].isEqualTo(this.cells[j][i])){
            c++;
            j++;
            i++;
        }
        //celdas hacia arriba/izquierda.
        j = position.getRow() -1;
        i = position.getColumn() -1;
        while(i>=0 && j>=0 && this.cells[position.getRow()][position.getColumn()].isEqualTo(this.cells[j][i])){
            c++;
            j--;
            i--;
        } 
        return c;
    }
    
     private int cuenta_diagonal_invert(Position position){
        int c = 0;//total de celdas conectadas.
        //celdas hacia abajo/izquierda y la celda position.
        int j=position.getRow();   //variacion de las filas.
        int i=position.getColumn(); //variacion de las columnas.
        while(i>=0 && j<numRows && this.cells[position.getRow()][position.getColumn()].isEqualTo(this.cells[j][i])){
            c++;
            j++;
            i--;
        }
        //celdas hacia arriba/derecha.
        j = position.getRow() -1;
        i = position.getColumn() +1;
        while(i<numColumns && j>=0 && this.cells[position.getRow()][position.getColumn()].isEqualTo(this.cells[j][i])){
            c++;
            j--;
            i++;
        } 
        return c;
    }
    
    
    // Only for testing

    public int loadBoard(String str) {
        // Does not check if it corresponds to a "real" game
        StringTokenizer st = new StringTokenizer(str, "\n");
        int nonEmpty = 0;
        int row = 0;
        while (st.hasMoreTokens()) {
            String rowChars = st.nextToken();
            for (int column = 0; column < rowChars.length(); column++) {
                Player player = Player.fromChar(rowChars.charAt(column));
                if (player != null) {
                    nonEmpty += 1;
                }
                cells[row][column] = player;
            }
            row += 1;
        }
        return nonEmpty;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(42);
        for (int row = 0; row < numRows; row++) {
            for (int column = 0; column < numColumns; column++) {
                Player player = cells[row][column];
                sb.append(player != null ? player.toString() : "·");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
