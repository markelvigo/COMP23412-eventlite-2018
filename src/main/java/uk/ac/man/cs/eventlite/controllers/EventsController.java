package uk.ac.man.cs.eventlite.controllers;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

import uk.ac.man.cs.eventlite.dao.EventService;
import uk.ac.man.cs.eventlite.dao.VenueService;

@Controller
@RequestMapping(value = "/events", produces = { MediaType.TEXT_HTML_VALUE })
public class EventsController {

	@Autowired
	private EventService eventService;

	@Autowired
	private VenueService venueService;

	@RequestMapping(method = RequestMethod.GET)
	public String getAllEvents(Model model) {		
		model.addAttribute("events", eventService.findAll());
		return "events/index";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getSingleEvents(@PathVariable("id") long id, Model model) {
		model.addAttribute("event",eventService.findOne(id));
		return "events/single";
	}
	
	@RequestMapping(value = "/searchresult", method = RequestMethod.GET)
	public String search(@RequestParam(value = "searchterm") String keyword, Model model) {
		model.addAttribute("events",eventService.find(keyword));
		return "events/index";
	}

}
