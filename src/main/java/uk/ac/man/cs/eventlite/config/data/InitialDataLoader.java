package uk.ac.man.cs.eventlite.config.data;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import uk.ac.man.cs.eventlite.dao.EventService;
import uk.ac.man.cs.eventlite.dao.VenueService;
import uk.ac.man.cs.eventlite.entities.Event;
import uk.ac.man.cs.eventlite.entities.Venue;

@Component
@Profile({ "default", "test" })
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private final static Logger log = LoggerFactory.getLogger(InitialDataLoader.class);

	@Autowired
	private EventService eventService;

	@Autowired
	private VenueService venueService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (eventService.count() > 0) {
			log.info("Database already populated. Skipping data initialization.");
			return;
		}
		
		
		// Build and save initial models here.
		ArrayList<Venue> venues = new ArrayList<Venue>();
		ArrayList<Event> events = new ArrayList<Event>();
		
		Venue v1 = new Venue();
		v1.setName("Kilburn, G23");v1.setCapacity(80);v1.setId(1);v1.setAddress("Oxford Road, Manchester, M13 9PL, UK");
		venues.add(v1);
		
		Venue v2 = new Venue();
		v2.setName("Manchester Arena");v2.setCapacity(6000);v2.setId(2);v2.setAddress("Victoria Station, Hunts Bank, Manchester, M3 1AR, UK");
		venues.add(v2);
		venueService.save(venues);
		
		Event e1 = new Event();
		e1.setName("COMP23412 Showcase, group G");
		e1.setId(1);
		e1.setTime(new Date(118,5,10,11,00,00));
		e1.setDate(new Date(118,5,10,11,00,00));
		e1.setVenue(v1);events.add(e1);
		
		Event e2 = new Event();
		e2.setName("COMP23412 Showcase, group H");
		e2.setId(2);
		e2.setTime(new Date(118,5,11,9,00,00));
		e2.setDate(new Date(118,5,11,9,00,00));
		e2.setVenue(v1);events.add(e2);
		
		Event e3 = new Event();
		e3.setName("COMP23412 Showcase, group F");
		e3.setId(3);
		e3.setTime(new Date(118,5,11,11,00,00));
		e3.setDate(new Date(118,5,11,11,00,00));
		e3.setVenue(v1);events.add(e3);
		
		Event e4 = new Event();
		e4.setName("Rock gig");
		e4.setId(4);
		e4.setTime(new Date(118,6,15,19,00,00));
		e4.setDate(new Date(118,6,15,19,00,00));
		e4.setVenue(v2);events.add(e4);
		
		
		eventService.save(events);
		
		
	}
}
