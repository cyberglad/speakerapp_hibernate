package com.example.speakerapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@SpringBootTest
public class SpeakerappApplicationTests {

	private static final Logger LOG = LoggerFactory.getLogger(SpeakerappApplicationTests.class);

	@Autowired
	private SpeakerRepository speakerRepository;

	//prepare DB, no rollback, because we do every test against this data
	@Before
	@Rollback(false)
	public void setUp() {
		Speaker evgeny  = new Speaker("Mr Evgeny Krivosheev");
		Speaker jeka = new Speaker("Mr Evgeny Borisov");
		jeka.addTalk(new Talk("The internals of Spring", time("12:30")));
		jeka.addTalk(new Talk("The spring modules", time("17:30")));

		Speaker nikolay = new Speaker("Mr Nikolay Alimenkov");
		nikolay.addTalk(new Talk("CD JEE7", time("18:00")));
		Speaker baruch = new Speaker("Mr Baruch Sagodursky");
		baruch.addTalk(new Talk("AST groovy", time("12:00")));
		baruch.addTalk(new Talk("Making Spring Groovy", time("09:00")));

		speakerRepository.save(Arrays.asList(jeka, evgeny, baruch, nikolay));

	}

	private Date time(String s) {
		Date date =  null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm");
		String dateInString = "27-06-2017 "+s;
		try {
			date = sdf.parse(dateInString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	@Test
	public void testCount(){
		LOG.info("************ Number of speakers TEST**************");
		LOG.info("Speaker count: "+speakerRepository.count());
		LOG.info("**************************************************");
	}

    //	HIBERNATE VERSION
@Test
	public void testFindAll() {
	LOG.info("************ ALL SPEAKERS**************");
		Iterable<Speaker> allSpeakers = speakerRepository.getAllSpeakers();
		for(Speaker speaker: allSpeakers)
		{
			LOG.info(speaker.getName());
	}
	}
	//  SPRING DATA VERSION
//	@Test
//	public void testFindAll() {
//		LOG.info("************ ALL SPEAKERS**************");
//		Iterable<Speaker> allSpeakers = speakerRepository.findAll();
//		for(Speaker speaker: allSpeakers)
//		{
//			LOG.info(speaker.getName());
//		}
//	}

	@Test
	public void testFindByName() {
		LOG.info("************ ALL TALKS OF BORISOV**************");
		Speaker speaker = speakerRepository.findByName("Mr Evgeny Borisov").get(0);
		Set<Talk> talks = speaker.getTalks();

		for(Talk talk: talks)
		{
			LOG.info("talk = "+talk);
		}
		LOG.info("**************************************************");
	}




	@Test
	public void contextLoads() {
	}

}
