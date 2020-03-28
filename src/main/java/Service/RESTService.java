package Service;

import DAO.DAOCity;
import lombok.RequiredArgsConstructor;
import model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class RESTService {
    @Autowired
    private DAOCity daoCity;
    @Autowired
    private GuideService guideService;
    @Context
    private HttpHeaders httpHeaders;

    @GetMapping("/all_cities")
    public ResponseEntity<ArrayList<City>> getCity() {
        ArrayList<City> cities = daoCity.getAll();

        return cities != null && !cities.isEmpty()
                ? new ResponseEntity<>(cities, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/all_cities/{name}")
    public ResponseEntity<City> getCity(@PathVariable(name = "name") String name) {
        City city = daoCity.getByName(name);

        return city != null
                ? new ResponseEntity<>(city, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/all_cities/update/{name}/{info}")
    public ResponseEntity<?> update(@PathVariable("name") String name,
                                    @PathVariable("info") String info) {
        return guideService.update(name, info)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/all_cities/add/{name}/{info}")
    public ResponseEntity<?> add(@PathVariable("name") String name,
                                 @PathVariable("info") String info){
        City city = new City(name, info);
        return guideService.add(city)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    @GetMapping(value = "/all_cities/delete/{name}")
    public ResponseEntity<?> delete(@PathVariable(name = "name") String name) {
        return guideService.delete(name)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
