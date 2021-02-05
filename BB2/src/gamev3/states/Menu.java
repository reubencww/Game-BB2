package gamev3.states;

import java.awt.Color;
import java.awt.Graphics;

import gamev3.Handler;
import gamev3.gfx.Assets;
import gamev3.ui.ClickListener;
import gamev3.ui.UIImageButton;
import gamev3.ui.UIManager;

//every state you have should extend state
public class Menu extends State {

	private UIManager uiManager;

	public Menu(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);

		// X, Y, Width, Height, Buffer Image
		uiManager.addObject(new UIImageButton(200, 200, 128, 64, Assets.button_start, new ClickListener() {

			// Just creating an instance of this click listener class on the flag?
			@Override
			public void onClick() {
				State.setState(handler.getGame().gameState);
			}
		}));
		// from line 21 to here, it allows us to add new button to our game

	}

	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
	}

}
