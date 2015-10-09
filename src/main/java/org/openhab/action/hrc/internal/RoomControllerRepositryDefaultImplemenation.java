package org.openhab.action.hrc.internal;

import de.novity.openhab.hvac.api.RoomControllerRepository;
import de.novity.openhab.hvac.domain.HVACRoomControllerGroup;

import java.util.HashMap;
import java.util.Map;

public class RoomControllerRepositryDefaultImplemenation implements RoomControllerRepository {
    private final Map<String, HVACRoomControllerGroup> roomControllerMap;

    public RoomControllerRepositryDefaultImplemenation() {
        this.roomControllerMap = new HashMap<String, HVACRoomControllerGroup>();
    }

    public HVACRoomControllerGroup findByName(String roomControllerGroupName) {
        if ((roomControllerGroupName == null) || (roomControllerGroupName.isEmpty())) {
            throw new IllegalArgumentException("Room controller group name must not be null or empty");
        }

        return roomControllerMap.get(roomControllerGroupName);
    }

    public void addRoomControllerGroup(HVACRoomControllerGroup roomControllerGroup) {
        if (roomControllerGroup == null) {
            throw new NullPointerException("Room controller group must not be null");
        }

        roomControllerMap.put(roomControllerGroup.getName(), roomControllerGroup);
    }
}
