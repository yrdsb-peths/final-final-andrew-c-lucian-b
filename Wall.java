import greenfoot.*;

public class Wall extends Actor
{
    public Wall(int w, int h)
    {
        GreenfootImage img = new GreenfootImage(w, h);
    
        // 🔥 TEMP: make visible
        img.setColor(new Color(255, 0, 0, 80));
        img.fill();
    
        setImage(img);
    }
}