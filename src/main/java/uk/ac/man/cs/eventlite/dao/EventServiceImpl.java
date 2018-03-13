package uk.ac.man.cs.eventlite.dao;

import java.util.Iterator;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import uk.ac.man.cs.eventlite.entities.Event;

@Service
public class EventServiceImpl implements EventService {

	private final static Logger log = LoggerFactory.getLogger(EventServiceImpl.class);

	private final static String DATA = "data/events.json";
	
	@Autowired
	private EventRepository eventRepository;
	
	@Override
	public long count() {
		return eventRepository.count();
	}

	@Override
	public Iterable<Event> findAll() {
		return eventRepository.findAll();
	}

	@Override
	public void save(Iterable<Event> e) {
		eventRepository.save(e);
	}
	
	@Override
	public Iterable<Event> find(String name){
		return eventRepository.findAllByNameOrderByNameAsc(name);
	}
	
	@Override
	public Event findOne(long id){
		return eventRepository.findOne(id);
	}
	
}
