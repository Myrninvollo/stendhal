package games.stendhal.server.core.engine;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import games.stendhal.common.filter.FilterCriteria;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;

import marauroa.common.game.RPObject;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import utilities.PlayerTestHelper;

public class PlayerListTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		MockStendlRPWorld.get();
	}

	@AfterClass
	public static void teardownAfterClass() throws Exception {
		
		MockStendlRPWorld.reset();
	}

	@Test
	public void testPlayerList() {
		@SuppressWarnings("unused")
		final
		PlayerList list = new PlayerList();
		
	}

	@Test
	public void testGetOnlinePlayer() {
		final PlayerList list = new PlayerList();
		assertThat(list.size(), is(0));
		final Player jack = PlayerTestHelper.createPlayer("jack");
		list.add(jack);
		assertThat(list.size(), is(1));
		assertSame(jack, list.getOnlinePlayer("jack"));
		final Player jack2 = PlayerTestHelper.createPlayer("jack");
		list.add(jack2);
		assertThat(list.size(), is(1));
		assertThat(jack2, sameInstance(list.getOnlinePlayer("jack")));
		assertThat(jack, not(sameInstance(list.getOnlinePlayer("jack"))));
		assertTrue(list.remove(jack));
		assertThat(list.size(), is(0));
	}

	@Test
	public void testAllPlayersModify() {
		final Player jack = PlayerTestHelper.createPlayer("jack");
		final Player bob = PlayerTestHelper.createPlayer("bob");
		final Player ghost = PlayerTestHelper.createPlayer("ghost");
		ghost.setGhost(true);
		final PlayerList list = new PlayerList();
		list.add(jack);
		list.add(bob);
		list.add(ghost);
		final String testString = "testString";
		list.forAllPlayersExecute(new Task<Player>() {
			public void execute(final Player player) {
				player.put(testString, testString);
			}
		});

		assertEquals(testString, jack.get(testString));
		assertEquals(testString, bob.get(testString));
		assertEquals(testString, ghost.get(testString));

		list.forFilteredPlayersExecute(new Task<Player>() {
			public void execute(final Player player) {
				player.put(testString, "");
			}
		}, new FilterCriteria<Player>() {
			public boolean passes(final Player o) {
				return o.isGhost();
			}
		});
		assertEquals(testString, jack.get(testString));
		assertEquals(testString, bob.get(testString));
		assertEquals("", ghost.get(testString));
	}

	@Test
	public void testAllPlayersRemove() {
		final Player jack = PlayerTestHelper.createPlayer("jack");
		final Player bob = PlayerTestHelper.createPlayer("bob");
		final Player ghost = PlayerTestHelper.createPlayer("ghost");
		ghost.setGhost(true);
		final PlayerList list = new PlayerList();
		list.add(jack);
		list.add(bob);
		list.add(ghost);
		list.forAllPlayersExecute(new Task<Player>() {

			public void execute(final Player player) {
				list.remove(player);
			}

		});
		assertThat(list.size(), is(0));
	}

	@Test
	public void testGetOnlineCaseInsensitivePlayer() {
		final PlayerList list = new PlayerList();
		assertThat(list.size(), is(0));
		final Player jack = PlayerTestHelper.createPlayer("jack");
		list.add(jack);
		assertThat(list.size(), is(1));
		assertSame(jack, list.getOnlinePlayer("jack"));
		assertSame(jack, list.getOnlinePlayer("Jack"));
		assertSame(jack, list.getOnlinePlayer("jAck"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddPlayerWithEqualName() {
		final PlayerList list = new PlayerList();
		final Player jack = new Player(new RPObject()) {
		};
		list.add(jack);

	}

}
