package gdx.menu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import gdx.menu.Screens.ScrMessages;
import gdx.menu.Screens.ScrMenu;
import gdx.menu.Screens.ScrSimple;
import com.badlogic.gdx.graphics.GL20;


public class GamMenu extends Game {
    ScrMenu scrMenu;
    ScrSimple scrMessages;
    ScrMessages scrCalculator;
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
        scrMessages = new ScrSimple(this);
        scrCalculator = new ScrMessages(this);
        scrSimple = new ScrSimple(this);
        updateState(0);
    }

    @Override
    public void render() {
        super.render();
    }


    @Override
    public void dispose() {
        super.dispose();
    }
}