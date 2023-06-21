package com.gdx.abyssconquest.imgbtn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.example.game.audio.ButtonSoundEffect;

public class SoundEffect extends Game {
    private ButtonSoundEffect buttonSoundEffect;

    public void create() {
        // Membuat instance ButtonSoundEffect dengan path file suara relatif
        buttonSoundEffect = new ButtonSoundEffect("sounds/button_sound.wav");
    }

    public void render() {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            // Memainkan sound effect saat tombol spasi ditekan
            buttonSoundEffect.play();
        }

        // Panggil super.render() untuk melanjutkan proses render yang diberikan oleh Game
        super.render();
    }

    public void dispose() {
        // Membersihkan sumber daya sound effect
        buttonSoundEffect.dispose();
        // Panggil super.dispose() untuk membebaskan sumber daya lain yang dikelola oleh Game
        super.dispose();
    }
}
