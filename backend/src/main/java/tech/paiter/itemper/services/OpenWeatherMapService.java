package tech.paiter.itemper.services;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.model.param.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.paiter.itemper.apis.OpenWeatherMapUtil;
import tech.paiter.itemper.models.Coordenadas;

@Service
public class OpenWeatherMapService {

    @Autowired
    OpenWeatherMapUtil openWeatherMapUtil;

    public Double getTemperatura(String cidadeNome) throws APIException {
        Main cidade = openWeatherMapUtil.getInfoCidade(cidadeNome);
        return cidade.getTemp();
    }

    public Double getLatLong(String lat, String log) throws APIException {
        Main cidade = openWeatherMapUtil.getCidadeByLatLog(lat, log);
        return cidade.getTemp();
    }

    public Double getLatLong(Coordenadas coord) throws APIException {
        Main cidade = openWeatherMapUtil.getCidadeByCoordenadas(coord);
        return cidade.getTemp();
    }

}
