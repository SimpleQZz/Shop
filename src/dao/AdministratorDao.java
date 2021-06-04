package dao;

import domain.Administrator;
import domain.Company;

import java.util.List;

public interface AdministratorDao {
    public void save(Administrator administrator);

    List<Administrator> findAll();

    Administrator findOne(String text, String text1);
}
