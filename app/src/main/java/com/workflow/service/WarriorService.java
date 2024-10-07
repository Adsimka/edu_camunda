package com.workflow.service;

import com.workflow.model.Warrior;
import com.workflow.util.WarriorUtil;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class WarriorService {

    public Warrior buildRandomWarrior(Integer number) {
        String name = String.format("Warrior %s", number);
        int hp = WarriorUtil.generate100();
        boolean isAlive = WarriorUtil.generateBoolean();

        return Warrior.builder()
                .name(name)
                .hp(hp)
                .isAlive(isAlive)
                .build();
    }
}
