package com.pfe.ldb.event.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pfe.ldb.core.protogest.event.Event;
import com.pfe.ldb.core.protogest.event.EventGroup;
import com.pfe.ldb.core.protogest.event.EventJson;
import com.pfe.ldb.event.controller.path.PathURI;
import com.pfe.ldb.event.iservice.IEventService;

@RestController

public class EventController {

	@Autowired
	private IEventService eventService;

	@CrossOrigin(origins = "http://localhost:3001")
	@RequestMapping(method = RequestMethod.GET, value = PathURI.EVENTS)
	public List<EventJson> getEvents() throws Exception {
		return eventService.loadEvents();
	}
	
	@CrossOrigin(origins = "http://localhost:3001")
	@RequestMapping(method = RequestMethod.GET, value = PathURI.EVENTS_USER)
	public List<EventJson> getEventsForCurrentUser(HttpServletRequest request) throws Exception {
		
		String user = request.getHeader("email");
		return eventService.loadEventsForCurrentUser(user);
	}

	@CrossOrigin(origins = "http://localhost:3001")
	@RequestMapping(method = RequestMethod.GET, value = PathURI.EVENTS_GROUP)
	public List<EventGroup> getEventsGroup() throws Exception {
		return eventService.loadEventsGroup();
	}

	@CrossOrigin(origins = "http://localhost:3001")
	@RequestMapping(method = RequestMethod.POST, value = PathURI.EVENT)
	public Event updateEvent(@RequestBody(required = true) final Map<String, String> event) throws Exception {
		
		return eventService.updateEventForCurrentUser(event);
	}
	
	
	@CrossOrigin(origins = "http://localhost:3001")
	@RequestMapping(method = RequestMethod.POST, value = PathURI.EVENT_SUGGESTION)
	public Event updateEventWithSuggestion(@RequestBody(required = true) final Map<String, String> event) throws Exception {
		
		return eventService.updateEventWithSuggestionForCurrentUser(event);
	}
	
	
	@CrossOrigin(origins = "http://localhost:3001")
	@RequestMapping(method = RequestMethod.POST, value = PathURI.EVENTS)
	public List<Event> updateEvents(@RequestBody(required = true) final Map<String, String> events) throws Exception {
		List<Event> eventss = new ArrayList<>();
		Thread.sleep(1000);
		int cnt = 1;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String eventName = events.get("eventName");
		List<String> emailsToNotify = new ArrayList<>();
		emailsToNotify.add(events.get("email1"));
		emailsToNotify.add(events.get("email2"));

		for (String key : events.keySet()) {
			if(!key.equals("eventName") && !key.equals("email1") && !key.equals("email2")) {
				eventss.add(new Event(formatter.parse(events.get(key)), cnt++, eventName,emailsToNotify));
			}
		}
		return eventService.updateEvents(eventss);
	}

	

}
