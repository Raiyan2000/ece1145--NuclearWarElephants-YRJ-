package hotciv.visual;

import hotciv.framework.Game;
import hotciv.framework.HotCivFactory;
import hotciv.standard.AlphaCivFactory;
import hotciv.standard.GameImpl;
import hotciv.standard.SemiCivFactory;
import hotciv.stub.StubGame2;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

public class ShowSemiCiv {
    public static void main(String[] args) {

        HotCivFactory alphaCiv = new AlphaCivFactory();

        Game game = new GameImpl(alphaCiv);

        DrawingEditor editor =
                new MiniDrawApplication( "Move any unit using the mouse",
                        new HotCivFactory4(game) );
        editor.open();
        editor.showStatus("Move units to see Game's moveUnit method being called.");

        editor.setTool(new CompositionTool(editor,game));
    }
}
