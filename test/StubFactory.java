import com.step.communication.channel.MessageChannel;
import com.step.communication.channel.MessageChannelListener;
import com.step.communication.factory.CommunicationFactory;
import com.step.communication.server.MessageServer;
import com.step.uno.client.GameClient;
import com.step.uno.client.GameClientObserver;
import com.step.uno.client.view.WaitingView;
import com.step.uno.server.GameMasterObserver;
import com.step.uno.server.network.GameMaster;
import com.step.uno.server.screen.ServerScreen;

import static org.mockito.Mockito.mock;

public class StubFactory extends CommunicationFactory {
    public final MessageChannel channel = mock(MessageChannel.class);
    public final GameClient gameClient = mock(GameClient.class);
    public final MessageServer messageServer = mock(MessageServer.class);
    public final ServerScreen serverScreen = mock(ServerScreen.class);
    public final GameMaster gameMaster = mock(GameMaster.class);
    public WaitingView waitingView = mock(WaitingView.class);


    @Override
    public MessageChannel connectTo(String serverAddress, MessageChannelListener observer) {
        return channel;
    }

    @Override
    public GameClient createGameClient(GameClientObserver observer) {
        return gameClient;
    }

    @Override
    public MessageServer createMessageServer() {
        return messageServer;
    }

    @Override
    public ServerScreen getServerView(int players, int packs) {
        return serverScreen;
    }

    @Override
    public GameMaster createGameServer(int numberOfPlayers, int numberOfPacks, GameMasterObserver observer) {
        return gameMaster;
    }

    @Override
    public WaitingView getWaitingView() {
        return waitingView;
    }
}
