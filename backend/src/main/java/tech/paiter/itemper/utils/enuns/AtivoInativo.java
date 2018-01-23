package tech.paiter.itemper.utils.enuns;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AtivoInativo {

    public enum ATIVO_INATIVO implements BaseEnum {

        ATIVO("Ativo"),
        INATIVO("Inativo");

        private String nome;

        ATIVO_INATIVO(String nome) {
            this.nome = nome;
        }

        @Override
        public String getNome() {
            return this.nome;
        }

        public Map<String, String> getMap() {
            Map<String, String> map = new HashMap<String, String>();
            for (ATIVO_INATIVO tipo : Arrays.asList(ATIVO_INATIVO.values())) {
                map.put(tipo.name(), tipo.getNome());
            }
            return map;
        }

    }

}