import com.step.communication.channel.MessageChannel;
import com.step.communication.factory.CommunicationFactory;
import com.step.communication.server.MessageServer;
import com.step.uno.server.network.GameMaster;
import com.step.uno.server.screen.ServerScreen;
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
}

