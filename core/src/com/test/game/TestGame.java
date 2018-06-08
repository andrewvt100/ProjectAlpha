package com.test.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

// import com.badlogic.gdx.graphics.Texture;
// import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TestGame extends ApplicationAdapter {
	// SpriteBatch batch;
	// Texture img;

    public Environment environment;
	public PerspectiveCamera cam;
    public ModelBatch modelBatch;
	public Model model;
    public ModelInstance instance;
    public CameraInputController camController;
	
	@Override
	public void create () {

		System.out.println("Create");

		environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(10f, 10f, 10f);
        cam.lookAt(0,0,0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        ModelLoader loader = new ObjLoader();
        model = loader.loadModel(Gdx.files.internal("smiley.obj"));
        instance = new ModelInstance(model);

    	modelBatch = new ModelBatch();

    	camController = new CameraInputController(cam);
        Gdx.input.setInputProcessor(camController);

		// batch = new SpriteBatch();
		// img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {

		System.out.println("Render");

        camController.update();

		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        modelBatch.begin(cam);
        modelBatch.render(instance, environment);
        modelBatch.end();

		// Gdx.gl.glClearColor(1, 0, 0, 1);
		// Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// batch.begin();
		// batch.draw(img, 0, 0);
		// batch.end();
	}
	
	@Override
	public void dispose () {

		System.out.println("Dispose");

        modelBatch.dispose();
		model.dispose();

		// batch.dispose();
		// img.dispose();
	}
}
