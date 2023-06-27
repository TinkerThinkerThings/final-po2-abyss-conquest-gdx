package com.gdx.abyssconquest.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.gdx.abyssconquest.AbyssConquest;
import com.gdx.abyssconquest.KarakterUtama;

public class GameScreen extends AbyssScreen {
  private SpriteBatch batch;
  private OrthographicCamera camera;
  private ExtendViewport vp;
  private TiledMap map;
  private TiledMapRenderer mapRenderer;
  private KarakterUtama player;
  private Music gsm;

  public Music getGsm() {
    return gsm;
  }

  public void setGsm(Music gsm) {
    this.gsm = gsm;
  }

  public GameScreen(Game game) {
    super(game);
    AbyssConquest ac = (AbyssConquest) game;
    ac.getScm().stop();
  }

  @Override
  public void show() {
    float worldWidth = 800;
    float worldHeight = 200;
    batch = new SpriteBatch();
    camera = new OrthographicCamera();
    vp = new ExtendViewport(worldWidth, worldHeight, camera);
    vp.apply();

    map = new TmxMapLoader().load("assets/images/Map/MapAbyssConquest.tmx");
    mapRenderer = new OrthogonalTiledMapRenderer(map);

    float playerX = 100;
    float playerY = 100;
    float playerWidth = 64;
    float playerHeight = 64;
    float playerSpeed = 0;
    int playerHealth = 100;

    player = new KarakterUtama(playerX, playerY, playerWidth, playerHeight,
        "assets/images/animasi_karakter/abyss_diam.png.png", playerSpeed, playerHealth);
    player.create();
    player.updateStateTime(playerHealth);
    gsm = Gdx.audio.newMusic(Gdx.files.internal("assets/music_and_sounds/gs_music.mp3"));
    gsm.play();
    gsm.setVolume(0.3f);
    gsm.setLooping(true);
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    // Perbarui posisi pemain berdasarkan input
    player.handleInput();
    player.update(delta);

    // Perbarui posisi kamera agar mengikuti pemain
    camera.position.set(player.getPosition().x + player.getWidth() / 2, player.getPosition().y + player.getHeight() / 2,
        0);
    camera.update();

    // Terapkan viewport
    vp.apply();

    // Atur tampilan peta untuk kamera
    mapRenderer.setView(camera);
    mapRenderer.render();

    // Mulai batch sprite
    batch.setProjectionMatrix(camera.combined);
    batch.begin();

    // Gambar pemain
    player.updateStateTime(delta);
    player.render(batch);

    // Selesai batch sprite
    batch.end();
  }

  @Override
  public void resize(int width, int height) {
    vp.update(width, height, true);
  }

  @Override
  public void dispose() {
    batch.dispose();
    map.dispose();
    player.dispose();
  }
}