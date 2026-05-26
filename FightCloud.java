import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FightCloud here.
 * 
 * @Lucian and Andrew
 * @May 26, 2026
 */

public class FightCloud extends Actor
{
    private GreenfootImage[] frames;
    private int imageIndex = 0;
    private int timer = 0;

    public FightCloud()
    {
        frames = new GreenfootImage[4];

        for (int i = 0; i < frames.length; i++)
        {
            frames[i] = new GreenfootImage("fight_cloud/fight_cloud" + (i+1) + ".png");
            frames[i].scale(180, 180);
        }

        setImage(frames[0]);
    }

    public void act()
    {
        animateLoop();
    }

    private void animateLoop()
    {
        timer++;

        if (timer % 6 == 0)
        {
            imageIndex = (imageIndex + 1) % frames.length;
            setImage(frames[imageIndex]);
        }
    }
}