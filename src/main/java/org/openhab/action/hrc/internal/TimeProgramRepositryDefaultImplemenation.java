package org.openhab.action.hrc.internal;

import de.novity.openhab.hvac.api.TimeProgramRepository;
import de.novity.openhab.hvac.domain.TimeProgram;

import java.util.HashMap;
import java.util.Map;

public class TimeProgramRepositryDefaultImplemenation implements TimeProgramRepository {
    private final Map<String, TimeProgram> timeProgramMap;

    public TimeProgramRepositryDefaultImplemenation() {
        this.timeProgramMap = new HashMap<String, TimeProgram>();
    }

    public TimeProgram findById(String id) {
        return timeProgramMap.get(id);
    }

    public void addTimeProgram(TimeProgram timeProgram) {
        if (timeProgram == null) {
            throw new NullPointerException("Timeprogram must not be null");
        }

        timeProgramMap.put(timeProgram.getId(), timeProgram);
    }

    public void updateTimeProgram(TimeProgram timeProgram) {
        if (timeProgram == null) {
            throw new NullPointerException("Timeprogram must not be null");
        }

        timeProgramMap.put(timeProgram.getId(), timeProgram);
    }
}
