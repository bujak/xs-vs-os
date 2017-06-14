import com.codecool.UI.UserInterface;
import com.codecool.controller.PvPController;
import com.codecool.xvso.model.Board;
import com.codecool.xvso.model.Game;

/**
 * Created by pgurdek on 14.06.17.
 */
public class Main {

    public static void main(String[] args) {

        UserInterface userInterface = new UserInterface();

        userInterface.showMenu();
        String gameMode = userInterface.getInput();

        if (gameMode.equals("1")) {

            PvPController pvPController = new PvPController(new Game(new Board()));
            pvPController.startGame();
        }
    }
}
