import com.step.communication.channel.MessageChannel;
import com.step.communication.factory.CommunicationFactory;
import com.step.communication.server.MessageServer;
import com.step.uno.server.screen.ServerScreen;
import com.step.uno.messages.GameSnapshot;
import com.step.uno.server.controller.GameMasterController;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class GameMasterControllerTest {

    StubFactory stub = new StubFactory();

    @Test
    public void startsTheGameAfterAllPlayersJoin() {
        MessageChannel channel = mock(MessageChannel.class);
        GameMasterController controller = new GameMasterController(1, 1, stub);
        controller.waitForConnections();
        controller.onNewConnection(channel);

        verify(channel, times(1)).send(any(GameSnapshot.class));
    }

    @Test
    public void rejectsConnectionsAfterAllPlayersJoin() {
        MessageChannel channel = mock(MessageChannel.class);
        MessageChannel lateChannel = mock(MessageChannel.class);
        GameMasterController controller = new GameMasterController(1, 1, stub);
        controller.waitForConnections();
        controller.onNewConnection(channel);
        controller.onNewConnection(lateChannel);
        verify(lateChannel, times(1)).stop();
        verify(lateChannel, never()).send(any(GameSnapshot.class));
    }

    class StubFactory extends CommunicationFactory {
        public final MessageServer messageServer = mock(MessageServer.class);
        private final ServerScreen serverScreen = mock(ServerScreen.class);

        @Override
        public MessageServer createMessageServer() {
            return messageServer;
        }

        @Override
        public ServerScreen getServerView(int players, int packs) {
            return serverScreen;
        }
    }
}

