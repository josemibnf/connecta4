public class Position {

    private final int row;
    private final int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }
    
    public int getRow() {
       return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public Position move(Direction direction) {
        return new Position(this.getRow() + direction.getChangeInRow(), this.getColumn() + direction.getChangeInColumn());
    }

    public boolean isEqualTo(Position other) {
        return (this.getRow() == other.getRow() && this.getColumn() == other.getColumn());
    }

    public static int pathLength(Position pos1, Position pos2) {
        //Tambien se podria haber puesto el "if de diagonales" con el "if de filas".
        if(pos1.getColumn()==pos2.getColumn() || Math.abs(pos1.getRow()-pos2.getRow())==Math.abs(pos1.getColumn()-pos2.getColumn())){    //misma columna o misma diagonal.
            //restar getRows
            return Math.abs( pos1.getRow() - pos2.getRow()) +1;
        }
        else if(pos1.getRow() == pos2.getRow()){     //misma linea
            //restar getColumns
            return Math.abs(pos1.getColumn() - pos2.getColumn()) +1;
        }
        return -1;
    }
}
