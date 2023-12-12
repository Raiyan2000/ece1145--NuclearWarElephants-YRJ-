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
import minidraw.standard.handlers.DragTracker;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class UnitMoveTool extends AbstractTool implements Tool
{
        protected Figure selectedFigure;

        private Position initialSelectPosition;
        protected Tool fChild;

        protected Tool cachedNullTool;

        private Game game;

        private int initialXPixel;

        private int initialYPixel;

        public UnitMoveTool(DrawingEditor editor, Game game)
        {
            super(editor);
            fChild = cachedNullTool = new NullTool();
            this.game = game;
            selectedFigure = null;
            initialSelectPosition = null;
            initialXPixel = 0;
            initialYPixel = 0;
        }

        public void mouseDown(MouseEvent e, int x, int y)
        {

            Drawing model = editor().drawing();

            model.lock();

            selectedFigure = model.findFigure(e.getX(), e.getY());

            if (selectedFigure != null) {
                fChild = createDragTracker(selectedFigure);

                Unit clickedUnit = game.getUnitAt(GfxConstants.getPositionFromXY(x,y));

                if(clickedUnit != null)
                {
                    System.out.println("Unit Present");
                    fChild = createDragTracker( selectedFigure );
                    Position temp = GfxConstants.getPositionFromXY(x,y);
                    initialSelectPosition = new Position(temp.getRow(), temp.getColumn());
                    initialXPixel = x;
                    initialYPixel = y;
                }
            } else {
                System.out.println("No figure");
            }

            fChild.mouseDown(e,x,y);

        }

        public void mouseDrag(MouseEvent e, int x, int y)
        {
           fChild.mouseDrag(e, x, y);
        }

        public void mouseUp(MouseEvent e, int x, int y)
        {
            editor().drawing().unlock();

            if(selectedFigure != null && initialSelectPosition != null) {
                //call moveUnit function
                System.out.println("Call unit move function");
                Position to = GfxConstants.getPositionFromXY(x,y);
                Position movedTo = new Position(to.getRow(),to.getColumn());
                boolean canMove = game.moveUnit(initialSelectPosition, movedTo);
                if(canMove)
                {
                    System.out.println("can move");
                }
                else
                {
                    selectedFigure.moveBy(initialXPixel - x,initialYPixel - y);
                    System.out.println("Cant move");
                }
            }

            fChild.mouseUp(e, x, y);
            fChild = cachedNullTool;
            selectedFigure = null;
        }
        public void mouseMove(MouseEvent e, int x, int y)
        {
            fChild.mouseMove(e,x,y);
        }
        public void keyDown(KeyEvent e, int key)
        {
            return;
        }

        protected Tool createDragTracker(Figure f) {
            return new DragTracker(editor(), f);
        }

}
