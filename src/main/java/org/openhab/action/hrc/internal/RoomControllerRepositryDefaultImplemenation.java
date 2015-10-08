package org.openhab.action.hrc.internal;

import de.novity.openhab.hvac.api.RoomControllerRepository;
import de.novity.openhab.hvac.api.TimeProgramRepository;
import de.novity.openhab.hvac.domain.HVACRoomController;
import de.novity.openhab.hvac.domain.TimeProgram;

import java.util.HashMap;
import java.util.Map;

public class RoomControllerRepositryDefaultImplemenation implements RoomControllerRepository {
    private final Map<String, HVACRoomController> roomControllerMap;

    public RoomControllerRepositryDefaultImplemenation() {
        this.roomControllerMap = new HashMap<String, HVACRoomController>();
    }

    public HVACRoomController findById(String id) {
        return roomControllerMap.get(id);
    }

    public void addRoomController(HVACRoomController roomController) {
        if (roomController == null) {
            throw new NullPointerException("Room controller must not be null");
        }

        roomControllerMap.put(roomController.getName(), roomController);
    }

    public void updateRoomController(HVACRoomController roomController) {
        if (roomController == null) {
            throw new NullPointerException("Room controller must not be null");
        }

        roomControllerMap.put(roomController.getName(), roomController);
    }
}
