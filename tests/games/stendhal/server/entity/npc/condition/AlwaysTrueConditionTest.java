/**
 * Generated by Agitar build: JUnitFactory Version 2.1.0.000576 (Build date: Oct 19, 2007) [2.1.0.000576]
 * JDK Version: 1.5.0_11
 *
 * Generated on 11.11.2007 05:53:06
 * Time to generate: 00:24.052 seconds
 *
 */

package games.stendhal.server.entity.npc.condition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import games.stendhal.server.entity.npc.parser.ConversationParser;

import org.junit.Test;

import utilities.PlayerTestHelper;
import utilities.SpeakerNPCTestHelper;

public class AlwaysTrueConditionTest {

	@Test
	public void testConstructor() throws Throwable {
		new AlwaysTrueCondition();
	}

	@Test
	public void testEquals() throws Throwable {
		assertFalse(new AlwaysTrueCondition().equals(null));

		AlwaysTrueCondition obj = new AlwaysTrueCondition();
		assertTrue(obj.equals(obj));

		assertFalse(new AlwaysTrueCondition().equals(Integer.valueOf(100)));

		assertTrue(new AlwaysTrueCondition().equals(new AlwaysTrueCondition()));
		assertTrue(new AlwaysTrueCondition().equals(new AlwaysTrueCondition() {
		}));
	}
	@Test
	public void testFire() throws Throwable {
		assertTrue(new AlwaysTrueCondition().fire(
				PlayerTestHelper.createPlayer("player"),
				ConversationParser.parse("testAllwaysTrueConditionText"),
				SpeakerNPCTestHelper.createSpeakerNPC()));
	}
	@Test
	public void testHashCode() throws Throwable {
		assertEquals(17, new AlwaysTrueCondition().hashCode());
	}
	@Test
	public void testToString() throws Throwable {
		assertEquals("true", new AlwaysTrueCondition().toString());
	}
}
