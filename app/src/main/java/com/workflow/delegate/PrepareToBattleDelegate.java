package com.workflow.delegate;

import com.workflow.model.Warrior;
import com.workflow.service.WarriorService;
import com.workflow.util.WarriorUtil;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class PrepareToBattleDelegate implements JavaDelegate {

    @Value("${maxWarriors}")
    private int maxWarriors;

    private final WarriorService warriorService;

    private static final Logger log = LoggerFactory.getLogger(PrepareToBattleDelegate.class);


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        var warriors = (int) delegateExecution.getVariable("warriors");
        var enemyWarriors = WarriorUtil.generate100();

        if (warriors < 1 || warriors > maxWarriors) {
            throw new BpmnError("warriorsError");
        }

        var army = new ArrayList<Warrior>();
        for (int i = 0; i < warriors; i++) {
            var warrior = warriorService.buildRandomWarrior(i);
            army.add(warrior);
        }

        var armyJson = Variables.objectValue(army)
                .serializationDataFormat("application/json")
                .create();

        log.info("Prepared to battle!");

        delegateExecution.setVariable("enemyWarriors", enemyWarriors);
        delegateExecution.setVariable("army", army);
        delegateExecution.setVariable("armyJson", armyJson);
    }
}
