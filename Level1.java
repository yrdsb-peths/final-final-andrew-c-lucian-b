import greenfoot.*;

public class Level1 extends World
{
    private Fly player;

    private GreenfootImage bg;
    private int bgX = 0;
    private int bgY = 0;

    private int sampleSize = 6; // 🔥 slightly bigger = fewer gaps

    public Level1()
    {
        super(800, 600, 1);

        bg = new GreenfootImage("Level 1 background.png");

        setBackground(new GreenfootImage(getWidth(), getHeight()));

        generateWallsFromImage();

        player = new Fly();
        addObject(player, getWidth()/2, getHeight()/2);
    }

    public void act()
    {
        drawBackground();
    }

    private void drawBackground()
    {
        GreenfootImage view = getBackground();
        view.clear();
        view.drawImage(bg, bgX, bgY);
    }

    // 🔥 FIXED WALL GENERATION
    private void generateWallsFromImage()
    {
        for (int x = 0; x < bg.getWidth(); x += 3) // 🔥 smaller sample = better accuracy
        {
            for (int y = 0; y < bg.getHeight(); y += 3)
            {
                Color c = bg.getColorAt(x, y);
    
                // 🔥 COLOR-BASED WALL DETECTION (NOT brightness anymore)
                if (c.getRed() < 120 && c.getGreen() < 100 && c.getBlue() < 80)
                {
                    Wall w = new Wall(3, 3);
    
                    // 🔥 CENTER properly
                    addObject(w, x + 1, y + 1);
                }
            }
        }
    }

    public void scrollWorld(int dx, int dy)
    {
        // X movement
        if (!player.willTouchWall(dx, 0))
        {
            for (Actor a : getObjects(Actor.class))
            {
                if (a instanceof Fly) continue;

                a.setLocation(a.getX() - dx, a.getY());
            }
            bgX -= dx;
        }

        // Y movement
        if (!player.willTouchWall(0, dy))
        {
            for (Actor a : getObjects(Actor.class))
            {
                if (a instanceof Fly) continue;

                a.setLocation(a.getX(), a.getY() - dy);
            }
            bgY -= dy;
        }
    }
}