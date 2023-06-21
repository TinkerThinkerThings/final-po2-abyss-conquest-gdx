package com.gdx.abyssconquest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Map {
  private TiledMap tiledMap;
  private OrthogonalTiledMapRenderer mapRenderer;
  private OrthographicCamera camera;

  public Map(String mapPath, OrthographicCamera camera) {
    this.camera = camera;
    loadMap(mapPath);
  }

  private void loadMap(String mapPath) {
    TmxMapLoader mapLoader = new TmxMapLoader();
    tiledMap = mapLoader.load(mapPath);
    mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
  }

  public void render() {
    mapRenderer.setView(camera);
    mapRenderer.render();
  }

  public void dispose() {
    tiledMap.dispose();
    mapRenderer.dispose();
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

  public TiledMap getTiledMap() {
    return tiledMap;
  }
}