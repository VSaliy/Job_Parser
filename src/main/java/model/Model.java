package model;

import view.View;
import vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private View view;
    private Provider[] providers;

    public Model(View view, Provider[] providers) {
        if (view == null || providers.length == 0) {
            throw new IllegalArgumentException();
        }
        this.view = view;
        this.providers = providers;
    }

    public void selectSity(String sity) {
        List<Vacancy> vacancies = new ArrayList<Vacancy>();
        for (Provider provider : providers) {
            for(Vacancy vacancy : provider.getJavaVacancies(sity)) {
                vacancies.add(vacancy);
            }
        }
        view.update(vacancies);
    }
}
