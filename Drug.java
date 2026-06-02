import greenfoot.*;

public class Drug extends Actor
{
    private double dx;
    private double dy;

    private int damage = 40;

    public Drug(double dx, double dy)
    {
        setImage("drugs.png");
        getImage().scale(75, 75);

        double speed = 4.5;

        this.dx = dx * speed;
        this.dy = dy * speed;
    }

    public void act()
    {
        World world = getWorld();

        if (world == null)
        {
            return;
        }

        setLocation((int)(getX() + dx), (int)(getY() + dy));

        world = getWorld();
        if (world == null)
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

            world.removeObject(this);
            return; // 🔥 CRITICAL: stop immediately after removal
        }

        world = getWorld();
        if (world == null)
        {
            return;
        }

        if (getX() < 0 || getX() > world.getWidth() ||
            getY() < 0 || getY() > world.getHeight())
        {
            world.removeObject(this);
            return; // 🔥 CRITICAL again
        }
    }
}