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

import java.awt.*;


public class ScrCalculator implements Screen, InputProcessor {
    GamMenu gamMenu;
    TbsMenu tbsMenu;
    TbMenu tbMessages, tbMenu;
    Stage stage;
    SpriteBatch batch;
    BitmapFont screenName;


    public ScrCalculator(GamMenu _gamMenu) {  //Referencing the main class.
        gamMenu = _gamMenu;
    }

    public void show() {
        stage = new Stage();
        tbsMenu = new TbsMenu();
        batch = new SpriteBatch();
        screenName = new BitmapFont();
        tbMessages = new TbMenu("BACK", tbsMenu);
        tbMenu = new TbMenu("MENU", tbsMenu);
        tbMessages.setY(0);
        tbMessages.setX(0);
        tbMenu.setY(0);
        tbMenu.setX(440);
        stage.addActor(tbMenu);
        stage.addActor(tbMessages);
        Gdx.input.setInputProcessor(stage);
        btnMenuListener();
        btnPlayListener();
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); //black background.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        screenName.draw(batch, "Calculator Function", 265, 475);
        batch.end();
        stage.act();
        stage.draw();
    }

    public void btnMenuListener() {
        tbMenu.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {

                gamMenu.updateState(0);
            }
        });
    }

    public void btnPlayListener() {
        tbMessages.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {

                gamMenu.updateState(1);
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