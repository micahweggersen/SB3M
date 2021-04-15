package com.RoboRallySB3M;

import com.RoboRallySB3M.Screens.ParentScreen;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {
    public static void main(String[] args) {

        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("SB3M v3.0.0");
        cfg.setWindowedMode(1000, 1000);

        new Lwjgl3Application(new ParentScreen(), cfg); //Need to find better name for HalloWorld
    }
}