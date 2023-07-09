package com.gdx.abyssconquest.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.gdx.abyssconquest.AbyssConquest;
import com.gdx.abyssconquest.KarakterUtama;
import com.gdx.abyssconquest.Zombie;

public class GameScreen extends AbyssScreen {
  private Music gsm;
  private TiledMap map;
  private Zombie zombie1, zombie2, zombie3, zombie4;
  private SpriteBatch batch;
  private ExtendViewport vp;
  private KarakterUtama karakter;
  private OrthographicCamera camera;
  private TiledMapRenderer mapRenderer;

  private int layerIndex;

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
    float worldWidth = Gdx.graphics.getWidth();
    float worldHeight = Gdx.graphics.getHeight();
    batch = new SpriteBatch();
    camera = new OrthographicCamera();
    vp = new ExtendViewport(worldWidth, worldHeight, camera);
    vp.apply();

    map = new TmxMapLoader().load("assets/images/Map/MapAbyssConquest.tmx");
    mapRenderer = new OrthogonalTiledMapRenderer(map);
    layerIndex = 0;
    karakter = new KarakterUtama(50, 125, 64, 64);
    zombie1 = new Zombie(235, 125, 64, 64);
    zombie2 = new Zombie(800, 125, 64, 64);
    zombie3 = new Zombie(1000, 125, 64, 64);
    zombie4 = new Zombie(1200, 125, 64, 64);

    gsm = Gdx.audio.newMusic(Gdx.files.internal("assets/music_and_sounds/gs_music.mp3"));
    gsm.play();
    gsm.setVolume(0.3f);
    gsm.setLooping(true);
  }

  @Override
  public void update() {
    // Periksa Tabrakan dengan Objek di Map
    TiledMapTileLayer objectLayer = (TiledMapTileLayer) map.getLayers().get(layerIndex);
    for (int x = 0; x < objectLayer.getWidth(); x++) {
      for (int y = 0; y < objectLayer.getHeight(); y++) {
        Cell cell = objectLayer.getCell(x, y);
        if (cell != null) {
          // Periksa tabrakan dengan objek pada kordinat (x, y)
          float objectX = x * objectLayer.getTileWidth();
          float objectY = y * objectLayer.getTileHeight();
          int objectWidth = objectLayer.getTileWidth();
          int objectHeight = objectLayer.getTileHeight();
          if (karakter.detectCollision(objectX, objectY, objectWidth, objectHeight)) {

          }
        }
      }
    }
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    // Perbarui posisi kamera agar mengikuti pemain
    float targetX = karakter.getX() + karakter.getWidth() / 2;
    float targetY = karakter.getY() + karakter.getHeight() / 2;

    // Batasi camera agar tidak melewati batas peta
    float cameraX = MathUtils.clamp(targetX, Gdx.graphics.getWidth() / 2, 2400 - Gdx.graphics.getWidth() / 2);
    float cameraY = MathUtils.clamp(targetY, Gdx.graphics.getHeight() / 2, 608 - Gdx.graphics.getHeight() / 2);
    camera.position.set(cameraX, cameraY, 0);
    camera.update();

    // Terapkan viewport
    vp.apply();

    // Atur tampilan peta untuk kamera
    mapRenderer.setView(camera);
    mapRenderer.render(new int[] { layerIndex });

    // Mulai batch sprite
    batch.setProjectionMatrix(camera.combined);
    batch.begin();
    karakter.update();
    zombie1.update();
    zombie2.update();
    zombie3.update();
    zombie4.update();
    karakter.render(batch);
    zombie1.render(batch);
    zombie2.render(batch);
    zombie3.render(batch);
    zombie4.render(batch);
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
  }
}