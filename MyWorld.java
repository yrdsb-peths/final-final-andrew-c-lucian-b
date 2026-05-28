import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The world.
 * 
 * @Lucian and Andrew
 * @May 20, 2026
 */

public class MyWorld extends World
{
    private boolean gameOver = false;

    public MyWorld()
    {
        super(900, 600, 1, false);

        Grandma grandma = new Grandma();
        addObject(grandma, 200, 200);

        Fly fly = new Fly();
        addObject(fly, 400, 300);
    }

    public void act()
    {
        if (gameOver == false)
        {
            checkGameOver();
        }
    }

    private void checkGameOver()
    {
        if (getObjects(Fly.class).isEmpty())
        {
            gameOver = true;
            showGameOver();
        }
    }

    private void showGameOver()
    {
        showText("GAME OVER", getWidth() / 2, getHeight() / 2);
    }
}