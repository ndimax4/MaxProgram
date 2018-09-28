package gdx.menu.Screens;
 import com.badlogic.gdx.Gdx;
 import com.badlogic.gdx.InputProcessor;
 import com.badlogic.gdx.Screen;
 import com.badlogic.gdx.graphics.GL20;
 import com.badlogic.gdx.graphics.g2d.BitmapFont;
 import com.badlogic.gdx.graphics.g2d.SpriteBatch;
 import com.badlogic.gdx.scenes.scene2d.Actor;
 import com.badlogic.gdx.scenes.scene2d.Stage;
 import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
 import gdx.menu.GamMenu;
 import gdx.menu.TbMenu;
 import gdx.menu.TbsMenu;

public class ScrMessages implements Screen, InputProcessor {
    GamMenu gamMenu;
    TbsMenu tbsMenu;
    TbMenu tbMenu, tbCalculator, tbSimple;
    Stage stage;
    BitmapFont screenName;
    SpriteBatch batch;

    public ScrMessages(GamMenu _gamMenu) {  //Referencing the main class.
        gamMenu = _gamMenu;
    }

    public void show() {
        stage = new Stage();
        tbsMenu = new TbsMenu();
        batch = new SpriteBatch();
        screenName = new BitmapFont();
        tbMenu = new TbMenu("BACK", tbsMenu);
        tbCalculator = new TbMenu("Calculator", tbsMenu);
        tbSimple = new TbMenu("Simple", tbsMenu);
        tbMenu.setY(0);
        tbMenu.setX(0);
        tbSimple.setY(0);
        tbSimple.setX(220);
        tbCalculator.setY(0);
        tbCalculator.setX(440);
        stage.addActor(tbMenu);
        stage.addActor(tbCalculator);
        Gdx.input.setInputProcessor(stage);
        btnMenuListener();
        btnGameoverListener();
    }

    public void render(float delta) {
                stage.act();
                stage.draw();
            }


    public void btnGameoverListener() {
        tbCalculator.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
              
                gamMenu.updateState(3);
            }
        });
    }

    public void btnMenuListener() {
        tbMenu.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
               
                gamMenu.updateState(0);
            }
        });
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}