package gdx.menu.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.TimeUtils;
import gdx.menu.GamMenu;
import gdx.menu.TbMenu;
import gdx.menu.TbsMenu;


public class ScrScroll implements Screen, InputProcessor {
    GamMenu gamMenu;
    TbsMenu tbsMenu;
    TbMenu tbMessages, tbMenu, tbSimple;
    Stage stage;
    SpriteBatch batch;
    BitmapFont screenName;
    OrthographicCamera camera;
    Texture background;
    float currentBgX;
    long lastTimeBg;
    Texture TxBall;
    Sprite sprBall;
    float spriteXposition = -420;
    float spriteYposition = -50;


    public ScrScroll(GamMenu _gamMenu) {  //Referencing the main class.
        gamMenu = _gamMenu;
    }

    public void show() {
        stage = new Stage();
        tbsMenu = new TbsMenu();
        batch = new SpriteBatch();
        screenName = new BitmapFont();
        tbMessages = new TbMenu("BACK", tbsMenu);
        tbMenu = new TbMenu("MENU", tbsMenu);
        tbSimple = new TbMenu("SIMPLE", tbsMenu);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        background = new Texture(Gdx.files.internal("bg.png"));
        currentBgX = 800;
        lastTimeBg = TimeUtils.nanoTime();
        TxBall = new Texture("Ball.png");
        sprBall = new Sprite(TxBall);
        tbMessages.setY(0);
        tbMessages.setX(0);
        tbSimple.setY(0);
        tbSimple.setX(220);
        tbMenu.setY(0);
        tbMenu.setX(440);
        stage.addActor(tbMenu);
        stage.addActor(tbMessages);
        stage.addActor(tbSimple);
        Gdx.input.setInputProcessor(stage);
        btnMenuListener();
        btnPlayListener();
        btnSimpleListener();
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); //black background.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, currentBgX - 800, 0);
        batch.draw(background, currentBgX, 0);
        sprBall.setPosition(spriteXposition, spriteYposition);
        sprBall.draw(batch);
        batch.end();
        spriteControl();
        if(TimeUtils.nanoTime() - lastTimeBg > 100000000){
            currentBgX -= 50;
            lastTimeBg = TimeUtils.nanoTime();
        }
        if(currentBgX == 0){
            currentBgX = 800;
        }
        stage.act();
        stage.draw();
    }

    public void spriteControl() {
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            spriteYposition+= 5;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            spriteYposition-= 5;
        }
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
    public void btnSimpleListener() {
        tbSimple.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {

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