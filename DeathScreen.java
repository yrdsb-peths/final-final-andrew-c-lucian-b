import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot)

public class DeathScreen extends World
{
    public DeathScreen()
    {    
        // Set to your exact 900x600 resolution
        super(900, 600, 1); 
        
        // Load and scale the game over image to fit perfectly
        GreenfootImage bg = new GreenfootImage("DeathScreen.png");
        bg.scale(900, 600);
        setBackground(bg);
    }

    public void act()
    {
        // If they press space, send them back to the main game world to try again
        if (Greenfoot.isKeyDown("space"))
        {
            Greenfoot.setWorld(new MyWorld()); 
        }
    }
}