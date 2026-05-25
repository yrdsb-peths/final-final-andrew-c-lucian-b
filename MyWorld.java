import greenfoot.*;

public class MyWorld extends World {
    public MyWorld() {
        super(900, 600, 1, false);
        
        Grandma grandma = new Grandma();
        addObject(grandma, 300, 200);
        
        Fly fly = new Fly();
        addObject(fly, 400, 300);
    }
}
