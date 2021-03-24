package com.cg.aps.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.aps.dao.DomesticHelpDAO;
import com.cg.aps.entity.DomesticHelp;
import com.cg.aps.exception.DatabaseException;
import com.cg.aps.exception.DuplicateRecordException;
import com.cg.aps.exception.RecordNotFoundException;
/**
 * 
 * @author Harsh
 * implementation class of domestic help service interface
 *
 */
@Service
@Transactional
public class DomesticHelpServiceImpl implements DomesticHelpService{

	@Autowired
	private DomesticHelpDAO domesticHelpDao;
	
	@Override
	public Integer addDomesticHelp(DomesticHelp domesticHelp) throws DuplicateRecordException {
		try {	
			domesticHelpDao.save(domesticHelp);
			return domesticHelp.getHelpId();
		}catch(DataAccessException e) {
			throw new DuplicateRecordException(e.getMessage());
		}catch(Exception e) {
			throw new DuplicateRecordException(e.getMessage());
		}
	}

	@Override
	public void updateDomesticHelp(DomesticHelp domesticHelp) throws RecordNotFoundException {
		try {			
			domesticHelpDao.save(domesticHelp);
		}catch(DataAccessException e) {
			throw new RecordNotFoundException(e.getMessage());
		}catch(Exception e) {
			throw new RecordNotFoundException(e.getMessage());
		}
		
	}

	@Override
	public void deleteDomesticHelp(DomesticHelp domesticHelp) throws RecordNotFoundException {
		try {			
			domesticHelpDao.delete(domesticHelp);
		}catch(DataAccessException e) {
			throw new RecordNotFoundException(e.getMessage());
		}catch(Exception e) {
			throw new RecordNotFoundException(e.getMessage());
		}
		
		
	}

	@Override
	public DomesticHelp findByPk(Integer id) throws RecordNotFoundException {
		try {			
			Optional<DomesticHelp> optional = domesticHelpDao.findById(id);
			if(optional.isPresent()) {
				return optional.get();
			}else {
				throw new Exception("Invalid id");
			}
		}catch(DataAccessException e) {
			throw new RecordNotFoundException(e.getMessage());
		}catch(Exception e) {
			throw new RecordNotFoundException(e.getMessage());
		}
	}

	@Override
	public List<DomesticHelp> search(Integer pageNo, Integer pageSize) throws DatabaseException {
		try {	
			PageRequest paging = PageRequest.of(pageNo, pageSize);
			Page<DomesticHelp> pagedResult = domesticHelpDao.findAll(paging);
			if(pagedResult.hasContent()) {
				return pagedResult.getContent();
			}else {
				throw new Exception("Invalid pageNo and pageSize");
			}
		}catch(DataAccessException e) {
			throw new DatabaseException(e.getMessage());
		}catch(Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	@Override
	public List<DomesticHelp> search() throws DatabaseException {
		try {			
			return domesticHelpDao.findAll();
		}catch(DataAccessException e) {
			throw new DatabaseException(e.getMessage());
		}catch(Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	@Override
	public List<DomesticHelp> findByPersonName(String name) throws RecordNotFoundException {
		try {			
			return domesticHelpDao.findByPersonName(name);
		}catch(DataAccessException e) {
			throw new RecordNotFoundException(e.getMessage());
		}catch(Exception e) {
			throw new RecordNotFoundException(e.getMessage());
		}
	}

}
