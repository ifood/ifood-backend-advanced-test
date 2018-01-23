package tech.paiter.itemper.services.utils.categorias;

public class PopCategoria implements ICategoria {

    private static final String POP = "pop";

    private ICategoria nextCategoria;

    @Override
    public void setNextChain(ICategoria nextCategoria) {
        this.nextCategoria = nextCategoria;
    }

    @Override
    public String dispense(Double tempreatura) {
        if (tempreatura.intValue() >= 15 && tempreatura.intValue() <= 30 )
            return POP;

        return this.nextCategoria.dispense(tempreatura);
    }

}