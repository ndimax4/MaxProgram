package gdx.menu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import gdx.menu.Screens.ScrScroll;
import gdx.menu.Screens.ScrMenu;
import gdx.menu.Screens.ScrMessages;
import gdx.menu.Screens.ScrSimple;


public class GamMenu extends Game {
    ScrMenu scrMenu;
    ScrMessages scrMessages;
    ScrScroll scrScroll;
    ScrSimple scrSimple;
    int nScreen; // 0 for menu, 1 for play, and 2 for game over
    //Table table = new Table();

    public void updateState(int _nScreen) {
        nScreen = _nScreen;
        if ( nScreen == 0) {
            setScreen(scrMenu);
        } else if (nScreen == 1) {
            setScreen(scrMessages);
        } else if (nScreen ==2) {
            setScreen(scrScroll);
        } else if (nScreen == 3){
            setScreen(scrSimple);
        }
    }

    @Override
    public void create() {
        nScreen = 0;
        scrMenu = new ScrMenu(this);
        scrMessages = new ScrMessages(this);
        scrScroll = new ScrScroll(this);
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