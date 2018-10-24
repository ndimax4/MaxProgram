package gdx.menu.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
    TbMenu tbMessages, tbSimple, tbScroll;
    Stage stage;
    SpriteBatch batch;
    BitmapFont screenName;
    private OrthographicCamera camera;
    //private SpriteBatch batch;

    public ScrMenu(GamMenu _gamMenu) {  //Referencing the main class.
        gamMenu = _gamMenu;
    }

    public void show() {
        stage = new Stage();
        tbsMenu = new TbsMenu();
        batch = new SpriteBatch();
        screenName = new BitmapFont();
        tbMessages = new TbMenu("MESSAGES", tbsMenu);
        tbSimple = new TbMenu("SIMPLE", tbsMenu);
        tbScroll = new TbMenu("SCROLL", tbsMenu);
        tbSimple.setY(0);
        tbSimple.setX(220);
        tbMessages.setY(0);
        tbMessages.setX(440);
        tbScroll.setY(0);
        tbScroll.setX(0);
        stage.addActor(tbMessages);
        stage.addActor(tbSimple);
        stage.addActor((tbScroll));
        Gdx.input.setInputProcessor(stage);
        btnPlayListener();
        btnGameoverListener();
        btnSimpleListener();
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        screenName.draw(batch, "MPhone", 265, 275);
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
        tbScroll.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {               
                gamMenu.updateState(2);
            }
        });
    }

    public void btnSimpleListener() {
        tbSimple.addListener(new ChangeListener() {
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

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }
}