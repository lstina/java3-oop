import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FifteenGame {
    private final int size;
    private final int[] tiles;
    private int emptyTileIndex;
    private final Random random = new Random();
    private static final int SHUFFLE_STEPS = 200;

    public FifteenGame(int size){
        this.size = size;
        this.tiles = new int[size * size];
        reset();
    }

    public int getSize() {
        return size;
    }

    public int[] getTiles() {
        return tiles;
    }

    /**
    * Återställer spelet till ursprungsläget (1-15, tom ruta sist)
     * Används när man startar ett nytt spel eller innan blandning
     * Loopen fyller arrayen med talen 1-15 och sätter sista positionen till 0
    **/
    public void reset() {
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = (i + 1) % tiles.length;
        }
        emptyTileIndex = tiles.length - 1;
    }

    /**
    * Kontrollerar om en vald bricka ligger brevid den tomma rutan
     * Räknar ut rad och kolumn för både brickan och den tomma rutan
     * Använder division för att få ut radnumret och modulus för att få kolumnnumret
     * Math.abs för att jämföra avståndet mellan brickan och tomma rutan
     * Returnerar true om de ligger direkt intill varandra
     **/
    public boolean isNextToEmpty(int tileIndex){
        int row = tileIndex / size;
        int col = tileIndex % size;
        int emptyRow = emptyTileIndex / size;
        int emptyCol = emptyTileIndex % size;

        boolean sameRow = (row == emptyRow) && (Math.abs(col - emptyCol) == 1);
        boolean sameCol = (col == emptyCol) && (Math.abs(row - emptyRow) == 1);

        if (sameRow || sameCol)
        return true;
        else
            return false;
    }

    /**
     *    Försöker flytta en vald bricka
     *    Kontrollerar först om brickan ligger brevid den tomma rutan
     *    Om det stämmer byter brickan och den tomma rutan plats med swap
     *    Uppdaterar vilken ruta som nu är tom
     *    Returnerar true om flytten lyckades
     */
    public boolean move(int tileIndex){
        if(!isNextToEmpty(tileIndex))
            return false;

        swap(tileIndex, emptyTileIndex);
        emptyTileIndex = tileIndex;
        return true;
    }

    // Startar ett nytt spel genom att blanda brickorna slumpmässigt (200 gånger)
    public void newGame() {
        shuffleTiles(SHUFFLE_STEPS);
    }

    /** Kontrollerar om spelaren har vunnit
    * Går igenom alla brickor och kontrollerar om de ligger i rätt ordning (1-15)
    * Sista rutan ska vara tom, 0, för att vara löst
    * Returnerar true om spelet är löst
     **/
    public boolean gameSolved() {
        for (int i = 0; i < tiles.length - 1; i++){
            if (tiles[i] != i + 1)
                return false;
        }
        // Kontrollerar att sista rutan är tom
        if (tiles[tiles.length - 1] == 0)
            return true;
        else
            return false;
    }

    /**
    * Hjälpmedtod som byter plats på två brickor i arrayen
    * Sparar först värdet på den första brickan  tillfälligt
    * sedan byts värdena mellan positionerna
    **/
    private void swap(int firstTileIndex, int secondTileIndex) {
        int temp = tiles[firstTileIndex];
        tiles[firstTileIndex] = tiles[secondTileIndex];
        tiles[secondTileIndex] = temp;
    }

    /**
    *Blandar brickorna genom att flytta den tomma rutan slumpmässigt flera gånger
    * Börjar med att återställa brädet till ett löst läge
    *Vid varje steg hämtas alla giltiga grannar till den tomma rutan
    * En slumpmässig granne väljs och byter plats med den tomma rutan
     **/
    private void shuffleTiles(int steps){
        reset();
        int previousEmptyTileIndex = -1;
        for (int step = 0; step < steps; step++){
            List<Integer> validNeighbors = new ArrayList<>();
            int emptyRow = emptyTileIndex / size, emptyColumn = emptyTileIndex % size;
            addIfValid(validNeighbors, emptyRow - 1, emptyColumn); //Upp
            addIfValid(validNeighbors, emptyRow + 1, emptyColumn); //Ner
            addIfValid(validNeighbors, emptyRow, emptyColumn - 1); //Vänster
            addIfValid(validNeighbors, emptyRow, emptyColumn + 1); //Höger

            if (previousEmptyTileIndex != -1) validNeighbors.remove(Integer.valueOf(previousEmptyTileIndex));
            int selectedNeighborIndex = validNeighbors.get(random.nextInt(validNeighbors.size()));
            swap(selectedNeighborIndex, emptyTileIndex);
            previousEmptyTileIndex = emptyTileIndex;
            emptyTileIndex = selectedNeighborIndex;
        }
    }

    /**
    *Tar emot listan där alla giltiga grannar till den tomma rutan sparas
    * Kontrollerar att row och column ligger inom brädets gränser
    * Om positionen är giltig, räknas den om till ett index och läggs i listan
     **/
    private void addIfValid(List<Integer> validNeighbors, int row, int column){
        if (row >= 0 && row < size && column >=0 && column < size) {
            int index = row * size + column;
            validNeighbors.add(index);
        }

    }
}
