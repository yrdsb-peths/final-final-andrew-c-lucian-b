import greenfoot.*;

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
        moveFly();
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
        int dx = 0;
        int dy = 0;

        if (Greenfoot.isKeyDown("w")) { dy -= speed; facingDown = false; }
        if (Greenfoot.isKeyDown("s")) { dy += speed; facingDown = true; }
        if (Greenfoot.isKeyDown("a")) { dx -= speed; facingRight = false; }
        if (Greenfoot.isKeyDown("d")) { dx += speed; facingRight = true; }

        // 🚨 DO NOT MOVE FLY — move world instead
        if (dx != 0 || dy != 0)
        {
            ((Level1)getWorld()).scrollWorld(dx, dy);

            if (dx != 0) animateHorizontal();
            else animateVertical();
        }
    }

    private void animateHorizontal()
    {
        animationCounter++;

        if (animationCounter % 5 == 0)
        {
            imageIndex = (imageIndex + 1) % flyRight.length;

            GreenfootImage img = new GreenfootImage(flyRight[imageIndex]);

            if (!facingRight)
            {
                img.mirrorHorizontally();
            }

            setImage(img);
        }
    }

    private void animateVertical()
    {
        animationCounter++;

        if (animationCounter % 5 == 0)
        {
            imageIndex = (imageIndex + 1) % flyVertical.length;

            GreenfootImage img = new GreenfootImage(flyVertical[imageIndex]);

            if (facingDown)
            {
                img.rotate(180);
            }

            setImage(img);
        }
    }
    public boolean willTouchWall(int dx, int dy)
    {
        for (Wall w : getWorld().getObjects(Wall.class))
        {
            int futureX = getX();
            int futureY = getY();
    
            int wallFutureX = w.getX() - dx;
            int wallFutureY = w.getY() - dy;
    
            if (Math.abs(futureX - wallFutureX) < w.getImage().getWidth()/2 &&
                Math.abs(futureY - wallFutureY) < w.getImage().getHeight()/2)
            {
                return true;
            }
        }
        return false;
    }
}