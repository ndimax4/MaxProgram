package gdx.menu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
    Stage stage;
    int nScreen;
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
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        int actorWidth = Gdx.graphics.getWidth() / rowActors;
        int actorHeight = Gdx.graphics.getHeight() /  columnActors;
        Actor[] actors = new Actor[rowActors * columnActors];
        updateState(0);

        for (int i = 0; i < rowActors; i++){
            for (int j = 0; j < columnActors; j++){
                Actor actor = actors[(i * columnActors) + j];
                table.add(actor).width(actorWidth).height(actorHeight);
            }
            table.row();
        }
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