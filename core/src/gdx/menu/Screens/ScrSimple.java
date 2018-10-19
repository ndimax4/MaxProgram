package gdx.menu.Screens;
 import com.badlogic.gdx.Gdx;
 import com.badlogic.gdx.Input;
 import com.badlogic.gdx.InputProcessor;
 import com.badlogic.gdx.Screen;
 import com.badlogic.gdx.audio.Music;
 import com.badlogic.gdx.audio.Sound;
 import com.badlogic.gdx.graphics.GL20;
 import com.badlogic.gdx.graphics.OrthographicCamera;
 import com.badlogic.gdx.graphics.Texture;
 import com.badlogic.gdx.graphics.g2d.BitmapFont;
 import com.badlogic.gdx.graphics.g2d.SpriteBatch;
 import com.badlogic.gdx.math.MathUtils;
 import com.badlogic.gdx.math.Rectangle;
 import com.badlogic.gdx.math.Vector3;
 import com.badlogic.gdx.scenes.scene2d.Actor;
 import com.badlogic.gdx.scenes.scene2d.Stage;
 import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
 import com.badlogic.gdx.utils.Array;
 import com.badlogic.gdx.utils.TimeUtils;
 import gdx.menu.GamMenu;
 import gdx.menu.TbMenu;
 import gdx.menu.TbsMenu;

 import java.util.Iterator;

public class ScrSimple implements Screen, InputProcessor {
    GamMenu gamMenu;
    TbsMenu tbsMenu;
    TbMenu tbMenu, tbScroll, tbSimple, tbMessages;
    Stage stage;
    BitmapFont screenName;
    private Texture Token;
    private Texture Bag;
    private Sound dropSound;
    private Music BackMusic;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Rectangle bag;
    private Array<Rectangle> tokens;
    private long lastDropTime;
    private int score;
    private String yourScoreName;
    BitmapFont yourBitmapFontName;

    public ScrSimple(GamMenu _gamMenu) {  //Referencing the main class.
        gamMenu = _gamMenu;
    }

    public void show() {
        stage = new Stage();
        tbsMenu = new TbsMenu();
        batch = new SpriteBatch();
        screenName = new BitmapFont();
        tbMenu = new TbMenu("BACK", tbsMenu);
        tbScroll = new TbMenu("SCROLL", tbsMenu);
        tbMessages = new TbMenu("MESSAGES", tbsMenu);
        score = 0;
        yourScoreName = "SCORE: 0";
        yourBitmapFontName = new BitmapFont();
        Token = new Texture(Gdx.files.internal("token.png"));
        Bag = new Texture(Gdx.files.internal("money-bag.png"));
        dropSound = Gdx.audio.newSound(Gdx.files.internal("coin.wav"));
        BackMusic = Gdx.audio.newMusic(Gdx.files.internal("backmusic.mp3"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        batch = new SpriteBatch();
        bag = new Rectangle();
        bag.x = 800 / 2 - 64 / 2;
        bag.y = 20;
        bag.width = 80;
        bag.height = 80;
        tokens = new Array<Rectangle>();
        spawnToken();
        tbMenu.setY(0);
        tbMenu.setX(0);
        tbMessages.setY(0);
        tbMessages.setX(220);
        tbScroll.setY(0);
        tbScroll.setX(440);
        stage.addActor(tbMenu);
        stage.addActor(tbScroll);
        Gdx.input.setInputProcessor(stage);
        btnMenuListener();
        btnGameoverListener();
        btnMessagesListener();
    }


    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        BackMusic.play();
        BackMusic.setLooping(true);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        yourBitmapFontName.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        yourBitmapFontName.draw(batch, yourScoreName, 0, 700);
        yourScoreName = "SCORE: " + score;
        batch.draw(Bag, bag.x, bag.y);
        for (Rectangle token : tokens) {
            batch.draw(Token, token.x, token.y);
        }
        batch.end();

        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            bag.x = touchPos.x - 64 / 2;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) bag.x -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bag.x += 200 * Gdx.graphics.getDeltaTime();

        if (bag.x < 0) bag.x = 0;
        if (bag.x > 800 - 64) bag.x = 800 - 64;
        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnToken();

        for (Iterator<Rectangle> iter = tokens.iterator(); iter.hasNext(); ) {
            Rectangle token = iter.next();
            token.y -= 200 * Gdx.graphics.getDeltaTime();
            if (token.y + 64 < 0) iter.remove();
            if (token.overlaps(bag)) {
                score++;
                dropSound.play();
                iter.remove();
            }
        }
        stage.act();
        stage.draw();

    }
    private void spawnToken() {
        Rectangle token = new Rectangle();
        token.x = MathUtils.random(0, 800-64);
        token.y = 480;
        token.width = 50;
        token.height = 50;
        tokens.add(token);
        lastDropTime = TimeUtils.nanoTime();
    }

    public void btnGameoverListener() {
        tbScroll.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {

                gamMenu.updateState(2);
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
    public void btnMessagesListener() {
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
            Token.dispose();
            Bag.dispose();
            dropSound.dispose();
            BackMusic.dispose();
            batch.dispose();
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
