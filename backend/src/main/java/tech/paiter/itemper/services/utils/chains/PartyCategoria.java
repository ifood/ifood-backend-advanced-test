package tech.paiter.itemper.services.utils.chains;

public class PartyCategoria implements ICategoria {

    private static final String PARTY = "party";

    private ICategoria nextCategoria;

    @Override
    public void setNextChain(ICategoria nextCategoria) {
        this.nextCategoria = nextCategoria;
    }

    @Override
    public String dispense(Double tempreatura) {
        if (tempreatura.intValue() > 30 )
            return PARTY;

        return this.nextCategoria.dispense(tempreatura);
    }

}