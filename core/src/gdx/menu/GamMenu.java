package gdx.menu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import gdx.menu.Screens.ScrCalculator;
import gdx.menu.Screens.ScrMenu;
import gdx.menu.Screens.ScrMessages;

import com.badlogic.gdx.graphics.GL20;

import gdx.menu.Screens.ScrSimple;


public class GamMenu extends Game {
    ScrMenu scrMenu;
    ScrMessages scrMessages;
    ScrCalculator scrCalculator;
    ScrSimple scrSimple;
    int nScreen; // 0 for menu, 1 for play, and 2 for game over

    
    public void updateState(int _nScreen) {
        nScreen = _nScreen;
        if ( nScreen == 0) {
            setScreen(scrMenu);
        } else if (nScreen == 1) {
            setScreen(scrMessages);
        } else if (nScreen ==2) {
            setScreen(scrCalculator);
        } else if (nScreen == 3){
            setScreen(scrSimple);
        }
    }

    @Override
    public void create() {
        nScreen = 0;
        scrMenu = new ScrMenu(this);
        scrMessages = new ScrMessages(this);
        scrCalculator = new ScrCalculator(this);
        scrSimple = new ScrSimple(this);
        updateState(0);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            }


    @Override
    public void dispose() {
        super.dispose();
    }
}