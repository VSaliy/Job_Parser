package model;

import vo.Vacancy;

import java.util.List;

public class Provider {

    private Strategy[] strategies;

    public Provider(Strategy... strategies) {
        this.strategies = strategies;
    }

    public void setStrategy(Strategy... strategies) {
        this.strategies = strategies;
    }

    public List<Vacancy> getJavaVacancies(String searchString) {
        List<Vacancy> vacancies = strategies[0].getVacancies(searchString);
        for (int i = 1; i < strategies.length; i++) {
            vacancies.addAll(strategies[i].getVacancies(searchString));
        }
        return vacancies;
    }
}
