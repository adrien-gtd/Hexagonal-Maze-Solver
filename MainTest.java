

public class MainTest {
    public static void main(String[] args) {
        Maze m = new Maze(0, 0);
        try {
            m.initFromTextFile("../data/labyrinthe.maze");
            for (MazeBox []i : m.getGrid()) {
                for(MazeBox j: i) {
                    System.out.println(j.getLabel());
                }
            }
            m.saveToTextFile("../data/labyrinthe2.maze");
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
    
}
