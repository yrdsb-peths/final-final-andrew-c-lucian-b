import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Grandma here.
 * 
 * @Lucian and Andrew
 * @May 20, 2026
 */
public class Grandma extends Actor
{
    GreenfootImage[] grandmaRight = new GreenfootImage[4];
    GreenfootImage[] grandmaLeft = new GreenfootImage[4];
    
    String facing = "right";
    
    SimpleTimer animationTimer = new SimpleTimer();
    
    int imageIndex = 0;
    
    public Grandma()
    {
        for(int i = 0; i < grandmaRight.length; i++)
        {
            grandmaRight[i] = new GreenfootImage("grandma_walk/grandma_walk" + (i+1) + ".png");
            grandmaRight[i].scale(150, 150);
        }
        
        for(int i = 0; i < grandmaLeft.length; i++)
        {
            grandmaLeft[i] = new GreenfootImage("grandma_walk/grandma_walk" + (i+1) + ".png");
            grandmaLeft[i].mirrorHorizontally();
            grandmaLeft[i].scale(150, 150);
        }
        
        setImage(grandmaRight[0]);
        animationTimer.mark();
    }
    
    public void animateGrandma()
    {
        if(animationTimer.millisElapsed() < 120)
        {
            return;
        }
        
        animationTimer.mark();
        
        if(facing.equals("right"))
        {
            setImage(grandmaRight[imageIndex]);
        }
        else
        {
            setImage(grandmaLeft[imageIndex]);
        }
        
        imageIndex = (imageIndex + 1) % grandmaRight.length;
    }
    
    public void act()
    {
<<<<<<< Updated upstream
        /**
        if(Greenfoot.isKeyDown("left")){
=======
        boolean moving = false;
        
        if(Greenfoot.isKeyDown("left"))
        {
>>>>>>> Stashed changes
            setLocation(getX() - 3, getY());
            facing = "left";
            moving = true;
        }
        
        if(Greenfoot.isKeyDown("right"))
        {
            setLocation(getX() + 3, getY());
            facing = "right";
            moving = true;
        }
        
        if(Greenfoot.isKeyDown("up"))
        {
            setLocation(getX(), getY() - 3);
            moving = true;
        }
        
        if(Greenfoot.isKeyDown("down"))
        {
            setLocation(getX(), getY() + 3);
            moving = true;
        }
        
        if(moving)
        {
            animateGrandma();
        }
        else
        {
            if(facing.equals("right"))
            {
                setImage(grandmaRight[0]);
            }
            else
            {
                setImage(grandmaLeft[0]);
            }
        }
        */
    }
}