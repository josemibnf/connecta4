public class Geometry {

    private final int windowHeight;
    private final int windowWidth;
    private final int displayRows;
    private final int displayColumns;
    private final double padding;

    public Geometry(int windowHeight, int windowWidth, int displayRows, int displayColumns, double padding) {
        this.windowHeight = windowHeight;
	this.windowWidth = windowWidth;
	this.displayRows = displayRows;
	this.displayColumns = displayColumns;
	this.padding = padding;
    }

    public double getRowHeight() {
        return this.windowHeight/this.displayRows;
    }

    public double getColumnWidth() {
        return this.windowWidth/this.displayColumns;
    }

    public double getDiskHeight() {
        return this.getRowHeight()*(1 - padding);
    }

    public double getDiskWidth() {
       return this.getColumnWidth()*(1 - padding);
    }

    public double columnToCenterX(int column) {
		return this.getColumnWidth()/2 + column*this.getColumnWidth();  //tmb se podria sacar factor comun, pero se ve mas claro asi.
    }

    public double rowToCenterY(int row) {
        return this.getRowHeight()/2 + row*this.getRowHeight();
    }

    public double columnToTopLeftX(int column) {
        return (this.padding/2) *this.getColumnWidth() +column*this.getColumnWidth();
    }

    public double rowToTopLeftY(int row) {
        return (this.padding/2) *this.getRowHeight() + row*this.getRowHeight();
    }

    public int xToColumn(int x) {
        for(int i=0; i<this.displayColumns; i++){
            if(x>this.getColumnWidth()*i && x<this.getColumnWidth()*(i+1)){
                return i;
            }
        }
        return -1; //si x está fuera de display.
    }
}
