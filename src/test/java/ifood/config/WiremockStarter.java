package ifood.config;

import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class WiremockStarter {

    public static final int PORT_NUMBER = 8089;

    private static WireMockServer wireMockServer = null;

    private static WiremockStarter instance = null;

    private WiremockStarter() { }

    public static synchronized WiremockStarter getInstance() {
        if(instance == null) {
            instance = new WiremockStarter();
        }
        return instance;
    }

    public synchronized void startOnce() {
        if(wireMockServer == null) {
            wireMockServer = new WireMockServer(wireMockConfig().port(PORT_NUMBER));
            wireMockServer.start();
        }
    }

    public synchronized void stop() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }
}
