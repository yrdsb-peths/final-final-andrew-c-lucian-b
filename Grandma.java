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

    private int speed = 2;

    public Grandma()
    {
        for (int i = 0; i < grandmaRight.length; i++)
        {
            grandmaRight[i] = new GreenfootImage("grandma_walk/grandma_walk" + (i+1) + ".png");
            grandmaRight[i].scale(150, 150);

            grandmaLeft[i] = new GreenfootImage("grandma_walk/grandma_walk" + (i+1) + ".png");
            grandmaLeft[i].mirrorHorizontally();
            grandmaLeft[i].scale(150, 150);
        }

        setImage(grandmaRight[0]);
        animationTimer.mark();
    }

    public void act()
    {
        chaseFly();
        animateGrandma();
    }

    private void chaseFly()
    {
        if (getWorld().getObjects(Fly.class).isEmpty()) return;

        Fly fly = (Fly) getWorld().getObjects(Fly.class).get(0);

        int dx = fly.getX() - getX();
        int dy = fly.getY() - getY();

        if (Math.abs(dx) > Math.abs(dy))
        {
            if (dx > 0)
            {
                setLocation(getX() + speed, getY());
                facing = "right";
            }
            else
            {
                setLocation(getX() - speed, getY());
                facing = "left";
            }
        }
        else
        {
            if (dy > 0)
                setLocation(getX(), getY() + speed);
            else
                setLocation(getX(), getY() - speed);
        }
    }

    private void animateGrandma()
    {
        if (animationTimer.millisElapsed() < 120) return;

        animationTimer.mark();

        if (facing.equals("right"))
            setImage(grandmaRight[imageIndex]);
        else
            setImage(grandmaLeft[imageIndex]);

        imageIndex = (imageIndex + 1) % grandmaRight.length;
    }
}