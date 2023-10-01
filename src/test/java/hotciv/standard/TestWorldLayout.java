package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

/** Skeleton class for AlphaCiv test cases

 Updated Oct 2015 for using Hamcrest matchers

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
public class TestWorldLayout {
    private WorldLayout layout_delta;
    private WorldLayout layout_alpha;

    /** Fixture for WorldLayout testing. */
    @Before
    public void setUp() {
        TileImpl[][] alpha_tile= new TileImpl[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
        TileImpl[][] delta_tile= new TileImpl[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
        layout_delta = new DeltaCivWorld();
        layout_delta.createWorld(alpha_tile);
        layout_alpha = new AlphaCivWorld();
        layout_delta.createWorld(delta_tile);
    }
}

