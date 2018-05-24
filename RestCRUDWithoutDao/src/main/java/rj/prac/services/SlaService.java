package rj.prac.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import rj.prac.model.Sla;

@Service
public class SlaService {

	static HashMap<String,ArrayList<Sla>> slaDefList = getSlaDefList();
	
	public  SlaService() {
		if(slaDefList == null) {
			
			slaDefList = new HashMap<String,ArrayList<Sla>>();
			
			Sla sla88_1 = new Sla("88","24","15","30");
			Sla sla88_2 = new Sla("88","24","15","30");
			Sla sla88_3 = new Sla("88","24","15","30");
			
			Sla sla87_1 = new Sla("87","24","15","30");
			Sla sla87_2 = new Sla("87","24","15","30");
			Sla sla87_3 = new Sla("87","24","15","30");
			
			Sla sla89_1 = new Sla("89","24","15","30");
			
			ArrayList<Sla> sla88 = new ArrayList<Sla>();
			sla88.add(sla88_1);
			sla88.add(sla88_2);
			sla88.add(sla88_3);
			
			ArrayList<Sla> sla89 = new ArrayList<Sla>();
			sla89.add(sla89_1);
			
			ArrayList<Sla> sla87 = new ArrayList<Sla>();
			sla87.add(sla87_1);
			sla87.add(sla87_2);
			sla87.add(sla87_3);
			
			slaDefList.put("88",sla88 );
			slaDefList.put("89",sla89);
			slaDefList.put("87",sla87);
			
		}
	
	}

	public static HashMap<String, ArrayList<Sla>> getSlaDefList() {
		return slaDefList;
	}
}
