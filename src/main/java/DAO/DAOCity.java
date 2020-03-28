package DAO;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import model.City;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Data
@Repository
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class DAOCity {
    @Autowired
    private Session session;

    @Transactional(readOnly = true)
    public ArrayList<City> getAll(){
        return (ArrayList<City>)session.createQuery("FROM City").list();
    }

    @Transactional(readOnly = true)
    public City getByName(String name){
        ArrayList<City> list = (ArrayList<City>) session
                .createQuery("FROM City WHERE name=:name")
                .setParameter("name", name)
                .list();
        return (list == null || list.size() == 0) ? null : (City)list.toArray()[0];
    }

    @Transactional
    public void update(City city){
        session.getTransaction().begin();
        session.update(city);
        session.getTransaction().commit();
    }

    @Transactional
    public void add(City city){
        session.getTransaction().begin();
        session.save(city);
        session.getTransaction().commit();
    }

    @Transactional
    public void delete(City city){
        session.getTransaction().begin();
        session.delete(city);
        session.getTransaction().commit();
    }
}
