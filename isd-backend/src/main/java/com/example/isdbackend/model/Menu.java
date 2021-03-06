package com.example.isdbackend.model;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.List;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private DayOfWeek dayOfWeek;

    private String image;

    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menu")
    private List<MenuType> menuTypes;

    public Menu() {
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public List<MenuType> getMenuTypes() {
        return menuTypes;
    }

    public void setMenuTypes(List<MenuType> menuTypes) {
        this.menuTypes = menuTypes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek.minus(1);
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
