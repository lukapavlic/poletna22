package si.um.feri.locations.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import si.um.feri.locations.vao.Location;

@RepositoryRestResource(collectionResourceRel = "locations",path = "locations")
public interface LocationRepository extends PagingAndSortingRepository<Location, Integer> {

}