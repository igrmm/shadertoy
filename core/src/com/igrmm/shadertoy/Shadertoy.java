package com.igrmm.shadertoy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.ScreenUtils;

public class Shadertoy extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	ShaderProgram shader;

	@Override
	public void create() {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		shader = new ShaderProgram(batch.getShader().getVertexShaderSource(), Gdx.files.internal("shader.frag").readString());
		if (!shader.isCompiled()) {
			System.out.println(shader.getLog());
		}
		ShaderProgram.pedantic = false;
	}

	@Override
	public void render() {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.setShader(shader);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}
}
