package gamev3.states;

import java.awt.Graphics;
import java.util.ArrayList;

import gamev3.Handler;
import gamev3.entity.EntityManager;
import gamev3.entity.creature.Background;
import gamev3.entity.creature.Big_rock;
import gamev3.entity.creature.Fire_rock;
import gamev3.entity.creature.Player;
import gamev3.entity.creature.Rocks;
import gamev3.entity.creature.Smoll_rock;
import gamev3.entity.creature.medium_rock;


//this is where the actual game is gg to be played at
public class GameState extends State{

	private Player player;
	private Player player2;
	private Background background;
	//private EntityManager entityManager;
	//private Rocks rock;
	//lets try to experiment with arraylist for rock spawner
	
	final private int NUM_ROCK = 1;	//total rock on screen at one time
	private ArrayList<Rocks> smallRockSpawner = new ArrayList<Rocks>();
	private ArrayList<Rocks> mediumRockSpawner = new ArrayList<Rocks>();
	private ArrayList<Rocks> bigRockSpawner = new ArrayList<Rocks>();
	private ArrayList<Rocks> fireRockSpawner = new ArrayList<Rocks>();
	
	

	public GameState(Handler handler) {
		super(handler);
		//making all objects
		background = new Background(handler,0,0);
		player = new Player(handler, 256, 410, 1);
		player2 = new Player(handler, 768, 410, 2);
		//rock = new Rocks(handler, (float)(Math.random() * (1024 - 0 + 1)+ 0),0);
		//entityManager = new EntityManager(handler);
		
		for (int i = 0; i < NUM_ROCK; i++) {
			smallRockSpawner.add(new Smoll_rock(handler, (float)(Math.random() * (1024 - 0 + 1)+ 0),0));
		}
		
		for (int i = 0; i < NUM_ROCK; i++) {
			mediumRockSpawner.add(new medium_rock(handler, (float)(Math.random() * (1024 - 0 + 1)+ 0),0));
		}
		
		for (int i = 0; i < NUM_ROCK; i++) {
			bigRockSpawner.add(new medium_rock(handler, (float)(Math.random() * (1024 - 0 + 1)+ 0),0));
		}
		
		for (int i = 0; i < NUM_ROCK; i++) {
			fireRockSpawner.add(new Fire_rock(handler, (float)(Math.random() * (1024 - 0 + 1)+ 0),0));
		}
	
		
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		player.tick();
		player2.tick();
		//entityManager.tick();
		//rock.tick();
		
		for (int i = 0; i < smallRockSpawner.size(); i++) {
			smallRockSpawner.get(i).tick();
			if (smallRockSpawner.get(i).isOffScreen()){
				smallRockSpawner.remove(i);
				smallRockSpawner.add(new Smoll_rock(handler, (float)(Math.random() * (1024 - 0 + 1)+ 0),0));
				smallRockSpawner.get(i).tick();
			} 
		}
	
		for (int i = 0; i < mediumRockSpawner.size(); i++) {
			mediumRockSpawner.get(i).tick();
			if (mediumRockSpawner.get(i).isOffScreen() && i < 10){
				mediumRockSpawner.remove(i);
				mediumRockSpawner.add(new medium_rock(handler, (float)(Math.random() * (1024 - 0 + 1)+ 0),0));
				mediumRockSpawner.get(i).tick();
			} 
		}
		
		for (int i = 0; i < bigRockSpawner.size(); i++) {
			bigRockSpawner.get(i).tick();
			if (bigRockSpawner.get(i).isOffScreen()){
				bigRockSpawner.remove(i);
				bigRockSpawner.add(new Big_rock(handler, (float)(Math.random() * (1024 - 0 + 1)+ 0),0));
				bigRockSpawner.get(i).tick();
			} 
		}
		
		for (int i = 0; i < fireRockSpawner.size(); i++) {
			fireRockSpawner.get(i).tick();
			if (fireRockSpawner.get(i).isOffScreen()){
				fireRockSpawner.remove(i);
				fireRockSpawner.add(new Fire_rock(handler, (float)(Math.random() * (1024 - 0 + 1)+ 0),0));
				fireRockSpawner.get(i).tick();
			} 
		}
		checkHit(player);
		checkHit(player2);
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub	
		
		background.render(g);
		//entityManager.render(g);
		player.render(g);
		player2.render(g);
		
		for (int i = 0; i < smallRockSpawner.size(); i++) {
			smallRockSpawner.get(i).render(g);
		}
		
		for (int i = 0; i < mediumRockSpawner.size(); i++) {
			mediumRockSpawner.get(i).render(g);
		}
		
		for (int i = 0; i < bigRockSpawner.size(); i++) {
			bigRockSpawner.get(i).render(g);
		}
		
		for (int i = 0; i < fireRockSpawner.size(); i++) {
			fireRockSpawner.get(i).render(g);
		}
		
	}
	public void checking(ArrayList<Rocks> list,Player player) {
		/*
		int playerX = player.getHitx();
		int playerRightX = player.getHitWidth();
		int playerY = player.getHity();
		int playerBtmY = player.getHitHeight();
		*/
		for (int i = 0; i < list.size(); i++) {
			if (player.bound().intersects(list.get(i).bound())) {
				list.remove(i);
				list.add(new Smoll_rock(handler, (float)(Math.random() * (1024 - 0 + 1)+ 0),0));
				list.get(i).tick();
				System.out.println("HIT");
			}
		}
			/*
			int leftx = list.get(i).getRectx();
			int topy = list.get(i).getRecty();
			int rightx = list.get(i).getRectrightx();
			int btmy = list.get(i).getRectHeight();
			
			//if player's top and rock's btm on the same level
			if (playerY == btmy){
				//check if player's left/right x in between rocks x's
				//if players left is bigger then rock's left and 
				//player left x smaller then rock's right
				//means rock has hit player
				if (playerX > leftx && playerX < rightx) {
					//once hit, remove rock and reinsert
					System.out.println("HIT");
					list.remove(i);
					list.add(new Smoll_rock(handler, (float)(Math.random() * (1024 - 0 + 1)+ 0),0));
					list.get(i).tick();
					return;
					
				}else if (playerRightX > leftx && playerRightX < rightx) {
					System.out.println("HIT");
					list.remove(i);
					list.add(new Smoll_rock(handler, (float)(Math.random() * (1024 - 0 + 1)+ 0),0));
					list.get(i).tick();
					return;
				}
			}else if (playerX == rightx){
				//this means that player's left is inline with rock's right
				//check if the y value hits
				if (playerY > topy && playerY < btmy) {
					//means player got hit, top left corner between rocks
					System.out.println("HIT");
					list.remove(i);
					list.add(new Smoll_rock(handler, (float)(Math.random() * (1024 - 0 + 1)+ 0),0));
					list.get(i).tick();
					return;
				}else if (playerBtmY > topy && playerBtmY < btmy) {
					//means player got hit
					System.out.println("HIT");
					list.remove(i);
					list.add(new Smoll_rock(handler, (float)(Math.random() * (1024 - 0 + 1)+ 0),0));
					list.get(i).tick();
					return;
				}else if(topy > playerY && topy < playerBtmY) {
					System.out.println("HIT");
					list.remove(i);
					list.add(new Smoll_rock(handler, (float)(Math.random() * (1024 - 0 + 1)+ 0),0));
					list.get(i).tick();
					return;
				}else if(btmy > playerY && btmy < playerBtmY) {
					System.out.println("HIT");
					list.remove(i);
					list.add(new Smoll_rock(handler, (float)(Math.random() * (1024 - 0 + 1)+ 0),0));
					list.get(i).tick();
					return;
				}
			}else if (playerRightX == leftx) {
				//player's right has hit rock's left
				if (playerY > topy && playerY < btmy) {
					//means player got hit, top left corner between rocks
					System.out.println("HIT");
					list.remove(i);
					list.add(new Smoll_rock(handler, (float)(Math.random() * (1024 - 0 + 1)+ 0),0));
					list.get(i).tick();
					return;
				}else if (playerBtmY > topy && playerBtmY < btmy) {
					//means player got hit
					System.out.println("HIT");
					list.remove(i);
					list.add(new Smoll_rock(handler, (float)(Math.random() * (1024 - 0 + 1)+ 0),0));
					list.get(i).tick();
					return;
				}else if(topy > playerY && topy < playerBtmY) {
					System.out.println("HIT");
					list.remove(i);
					list.add(new Smoll_rock(handler, (float)(Math.random() * (1024 - 0 + 1)+ 0),0));
					list.get(i).tick();
					return;
				}else if(btmy > playerY && btmy < playerBtmY) {
					System.out.println("HIT");
					list.remove(i);
					list.add(new Smoll_rock(handler, (float)(Math.random() * (1024 - 0 + 1)+ 0),0));
					list.get(i).tick();
					return;
				}
			}
		}*/
	}
	public void checkHit(Player player) {
		checking(smallRockSpawner, player);
		checking(mediumRockSpawner, player);
		checking(bigRockSpawner, player);
		checking(smallRockSpawner, player);
		checking(fireRockSpawner, player);
		
	//use this function to call player's rectangle and rock's rectangle.
		//check if the lower x of the rock's rectangle is equal to the top of player's height
		//if so, then minus health by 1
		//else if player left bound == rock right bound
		//minus 1
		//else if player right bound == rock left bound
		//minus 1
		//this ensures that if rock touches players top left or right, the health will minus 1.
		/*int playerX = player.getHitx();
		int playerRightX = playerX + player.getHitWidth();
		int playerY = player.getHity();
		int playerBtmY = playerY + player.getHitHeight();
		
		for (int i = 0; i < smallRockSpawner.size(); i++) {
			int leftx = smallRockSpawner.get(i).getRectx();
			int topy = smallRockSpawner.get(i).getRecty();
			int rightx = leftx + smallRockSpawner.get(i).getRectrightx();
			int btmy = topy + smallRockSpawner.get(i).getRectHeight();
			
			//if player's top and rock's btm on the same level
			if (playerY == btmy){
				//check if player's left/right x in between rocks x's
				//if players left is bigger then rock's left and 
				//player left x smaller then rock's right
				//means rock has hit player
				if (playerX > leftx && playerX < rightx) {
					//once hit, remove rock and reinsert
					System.out.println("HIT");
					smallRockSpawner.remove(i);
					smallRockSpawner.add(new Smoll_rock(handler, (float)(Math.random() * (1024 - 0 + 1)+ 0),0));
					smallRockSpawner.get(i).tick();
					
				}else if (playerRightX > leftx && playerRightX < rightx) {
					System.out.println("HIT");
					smallRockSpawner.remove(i);
					smallRockSpawner.add(new Smoll_rock(handler, (float)(Math.random() * (1024 - 0 + 1)+ 0),0));
					smallRockSpawner.get(i).tick();
				}
			}else if (playerX == rightx){
				//this means that player's left is inline with rock's right
				//check if the y value hits
				if (playerY > topy && playerY < btmy) {
					//means player got hit, top left corner between rocks
					System.out.println("HIT");
					smallRockSpawner.remove(i);
					smallRockSpawner.add(new Smoll_rock(handler, (float)(Math.random() * (1024 - 0 + 1)+ 0),0));
					smallRockSpawner.get(i).tick();
				}else if (playerBtmY > topy && playerBtmY < btmy) {
					//means player got hit
					System.out.println("HIT");
					smallRockSpawner.remove(i);
					smallRockSpawner.add(new Smoll_rock(handler, (float)(Math.random() * (1024 - 0 + 1)+ 0),0));
					smallRockSpawner.get(i).tick();
				}else if(topy > playerY && topy < playerBtmY) {
					System.out.println("HIT");
					smallRockSpawner.remove(i);
					smallRockSpawner.add(new Smoll_rock(handler, (float)(Math.random() * (1024 - 0 + 1)+ 0),0));
					smallRockSpawner.get(i).tick();
				}else if(btmy > playerY && btmy < playerBtmY) {
					System.out.println("HIT");
					smallRockSpawner.remove(i);
					smallRockSpawner.add(new Smoll_rock(handler, (float)(Math.random() * (1024 - 0 + 1)+ 0),0));
					smallRockSpawner.get(i).tick();
				}
			}else if (playerRightX == leftx) {
				//player's right has hit rock's left
				if (playerY > topy && playerY < btmy) {
					//means player got hit, top left corner between rocks
					System.out.println("HIT");
					smallRockSpawner.remove(i);
					smallRockSpawner.add(new Smoll_rock(handler, (float)(Math.random() * (1024 - 0 + 1)+ 0),0));
					smallRockSpawner.get(i).tick();
				}else if (playerBtmY > topy && playerBtmY < btmy) {
					//means player got hit
					System.out.println("HIT");
					smallRockSpawner.remove(i);
					smallRockSpawner.add(new Smoll_rock(handler, (float)(Math.random() * (1024 - 0 + 1)+ 0),0));
					smallRockSpawner.get(i).tick();
				}else if(topy > playerY && topy < playerBtmY) {
					System.out.println("HIT");
					smallRockSpawner.remove(i);
					smallRockSpawner.add(new Smoll_rock(handler, (float)(Math.random() * (1024 - 0 + 1)+ 0),0));
					smallRockSpawner.get(i).tick();
				}else if(btmy > playerY && btmy < playerBtmY) {
					System.out.println("HIT");
					smallRockSpawner.remove(i);
					smallRockSpawner.add(new Smoll_rock(handler, (float)(Math.random() * (1024 - 0 + 1)+ 0),0));
					smallRockSpawner.get(i).tick();
				}
			}
		}*/
	}
	
}
