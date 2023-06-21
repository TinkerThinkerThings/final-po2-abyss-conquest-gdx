package com.gdx.abyssconquest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.gdx.abyssconquest.screens.WelcomeScreen;

public class AbyssConquest extends Game {
	private Music scm;

	public Music getScm() {
		return scm;
	}

	public void setScm(Music scm) {
		this.scm = scm;
	}

	@Override
	public void create() {
		scm = Gdx.audio.newMusic(Gdx.files.internal("assets/music_and_sounds/sc_music.mp3"));
		scm.play();
		scm.setVolume(0.7f);
		scm.setLooping(true);
		setScreen(new WelcomeScreen(this));
	}

	@Override
	public void dispose() {
		scm.dispose();
	}
}