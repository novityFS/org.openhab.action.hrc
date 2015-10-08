package org.openhab.action.hrc.internal;

import de.novity.openhab.hvac.api.RoomControllerService;
import de.novity.openhab.hvac.api.TimeProgramService;
import org.openhab.core.events.EventPublisher;

public interface Infrastructure {
    TimeProgramService getTimeProgramService();
    RoomControllerService getRoomControllerService();
    EventPublisher getEventPublisher();
}
