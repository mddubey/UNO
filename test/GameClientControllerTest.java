import com.step.communication.channel.MessageChannel;
import com.step.communication.channel.MessageChannelListener;
import com.step.communication.factory.CommunicationFactory;
import com.step.uno.client.controller.GameClientController;
import com.step.uno.client.view.JoinGameView;
import com.step.uno.client.view.PlayerView;
import com.step.uno.messages.GameSnapshot;
import com.step.uno.messages.Introduction;
import com.step.uno.messages.Snapshot;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class GameClientControllerTest {
    StubFactory stub = new StubFactory();
    PlayerView playerView = mock(PlayerView.class);
    JoinGameView joinGameView = mock(JoinGameView.class);
    private GameClientController controller;

    @Before
    public void setup() {
        when(joinGameView.switchToPlayerView()).thenReturn(playerView);
        controller = new GameClientController(stub);
        controller.bindView(joinGameView);
    }

    @Test
    public void sendsIntroductionAfterJoiningGame() {
        controller.join("serverAddress", "me");

        verify(stub.channel,times(1)).startListeningForMessages(controller);
        verify(stub.channel, times(1)).send(any(Introduction.class));
    }

    @Test
    public void doesNotInformUserWhenConnectionIsLostBeforeGameStarts() {
        controller.join("serverAddress", "me");
        controller.onConnectionClosed(stub.channel);
        verify(playerView, times(0)).showDisconnected();
    }

    @Test
    public void displaysGameSnapshotAsItArrives() {
        controller.join("serverAddress", "me");
        Snapshot snapshot = new Snapshot();
        controller.onMessage(stub.channel, snapshot);
        verify(playerView, times(1)).update(snapshot);
    }



    class StubFactory extends CommunicationFactory {
        public final MessageChannel channel = mock(MessageChannel.class);

        @Override
        public MessageChannel connectTo(String serverAddress, MessageChannelListener observer) {
            return channel;
        }
    }
}