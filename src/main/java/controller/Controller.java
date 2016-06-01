package controller;

import model.Model;
import model.Provider;
import vo.Vacancy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {

    private Model model;

    public Controller(Model model) {
        if (model == null) {
            throw new IllegalArgumentException();
        }
        this.model = model;
    }

    public void onCitySelect(String cityName) {
        model.selectSity(cityName);
    }
}
