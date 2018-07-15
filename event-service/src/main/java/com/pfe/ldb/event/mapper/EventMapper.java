package com.pfe.ldb.event.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;

import com.pfe.ldb.core.protogest.event.Event;
import com.pfe.ldb.core.protogest.event.EventState;
import com.pfe.ldb.core.protogest.task.Task;
import com.pfe.ldb.core.protogest.user.User;
import com.pfe.ldb.core.protogest.utils.AbstractModel;
import com.pfe.ldb.entity.AbstractEntity;
import com.pfe.ldb.entity.EventEntity;
import com.pfe.ldb.entity.EventGroupEntity;
import com.pfe.ldb.entity.EventStateEntity;
import com.pfe.ldb.entity.MemberEntity;
import com.pfe.ldb.entity.TaskEntity;
import com.pfe.ldb.entity.TaskGroupEntity;
import com.pfe.ldb.event.imapper.IMapper;
import com.pfe.ldb.event.repository.EventGroupRepository;
import com.pfe.ldb.event.repository.EventRepository;
import com.pfe.ldb.event.repository.EventStateRepository;
import com.pfe.ldb.event.repository.MemberRepository;
import com.pfe.ldb.event.repository.TaskRepository;

@Component
public class EventMapper implements IMapper {

	// For PROTOTYPE purpose
	private final Integer SOURCE_ID = 1;
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private EventStateRepository eventStateRepository;
	@Autowired
	private EventGroupRepository eventGroupRepository;
	@Autowired
	private EventRepository eventRepository;

	@Override
	public AbstractModel convertToDTO(AbstractEntity entity) throws ParseException {
		EventEntity eventEntity = (EventEntity) entity;
		EventState state = EventState.valueOf(eventEntity.getEventState().getName().toUpperCase());
		Event event = new Event(eventEntity.getId(), eventEntity.getEventDate(), eventEntity.getTask().getId(),
				eventEntity.getMember().getId(), state);
		return event;
	}

	@Override
	public AbstractEntity convertToEntity(AbstractModel model) throws ParseException {
		Event event = (Event) model;
		EventEntity eventEntity;
		EventGroupEntity eventGroupEntity = eventGroupRepository.findByName(event.getEventName());
		if (eventGroupEntity != null) {
			eventEntity = eventRepository.findByTaskIdAndEventGroupId(event.getTask(),
					eventGroupEntity.getId());
			if(eventEntity != null) {
				eventEntity.setEventDate(event.getEventDate());
				return eventEntity;
			}
	
		} 
			TaskEntity taskEntity = taskRepository.findById(event.getTask()).get();
			MemberEntity memberEntity = memberRepository.findById(SOURCE_ID).get();
			EventStateEntity eventState = eventStateRepository.findByName(event.getEventState().getState());
			if (eventGroupEntity == null) {
				eventGroupEntity = eventGroupRepository.save(new EventGroupEntity(event.getEventName(), ""));
			}
			 eventEntity = new EventEntity("", "", event.getEventDate(), eventGroupEntity, taskEntity,
					memberEntity, eventState);
		
		return eventEntity;
	}

}
