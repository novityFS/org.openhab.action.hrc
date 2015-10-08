/**
 * Copyright (c) 2010-2013, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.action.hrc.internal;

import de.novity.openhab.hvac.api.*;
import de.novity.openhab.hvac.domain.OperatingMode;
import de.novity.openhab.hvac.domain.RoomControllerDefaultService;
import de.novity.openhab.hvac.domain.TimeProgramDefaultService;
import org.openhab.core.events.EventPublisher;
import org.openhab.core.library.types.DecimalType;
import org.openhab.core.scriptengine.action.ActionService;
import org.openhab.core.types.Command;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
	
/**
 * This class registers an OSGi service for the HVACRoomControllerAction action.
 * 
 * @author Frank Seidinger
 * @since 0.1.0-SNAPSHOT
 */
public class HVACRoomControllerActionService implements ActionService, Infrastructure, OperatingModeChangedListener {
	private static final Logger logger = LoggerFactory.getLogger(HVACRoomControllerActionService.class);

	private BundleContext context;
	private Map<String, Object> configuration;

    private final TimeProgramService timeProgramService;
	private final RoomControllerService roomControllerService;

    private EventPublisher eventPublisher;

	public HVACRoomControllerActionService() {
		TimeProgramRepository timeProgramRepository = new TimeProgramRepositryDefaultImplemenation();
        this.timeProgramService = new TimeProgramDefaultService(timeProgramRepository);

		RoomControllerRepository roomControllerRepository = new RoomControllerRepositryDefaultImplemenation();
		this.roomControllerService = new RoomControllerDefaultService(roomControllerRepository, timeProgramRepository, this);
        this.eventPublisher = null;
	}
	
	public void activate(final BundleContext context, final Map<String, Object> configuration) {
		this.context = context;
		this.configuration = configuration;

        HVACRoomControllerAction.infrastructure = this;
	}
	
	public void deactivate(final int reason) {
        HVACRoomControllerAction.infrastructure = null;
	}

    public void setEventPublisher(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
        logger.debug("Got event publisher {}", eventPublisher);
    }

    public void unsetEventPublisher(EventPublisher eventPublisher) {
        this.eventPublisher = null;
        logger.debug("Lost event publisher");
    }

    public EventPublisher getEventPublisher() {
        return  eventPublisher;
    }
	
	public String getActionClassName() {
		return HVACRoomControllerAction.class.getCanonicalName();
	}

	public Class<?> getActionClass() {
		return HVACRoomControllerAction.class;
	}

    public TimeProgramService getTimeProgramService() {
        return timeProgramService;
    }

    public RoomControllerService getRoomControllerService() {
        return roomControllerService;
    }

    public void operatingModeChanged(String itemName, OperatingMode oldMode, OperatingMode newMode) {
        Command command = new DecimalType(newMode.getValue());
        eventPublisher.postCommand(itemName, command);
    }
}
