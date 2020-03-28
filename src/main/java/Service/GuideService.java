package Service;

import DAO.DAOCity;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import model.City;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@Data
@org.springframework.stereotype.Service
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class GuideService {

    @Autowired
    private DAOCity daoCity;

    public String getCityInfoByName(String name){
        return daoCity.getByName(name).getInfo();
    }

    public boolean isValidData(String name){
        ArrayList<City> list = daoCity.getAll();
        for(City city: list){
            if(city.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public boolean update(String name, String info){
        try {
            City city = daoCity.getByName(name);
            if (city == null){
                return false;
            }
            city.setInfo(info);
            daoCity.update(city);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean delete(String name){
        try{
            daoCity.delete(daoCity.getByName(name));
        } catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean add(City city){
        try{
            daoCity.add(city);
        } catch (Exception e){
            return false;
        }
        return true;
    }
}
