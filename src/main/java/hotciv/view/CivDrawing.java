package hotciv.view;


import hotciv.framework.*;
import minidraw.framework.*;
import minidraw.standard.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/** CivDrawing is a specialized Drawing (model component) from
 * MiniDraw that dynamically builds the list of Figures for MiniDraw
 * to render the Unit and other information objects that are visible
 * in the Game instance.
 *
 * TODO: This is a TEMPLATE for the SWEA Exercise! This means
 * that it is INCOMPLETE and that there are several options
 * for CLEANING UP THE CODE when you add features to it!

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

public class CivDrawing 
  implements Drawing, GameObserver {
  
  protected Drawing delegate;
  /** store all moveable figures visible in this drawing = units */
  protected Map<Unit,UnitFigure> unitFigureMap;

  /**stores city figures in this drawing */
  protected Map<City,CityFigure> unitCityMap;

  /** the Game instance that this CivDrawing is going to render units
   * from */
  protected Game game;
  
  public CivDrawing( DrawingEditor editor, Game game ) {
    super();
    this.delegate = new StandardDrawing();
    this.game = game;
    this.unitFigureMap = new HashMap<>();

    // register this unit drawing as listener to any game state
    // changes...
    game.addObserver(this);
    // ... and build up the set of figures associated with
    // units in the game.
    defineUnitMap();
    // and the set of 'icons' in the status panel
    defineIcons();
  }
  
  /** The CivDrawing should not allow client side
   * units to add and manipulate figures; only figures
   * that renders game objects are relevant, and these
   * should be handled by observer events from the game
   * instance. Thus this method is 'killed'.
   */
  public Figure add(Figure arg0) {
    throw new RuntimeException("Should not be used...");
  }


  /** erase the old list of units, and build a completely new
   * one from scratch by iterating over the game world and add
   * Figure instances for each unit in the world.
   */
  protected void defineUnitMap() {
    // ensure no units of the old list are accidental in
    // the selection!
    clearSelection();

    // remove all unit figures in this drawing
    removeAllUnitFigures();

    // iterate world, and create a unit figure for
    // each unit in the game world, as well as
    // create an association between the unit and
    // the unitFigure in 'unitFigureMap'.
    Position p;
    for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
      for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
        p = new Position(r,c);
        Unit unit = game.getUnitAt(p);
        if ( unit != null ) {
          String type = unit.getTypeString();
          // convert the unit's Position to (x,y) coordinates
          Point point = new Point( GfxConstants.getXFromColumn(p.getColumn()),
                                   GfxConstants.getYFromRow(p.getRow()) );
          UnitFigure unitFigure =
            new UnitFigure( type, point, unit );
          unitFigure.addFigureChangeListener(this);
          unitFigureMap.put(unit, unitFigure);

          // also insert in delegate list as it is
          // this list that is iterated by the
          // graphics rendering algorithms
          delegate.add(unitFigure);
        }
      }
    }
  }

  /** remove all unit figures in this
   * drawing, and reset the map (unit->unitfigure).
   * It is important to actually remove the figures
   * as it forces a graphical redraw of the screen
   * where the unit figure was.
   */
  protected void removeAllUnitFigures() {
    for (Unit u : unitFigureMap.keySet()) {
      UnitFigure uf = unitFigureMap.get(u);
      delegate.remove(uf);
    }
    unitFigureMap.clear();
  }

  protected ImageFigure turnShieldIcon;
  protected ImageFigure unitShieldIcon;
  protected ImageFigure cityShieldIcon;
  protected TextFigure ageIcon;
  protected ImageFigure refreshIcon;
  protected ImageFigure productionIcon;
  protected ImageFigure unitInfoIcon;
  protected ImageFigure workforceFocusIcon;

  protected void defineIcons() {

    if(game.getPlayerInTurn() == Player.RED) {
      turnShieldIcon =
              new ImageFigure("redshield",
                      new Point(GfxConstants.TURN_SHIELD_X,
                              GfxConstants.TURN_SHIELD_Y));
      // insert in delegate figure list to ensure graphical
      // rendering.
      delegate.add(turnShieldIcon);

      //adds the red player unit shield icon onto map
      unitShieldIcon = new ImageFigure("redshield",new Point(GfxConstants.UNIT_SHIELD_X,GfxConstants.UNIT_SHIELD_Y));
      delegate.add(unitShieldIcon);

      //adds the red player city shield icon onto map
      cityShieldIcon = new ImageFigure("redshield",new Point(GfxConstants.CITY_SHIELD_X,GfxConstants.CITY_SHIELD_Y));
      delegate.add(cityShieldIcon);
    }
    else
    {
      //blue city shield at top
      turnShieldIcon = new ImageFigure("blueshield",new Point(GfxConstants.TURN_SHIELD_X,GfxConstants.TURN_SHIELD_Y));
      delegate.add(turnShieldIcon);

      //blue unit shield
      unitShieldIcon = new ImageFigure("blueshield",new Point(GfxConstants.UNIT_SHIELD_X,GfxConstants.UNIT_SHIELD_Y));
      delegate.add(unitShieldIcon);

      //blue city shield
      cityShieldIcon = new ImageFigure("blueshield",new Point(GfxConstants.CITY_SHIELD_X,GfxConstants.CITY_SHIELD_Y));
      delegate.add(cityShieldIcon);
    }

    //add refresh button onto the game
    refreshIcon = new ImageFigure("refresh",new Point(GfxConstants.REFRESH_BUTTON_X, GfxConstants.REFRESH_BUTTON_Y));
    delegate.add(refreshIcon);

    //add age information icon
    int age = game.getAge();

    //checks if age should be BC or AD
    if(age>0) {
      String year = Integer.toString(game.getAge()); //obtain Age of Game
      ageIcon = new TextFigure(year+" AD", new Point(GfxConstants.AGE_TEXT_X, GfxConstants.AGE_TEXT_Y));
    }
    else if(age == 0)
    {
      String year = Integer.toString(game.getAge()); //obtain Age of Game
      ageIcon = new TextFigure(year, new Point(GfxConstants.AGE_TEXT_X, GfxConstants.AGE_TEXT_Y));
    }
    else
    {
      String year = Integer.toString(Math.abs(age));
      ageIcon = new TextFigure(year+" BC", new Point(GfxConstants.AGE_TEXT_X, GfxConstants.AGE_TEXT_Y));
    }
    delegate.add(ageIcon);

    //add production information icon
    productionIcon = new ImageFigure("black",new Point(GfxConstants.CITY_PRODUCTION_X,GfxConstants.CITY_PRODUCTION_Y));
    delegate.add(productionIcon);

    //add unit info icon
    unitInfoIcon = new ImageFigure("black",new Point(GfxConstants.UNIT_COUNT_X,GfxConstants.UNIT_COUNT_Y));
    delegate.add(unitInfoIcon);

    //add workforce focus icon
    workforceFocusIcon = new ImageFigure("black",new Point(GfxConstants.WORKFORCEFOCUS_X,GfxConstants.WORKFORCEFOCUS_Y));
    delegate.add(workforceFocusIcon);

  }
 
  // === Observer Methods ===

  public void worldChangedAt(Position pos) {
    // TODO: Remove system.out debugging output
    System.out.println( "CivDrawing: world changes at "+pos);
    // this is a really brute-force algorithm: destroy
    // all known units and build up the entire set again
    defineUnitMap();

    // TODO: Cities may change on position as well
    requestUpdate();
  }

  public void turnEnds(Player nextPlayer, int age) {
    // TODO: Remove system.out debugging output
    System.out.println( "CivDrawing: turnEnds at "+age+", next is "+nextPlayer );
    String playername = "red";
    if ( nextPlayer == Player.BLUE ) { playername = "blue"; }
    turnShieldIcon.set( playername+"shield",
                        new Point( GfxConstants.TURN_SHIELD_X,
                                   GfxConstants.TURN_SHIELD_Y ) );
    // TODO: Age output pending
    
  }

  public void tileFocusChangedAt(Position position) {
    // TODO: Implementation pending
    System.out.println( "Fake it: tileFocusChangedAt "+position );

    //obtain the city or unit or both at the position
    Unit changedUnit = game.getUnitAt(position);
    City changedCity = game.getCityAt(position);

    //puts number of moves onto the display screen on right side
    if(changedUnit != null) {
      TextFigure movesLeft = new TextFigure(Integer.toString(changedUnit.getMoveCount()), new Point(GfxConstants.UNIT_COUNT_X, GfxConstants.UNIT_COUNT_Y));
      delegate.add(movesLeft);
    }

    if(changedCity != null)
    {
      if(changedCity.getWorkforceFocus().equals(GameConstants.productionFocus))
      {
        workforceFocusIcon = new ImageFigure("hammer",new Point(GfxConstants.WORKFORCEFOCUS_X,GfxConstants.WORKFORCEFOCUS_Y));
      }
      else
      {
        workforceFocusIcon = new ImageFigure("apple",new Point(GfxConstants.WORKFORCEFOCUS_X,GfxConstants.WORKFORCEFOCUS_Y));
      }
      delegate.add(workforceFocusIcon);

      if(changedCity.getProduction().equals(GameConstants.LEGION))
      {
        productionIcon = new ImageFigure("legion",new Point(GfxConstants.CITY_PRODUCTION_X,GfxConstants.CITY_PRODUCTION_Y));
      }
      else if(changedCity.getProduction().equals(GameConstants.SETTLER))
      {
        productionIcon = new ImageFigure("settler",new Point(GfxConstants.CITY_PRODUCTION_X,GfxConstants.CITY_PRODUCTION_Y));
      }
      else
      {
        productionIcon = new ImageFigure("archer",new Point(GfxConstants.CITY_PRODUCTION_X,GfxConstants.CITY_PRODUCTION_Y));
      }
      delegate.add(productionIcon);

    }



  }

  @Override
  public void requestUpdate() {
    // A request has been issued to repaint
    // everything. We simply rebuild the
    // entire Drawing.
    defineUnitMap();
    defineIcons();
    // TODO: Cities pending
    for(int i=0;i<GameConstants.WORLDSIZE;i++) {
      for(int j=0;j<GameConstants.WORLDSIZE;j++) {

        Position coordinate = new Position(i,j);

        //check for newly built city
        City newCity = game.getCityAt(coordinate);

        if(newCity != null) {

          //convert game coordinates into pixel coordinates
          Point cityPoint = new Point(GfxConstants.getXFromColumn(coordinate.getColumn()),
                  GfxConstants.getYFromRow(coordinate.getRow()));

          //create a city object
          CityFigure cityFig = new CityFigure(newCity, cityPoint);

          //add city onto the game map
          cityFig.addFigureChangeListener(this);
          unitCityMap.put(newCity, cityFig);
        }
      }
    }



  }

  @Override
  public void addToSelection(Figure arg0) {
    delegate.addToSelection(arg0);
  }

  @Override
  public void clearSelection() {
    delegate.clearSelection();
  }

  @Override
  public void removeFromSelection(Figure arg0) {
    delegate.removeFromSelection(arg0);
  }

  @Override
  public List<Figure> selection() {
    return delegate.selection();
  }

  @Override
  public void toggleSelection(Figure arg0) {
    delegate.toggleSelection(arg0);
  }

  @Override
  public void figureChanged(FigureChangeEvent arg0) {
    delegate.figureChanged(arg0);
  }

  @Override
  public void figureInvalidated(FigureChangeEvent arg0) {
    delegate.figureInvalidated(arg0);
  }

  @Override
  public void figureRemoved(FigureChangeEvent arg0) {
    delegate.figureRemoved(arg0);
  }

  @Override
  public void figureRequestRemove(FigureChangeEvent arg0) {
    delegate.figureRequestRemove(arg0);
  }

  @Override
  public void figureRequestUpdate(FigureChangeEvent arg0) {
    delegate.figureRequestUpdate(arg0);
  }

  @Override
  public void addDrawingChangeListener(DrawingChangeListener arg0) {
    delegate.addDrawingChangeListener(arg0);   
  }

  @Override
  public void removeDrawingChangeListener(DrawingChangeListener arg0) {
    delegate.removeDrawingChangeListener(arg0);
  }

  @Override
  public Figure findFigure(int arg0, int arg1) {
    return delegate.findFigure(arg0, arg1);
  }

  @Override
  public Iterator<Figure> iterator() {
    return delegate.iterator();
  }

  @Override
  public void lock() {
    delegate.lock();
  }

  @Override
  public Figure remove(Figure arg0) {
    return delegate.remove(arg0);
  }

  @Override
  public void unlock() {
    delegate.unlock();
  }
}
