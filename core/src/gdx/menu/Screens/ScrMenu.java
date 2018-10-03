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



public class ScrMenu implements Screen, InputProcessor {
    GamMenu gamMenu;
    TbsMenu tbsMenu;
    TbMenu tbMessages, tbSimple, tbCalculator;
    Stage stage;
    SpriteBatch batch;
    BitmapFont screenName;

    public ScrMenu(GamMenu _gamMenu) {  //Referencing the main class.
        gamMenu = _gamMenu;
    }

    public void show() {
        stage = new Stage();
        tbsMenu = new TbsMenu();
        batch = new SpriteBatch();
        screenName = new BitmapFont();
        tbMessages = new TbMenu("Simple", tbsMenu);
        tbSimple = new TbMenu("Messages", tbsMenu);
        tbCalculator = new TbMenu("Calculator", tbsMenu);
        tbSimple.setY(0);
        tbSimple.setX(220);
        tbMessages.setY(0);
        tbMessages.setX(440);
        tbCalculator.setY(0);
        tbCalculator.setX(0);
        stage.addActor(tbMessages);
        stage.addActor(tbSimple);
        stage.addActor((tbCalculator));
        Gdx.input.setInputProcessor(stage);
        btnPlayListener();
        btnGameoverListener();
        btnCalculatorListener();
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        screenName.draw(batch, "Welcome to mFone", 265, 275);
        batch.end();
        stage.act();
        stage.draw();
    }

    public void btnPlayListener() {
        tbMessages.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                
                gamMenu.updateState(1); // switch to Play screen.
            }
        });
    }

    public void btnGameoverListener() {
        tbSimple.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {               
                gamMenu.updateState(2);
            }
        });
    }

    public void btnCalculatorListener() {
        tbCalculator.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gamMenu.updateState(3);
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