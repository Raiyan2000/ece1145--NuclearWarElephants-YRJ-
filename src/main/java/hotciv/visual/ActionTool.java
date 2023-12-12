package hotciv.visual;

import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;
import hotciv.view.CivDrawing;
import hotciv.view.GfxConstants;
import minidraw.framework.*;
import minidraw.standard.AbstractTool;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class ActionTool extends AbstractTool implements Tool {

    private Game game;

    public ActionTool(DrawingEditor editor, Game game){
        super(editor);
        this.game = game;
    }
    @Override
    public void mouseDown(MouseEvent e, int x, int y) {

        editor().drawing().lock();

        //locate unit
        Unit clickedOnUnit = game.getUnitAt(GfxConstants.getPositionFromXY(x,y));

        if(e.isShiftDown() && clickedOnUnit != null) {
            game.performUnitActionAt(GfxConstants.getPositionFromXY(x, y));
        }
    }

    @Override
    public void mouseDrag(MouseEvent e, int x, int y) {
        return;
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        editor().drawing().unlock();
    }

    @Override
    public void mouseMove(MouseEvent evt, int x, int y) {
        return;
    }

    @Override
    public void keyDown(KeyEvent evt, int key) {
        return;
    }
}
