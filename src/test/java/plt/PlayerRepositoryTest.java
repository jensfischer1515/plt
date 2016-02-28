package plt;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.BDDAssertions.then;
import static plt.Player.newPlayer;

@PartyLootTrackerTests
@RunWith(SpringJUnit4ClassRunner.class)
public class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @After
    public void cleanup() {
        playerRepository.deleteAllInBatch();
    }

    @Test
    public void should_save_player() {
        // GIVEN
        Player transientPlayer = newPlayer()
                .email("you@localtest.me")
                .name("You")
                .build();

        // WHEN
        Player persistentPlayer = playerRepository.saveAndFlush(transientPlayer);

        // THEN
        then(persistentPlayer.getId()).isNotNull();
        then(persistentPlayer.getCreatedDate()).isNotNull();
        then(persistentPlayer.getLastModifiedDate()).isNotNull();
        then(persistentPlayer.getVersion()).isNotNull();
    }

    @Test
    public void should_find_player_by_id() {
        // GIVEN
        Player persistedPlayer = playerRepository.saveAndFlush(newPlayer()
                .email("you@localtest.me")
                .name("You")
                .build());

        // WHEN
        Player foundPlayer = playerRepository.findOne(persistedPlayer.getId());

        // THEN
        then(foundPlayer).isNotNull();
    }

    @Test
    public void should_find_all_players() {
        // GIVEN
        Player persistedPlayer = playerRepository.saveAndFlush(newPlayer()
                .email("you@localtest.me")
                .name("You")
                .build());

        // WHEN
        Page<Player> foundPlayers = playerRepository.findAll(new PageRequest(0, 10));

        // THEN
        then(foundPlayers.getContent()).hasSize(1);
        then(foundPlayers
                .getContent()
                .get(0)).isEqualTo(persistedPlayer);
    }
}
