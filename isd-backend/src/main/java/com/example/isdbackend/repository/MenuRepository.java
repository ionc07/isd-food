package com.example.isdbackend.repository;

import com.example.isdbackend.model.Menu;
import org.springframework.data.repository.CrudRepository;

import java.time.DayOfWeek;
import java.util.List;

public interface MenuRepository extends CrudRepository<Menu,Long> {
    List<Menu> findByDayOfWeek(DayOfWeek day);

    List<Menu> findByDayOfWeekAndActive(DayOfWeek day, Boolean active);
}
