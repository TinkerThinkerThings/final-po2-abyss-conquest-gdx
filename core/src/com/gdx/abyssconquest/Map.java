package com.gdx.abyssconquest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Disposable;

public class Map {
  private TiledMap tiledMap;
  private TiledMapRenderer mapRenderer;
  private OrthographicCamera camera;

  public Map(String mapPath, OrthographicCamera camera) {
    this.camera = camera;
    loadMap(mapPath);
  }

  private void loadMap(String mapPath) {
    TmxMapLoader mapLoader = new TmxMapLoader();
    tiledMap = mapLoader.load(mapPath);
    mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

    float mapWidth = tiledMap.getProperties().get("width", Integer.class)
        * tiledMap.getProperties().get("tilewidth", Integer.class);
    float mapHeight = tiledMap.getProperties().get("height", Integer.class)
        * tiledMap.getProperties().get("tileheight", Integer.class);
    camera.setToOrtho(false, mapWidth, mapHeight);
  }

  public void render() {
    mapRenderer.setView(camera);
    mapRenderer.render();
  }

  public void dispose() {
    tiledMap.dispose();
    ((Disposable) mapRenderer).dispose();
  }

  public MapObjects getObjects(String layerName) {
    return tiledMap.getLayers().get(layerName).getObjects();
  }

  // Contoh metode untuk memperoleh objek karakter utama
  public RectangleMapObject getPlayerObject(String layerName) {
    MapObjects objects = getObjects(layerName);
    for (MapObject object : objects) {
      if (object instanceof RectangleMapObject) {
        RectangleMapObject rectangleObject = (RectangleMapObject) object;
        if (rectangleObject.getName().equals("player")) {
          return rectangleObject;
        }
      }
    }
    return null;
  }
}