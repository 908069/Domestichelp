package com.cg.aps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cg.aps.entity.DomesticHelp;
import com.cg.aps.exception.DatabaseException;
import com.cg.aps.exception.DuplicateRecordException;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.service.DomesticHelpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/api/owner")
public class OwnerController {

	@Autowired
	private DomesticHelpService domesticHelpService;
	
	
	/**
	 * @author Harsh
	 * @param helpId - getDomesticHelpById
	 * @return Help
	 */
	@ApiOperation(value = "get domestic help by Id",
			response = DomesticHelp.class,
			tags = "get-help",
			consumes = "helpId",
			httpMethod = "GET")
	@GetMapping("/domesticHelp/{helpId}")
	public ResponseEntity<DomesticHelp> getDomesticHelpById(@PathVariable Integer helpId){
		try {
			DomesticHelp domesticHelp = domesticHelpService.findByPk(helpId);
			return new ResponseEntity<>(domesticHelp,HttpStatus.OK);
		}catch(RecordNotFoundException e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	/**
	 * @author Harsh
	 * @return all helps
	 */
	@ApiOperation(value = "get all domestic help",
			response = DomesticHelp.class,
			tags = "get-all-help",
			httpMethod = "GET")
	@GetMapping("/domesticHelp")
	public ResponseEntity<List<DomesticHelp>> getAllHelps(){
		try {
			List<DomesticHelp> helpList = domesticHelpService.search();
			return new ResponseEntity<>(helpList,HttpStatus.OK);
		}catch(DatabaseException e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	/**
	 * @author Harsh
	 * @param pageNo - getHelps
	 * @param pageSize - getHelps
	 * @return helps of the pageNo with pageSize
	 */
	@ApiOperation(value = "get domestic help by page no and page size",
			response = DomesticHelp.class,
			tags = "get-help",
			consumes = "page no and page size",
			httpMethod = "GET")
	@GetMapping("/domesticHelp/{pageNo}/{pageSize}")
	public ResponseEntity<List<DomesticHelp>> getHelps(@PathVariable Integer pageNo,@PathVariable Integer pageSize){
		try {
			List<DomesticHelp> helpList = domesticHelpService.search(pageNo, pageSize);
			return new ResponseEntity<>(helpList,HttpStatus.OK);
		}catch(DatabaseException e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	/**
	 * @author Harsh
	 * @param domesticHelp - addHelp
	 * @return adds domesticHelp
	 */
	@ApiOperation(value = "add domestic help",
			response = Integer.class,
			tags = "add-help",
			consumes = "domestic help",
			httpMethod = "POST")
	@PostMapping("/domesticHelp")
	public ResponseEntity<Integer> addHelp(@RequestBody DomesticHelp domesticHelp) {
		try {
			Integer helpId = domesticHelpService.addDomesticHelp(domesticHelp);
			return new ResponseEntity<>(helpId,HttpStatus.OK);
		}catch(DuplicateRecordException e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	/**
	 * @author Harsh
	 * @param domesticHelp - updateHelp
	 * @return updating DomesticHelp
	 */
	@ApiOperation(value = "update domestic help",
			response = String.class,
			tags = "update-help",
			consumes = "domestic help",
			httpMethod = "PUT")
	@PutMapping("/domesticHelp")
	public ResponseEntity<String> updateHelp(@RequestBody DomesticHelp domesticHelp) {
		try {
			domesticHelpService.updateDomesticHelp(domesticHelp);
			return new ResponseEntity<>("updated successfully",HttpStatus.OK);
		}catch(RecordNotFoundException e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	/**
	 * @author Harsh
	 * @param domesticHelp - deleteHelp
	 * @return deleting DomesticHelp
	 */
	@ApiOperation(value = "delete domestic help",
			response = String.class,
			tags = "delete-help",
			consumes = "domestic help",
			httpMethod = "DELETE")
	@DeleteMapping("/domesticHelp")
	public ResponseEntity<String> deleteHelp(@RequestBody DomesticHelp domesticHelp) {
		try {
			domesticHelpService.deleteDomesticHelp(domesticHelp);
			return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
		}catch(RecordNotFoundException e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	
	
		
	}
