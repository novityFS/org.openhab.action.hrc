/**
 * Copyright (c) 2010-2013, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.action.hrc.internal;

import de.novity.openhab.hvac.api.RoomControllerService;
import de.novity.openhab.hvac.api.SwitchCycleData;
import de.novity.openhab.hvac.api.TimeProgramService;
import de.novity.openhab.hvac.domain.HVACRoomController;
import de.novity.openhab.hvac.domain.OperatingMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;

/**
 * This class contains the methods that are made available in scripts and rules for HVACRoomControllerAction.
 * 
 * @author Frank Seidinger
 * @since 0.1.0-SNAPSHOT
 */
public class HVACRoomControllerAction {
    private static final Logger logger = LoggerFactory.getLogger(HVACRoomControllerAction.class);

    public static Infrastructure infrastructure;

    public static void defineTimeProgram(String id) {
        TimeProgramService service = infrastructure.getTimeProgramService();
        service.defineTimeProgram(id);
    }

    public static void addSwitchCycle(String timeProgramId, String switchTime, int operatingMode) {
        SwitchCycleData switchCycleData = new SwitchCycleData();

        if (switchTime != null) {
            switchTime = switchTime.trim();
            switchCycleData.switchTime = LocalTime.parse(switchTime);
        }

        switchCycleData.operatingMode = operatingMode;

        TimeProgramService service = infrastructure.getTimeProgramService();
        service.addSwitchCycle(timeProgramId, switchCycleData);
    }

    public static void defineRoomController(String name, String itemName) {
        RoomControllerService roomControllerService = infrastructure.getRoomControllerService();
        roomControllerService.defineRoomController(name, itemName);
    }

    public static void applyTimeProgram(String roomControllerName, String timeProgramId) {
        RoomControllerService roomControllerService = infrastructure.getRoomControllerService();
        roomControllerService.applyTimeProgram(roomControllerName, timeProgramId);
    }

    public static void updateOperatingMode(String roomControllerName, LocalTime timeOfUpdate) {
        RoomControllerService roomControllerService = infrastructure.getRoomControllerService();
        roomControllerService.updateOperatingMode(roomControllerName, timeOfUpdate);
    }
}
