package com.igrmm.shadertoy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class Shadertoy extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img1, img2;
	ShaderProgram shader;
	float scale = 1f;
	Vector2 pos = new Vector2();
	Vector2 texSize;

	@Override
	public void create() {
		batch = new SpriteBatch();
		img1 = new Texture("badlogic.jpg");
		img2 = new Texture("badlogic.jpg");
		img1.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		texSize = new Vector2(img1.getWidth(), img1.getHeight());
		shader = new ShaderProgram(batch.getShader().getVertexShaderSource(), Gdx.files.internal("shader.frag").readString());
		if (!shader.isCompiled()) {
			System.out.println(shader.getLog());
		}
		ShaderProgram.pedantic = false;
	}

	@Override
	public void render() {
		if (pos.x < Gdx.graphics.getWidth()) {
			pos.x += 7 * Gdx.graphics.getDeltaTime();
		} else {
			Gdx.app.exit();
		}

		ScreenUtils.clear(1, 0, 0, 1);
		batch.setShader(shader);
		shader.bind();
		shader.setUniformf("u_pos", pos);
		shader.setUniformf("u_texSize", texSize);
		shader.setUniformf("u_scale", scale);
		batch.begin();
		batch.draw(img1, pos.x, pos.y, img1.getWidth() * scale, img1.getHeight() * scale);
		batch.end();
		batch.setShader(null);
		batch.begin();
		batch.draw(img2, pos.x, pos.y + img2.getHeight() * scale, img2.getWidth() * scale, img2.getHeight() * scale);
		batch.end();

		Gdx.graphics.setTitle(Gdx.graphics.getFramesPerSecond() + "");
	}

	@Override
	public void dispose() {
		batch.dispose();
		img1.dispose();
		img2.dispose();
	}
}
