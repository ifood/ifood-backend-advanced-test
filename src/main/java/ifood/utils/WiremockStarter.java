package ifood.utils;

import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class WiremockStarter {
    private static WireMockServer wireMockServer = null;

    private static WiremockStarter instance = null;

    public static int PORT = 8081;

    private WiremockStarter() {
    }

    public static synchronized WiremockStarter getInstance() {
        if (instance == null) {
            instance = new WiremockStarter();
        }
        return instance;
    }

    public synchronized void startOnce() {
        if (wireMockServer == null) {
            wireMockServer = new WireMockServer(wireMockConfig().port(PORT));
            wireMockServer.start();
        }
    }

    public synchronized void stop() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }
}