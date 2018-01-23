package tech.paiter.itemper.services.utils.chains;

public interface ICategoria {

    void setNextChain(ICategoria nextChain);

    String dispense(Double tempreatura);

}
