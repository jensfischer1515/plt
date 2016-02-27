package plt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

import static org.assertj.core.api.BDDAssertions.then;

@PartyLootTrackerTests
@RunWith(SpringJUnit4ClassRunner.class)
public class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void should_save_entity() {
        // GIVEN
        final UUID id = UUID.randomUUID();
        Player player = Player
                .builder()
                .id(id)
                .email("you@localtest.me")
                .name("You")
                .build();

        // WHEN
        player = playerRepository.saveAndFlush(player);

        // THEN
        then(player.getId()).isEqualTo(id);
        then(player.getCreatedDate()).isNotNull();
        then(player.getLastModifiedDate()).isNotNull();
        then(player.getVersion()).isNotNull();
    }
}
