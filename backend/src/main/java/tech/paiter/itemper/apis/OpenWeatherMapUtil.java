package tech.paiter.itemper.apis;


import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.param.Main;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OpenWeatherMapUtil {

    @Value("${api.key.openWeatherMap}")
    private String apiKey;

    public Main getInfoCidade(String cidadeNome) throws APIException {
        OWM owm = new OWM(apiKey);
        owm.setUnit(OWM.Unit.METRIC);
        CurrentWeather cwd = owm.currentWeatherByCityName(cidadeNome);
        return cwd.getMainData();
    }


}
