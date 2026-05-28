import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Grandma here.
 * 
 * @Lucian and Andrew
 * @May 20, 2026
 */

public class Grandma extends Actor
{
    private GreenfootImage[] grandmaRight = new GreenfootImage[4];
    private GreenfootImage[] grandmaLeft = new GreenfootImage[4];

    private String facing = "right";

    private SimpleTimer animationTimer = new SimpleTimer();
    private int imageIndex = 0;

    private int speed = 2;
    private int throwCooldown = 0;

    public Grandma()
    {
        for (int i = 0; i < grandmaRight.length; i++)
        {
            grandmaRight[i] = new GreenfootImage("grandma_walk/grandma_walk" + (i + 1) + ".png");
            grandmaRight[i].scale(150, 150);

            grandmaLeft[i] = new GreenfootImage("grandma_walk/grandma_walk" + (i + 1) + ".png");
            grandmaLeft[i].mirrorHorizontally();
            grandmaLeft[i].scale(150, 150);
        }

        setImage(grandmaRight[0]);
        animationTimer.mark();
    }

    public void act()
    {
        chaseFly();
        throwShoe();
        animateGrandma();
        stayInBounds();
    }

    private void chaseFly()
    {
        if (getWorld() == null)
        {
            return;
        }

        java.util.List<Fly> flies = getWorld().getObjects(Fly.class);

        if (flies.isEmpty())
        {
            return;
        }

        Fly fly = flies.get(0);

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
            {
                setLocation(getX(), getY() + speed);
            }
            else
            {
                setLocation(getX(), getY() - speed);
            }
        }
    }

    private void throwShoe()
    {
        if (throwCooldown > 0)
        {
            throwCooldown--;
            return;
        }

        if (getWorld() == null)
        {
            return;
        }

        java.util.List<Fly> flies = getWorld().getObjects(Fly.class);

        if (flies.isEmpty())
        {
            return;
        }

        Fly fly = flies.get(0);

        int dx = fly.getX() - getX();
        int dy = fly.getY() - getY();

        double length = Math.sqrt(dx * dx + dy * dy);

        if (length == 0)
        {
            return;
        }

        int dirX = (int) Math.round(dx / length);
        int dirY = (int) Math.round(dy / length);

        getWorld().addObject(new Shoe(dirX, dirY), getX(), getY());

        throwCooldown = 60;
    }

    private void animateGrandma()
    {
        if (animationTimer.millisElapsed() < 120)
        {
            return;
        }

        animationTimer.mark();

        if (facing.equals("right"))
        {
            setImage(grandmaRight[imageIndex]);
        }
        else
        {
            setImage(grandmaLeft[imageIndex]);
        }

        imageIndex = (imageIndex + 1) % grandmaRight.length;
    }

    private void stayInBounds()
    {
        if (getX() < 0)
        {
            setLocation(0, getY());
        }

        if (getX() > getWorld().getWidth() - 1)
        {
            setLocation(getWorld().getWidth() - 1, getY());
        }

        if (getY() < 0)
        {
            setLocation(getX(), 0);
        }

        if (getY() > getWorld().getHeight() - 1)
        {
            setLocation(getX(), getWorld().getHeight() - 1);
        }
    }
}