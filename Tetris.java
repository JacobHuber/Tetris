
public class Tetris {

    public static void main(String[] args) {
        Game game = new Game();

        while (game.getGameRunning()) {
            game.tick();
        }
        System.out.println("Game Over!");

    }

}
