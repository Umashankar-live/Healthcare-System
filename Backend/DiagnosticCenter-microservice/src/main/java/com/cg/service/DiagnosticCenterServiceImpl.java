package com.cg.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.bean.DiagnosticCenters;
import com.cg.dao.DiagnosticCenterDao;
import com.cg.exception.NoValueFoundException;
import com.cg.exception.NotPossibleException;

@Service
public class DiagnosticCenterServiceImpl implements DiagnosticCenterService {

	@Autowired
	private DiagnosticCenterDao dao;
	
	private static final Logger logger = LoggerFactory.getLogger(DiagnosticCenterServiceImpl.class);
	/***
	 * This Function is used to add center
	 */
	@Override
	public DiagnosticCenters addCenter(DiagnosticCenters center) {
		if(center==null || center.getCenterName()==null|| center.getListOfTests()==null) {
			logger.warn("check weather data to data to input is correct or not");
			throw new NotPossibleException("Cannot Add this center Either one of the field is empty");
		}
		return this.dao.save(center);
	}

	/***
	 * THis function is used to delete center 
	 */
	@Override
	public void deleteCenter(Integer centerId) {
		try {
			this.dao.deleteById(centerId);
		} catch (Exception e) {
			logger.warn("Check the centerId is correct or not");
			throw new NotPossibleException("Cannot Delete this center CenterId Doesnot Exists");
		}
	}

	/***
	 * This Function is used to search the center
	 */
	@Override
	public DiagnosticCenters searchCenter(Integer centerId) {
		if(this.dao.findAll().stream().filter(x -> centerId.equals(x.getCenterId())).findAny().orElse(null)==null) {
			logger.warn("Check whether the centerId is correct or not");
			throw new NoValueFoundException("Center not Found with this ID");
		}
		return this.dao.findAll().stream().filter(x -> centerId.equals(x.getCenterId())).findAny().orElse(null);		
		
	}

	/***
	 * This function is used to get all Centers 
	 */
	@Override
	public List<DiagnosticCenters> getAllCenter() {
		 if(this.dao.findAll()==null) {
			 logger.warn("Check if database is empty or not");
			 throw new NoValueFoundException("There is no Center in Table");
		 } 
		 return this.dao.findAll();
	}

	/***
	 * This Function is used to update the center
	 */
	@Override
	public DiagnosticCenters updateCenter(DiagnosticCenters center) {
		if(this.dao.save(center)==null)
			throw new NotPossibleException("Cannot upadte this center");
		return this.dao.save(center);
	}

	/***
	 * This function is used to count no of centers in the database
	 */
	@Override
	public long countCenters() {
		return dao.count();
	}

}
