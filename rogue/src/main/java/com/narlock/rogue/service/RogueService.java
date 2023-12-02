package com.narlock.rogue.service;

import com.narlock.rogue.RB;
import com.narlock.rogue.model.event.RBEvent;
import com.narlock.rogue.model.result.RBResult;
import org.springframework.stereotype.Service;

@Service
public class RogueService {
    public RBResult sendMessageToEventQueue(RBEvent event) {
        // Process the event
        RBResult result = processEvent(event);

        // Draw the event
        RB.window.gp.event(event, result);

        // Return result
        return result;
    }

    protected RBResult processEvent(RBEvent event) {
        return null;
    }
}
