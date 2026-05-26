import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fly here.
 * 
 * @Lucian and Andrew
 * @May 20, 2026
 */
public class Fly extends Actor
{
    private int speed = 4;

    private GreenfootImage[] flyRight;
    private GreenfootImage[] flyVertical;

    private int imageIndex = 0;
    private int animationCounter = 0;

    private boolean facingRight = true;
    private boolean facingDown = true;

    public Fly()
    {
        loadAnimations();
    }

    public void act()
    {
        checkCatch();
        moveFly();
    }

    private void checkCatch()
    {
        if (isTouching(Grandma.class))
        {
            Grandma g = (Grandma) getOneIntersectingObject(Grandma.class);
            if (g == null) return;
    
            int cx = (getX() + g.getX()) / 2;
            int cy = (getY() + g.getY()) / 2;
    
            getWorld().addObject(new FightCloud(), cx, cy);
    
            getWorld().removeObject(g);
            getWorld().removeObject(this);
        }
    }

    private void loadAnimations()
    {
        flyRight = new GreenfootImage[4];
        flyVertical = new GreenfootImage[4];

        for (int i = 0; i < 4; i++)
        {
            flyRight[i] = new GreenfootImage("fly_move/fly_horizontal" + (i+1) + ".png");
            flyVertical[i] = new GreenfootImage("fly_move/fly_vertical" + (i+1) + ".png");

            flyRight[i].scale(flyRight[i].getWidth()/3, flyRight[i].getHeight()/3);
            flyVertical[i].scale(flyVertical[i].getWidth()/3, flyVertical[i].getHeight()/3);
        }
    }

    private void moveFly()
    {
        boolean moving = false;

        int dx = 0;
        int dy = 0;

        if (Greenfoot.isKeyDown("w")) { dy -= speed; facingDown = false; }
        if (Greenfoot.isKeyDown("s")) { dy += speed; facingDown = true; }
        if (Greenfoot.isKeyDown("a")) { dx -= speed; facingRight = false; }
        if (Greenfoot.isKeyDown("d")) { dx += speed; facingRight = true; }

        if (dx != 0 || dy != 0)
        {
            setLocation(getX() + dx, getY() + dy);
            moving = true;

            if (dx != 0) animateHorizontal();
            else animateVertical();
        }

        if (!moving)
        {
            setImage(flyRight[0]);
        }
    }

    private void animateVertical()
    {
        animationCounter++;

        if (animationCounter % 5 == 0)
        {
            imageIndex = (imageIndex + 1) % flyVertical.length;

            GreenfootImage img = new GreenfootImage(flyVertical[imageIndex]);
            if (facingDown) img.rotate(180);

            setImage(img);
        }
    }

    private void animateHorizontal()
    {
        animationCounter++;

        if (animationCounter % 5 == 0)
        {
            imageIndex = (imageIndex + 1) % flyRight.length;

            GreenfootImage img = new GreenfootImage(flyRight[imageIndex]);
            if (!facingRight) img.mirrorHorizontally();

            setImage(img);
        }
    }
}