import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot)

/**
 * A simple title screen that waits for the player to press Space to start.
 */
public class TitleScreen extends World
{
    /**
     * Constructor for objects of class TitleScreen.
     */
    
    //title screen created
    public TitleScreen()
    {    
        
        super(900, 600, 1); 
        GreenfootImage background = new GreenfootImage("TitleScreen.png");
    
        background.scale(900, 600);
    
       
        setBackground(background);
    }

    public void act()
    {
        // Check if the spacebar is pressed
        if (Greenfoot.isKeyDown("space"))
        {
        // Instead of loading the game world, load the instruction screen first
            Greenfoot.setWorld(new InstructionScreen());
        }
        
    }
}