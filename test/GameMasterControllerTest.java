import com.step.communication.channel.MessageChannel;
import com.step.communication.factory.CommunicationFactory;
import com.step.communication.server.MessageServer;
import com.step.uno.server.network.GameMaster;
import com.step.uno.server.screen.ServerScreen;
import com.step.uno.messages.GameSnapshot;
import com.step.uno.server.controller.GameMasterController;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class GameMasterControllerTest {

    StubFactory stub = new StubFactory();

    @Test
    public void informsTheGameServerOnNewConnection() {
        MessageChannel channel = mock(MessageChannel.class);
        GameMasterController controller = new GameMasterController(1, 1, stub);
        controller.onNewConnection(channel);

        verify(stub.gameMaster, times(1)).onNewConnection(channel);
    }


    class StubFactory extends CommunicationFactory {
        public final MessageServer messageServer = mock(MessageServer.class);
        public final ServerScreen serverScreen = mock(ServerScreen.class);
        public final GameMaster gameMaster = mock(GameMaster.class);

        @Override
        public MessageServer createMessageServer() {
            return messageServer;
        }

        @Override
        public ServerScreen getServerView(int players, int packs) {
            return serverScreen;
        }

        @Override
        public GameMaster createGameServer(int numberOfPlayers, int numberOfPacks) {
            return gameMaster;
        }
    }
}

