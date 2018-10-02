package gdx.menu;
import java.util.Iterator;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import gdx.menu.Screens.ScrCalculator;
import gdx.menu.Screens.ScrMenu;
import gdx.menu.Screens.ScrMessages;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import gdx.menu.Screens.ScrSimple;


public class GamMenu extends Game {
    ScrMenu scrMenu;
    ScrMessages scrMessages;
    ScrCalculator scrCalculator;
    ScrSimple scrSimple;
    int nScreen; // 0 for menu, 1 for play, and 2 for game over
    private Texture Token;
    private Texture Bag;
    private Sound dropSound;
    private Music BackMusic;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Rectangle bag;
    private Array<Rectangle> tokens;
    private long lastDropTime;
    
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
        scrMessages = new ScrMessages(this);
        scrCalculator = new ScrCalculator(this);
        scrSimple = new ScrSimple(this);
        updateState(0);
        Token = new Texture(Gdx.files.internal("token.png"));
        Bag = new Texture(Gdx.files.internal("money-bag.png"));

        dropSound = Gdx.audio.newSound(Gdx.files.internal("coin.wav"));
        BackMusic = Gdx.audio.newMusic(Gdx.files.internal("backmusic.mp3"));

        BackMusic.setLooping(true);
        BackMusic.play();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();

        bag = new Rectangle();
        bag.x = 800 / 2 - 64 / 2;
        bag.y = 20;
        bag.width = 80;
        bag.height = 80;

        tokens = new Array<Rectangle>();
        spawnToken();
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

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
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
        if (Gdx.input.isKeyPressed(Keys.LEFT)) bag.x -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) bag.x += 200 * Gdx.graphics.getDeltaTime();

        if (bag.x < 0) bag.x = 0;
        if (bag.x > 800 - 64) bag.x = 800 - 64;
        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnToken();

        for (Iterator<Rectangle> iter = tokens.iterator(); iter.hasNext(); ) {
            Rectangle token = iter.next();
            token.y -= 200 * Gdx.graphics.getDeltaTime();
            if (token.y + 64 < 0) iter.remove();
            if (token.overlaps(bag)) {
                dropSound.play();
                iter.remove();
            }
        }
        super.render();
    }

    @Override
    public void dispose() {
        Token.dispose();
        Bag.dispose();
        dropSound.dispose();
        BackMusic.dispose();
        batch.dispose();
        super.dispose();
    }
}