import greenfoot.*;

/**
 * Write a description of class Shoe here.
 * 
 * @Lucian and Andrew
 * @May 28, 2026
 */

public class Shoe extends Actor
{
    private double dx;
    private double dy;

    public Shoe(int dirX, int dirY)
    {
        setImage("shoe.png");
        getImage().scale(40, 40);

        double speed = 6;

        dx = dirX * speed;
        dy = dirY * speed;

        updateRotation();
    }

    public void act()
    {
        setLocation((int)(getX() + dx), (int)(getY() + dy));

        updateRotation();
        checkHit();
        checkBounds();
    }

    private void updateRotation()
    {
        double angle = Math.toDegrees(Math.atan2(dy, dx));

        setRotation((int)(angle + 45));
    }

    private void checkHit()
    {
        if (isTouching(Fly.class))
        {
            Actor f = getOneIntersectingObject(Fly.class);

            if (f != null)
            {
                getWorld().removeObject(f);
            }

            getWorld().removeObject(this);
        }
    }

    private void checkBounds()
    {
        if (getWorld() == null)
        {
            return;
        }

        if (getX() < 0 || getX() > getWorld().getWidth() ||
            getY() < 0 || getY() > getWorld().getHeight())
        {
            getWorld().removeObject(this);
        }
    }
}