import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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

    private int damage = 20;

    public Shoe(double dirX, double dirY)
    {
        setImage("shoe.png");
        getImage().scale(40, 40);

        double speed = 4;

        dx = dirX * speed;
        dy = dirY * speed;

        updateRotation();
    }

    public void act()
    {
        if (getWorld() == null)
        {
            return;
        }

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
        if (getWorld() == null)
        {
            return;
        }

        if (isTouching(Fly.class))
        {
            Fly fly = (Fly)getOneIntersectingObject(Fly.class);

            if (fly != null)
            {
                fly.takeDamage(damage);
            }

            if (getWorld() != null)
            {
                getWorld().removeObject(this);
            }
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