package tech.paiter.itemper.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import tech.paiter.itemper.models.Cidade;

@CrossOrigin()
@RepositoryRestResource(collectionResourceRel="especialidades", path="especialidades")
public interface CidadeRepository extends MongoRepository<Cidade, String> {

}
