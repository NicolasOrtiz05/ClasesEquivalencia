package edu.unisabana.dyas.tdd.registry;

import java.util.ArrayList;
import java.util.List;

public class Registry {
    private List<Integer> registeredIds = new ArrayList<>();

    public RegisterResult registerVoter(Person p) {
        if (!p.isAlive()) {
            return RegisterResult.DEAD;
        } else if (p.getAge() <= 0 || p.getAge() >= 100) {
            return RegisterResult.INVALID_AGE;
        } else if (p.getAge() < 18) {
            return RegisterResult.UNDERAGE;
        } else if (registeredIds.contains(p.getId())) {
            return RegisterResult.DUPLICATED;
        } else {
            registeredIds.add(p.getId());
            return RegisterResult.VALID;
        }
    }
}