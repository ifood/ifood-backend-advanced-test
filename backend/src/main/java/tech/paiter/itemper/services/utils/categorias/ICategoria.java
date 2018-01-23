package tech.paiter.itemper.services.utils.categorias;

public interface ICategoria {

    void setNextChain(ICategoria nextChain);

    String dispense(Double tempreatura);

}
