package hotciv.visual;

import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;
import hotciv.view.GfxConstants;
import minidraw.framework.*;
import minidraw.standard.AbstractTool;
import minidraw.standard.NullTool;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public class CompositionTool extends AbstractTool implements Tool {

    private Game game;

    private Tool usedTool;

    public CompositionTool(DrawingEditor editor, Game game){
        super(editor);
        this.game = game;
        usedTool = new NullTool();
    }
    @Override
    public void mouseDown(MouseEvent e, int x, int y) {

        editor().drawing().lock();

        //obtain game position
        Position p = GfxConstants.getPositionFromXY(x,y);

        //check for figures
        Drawing model = editor().drawing();
        Figure checkFigure = model.findFigure(e.getX(), e.getY());

        //scenario to call end of turn tool
        if(x >=GfxConstants.TURN_SHIELD_X && x<=GfxConstants.TURN_SHIELD_X+GfxConstants.TILESIZE && y >= GfxConstants.TURN_SHIELD_Y && y<= GfxConstants.TURN_SHIELD_Y+GfxConstants.TILESIZE)
        {
            System.out.println("Using EndOfTurn Tool");
            usedTool = new EndOfTurnTool(editor,game);
        }

        if(e.isShiftDown())
        {
            usedTool = new ActionTool(editor,game);
        }

        usedTool.mouseDown(e,x,y);


    }

    @Override
    public void mouseDrag(MouseEvent e, int x, int y) {
        usedTool.mouseDrag(e,x,y);
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y)
    {
        usedTool.mouseUp(e,x,y);
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
