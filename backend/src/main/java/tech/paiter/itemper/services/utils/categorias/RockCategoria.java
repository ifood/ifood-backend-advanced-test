package tech.paiter.itemper.services.utils.categorias;

public class RockCategoria implements ICategoria {

    private static final String ROCK = "rock";

    private ICategoria nextCategoria;

    @Override
    public void setNextChain(ICategoria nextCategoria) {
        this.nextCategoria = nextCategoria;
    }

    @Override
    public String dispense(Double temperatura) {
        if (temperatura.intValue() >= 10 && temperatura.intValue() < 15 )
            return ROCK;

        return this.nextCategoria.dispense(temperatura);
    }

}