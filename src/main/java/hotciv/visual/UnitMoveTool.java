package hotciv.visual;

import hotciv.framework.City;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;
import minidraw.framework.*;
import minidraw.standard.AbstractTool;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class UnitMoveTool extends AbstractTool implements Tool
{
        protected Figure selectedFigure;

        protected GameImpl game;

        private Position initialSelectPosition;

        private DrawingEditor message;

        public UnitMoveTool(DrawingEditor editor)
        {
            super(editor);
        }

        public void mouseDown(MouseEvent e, int x, int y)
        {
            Drawing model = editor().drawing();
            model.lock();

            //obtains the figure selected
            selectedFigure = model.findFigure(e.getX(), e.getY());

            //get position coordinates
            Position clickedPosition = new Position(x,y);

            //see what type of object is being clicked on
            Unit clickedUnit = game.getUnitAt(clickedPosition);
            City clickedCity = game.getCityAt(clickedPosition);

            if(selectedFigure != null && e.isShiftDown())
            {
                initialSelectPosition = new Position(x,y);
            }
            else if(selectedFigure != null && !e.isShiftDown())
            {
                //a city is clicked on, so display city stats
                if(clickedCity != null)
                {

                }

                //a unit is being clicked, so display unit stats
                if (clickedCity != null)
                {
                    String cityOwner = clickedCity.getOwner().toString();
                    String cityFocus = clickedCity.getWorkforceFocus();
                    String cityProduce = clickedCity.getProduction();
                    String cityPopulate = Integer.toString(clickedCity.getSize());

                    message.showStatus("Owner: "+cityOwner+"Focus: "+cityFocus+"Production: "+cityProduce+"Population: "+cityPopulate);
                }
            }
            else
            {
                model.clearSelection();
            }
        }

        public void mouseDrag(MouseEvent e, int x, int y)
        {
            return;
        }

        public void mouseUp(MouseEvent e, int x, int y)
        {
            editor().drawing().unlock();

            if(selectedFigure != null)
            {
                Unit selectedUnit = game.getUnitAt(initialSelectPosition);

                if(selectedUnit != null)
                {
                    Position to = new Position(x,y);
                    boolean canMove = game.moveUnit(initialSelectPosition,to);
                }
            }
        }
        public void mouseMove(MouseEvent e, int x, int y)
        {
            return;
        }
        public void keyDown(KeyEvent e, int key)
        {
            return;
        }

}
