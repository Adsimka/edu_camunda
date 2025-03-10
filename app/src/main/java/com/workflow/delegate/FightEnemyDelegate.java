package com.workflow.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class FightEnemyDelegate implements JavaDelegate {

    private static final Logger log = LoggerFactory.getLogger(FightEnemyDelegate.class);
    private int enemyWarriors;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<Boolean> army = (List<Boolean>) delegateExecution.getVariable("army");
        enemyWarriors = (int) delegateExecution.getVariable("enemyWarriors");

        if (new Random().nextBoolean()) {
            enemyWarriors--;
            log.info("Enemy warrior killed!");
        } else {
            army.remove(army.size() - 1);
            log.info("Our warrior killed!");
        }
        delegateExecution.setVariable("enemyWarriors", enemyWarriors);
        delegateExecution.setVariable("warriors", army.size());
        delegateExecution.setVariable("arny", army);
    }
}
