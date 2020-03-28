package model;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = "id")
@Entity
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Table(name = "cities", schema = "city_storage")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "info")
    private String info;

    @Override
    public String toString() {
        return id + " " + name + " " + info;
    }

    public City (String name, String info){
        this.name = name;
        this.info = info;
    }

    public City () {}
}
