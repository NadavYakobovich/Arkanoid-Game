import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * id - 207984956.
 * name nadav yakobovich.
 * the SpriteCollection class.
 */
public class SpriteCollection {
    /**
     * SpriteCollection class contain a list that include all the sprite object of the game.
     */
    private List<Sprite> spiteList;

    /**
     * Constructor.
     * Initialize a new ArrayList fot the collection
     */
    public SpriteCollection() {
        this.spiteList = new ArrayList<Sprite>();
    }

    /**
     * add a new Sprite to the collection (like paddle or ball).
     *
     * @param s - the sprite that needed to added
     */
    public void addSprite(Sprite s) {
        this.spiteList.add(s);
    }

    /**
     * call timePassed() on all sprites.
     * @param s - the s that need to remove
     */
    public void removeSprite(Sprite s) {
        this.spiteList.remove(s);
    }

    /**
     * notify that the change in spitelist.
     */
    public void notifyAllTimePassed() {
        //make a copy of the spite list because there are changed while it is being iterated
        List<Sprite> copyListSpite = new ArrayList<>(this.spiteList);
        for (Sprite spite : copyListSpite) {
            spite.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d DrawSurface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite spite : this.spiteList) {
            spite.drawOn(d);
        }
    }
}