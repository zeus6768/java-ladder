package ladder.domain.game;

import static ladder.domain.ladder.LadderPosition.START_ROW;

import java.util.LinkedHashMap;
import java.util.Map;

import ladder.domain.ladder.Ladder;
import ladder.domain.ladder.LadderPosition;
import ladder.domain.player.Player;
import ladder.domain.player.Players;
import ladder.domain.reward.Reward;
import ladder.domain.reward.Rewards;

public class LadderGame {

    private final Players players;
    private final Rewards rewards;
    private final Ladder ladder;

    private LadderGame(final Players players, final Rewards rewards, final Ladder ladder) {
        this.ladder = ladder;
        this.players = players;
        this.rewards = rewards;
    }

    public static LadderGame of(final Players players, final Rewards rewards, final Ladder ladder) {
        return new LadderGame(players, rewards, ladder);
    }

    public LadderGameResult play() {
        Map<Player, Reward> result = new LinkedHashMap<>();
        players.players().forEach(player -> {
            LadderPosition start = new LadderPosition(START_ROW, players.orderOf(player));
            LadderPosition end = ladder.climbDownFrom(start);
            result.put(player, rewards.get(end.column()));
        });
        return new LadderGameResult(result);
    }

    public Ladder ladder() {
        return ladder;
    }

    public Players players() {
        return players;
    }

    public Rewards rewards() {
        return rewards;
    }
}
