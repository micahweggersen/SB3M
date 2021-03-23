package RoboRallySB3M;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        boolean isClientOnly = Boolean.parseBoolean(args[0]);

        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("SB3M DEMO");
        cfg.setWindowedMode(500, 500);

        new Lwjgl3Application(new GameRunner(isClientOnly), cfg); //Need to find better name for HalloWorld
    }
}