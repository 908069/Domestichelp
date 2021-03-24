package com.cg.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.cg.aps.application.ApartmentSecurityAppApplication;
import com.cg.aps.entity.DomesticHelp;
import com.cg.aps.exception.DuplicateRecordException;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.service.DomesticHelpService;

@SpringBootTest(classes = ApartmentSecurityAppApplication.class)
@Transactional
@Rollback(true)
public class DomesticHelpTest {

	@Autowired
	private DomesticHelpService helpService;
	
	public DomesticHelp addHelp() throws DuplicateRecordException, RecordNotFoundException {
		DomesticHelp help = new DomesticHelp();
		help.setHelpType("Electric");
		help.setHelpDate(LocalDate.now());
		help.setPersonName("rocky");
		help.setArrivalTime("10:30");
		help.setDepartureTime(null);
		Integer id = helpService.addDomesticHelp(help);
		return helpService.findByPk(id);
	} 
	
	/*
	 * check add help
	 */
	@Test
	public void testAddHelp() throws DuplicateRecordException, RecordNotFoundException {
		DomesticHelp help = addHelp();
		assertEquals("rocky", help.getPersonName());
		assertEquals("Electric",help.getHelpType());
		//assertEquals(null,help.getDepartureTime());
		assertNull(help.getDepartureTime());
		assertEquals("10:30",help.getArrivalTime());
	}
	
	/*
	 * check edit help
	 */
	@Test
	public void testEditHelp() throws DuplicateRecordException, RecordNotFoundException {
		DomesticHelp help = addHelp();
		help.setPersonName("Star");
		help.setHelpType("plumbing");
		help.setDepartureTime("11:00");
		helpService.updateDomesticHelp(help);
		assertEquals("Star",helpService.findByPk(help.getHelpId()).getPersonName());
		assertEquals("plumbing",helpService.findByPk(help.getHelpId()).getHelpType());
		assertEquals("11:00",helpService.findByPk(help.getHelpId()).getDepartureTime());
	}
	
	/*
	 * check delete help
	 */
	@Test
	public void testDeleteHelp() throws DuplicateRecordException, RecordNotFoundException {
		DomesticHelp help = addHelp();
		helpService.deleteDomesticHelp(help);
		assertThrows(RecordNotFoundException.class, ()->{
			helpService.findByPk(help.getHelpId());
			});
	}
}
