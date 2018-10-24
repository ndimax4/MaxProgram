package gdx.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import gdx.menu.Screens.ScrMenu;
import gdx.menu.Screens.ScrMessages;
import gdx.menu.Screens.ScrScroll;
import gdx.menu.Screens.ScrSimple;
import javafx.stage.Stage;


public class GamMenu extends Game {
    int nScreen;
    ScrMenu scrMenu;
    ScrMessages scrMessages;
    ScrScroll scrScroll;
    ScrSimple scrSimple;
    Stage stage;
    int rowActors;
    int columnActors;
    Table table;

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

        //table = new Table();
        //table.setFillParent(true);
        //table.addActor();
        //int actorWidth = Gdx.graphics.getWidth() / rowActors;
        //int actorHeight = Gdx.graphics.getHeight() /  columnActors;
        //Actor[] actors = new Actor[rowActors * columnActors];

        //for (int i = 0; i < rowActors; i++){
        //for (int j = 0; j < columnActors; j++){
            //Actor actor = actors[(i * columnActors) + j];
        //table.add(actor).width(actorWidth).height(actorHeight);
        //}
        //table.row();
        //}
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