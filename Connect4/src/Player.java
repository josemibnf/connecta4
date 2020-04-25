public class Player {

    private static final char PLAYER1 = '1';
    private static final char PLAYER2 = '2';

    final char id;

    private Player(char id) {
        this.id=id;	
    }
    
    public static Player player1() {
	return new Player('1');
    }

    public static Player player2() {
        return new Player('2');
    }

    public boolean isEqualTo(Player other) {
       try{
           //El this.id solo puede valer '1' o '2' ya que el constructor es privado.
           //Hay que tenerlo en cuenta en el resto del código.
           return this.id == other.id;    
        }catch(NullPointerException e){
            return false;
        }
        
    }
    
    public boolean isPlayer1() {
        return this.id == PLAYER1;
    }

    public boolean isPlayer2() {
        return this.id == PLAYER2;
    }

    // Only for testing

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    public static Player fromChar(char c) {
        switch (c) {
            case PLAYER1 : return player1();
            case PLAYER2 : return player2();
            default      : return null;
        }
    }
}
