import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * id - 207984956.
 *
 * @author name nadav yakobovich.
 * the main class for the game.
 */
public class Ass6Game {
    /**
     * check if the args from the commandLine if correct add the game that the user want to the game.
     *
     * @param arg - the level that the game should run
     * @return list of the level that the game will run by the order of the list.
     */
    private List<LevelInformation> checkInput(String[] arg) {

        // the list contain all the level by there order to run in the game
        List<LevelInformation> levelInformationList = new ArrayList<LevelInformation>();

        // if the game start with no arguments - start the 4 level regular
        if (arg.length == 0) {
            levelInformationList.add(new Level1Direct());
            levelInformationList.add(new Level2());
            levelInformationList.add(new Level3());
            levelInformationList.add(new Level4());
        } else {
            for (String str : arg) {
                if (str.equals("1")) {
                    levelInformationList.add(new Level1Direct());
                }
                if (str.equals("2")) {
                    levelInformationList.add(new Level2());
                }
                if (str.equals("3")) {
                    levelInformationList.add(new Level3());
                }
                if (str.equals("4")) {
                    levelInformationList.add(new Level4());
                }
            }
        }
        if (levelInformationList.size() == 0) {
            levelInformationList.add(new Level1Direct());
            levelInformationList.add(new Level2());
            levelInformationList.add(new Level3());
            levelInformationList.add(new Level4());
        }
        return levelInformationList;

    }

    /**
     * the main class for the game, first initailize the game, and second run it.
     *
     * @param args argument from the user
     */
    public static void main(String[] args) {

        GUI gui = new GUI("Ass6Game", 800, 600);
        AnimationRunner animationRunner = new AnimationRunner(gui);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        Ass6Game ass6Game = new Ass6Game();
        //put the level in array that contain the order of the level
        List<LevelInformation> levelInformationList = ass6Game.checkInput(args);

        //make the game flow start the game by the level in the list of information
        GameFlow gameFlow = new GameFlow(animationRunner, keyboard);
        gameFlow.runLevels(levelInformationList);
        return;
    }
}
