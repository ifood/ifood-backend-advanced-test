package tech.paiter.itemper.services.utils.categorias;

public class ClassicaCategoria implements ICategoria {

    private static final String CLASSICAL = "classical";

    private ICategoria nextCategoria;

    @Override
    public void setNextChain(ICategoria nextCategoria) {
        this.nextCategoria = nextCategoria;
    }

    @Override
    public String dispense(Double tempreatura) {
        if (tempreatura.intValue() < 10 )
            return CLASSICAL;

        return this.nextCategoria.dispense(tempreatura);
    }

}